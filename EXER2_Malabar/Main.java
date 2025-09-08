public class Main {
    public static void main(String[] args) {
        // Car objects
        Car car1 = new Car();
        Car car2 = new Car("Red", "ABC-1234", "CHS-98765", "Toyota", 2022, false);

        car1.displayInfo();
        System.out.println();
        car2.displayInfo();

        System.out.println("-------------------");

        // Customer objects
        Customer c1 = new Customer();
        Customer c2 = new Customer("Oliver", 18, "Male", "oliver@example.com", "09946788344", true);

        c1.displayInfo();
        System.out.println();
        c2.displayInfo();
    }
}
