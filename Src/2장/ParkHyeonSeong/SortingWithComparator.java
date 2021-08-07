package javaStudy_chapter2;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

public class SortingWithComparator {
	public static void main(String[] args) {
		List<Apple> appleList = new ArrayList<>();
		appleList.add(new Apple(40, "red"));
		appleList.add(new Apple(80, "green"));
		appleList.add(new Apple(20, "red"));
		appleList.add(new Apple(90, "green"));
		appleList.add(new Apple(160, "red"));
		
		List<Apple> appleList2 = new ArrayList<>();
		appleList2.add(new Apple(40, "red"));
		appleList2.add(new Apple(80, "green"));
		appleList2.add(new Apple(20, "red"));
		appleList2.add(new Apple(90, "green"));
		appleList2.add(new Apple(160, "red"));
		
		appleList.sort(new Comparator<Apple>() {
			public int compare(Apple a1, Apple a2) {
				return a1.getWeight().compareTo(a2.getWeight());
			}
		});
		
		appleList2.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));
		
		for(Apple a:appleList) {
			System.out.println(a);
		}
		System.out.println();
		for(Apple a2:appleList2) {
			System.out.println(a2);
		}
	}
}
