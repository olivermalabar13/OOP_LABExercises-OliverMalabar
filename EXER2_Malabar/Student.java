public class Customer {
    private String name;
    private int age;
    private String gender;
    private String email;
    private String phone;
    private boolean premiumMember;

    // Default constructor
    public Customer() {
        this.name = "Unknown";
        this.age = 0;
        this.gender = "Unspecified";
        this.email = "Not Provided";
        this.phone = "Not Provided";
        this.premiumMember = false;
    }

    // Parameterized constructor
    public Customer(String name, int age, String gender, String email, String phone, boolean premiumMember) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.premiumMember = premiumMember;
    }

    public void displayInfo() {
        System.out.println("Customer Information:");
        System.out.println("Name: " + this.name);
        System.out.println("Age: " + this.age);
        System.out.println("Gender: " + this.gender);
        System.out.println("Email: " + this.email);
        System.out.println("Phone: " + this.phone);
        System.out.println("Premium Member: " + (this.premiumMember ? "Yes" : "No"));
    }
}
