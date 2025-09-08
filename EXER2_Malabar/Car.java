public class Car {
    private String color;
    private String plateNo;
    private String chassisNo;
    private String brand;
    private int year;
    private boolean isElectric;

    // Default constructor
    public Car() {
        this.color = "No Color";
        this.plateNo = "No Plate Number";
        this.chassisNo = "No Chassis No Yet";
        this.brand = "Unknown";
        this.year = 0;
        this.isElectric = false;
    }

    // Parameterized constructor
    public Car(String color, String plateNo, String chassisNo, String brand, int year, boolean isElectric) {
        this.color = color;
        this.plateNo = plateNo;
        this.chassisNo = chassisNo;
        this.brand = brand;
        this.year = year;
        this.isElectric = isElectric;
    }

    public void displayInfo() {
        System.out.println("Car Information:");
        System.out.println("Color: " + this.color);
        System.out.println("Plate No: " + this.plateNo);
        System.out.println("Chassis No: " + this.chassisNo);
        System.out.println("Brand: " + this.brand);
        System.out.println("Year: " + this.year);
        System.out.println("Electric: " + (this.isElectric ? "Yes" : "No"));
    }
}
