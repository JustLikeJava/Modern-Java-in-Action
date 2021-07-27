
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ComparableSort {

	public static void main(String[] args) {
		List<Apple> inventory = new ArrayList<Apple>();
		inventory.add(new Apple(5));
		inventory.add(new Apple(1));
		inventory.add(new Apple(3));
		inventory.add(new Apple(10));
		inventory.add(new Apple(7));
		
		for(Apple a : inventory) {
			System.out.println(a.getWeight());
		}
		
		Collections.sort(inventory);
		
		for(Apple a : inventory) {
			System.out.println(a.getWeight());
		}
	}
}

// Comparable 인터페이스를 이용하는 방법
// compareTo 함수의 구현이 필요
class Apple implements Comparable<Apple> {
	
	private Integer weight;
	
	public Apple(int weight) {
		this.weight = weight;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public int compareTo(Apple a) {
		return this.weight.compareTo(a.getWeight());
	}
}