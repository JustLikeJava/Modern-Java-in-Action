package javaStudy_chapter7;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class WordCount {

  public static final String SENTENCE =
      " Nel   mezzo del cammin  di nostra  vita "
      + "mi  ritrovai in una  selva oscura"
      + " che la  dritta via era   smarrita ";

  public static void main(String[] args) {
    System.out.println("Found " + countWordsIteratively(SENTENCE) + " words");
    System.out.println("Found " + countWords(SENTENCE) + " words");
  }

  // 전통적인 반복문(for문) 사용
  public static int countWordsIteratively(String s) {
    int counter = 0;
    boolean lastSpace = true;
    for (char c : s.toCharArray()) {
      if (Character.isWhitespace(c)) {
        lastSpace = true;
      }
      else {
        if (lastSpace) {
          counter++;
        }
        lastSpace = Character.isWhitespace(c);
      }
    }
    return counter;
  }

  public static int countWords(String s) {
    //Stream<Character> stream = IntStream.range(0, s.length())
    //    .mapToObj(SENTENCE::charAt).parallel();
    Spliterator<Character> spliterator = new WordCounterSpliterator(s);
    Stream<Character> stream = StreamSupport.stream(spliterator, true);

    return countWords(stream);
  }

  private static int countWords(Stream<Character> stream) {
    WordCounter wordCounter = stream.reduce(new WordCounter(0, true), WordCounter::accumulate, WordCounter::combine);
    return wordCounter.getCounter();
  }

  private static class WordCounter {

    private final int counter;
    private final boolean lastSpace;

    public WordCounter(int counter, boolean lastSpace) {
      this.counter = counter;
      this.lastSpace = lastSpace;
    }

    // 반복 알고리즘처럼 accumulate 메서드는 문자열의 문자를 하나씩 탐색한다.
    public WordCounter accumulate(Character c) {
      if (Character.isWhitespace(c)) {
        return lastSpace ? this : new WordCounter(counter, true);
      }
      else {
    	  // 문자를 하나씩 탐색하다 공백 문자를 만나면 지금까지 탐색한문자를 단어로 간주하여(공백문자는 제외)
    	  // 단어 수를 증가시킨다.
    	  return lastSpace ? new WordCounter(counter + 1, false) : this;
      }
    }

    
    public WordCounter combine(WordCounter wordCounter) {
      return new WordCounter(counter + wordCounter.counter // 두 WordCounter의 counter값을 더한다.
    		  , wordCounter.lastSpace); // counter 값만 더할 것이므로 마지막 공백은 신경 쓰지 않는다.
    }

    public int getCounter() {
      return counter;
    }

  }

  // WordCounter 병렬로 수행하기
  // 스트림에 parallel만 붙여 수행하다보면 원래 문자열을 임의의 위치에서 둘로 나누다보니 예상치 못하게
  // 하나의 단어를 둘로 계산하는 상황이 발생할 수 있다.
  // 이 문제는 문자열을 임의의 위치에서 분할하지 말고 "단어가 끝나는 위치"에서만 분할하는 방법으로 이 문제를 해결할 수 있다.
  // 그러려면  단어 끝에서 문장을 분할하는 문자 Spliterator가 필요하다.
  private static class WordCounterSpliterator implements Spliterator<Character> {

    private final String string;
    private int currentChar = 0;

    private WordCounterSpliterator(String string) {
      this.string = string;
    }

    @Override
    public boolean tryAdvance(Consumer<? super Character> action) {
      action.accept(string.charAt(currentChar++)); // 현재 문자를소비
      return currentChar < string.length(); // 소비할 문자가 남아있으면 true를 반환
    }

    @Override
    public Spliterator<Character> trySplit() {
      int currentSize = string.length() - currentChar;
      if (currentSize < 10) { // 파싱할 문자열을 순차 처리할 수 있을 만큼 충분히 작아졌음을 알리는 null을 반환.
        return null;
      }
      
      // 파싱할 문자열의 중간을 분할 위치로 설정한다.
      for (int splitPos = currentSize / 2 + currentChar; splitPos < string.length(); splitPos++) {
    	  
    	  // 다음 공백이 나올때까지 분할 위치를 뒤로 이동시킴/
    	  if (Character.isWhitespace(string.charAt(splitPos))) {
    		  // 처음부터 분할위치까지 문자열을 파싱할 새로운 WordCounterSpliterator를 생성한다.
    		  Spliterator<Character> spliterator = new WordCounterSpliterator(string.substring(currentChar, splitPos));
    		  
    		  currentChar = splitPos; // 이 WordCounterSpliterator의 시작 위치를 분할 위치로 설정한다.
    		  return spliterator; // 공백을 찾았고 문자열을 분리했으므로 루프를 종료한다.
    	  }
      	}
      	return null;
    	}

    @Override
    public long estimateSize() {
      return string.length() - currentChar;
    }

    @Override
    public int characteristics() {
      return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
    }

  }

}

