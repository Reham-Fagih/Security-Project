public class Car {

    // 🔐 Encapsulation
    private String make;
    private String model;
    private String category;
    private double mpg;
    private int maxPassengers;

    // Constructor + 🔐 Input Validation
    public Car(String make, String model, String category, double mpg, int maxPassengers) {

        if (make == null || make.isEmpty()) {
            throw new IllegalArgumentException("Make cannot be empty");
        }

        if (model == null || model.isEmpty()) {
            throw new IllegalArgumentException("Model cannot be empty");
        }

        if (category == null || category.isEmpty()) {
            throw new IllegalArgumentException("Category cannot be empty");
        }

        if (mpg <= 0) {
            throw new IllegalArgumentException("MPG must be positive");
        }

        if (maxPassengers <= 0) {
            throw new IllegalArgumentException("Passengers must be greater than 0");
        }

        this.make = make;
        this.model = model;
        this.category = category;
        this.mpg = mpg;
        this.maxPassengers = maxPassengers;
    }

    // Getters فقط
    public String getMake() { return make; }
    public String getModel() { return model; }
    public String getCategory() { return category; }
    public double getMpg() { return mpg; }
    public int getMaxPassengers() { return maxPassengers; }

    // عرض البيانات
    public void displayCar() {
        System.out.println(make + " " + model +
                " | Category: " + category +
                " | MPG: " + mpg +
                " | Passengers: " + maxPassengers);
    }
}