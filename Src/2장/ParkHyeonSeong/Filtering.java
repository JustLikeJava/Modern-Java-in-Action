package javaStudy_chapter2;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Filtering {
	
	public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p){
		List<Apple> result = new ArrayList<>();
		for(Apple apple:inventory) {
			if(p.test(apple)) {
				result.add(apple);
			}
		}
		return result;
	}
	
	
	public static <T> List<T> filter(List<T> list, Predicate<T> p){
		List<T> result = new ArrayList<>();
		for(T e: list){
			if(p.test(e)){
				result.add(e);
			}
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		List<Apple> appleList = new ArrayList<>();
		appleList.add(new Apple(40, "red"));
		appleList.add(new Apple(80, "green"));
		appleList.add(new Apple(20, "red"));
		appleList.add(new Apple(90, "green"));
		appleList.add(new Apple(160, "red"));
		
		// ApplePredicate 인터페이스를 구현하는 클래스를 정의해 인스턴스화
		List<Apple> redApples1 = filterApples(appleList, new AppleRedColorPredicate());
		System.out.println("< 따로 정의한 ApplePredicate 인터페이스를 구현하는 클래스 이용 >");
		
		for(Apple a:redApples1) {
			System.out.println(a);
		}
		System.out.println();
		
		// 익명 클래스 사용
		List<Apple> redApples2 = filterApples(appleList, new ApplePredicate() {
			public boolean test(Apple apple) {
				return "red".equals(apple.getColor());
			}
		});
		
		System.out.println("< 익명 클래스 >");
		for(Apple a:redApples2) {
			System.out.println(a);
		}
		System.out.println();
		
		// 람다 표현식 사용
		List<Apple> redApples3 = filterApples(appleList, 
				(Apple apple) -> "red".equals(apple.getColor()));
		
		System.out.println("< 람다 표현식 >");
		for(Apple a:redApples3) {
			System.out.println(a);
		}
		System.out.println();
		
		// 리스트 형식으로 추상화
		List<Apple> redApples4 = filter(appleList, 
				(Apple apple) -> "red".equals(apple.getColor()));
		
		System.out.println("< 리스트 형식으로 추상화 >");
		for(Apple a:redApples4) {
			System.out.println(a);
		}
		System.out.println();
		
		

	}
	
	
	
	
}
