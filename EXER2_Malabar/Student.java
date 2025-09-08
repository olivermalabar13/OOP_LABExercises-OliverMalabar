public class Student {
    private String name;
    private int age;
    private String course;

    public Student() {
        this.name = "Unknown";
        this.age = 0;
        this.course = "Undeclared";
    }

    public Student(String name, int age, String course) {
        this.name = name;
        this.age = age;
        this.course = course;
    }

    public void displayInfo() {
        System.out.println("Student Information:");
        System.out.println("Name: " + this.name);
        System.out.println("Age: " + this.age);
        System.out.println("Course: " + this.course);
    }
}
