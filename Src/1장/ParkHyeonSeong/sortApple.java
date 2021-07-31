package javaStudy_chapter1;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

public class sortApple {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Apple> appleList1 = new ArrayList<>();
		appleList1.add(new Apple(40, "green"));
		appleList1.add(new Apple(80, "green"));
		appleList1.add(new Apple(20, "green"));
		appleList1.add(new Apple(90, "green"));
		appleList1.add(new Apple(160, "green"));
		
		System.out.println("< before sort(appleList1) >");
		for(Apple a:appleList1) {	
			System.out.println(a);
		}
		
		Collections.sort(appleList1, new Comparator<Apple>() {
			public int compare(Apple a1, Apple a2) {
				Integer i = new Integer(a1.getWeight());
				return i.compareTo(a2.getWeight());
			}
		});
		
		System.out.println();
		System.out.println("< before sort(appleList1) >");
		for(Apple a:appleList1) {	
			System.out.println(a);
		}
		
		List<Apple> appleList2 = new ArrayList<>();
		appleList2.add(new Apple(40, "green"));
		appleList2.add(new Apple(80, "green"));
		appleList2.add(new Apple(20, "green"));
		appleList2.add(new Apple(90, "green"));
		appleList2.add(new Apple(160, "green"));
		
		System.out.println();
		System.out.println("< before sort(appleList2) >");
		for(Apple a:appleList2) {	
			System.out.println(a);
		}
		
		appleList2.sort(Comparator.comparing(Apple::getWeight));
		
		System.out.println();
		System.out.println("< before sort(appleList2) >");
		for(Apple a:appleList2) {	
			System.out.println(a);
		}
		
	}
	
	public static class Apple {

	    private int weight = 0;
	    private String color = "";

	    public Apple(int weight, String color) {
	      this.weight = weight;
	      this.color = color;
	    }

	    public int getWeight() {
	      return weight;
	    }

	    public void setWeight(int weight) {
	      this.weight = weight;
	    }

	    public String getColor() {
	      return color;
	    }

	    public void setColor(String color) {
	      this.color = color;
	    }

	    @SuppressWarnings("boxing")
	    @Override
	    public String toString() {
	      return String.format("Apple{color='%s', weight=%d}", color, weight);
	    }

	  }


}
