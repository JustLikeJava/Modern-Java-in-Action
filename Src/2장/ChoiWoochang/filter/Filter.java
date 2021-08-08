package chapter02.filter;

import java.util.ArrayList;
import java.util.List;

enum Color { RED, GREEN }

public class Filter {

	public static List<Apple> filterGreenApples(List<Apple> inventory) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if (Color.GREEN.equals(apple.getColor())) {
				result.add(apple);
			}
		}
		return result;
	}
	
	public static List<Apple> filterApplesByColor(List<Apple> inventory, Color color) {
		List<Apple> result = new ArrayList<>();
		for(Apple apple : inventory) {
			if (apple.getColor().equals(color)) {
				result.add(apple);
			}
		}
		return result;
	}
	
	public static void print(List<Apple> inventory) {
		for(Apple apple : inventory) {
			System.out.println(apple.getColor());
		}
	}
	
	public static void main(String[] args) {
		List<Apple> inventory = new ArrayList<>();
		Apple apple1 = new Apple(Color.GREEN);
		Apple apple2 = new Apple(Color.RED);
		Apple apple3 = new Apple(Color.GREEN);
		Apple apple4 = new Apple(Color.RED);
		Apple apple5 = new Apple(Color.GREEN);
		inventory.add(apple1);
		inventory.add(apple2);
		inventory.add(apple3);
		inventory.add(apple4);
		inventory.add(apple5);
//		List<Apple> result = filterGreenApples(inventory);
//		for(Apple apple : result) {
//			System.out.println(apple.getColor());
//		} 단순히 특정 색상을 얻기 위한 코드
		List<Apple> redResult = filterApplesByColor(inventory, Color.RED);
		print(redResult);
		List<Apple> greenResult = filterApplesByColor(inventory, Color.GREEN);
		print(greenResult);
	}
	
	static class Apple {
		private Color color;
		
		public Apple(Color color) {
			this.color = color;
		}

		public Color getColor() {
			return color;
		}

		public void setColor(Color color) {
			this.color = color;
		}
	}
}
