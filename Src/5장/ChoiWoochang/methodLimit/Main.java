package com.company;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Main {

    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH)
        );

        List<Dish> specialMenu = menu.stream()
                                     .filter(dish -> {
                                         System.out.println("h");
                                         if(dish.getCalories() < 150)
                                             return true;
                                         return false;
                                     })
                                     .limit(1) // 개수가 채워지면 중단하고 반환
                                     .collect(toList());

        System.out.println(specialMenu.size());

        for(Dish d : specialMenu) {
            System.out.println(d.getName());
        }
    }
}
