import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;

public class Merge {

    public static void main(String[] args) {
        Map<String, String> family = Map.ofEntries(entry("Teo", "Star Wars"),
                entry("Cristina", "James Bond"));
        Map<String, String> friends = Map.ofEntries(entry("Raphael", "Star Wars"),
                entry("Cristina", "Matrix"));
        Map<String, String> everyone = new HashMap<>(family);
        friends.forEach((k, v) -> everyone.merge(k, v, (movie1, movie2) -> movie1 + " & " + movie2));
        System.out.println(everyone);
    }
}
