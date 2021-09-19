import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // 추가, 수정, 삭제 가능
        List<String> friends1 = new ArrayList<>();
        friends1.add("A");
        friends1.add("B");
        friends1.remove("A");
        friends1.add("C");
        friends1.set(1, "D");
        System.out.println(friends1);

        // 추가, 삭제는 불가능 수정은 가능
        List<String> friends = Arrays.asList("Raphael", "Olivia");
        friends.set(0, "Raphael");
        System.out.println(friends);

        // 추가, 삭제, 수정 불가능
        List<String> friends2 = List.of("Raphael", "Olivia");
        System.out.println(friends2);
    }
}
