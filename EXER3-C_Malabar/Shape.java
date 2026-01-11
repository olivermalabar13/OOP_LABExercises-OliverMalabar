public abstract class Shape {

    // abstract method
    public abstract double calculateArea();

    // concrete method
    public void displayArea() {
        System.out.println("Area: " + calculateArea());
    }
}
