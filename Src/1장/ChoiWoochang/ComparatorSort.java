import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparatorSort {

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
		
		// ArrayList 정렬에는 Collections.sort() 사용
		// Comparator 클래스를 이용하여 compare() 함수 구현이 필요: 정렬 기준을 정해주는 함수
		Collections.sort(inventory, new Comparator<Apple>() {
			public int compare(Apple a1, Apple a2) {
				return a1.getWeight().compareTo(a2.getWeight());
			}
		});
		
		for(Apple a : inventory) {
			System.out.println(a.getWeight());
		}
	}
}

class Apple {
	
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
}
