import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        System.out.println(stack.peek());

        List<Integer> result = Arrays.asList(2, 3, 4, 5);
        result.stream().parallel()
                .peek(n -> System.out.println("First: " + n))
                .map(n -> n + 17)
                .peek(n -> System.out.println("Second: " + n))
                .filter(n -> n % 2 == 0)
                .peek(n -> System.out.println("Third: " + n))
                .limit(3)
                .peek(n -> System.out.println("Fourth: " + n))
                .collect(Collectors.toList());

        System.out.println("----------");

        result.stream()
                .peek(n -> System.out.println("First: " + n))
                .map(n -> n + 17)
                .peek(n -> System.out.println("Second: " + n))
                .filter(n -> n % 2 == 0)
                .peek(n -> System.out.println("Third: " + n))
                .limit(3)
                .peek(n -> System.out.println("Fourth: " + n))
                .collect(Collectors.toList());

    }
}
