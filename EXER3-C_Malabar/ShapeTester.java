public class ShapeTester {

    public static void main(String[] args) {

        Shape circle = new Circle(5);
        Shape rectangle = new Rectangle(4, 6);
        Shape square = new Square(4);
        Shape triangle = new Triangle(3, 7);

        System.out.println("Circle");
        circle.displayArea();

        System.out.println("\nRectangle");
        rectangle.displayArea();

        System.out.println("\nSquare");
        square.displayArea();

        System.out.println("\nTriangle");
        triangle.displayArea();
    }
}
