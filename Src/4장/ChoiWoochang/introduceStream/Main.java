import java.util.Arrays;
import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class Main {
    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
             new Dish(800, "pork"),
             new Dish(700, "beef"),
             new Dish(400, "chicken"),
             new Dish(530, "french fries"),
             new Dish(350, "rice"),
             new Dish(120, "season fruit"),
             new Dish(550, "pizza"),
             new Dish(300, "prawn"),
             new Dish(450, "salmon")
        );

        List<String> lowCaloricDishesName =
                menu.stream()
                        .filter(d -> d.getCalories() < 400)
                        .sorted(comparing(Dish::getCalories))
                        .map(Dish::getName)
                        .collect(toList());

        for(String s : lowCaloricDishesName) {
            System.out.println(s);
        }
        
    }
}