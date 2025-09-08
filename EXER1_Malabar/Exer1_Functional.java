import java.util.Arrays;
import java.util.List;

public class Exer1_Functional {
    public static void main(String[] args) {
        // Functional approach: using Streams and Lambdas
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        // Example: Square each number and print only even results
        numbers.stream()
               .map(n -> n * n)         // square each number
               .filter(n -> n % 2 == 0) // keep only even squares
               .forEach(System.out::println);
    }
}
