public class RentalQuote {
    private final Car car;
    private final int rentalDays;
    private final double totalCost;

    public RentalQuote(Car car, int rentalDays, double totalCost) {
        if (car == null) {
            throw new IllegalArgumentException("Car cannot be null");
        }
        if (rentalDays <= 0) {
            throw new IllegalArgumentException("Rental days must be positive");
        }
        if (totalCost < 0) {
            throw new IllegalArgumentException("Total cost cannot be negative");
        }

        this.car = car;
        this.rentalDays = rentalDays;
        this.totalCost = totalCost;
    }

    public Car getCar() {
        return car;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public double getTotalCost() {
        return totalCost;
    }
}
