package javaStudy_chapter5;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;


public class StreamQuiz {
	public static void main(String[] args) {
		
		Trader raoul = new Trader("Raoul", "Cambridge");
	    Trader mario = new Trader("Mario", "Milan");
	    Trader alan = new Trader("Alan", "Cambridge");
	    Trader brian = new Trader("Brian", "Cambridge");

	    List<Transaction> transactions = Arrays.asList(
	        new Transaction(brian, 2011, 300),
	        new Transaction(raoul, 2012, 1000),
	        new Transaction(raoul, 2011, 400),
	        new Transaction(mario, 2012, 710),
	        new Transaction(mario, 2012, 700),
	        new Transaction(alan, 2012, 950)
	    );
	    // 1.
	    transactions.stream()
	    .filter(t -> t.getYear() == 2011)
	    .sorted(comparing(Transaction::getValue))
	    .forEach(t -> System.out.println(t));
	    
	    // 2.
	    List<String> cities = transactions.stream()
	    .map(t -> t.getTrader().getCity())
	    .distinct()
	    .collect(toList());
	    cities.stream().forEach(c -> System.out.println(c));
	    
	    // 3.
	    transactions.stream()
	    .map(Transaction::getTrader)
	    .filter(t -> t.getCity().contentEquals("Cambridge"))
	    .distinct()
	    .sorted(comparing(Trader::getName))
	    .forEach(tr -> System.out.println(tr));
	    
	    // 4.
	    transactions.stream()
	    .map(t -> t.getTrader().getName())
	    .distinct()
	    .sorted()
	    .forEach(s -> System.out.println(s));
	    
	    // 5.
	    boolean milanBased = transactions.stream()
	    .anyMatch(t -> t.getTrader().getCity().equals("Milan"));
	    
	    // 6.
	    transactions.stream()
	    .filter(t -> t.getTrader().getCity().contentEquals("Cambridge"))
	    .forEach(t -> System.out.println(t.getValue()));
	    
	    // 7.
	    int mx = transactions.stream()
	    .map(Transaction::getValue)
	    .reduce(0,Integer::max);
	    System.out.println(mx);
	    
	    
	    // 숫자 스트림
	    int values = transactions.stream()
	    		.mapToInt(Transaction::getValue)
	    		.sum();
	    System.out.println(values);
	    
	    IntStream is = transactions.stream()
	    		.mapToInt(Transaction::getValue);
	    //Stream<Integer> si = is.boxed();
	    OptionalInt mxv = is.max();
	    System.out.println(mxv.getAsInt());
	    
	    
	    //피타고라스 수
	    Stream<int[]> pythagoreanTriples = 
	    		IntStream.rangeClosed(1,100).boxed()
	    		.flatMap(a -> IntStream.rangeClosed(a,100)
	    				.filter(b -> Math.sqrt(a*a + b*b) % 1 == 0)
	    				.mapToObj(b -> new int[] {a,b,(int)Math.sqrt(a*a + b*b)})
	    				);
	    
	    pythagoreanTriples.limit(5)
	    .forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));
	}
}
