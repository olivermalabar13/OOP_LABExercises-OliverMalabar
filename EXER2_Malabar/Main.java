public class Main {
    public static void main(String[] args) {
      
        Laptop laptop1 = new Laptop();
        Laptop laptop2 = new Laptop("HP", "Victus 15", "SN-RTX4050");

        laptop1.displayInfo();
        System.out.println();
        laptop2.displayInfo();

        System.out.println("-------------------");

        Student s1 = new Student();
        Student s2 = new Student("Oliver", 21, "BSIT");

        s1.displayInfo();
        System.out.println();
        s2.displayInfo();
    }
}
