import java.util.ArrayList;
import java.util.List;

public class Exer1_Imperative {
    public static void main(String[] args) {
        // Imperative: tell exactly HOW to do it
        List<String> names = new ArrayList<>();
        names.add("Anna");
        names.add("Ben");
        names.add("Carlo");
        names.add("Alice");

        // Find names starting with 'A' manually
        int count = 0;
        for (String name : names) {
            if (name.startsWith("A")) {
                count++;
            }
        }

        System.out.println("Names that start with 'A': " + count);
    }
}
