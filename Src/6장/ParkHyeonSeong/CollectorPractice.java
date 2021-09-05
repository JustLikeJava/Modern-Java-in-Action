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
		
		// ��� ���� ���
		long howManyDishes1 = menu.stream().collect(Collectors.counting()); //Collectors ��������,import������
		long howManyDishes2 = menu.stream().count();
		System.out.println(howManyDishes2);
		
		
		// �ִ� �ּڰ�
		Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);
		Optional<Dish> mostCalorieDish = menu.stream().collect(maxBy(dishCaloriesComparator));
		System.out.println(mostCalorieDish.get());
		
		// ��� ����
		int totalCalories = menu.stream().collect(summingInt(Dish::getCalories));
		System.out.println(totalCalories);
		
		double avgCalories = menu.stream().collect(averagingInt(Dish::getCalories));
		System.out.println(avgCalories);
		
		IntSummaryStatistics menuStatistics = menu.stream().collect(summarizingInt(Dish::getCalories));
		System.out.println(menuStatistics);
		
		// ���ڿ� ����
		String shortMenu1 = menu.stream().map(Dish::getName).collect(joining());
		// ��ü�� toString �޼��带 �����ϸ� mapping �����ص� �ȴ��ߴµ� �� �ȵɱ�..
		// ������ ����
		String shortMenu2 = menu.stream().map(Dish::getName).collect(joining(","));
		System.out.println(shortMenu1);
		System.out.println(shortMenu2);
		
		
		// ���� ����� ��� ����
		int totalCalories2 = menu.stream().collect(reducing(0,Dish::getCalories,(i,j) -> i+j));
		int totalCalories3 = menu.stream().collect(reducing(0,Dish::getCalories,Integer::sum));
		System.out.println(totalCalories2);
		System.out.println(totalCalories3);
		
		// �׷�ȭ
		Map<Dish.Type, List<Dish>> dishesByType =
				menu.stream().collect(groupingBy(Dish::getType));
		System.out.println(dishesByType);
		
		// �׷�ȭ�� ��� ����
		Map<Dish.Type, Long> caloricDishesByType =
				menu.stream().collect(groupingBy(Dish::getType,
						counting()));
		System.out.println(caloricDishesByType);
		
		
		
	}
}
