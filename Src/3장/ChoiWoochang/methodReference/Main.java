import java.util.Arrays;
import java.util.List;

import static java.util.Comparator.comparing;

public class Main {

    public static void print(List<Apple> list) {
        for(Apple a : list) {
            System.out.println(a.getWeight());
        }
    }

    public static void main(String[] args) {
        Apple a1 = new Apple(7);
        Apple a2 = new Apple(3);
        Apple a3 = new Apple(4);
        Apple a4 = new Apple(10);
	    List<Apple> inventory = Arrays.asList(a1, a2, a3, a4);
	    print(inventory);
	    inventory.sort(comparing(Apple::getWeight));
	    print(inventory);
    }
}
