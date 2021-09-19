import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListTestApplication {

    public static void main(String[] args) {
        List<Integer> myList = new ArrayList<>();
        myList.add(2);
        myList.add(1);
        myList.add(5);
        myList.add(3);
        myList.add(4);
        Collections.sort(myList, Collections.reverseOrder());
        System.out.println(myList);
        myList.sort((Integer i1, Integer i2) -> i1.compareTo(i2));
        System.out.println(myList);
    }
}
