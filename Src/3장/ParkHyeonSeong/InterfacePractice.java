package javaStudy_chapter3;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.Comparator;
import java.util.HashMap;

public class InterfacePractice {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("<Predicate, Consumer, Function 사용>");
		List<String> stringList1 = new ArrayList<>();
		stringList1.add("");
		stringList1.add("hi");
		stringList1.add("hello");
		
		// 형식 추론
		Predicate<String> nonEmptyStringPredicate = s -> !s.isEmpty();
		
		List<String> nonEmpty = filter(stringList1,nonEmptyStringPredicate);
		forEach(nonEmpty,s -> System.out.println(s));
		
		List<Integer> stringLengthList = map(nonEmpty, s -> s.length());
		forEach(stringLengthList, i -> System.out.println(i));
		
		// 지역 변수 사용, 람다 캡처링은 final이거나 실질적으로 final로 선언된 변수와 똑같이 사용되어야 함.
		final String stringForLamdaCapturing = "문자의 길이: "; 
		forEach(stringLengthList, i -> System.out.println(stringForLamdaCapturing + i));
		
		
		System.out.println("<메서드 참조>");
		// 메서드 참조
		// 1. 정적 메서드 참조
		// 정적 메서드: 클래스가 메모리에 올라갈 때 정적 메소드가 자동적으로 생성된다.
		// 그렇기에 인스턴스를 생성하지 않고, 클래스만으로 메소드를 호출할 수 있어, 이점으로 연결된다.
		System.out.println("1. 정적 메서드 참조");
		List<String> stringList2 = new ArrayList<>();
		stringList2.add("123");
		stringList2.add("456");
		stringList2.add("789");
		List<Integer> intList = map(stringList2, Integer::parseInt); // 1. 정적 메서드 참조
		forEach(intList, i -> System.out.println(i));
		
		// 2. 다양한 형식의 인스턴스 메서드 참조
		System.out.println();
		System.out.println("2. 다양한 형식의 인스턴스 메서드 참조");
		List<Integer> intList2 = map(stringList2, String::length); // 2. 다양한 형식의 인스턴스 메서드 참조
		forEach(intList2, i -> System.out.println(i));
		
		// 3. 기존 객체의 인스턴스 메서드 참조 +  생성자 참조
		System.out.println();
		System.out.println("3. 기존 객체의 인스턴스 메서드 참조 + 생성자 참조");
		List<Apple> appleList = new ArrayList<>();
		TriFunction<Apple,Integer,String,String> tf = Apple::new; // 생성자 참조
		// TriFunction의 apply 메서드에 세가지 인수로 호출해서 Apple 객체 생성
		appleList.add(tf.apply(100, "red", "Korea")); 
		appleList.add(tf.apply(120, "green", "England"));
		appleList.add(tf.apply(90, "yellow", "Brazil"));
		
		List<Integer> weightList = map(appleList, Apple::getWeight); // 기존 객체의 인스턴스 메서드 참조
		forEach(weightList, i -> System.out.println(i));
		
	
	}


	private boolean isValidName(String string) {
		return Character.isUpperCase(string.charAt(0));
	}
	private static <T> List<T> filter(List<T> list, Predicate<T> p) {
		List<T> ret = new ArrayList<>();
		for(T t: list) {
			if(p.test(t)) {
				ret.add(t);
			}
		}
		return ret;
	}
	
	private static <T> void forEach(List<T> list, Consumer<T> c) {
		for(T t: list) {
			c.accept(t);
		}
	}
	
	private static <T,R> List<R> map(List<T> list, Function<T,R> f) {
		List<R> ret = new ArrayList<>();
		for(T t: list) {
			ret.add(f.apply(t));
		}
		return ret;
	}

}
