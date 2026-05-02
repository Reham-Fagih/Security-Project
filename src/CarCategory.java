public enum CarCategory {
    ECONOMY(150.0, 4, ComfortLevel.LOW),
    INTERMEDIATE(220.0, 5, ComfortLevel.MEDIUM),
    STANDARD(300.0, 5, ComfortLevel.HIGH),
    VAN(400.0, 8, ComfortLevel.MEDIUM);

    private final double dailyRentalCost;
    private final int passengerCapacity;
    private final ComfortLevel comfortLevel;

    CarCategory(double dailyRentalCost, int passengerCapacity, ComfortLevel comfortLevel) {
        if (dailyRentalCost <= 0) {
            throw new IllegalArgumentException("Daily rental cost must be positive");
        }
        if (passengerCapacity <= 0) {
            throw new IllegalArgumentException("Passenger capacity must be positive");
        }
        if (comfortLevel == null) {
            throw new IllegalArgumentException("Comfort level cannot be null");
        }

        this.dailyRentalCost = dailyRentalCost;
        this.passengerCapacity = passengerCapacity;
        this.comfortLevel = comfortLevel;
    }

    public double getDailyRentalCost() {
        return dailyRentalCost;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public ComfortLevel getComfortLevel() {
        return comfortLevel;
    }
}
