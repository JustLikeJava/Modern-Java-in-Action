package javaStudy_chapter5;

import java.util.List;
import java.util.Optional;
import java.util.Arrays;
import static java.util.stream.Collectors.toList;
import static javaStudy_chapter5.Dish.menu;

public class PracticeStream {
	public static void main(String[] args) {
		// 요소 제한
		List<Dish> filteredMenu1 = menu.stream()
										.filter(d -> d.getCalories() < 500)
										.limit(3)
										.collect(toList());
		
		System.out.println("<limit>");
		filteredMenu1.stream().forEach(d -> System.out.println(d));
		
		
		// 요소 건너뛰기
		List<Dish> filteredMenu2 = menu.stream()
				.filter(d -> d.getCalories() < 500)
				.skip(3)
				.collect(toList());
		
		System.out.println("<skip>");
		filteredMenu2.stream().forEach(d -> System.out.println(d));
		
		
		// 스트림 평면화
		List<String> words = Arrays.asList("Hello","World");
		List<String> uniqueWords = words.stream()
				.map(word -> word.split(""))
				// 각 배열을 별도의 스트림으로 생성, 생성된 스트림을 하나의 스트림으로 평면화
				// Arrays::stream은 array의 요소 타입으로 스트림 생성
				.flatMap(Arrays::stream) 
				.distinct()
				.collect(toList());
		System.out.println("<flatMap>");
		uniqueWords.stream().forEach(w -> System.out.println(w));
		
		
		//검색과 매칭
		if(menu.stream().anyMatch(Dish::isVegetarian)) { // 적어도 한 요소와 일치하는지
			System.out.println("The menu is (somewhat) vegetarian friendly!!");
		}
		
		// 모든 요소가 일치하는지, 반대 연산으로 noneMatch가 있다.
		boolean isHealthy = menu.stream().allMatch(d -> d.getCalories() < 1000);
		
		
		// 요소 검색
		
		// 임의의 요소 반환, findFirst는 첫번째요소 반환
		Optional<Dish> dish = menu.stream().filter(Dish::isVegetarian).findAny();
		if(dish.isPresent()) System.out.println("is Present!");
		dish.ifPresent(d -> System.out.println(d));
		// T get(),T orElse(T other)
		
		
		// 리듀싱
		System.out.println("<reduce>");
		List<Integer> numbers = Arrays.asList(1,2,3,4,5);
		int sum = numbers.stream().reduce(0, (a,b) -> a + b);
		int sum2 = numbers.stream().reduce(0, Integer::sum);
		int mx = numbers.stream().reduce(0, Integer::max);
		Optional<Integer> sumO = numbers.stream().reduce((a,b) -> a + b); // 초깃값없으면 Optional 리턴
		System.out.println(sum);
		
	}
}
