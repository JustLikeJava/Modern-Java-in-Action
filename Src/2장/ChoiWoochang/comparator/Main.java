package chapter02.comparator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		List<Apple> list = new ArrayList<>();
		Apple apple1 = new Apple();
		Apple apple2 = new Apple();
		Apple apple3 = new Apple();
		apple1.setWeight(50);
		apple2.setWeight(30);
		apple3.setWeight(40);
		list.add(apple1);
		list.add(apple2);
		list.add(apple3);
		
		list.sort(new Comparator<Apple>() {
			public int compare(Apple a1, Apple a2) {
				return a1.getWeight().compareTo(a2.getWeight());
			}
		});
		
		for(Apple a : list) {
			System.out.println(a.getWeight());
		}
		
		list.sort((Apple a1, Apple a2) -> a2.getWeight().compareTo(a1.getWeight()));
		
		for(Apple a : list) {
			System.out.println(a.getWeight());
		}
	}
}

class Apple {
	private int weight;

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
}
