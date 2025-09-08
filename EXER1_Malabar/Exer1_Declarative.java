import java.util.Arrays;
import java.util.List;

public class Exer1_Declarative {
    public static void main(String[] args) {
        // Declarative: say "what to do"
        List<String> fruits = Arrays.asList("apple", "banana", "mango", "orange");

        // Count fruits starting with 'a'
        long count = fruits.stream()
                           .filter(fruit -> fruit.startsWith("a"))
                           .count();

        System.out.println("Fruits that start with 'a': " + count);
    }
}
