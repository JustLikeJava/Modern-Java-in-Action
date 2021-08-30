package javaStudy_chapter5;

import java.util.List;
import java.util.Optional;
import java.util.Arrays;
import static java.util.stream.Collectors.toList;
import static javaStudy_chapter5.Dish.menu;

public class PracticeStream {
	public static void main(String[] args) {
		// ��� ����
		List<Dish> filteredMenu1 = menu.stream()
										.filter(d -> d.getCalories() < 500)
										.limit(3)
										.collect(toList());
		
		System.out.println("<limit>");
		filteredMenu1.stream().forEach(d -> System.out.println(d));
		
		
		// ��� �ǳʶٱ�
		List<Dish> filteredMenu2 = menu.stream()
				.filter(d -> d.getCalories() < 500)
				.skip(3)
				.collect(toList());
		
		System.out.println("<skip>");
		filteredMenu2.stream().forEach(d -> System.out.println(d));
		
		
		// ��Ʈ�� ���ȭ
		List<String> words = Arrays.asList("Hello","World");
		List<String> uniqueWords = words.stream()
				.map(word -> word.split(""))
				// �� �迭�� ������ ��Ʈ������ ����, ������ ��Ʈ���� �ϳ��� ��Ʈ������ ���ȭ
				// Arrays::stream�� array�� ��� Ÿ������ ��Ʈ�� ����
				.flatMap(Arrays::stream) 
				.distinct()
				.collect(toList());
		System.out.println("<flatMap>");
		uniqueWords.stream().forEach(w -> System.out.println(w));
		
		
		//�˻��� ��Ī
		if(menu.stream().anyMatch(Dish::isVegetarian)) { // ��� �� ��ҿ� ��ġ�ϴ���
			System.out.println("The menu is (somewhat) vegetarian friendly!!");
		}
		
		// ��� ��Ұ� ��ġ�ϴ���, �ݴ� �������� noneMatch�� �ִ�.
		boolean isHealthy = menu.stream().allMatch(d -> d.getCalories() < 1000);
		
		
		// ��� �˻�
		
		// ������ ��� ��ȯ, findFirst�� ù��°��� ��ȯ
		Optional<Dish> dish = menu.stream().filter(Dish::isVegetarian).findAny();
		if(dish.isPresent()) System.out.println("is Present!");
		dish.ifPresent(d -> System.out.println(d));
		// T get(),T orElse(T other)
		
		
		// �����
		System.out.println("<reduce>");
		List<Integer> numbers = Arrays.asList(1,2,3,4,5);
		int sum = numbers.stream().reduce(0, (a,b) -> a + b);
		int sum2 = numbers.stream().reduce(0, Integer::sum);
		int mx = numbers.stream().reduce(0, Integer::max);
		Optional<Integer> sumO = numbers.stream().reduce((a,b) -> a + b); // �ʱ갪������ Optional ����
		System.out.println(sum);
		
	}
}
