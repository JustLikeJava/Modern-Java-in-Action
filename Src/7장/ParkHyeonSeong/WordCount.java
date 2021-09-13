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

  // �������� �ݺ���(for��) ���
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

    // �ݺ� �˰���ó�� accumulate �޼���� ���ڿ��� ���ڸ� �ϳ��� Ž���Ѵ�.
    public WordCounter accumulate(Character c) {
      if (Character.isWhitespace(c)) {
        return lastSpace ? this : new WordCounter(counter, true);
      }
      else {
    	  // ���ڸ� �ϳ��� Ž���ϴ� ���� ���ڸ� ������ ���ݱ��� Ž���ѹ��ڸ� �ܾ�� �����Ͽ�(���鹮�ڴ� ����)
    	  // �ܾ� ���� ������Ų��.
    	  return lastSpace ? new WordCounter(counter + 1, false) : this;
      }
    }

    
    public WordCounter combine(WordCounter wordCounter) {
      return new WordCounter(counter + wordCounter.counter // �� WordCounter�� counter���� ���Ѵ�.
    		  , wordCounter.lastSpace); // counter ���� ���� ���̹Ƿ� ������ ������ �Ű� ���� �ʴ´�.
    }

    public int getCounter() {
      return counter;
    }

  }

  // WordCounter ���ķ� �����ϱ�
  // ��Ʈ���� parallel�� �ٿ� �����ϴٺ��� ���� ���ڿ��� ������ ��ġ���� �ѷ� �����ٺ��� ����ġ ���ϰ�
  // �ϳ��� �ܾ �ѷ� ����ϴ� ��Ȳ�� �߻��� �� �ִ�.
  // �� ������ ���ڿ��� ������ ��ġ���� �������� ���� "�ܾ ������ ��ġ"������ �����ϴ� ������� �� ������ �ذ��� �� �ִ�.
  // �׷�����  �ܾ� ������ ������ �����ϴ� ���� Spliterator�� �ʿ��ϴ�.
  private static class WordCounterSpliterator implements Spliterator<Character> {

    private final String string;
    private int currentChar = 0;

    private WordCounterSpliterator(String string) {
      this.string = string;
    }

    @Override
    public boolean tryAdvance(Consumer<? super Character> action) {
      action.accept(string.charAt(currentChar++)); // ���� ���ڸ��Һ�
      return currentChar < string.length(); // �Һ��� ���ڰ� ���������� true�� ��ȯ
    }

    @Override
    public Spliterator<Character> trySplit() {
      int currentSize = string.length() - currentChar;
      if (currentSize < 10) { // �Ľ��� ���ڿ��� ���� ó���� �� ���� ��ŭ ����� �۾������� �˸��� null�� ��ȯ.
        return null;
      }
      
      // �Ľ��� ���ڿ��� �߰��� ���� ��ġ�� �����Ѵ�.
      for (int splitPos = currentSize / 2 + currentChar; splitPos < string.length(); splitPos++) {
    	  
    	  // ���� ������ ���ö����� ���� ��ġ�� �ڷ� �̵���Ŵ/
    	  if (Character.isWhitespace(string.charAt(splitPos))) {
    		  // ó������ ������ġ���� ���ڿ��� �Ľ��� ���ο� WordCounterSpliterator�� �����Ѵ�.
    		  Spliterator<Character> spliterator = new WordCounterSpliterator(string.substring(currentChar, splitPos));
    		  
    		  currentChar = splitPos; // �� WordCounterSpliterator�� ���� ��ġ�� ���� ��ġ�� �����Ѵ�.
    		  return spliterator; // ������ ã�Ұ� ���ڿ��� �и������Ƿ� ������ �����Ѵ�.
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

