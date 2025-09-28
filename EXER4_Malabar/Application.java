class Application {
    private int applicationId;
    private String status;
    private String dateApplied;

    public void submit() {
        System.out.println("Application submitted.");
    }

    public void withdraw() {
        System.out.println("Application withdrawn.");
    }

    public void updateStatus() {
        System.out.println("Application status updated.");
    }
}
