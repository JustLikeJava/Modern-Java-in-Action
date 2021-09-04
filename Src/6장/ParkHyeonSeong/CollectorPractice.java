package javaStudy_chapter6;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.stream.Collectors;
import java.util.Arrays;
import static java.util.stream.Collectors.toList;
import static javaStudy_chapter6.Dish.menu;
import static java.util.stream.Collectors.*;

public class CollectorPractice {
	public static void main(String[] args) {
		
		// 요소 갯수 계산
		long howManyDishes1 = menu.stream().collect(Collectors.counting()); //Collectors 생략가능,import때문에
		long howManyDishes2 = menu.stream().count();
		System.out.println(howManyDishes2);
		
		
		// 최댓값 최솟값
		Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);
		Optional<Dish> mostCalorieDish = menu.stream().collect(maxBy(dishCaloriesComparator));
		System.out.println(mostCalorieDish.get());
		
		// 요약 연산
		int totalCalories = menu.stream().collect(summingInt(Dish::getCalories));
		System.out.println(totalCalories);
		
		double avgCalories = menu.stream().collect(averagingInt(Dish::getCalories));
		System.out.println(avgCalories);
		
		IntSummaryStatistics menuStatistics = menu.stream().collect(summarizingInt(Dish::getCalories));
		System.out.println(menuStatistics);
		
		// 문자열 연결
		String shortMenu1 = menu.stream().map(Dish::getName).collect(joining());
		// 객체가 toString 메서드를 포함하면 mapping 생략해도 된다했는데 왜 안될까..
		// 구분자 삽입
		String shortMenu2 = menu.stream().map(Dish::getName).collect(joining(","));
		System.out.println(shortMenu1);
		System.out.println(shortMenu2);
		
		
		// 범용 리듀싱 요약 연산
		int totalCalories2 = menu.stream().collect(reducing(0,Dish::getCalories,(i,j) -> i+j));
		int totalCalories3 = menu.stream().collect(reducing(0,Dish::getCalories,Integer::sum));
		System.out.println(totalCalories2);
		System.out.println(totalCalories3);
		
		// 그룹화
		Map<Dish.Type, List<Dish>> dishesByType =
				menu.stream().collect(groupingBy(Dish::getType));
		System.out.println(dishesByType);
		
		// 그룹화된 요소 조작
		Map<Dish.Type, Long> caloricDishesByType =
				menu.stream().collect(groupingBy(Dish::getType,
						counting()));
		System.out.println(caloricDishesByType);
		
		
		
	}
}
