public class Laptop {
    private String brand;
    private String model;
    private String serialNo;
    
    public Laptop() {
        this.brand = "No Brand";
        this.model = "No Model Yet";
        this.serialNo = "No Serial Number";
    }

    public Laptop(String brand, String model, String serialNo) {
        this.brand = brand;
        this.model = model;
        this.serialNo = serialNo;
    }
    
    public void displayInfo() {
        System.out.println("Laptop Information:");
        System.out.println("Brand: " + this.brand);
        System.out.println("Model: " + this.model);
        System.out.println("Serial No: " + this.serialNo);
    }
}
