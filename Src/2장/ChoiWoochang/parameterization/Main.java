package chapter02.parameterization;

import java.util.ArrayList;
import java.util.List;

enum Color { RED, GREEN }

public class Main {
	
	public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p) {
		List<Apple> result = new ArrayList<>();
		for(Apple apple : inventory) {
			if(p.test(apple)) {
				result.add(apple);
			}
		}
		return result;
	}
	
	public static void print(List<Apple> inventory) {
		for(Apple apple : inventory) {
			System.out.println(apple.getWeight());
			System.out.println(apple.getColor());
		}
	}
	
	public static void main(String[] args) {
		List<Apple> inventory = new ArrayList<>();
		Apple apple1 = new Apple();
		Apple apple2 = new Apple();
		Apple apple3 = new Apple();
		Apple apple4 = new Apple();
		apple1.setColor(Color.RED);
		apple1.setWeight(170);
		apple2.setColor(Color.GREEN);
		apple2.setWeight(148);
		apple3.setColor(Color.GREEN);
		apple3.setWeight(151);
		apple4.setColor(Color.RED);
		apple4.setWeight(130);
		inventory.add(apple1);
		inventory.add(apple2);
		inventory.add(apple3);
		inventory.add(apple4);
		List<Apple> heavyResult = filterApples(inventory, new AppleHeavyWeightPredicate());
		print(heavyResult);
		System.out.println("---------------------");
		List<Apple> greenResult = filterApples(inventory, new AppleGreenColorPredicate());
		print(greenResult);
	}
}

class Apple {
	private Color color;
	private int weight;
	
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
}
