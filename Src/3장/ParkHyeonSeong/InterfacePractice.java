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
		System.out.println("<Predicate, Consumer, Function ���>");
		List<String> stringList1 = new ArrayList<>();
		stringList1.add("");
		stringList1.add("hi");
		stringList1.add("hello");
		
		// ���� �߷�
		Predicate<String> nonEmptyStringPredicate = s -> !s.isEmpty();
		
		List<String> nonEmpty = filter(stringList1,nonEmptyStringPredicate);
		forEach(nonEmpty,s -> System.out.println(s));
		
		List<Integer> stringLengthList = map(nonEmpty, s -> s.length());
		forEach(stringLengthList, i -> System.out.println(i));
		
		// ���� ���� ���, ���� ĸó���� final�̰ų� ���������� final�� ����� ������ �Ȱ��� ���Ǿ�� ��.
		final String stringForLamdaCapturing = "������ ����: "; 
		forEach(stringLengthList, i -> System.out.println(stringForLamdaCapturing + i));
		
		
		System.out.println("<�޼��� ����>");
		// �޼��� ����
		// 1. ���� �޼��� ����
		// ���� �޼���: Ŭ������ �޸𸮿� �ö� �� ���� �޼ҵ尡 �ڵ������� �����ȴ�.
		// �׷��⿡ �ν��Ͻ��� �������� �ʰ�, Ŭ���������� �޼ҵ带 ȣ���� �� �־�, �������� ����ȴ�.
		System.out.println("1. ���� �޼��� ����");
		List<String> stringList2 = new ArrayList<>();
		stringList2.add("123");
		stringList2.add("456");
		stringList2.add("789");
		List<Integer> intList = map(stringList2, Integer::parseInt); // 1. ���� �޼��� ����
		forEach(intList, i -> System.out.println(i));
		
		// 2. �پ��� ������ �ν��Ͻ� �޼��� ����
		System.out.println();
		System.out.println("2. �پ��� ������ �ν��Ͻ� �޼��� ����");
		List<Integer> intList2 = map(stringList2, String::length); // 2. �پ��� ������ �ν��Ͻ� �޼��� ����
		forEach(intList2, i -> System.out.println(i));
		
		// 3. ���� ��ü�� �ν��Ͻ� �޼��� ���� +  ������ ����
		System.out.println();
		System.out.println("3. ���� ��ü�� �ν��Ͻ� �޼��� ���� + ������ ����");
		List<Apple> appleList = new ArrayList<>();
		TriFunction<Apple,Integer,String,String> tf = Apple::new; // ������ ����
		// TriFunction�� apply �޼��忡 ������ �μ��� ȣ���ؼ� Apple ��ü ����
		appleList.add(tf.apply(100, "red", "Korea")); 
		appleList.add(tf.apply(120, "green", "England"));
		appleList.add(tf.apply(90, "yellow", "Brazil"));
		
		List<Integer> weightList = map(appleList, Apple::getWeight); // ���� ��ü�� �ν��Ͻ� �޼��� ����
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
