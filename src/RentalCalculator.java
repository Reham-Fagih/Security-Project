public class RentalCalculator {

    public RentalQuote calculateQuote(Car car, int rentalDays) {
        if (car == null) {
            throw new IllegalArgumentException("Car cannot be null");
        }
        if (rentalDays <= 0) {
            throw new IllegalArgumentException("Rental days must be greater than 0");
        }

        double daily = car.getCategory().getDailyRentalCost();
        double total = daily * rentalDays;

        return new RentalQuote(car, rentalDays, total);
    }

    public boolean canFitPassengers(Car car, int passengers) {
        if (car == null) {
            throw new IllegalArgumentException("Car cannot be null");
        }
        if (passengers <= 0) {
            throw new IllegalArgumentException("Passengers must be greater than 0");
        }

        return passengers <= car.getCategory().getPassengerCapacity();
    }
}
