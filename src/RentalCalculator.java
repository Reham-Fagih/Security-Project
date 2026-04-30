public class RentalCalculator {

    private static final double FUEL_PRICE_PER_GALLON = 2.25;

    public double calculateRentalCost(Car car, int rentalDays) {
        if (car == null) {
            throw new IllegalArgumentException("Car cannot be null");
        }
        if (rentalDays <= 0) {
            throw new IllegalArgumentException("Rental days must be greater than 0");
        }

        return car.getCategory().getDailyRentalCost() * rentalDays;
    }

    public double calculateFuelCost(Car car, double distance) {
        if (car == null) {
            throw new IllegalArgumentException("Car cannot be null");
        }
        if (distance < 0) {
            throw new IllegalArgumentException("Distance cannot be negative");
        }
        if (car.getMpg() <= 0) {
            throw new IllegalArgumentException("MPG must be positive");
        }

        double gallonsNeeded = distance / car.getMpg();
        return gallonsNeeded * FUEL_PRICE_PER_GALLON;
    }

    public RentalQuote calculateQuote(Car car, int rentalDays, double distance) {
        if (car == null) {
            throw new IllegalArgumentException("Car cannot be null");
        }
        if (rentalDays <= 0) {
            throw new IllegalArgumentException("Rental days must be greater than 0");
        }
        if (distance < 0) {
            throw new IllegalArgumentException("Distance cannot be negative");
        }

        double rentalCost = calculateRentalCost(car, rentalDays);
        double fuelCost = calculateFuelCost(car, distance);
        double total = rentalCost + fuelCost;

        return new RentalQuote(car, rentalDays, distance, rentalCost, fuelCost, total);
    }

    public RentalQuote[] findBestCars(java.util.List<Car> cars, int rentalDays, double distance, int passengers) {
        if (cars == null || cars.isEmpty()) {
            throw new IllegalArgumentException("Cars list cannot be empty");
        }
        if (rentalDays <= 0) {
            throw new IllegalArgumentException("Rental days must be greater than 0");
        }
        if (distance < 0) {
            throw new IllegalArgumentException("Distance cannot be negative");
        }
        if (passengers <= 0) {
            throw new IllegalArgumentException("Passengers must be greater than 0");
        }

        java.util.ArrayList<RentalQuote> candidateQuotes = new java.util.ArrayList<>();

        for (Car car : cars) {
            if (car == null) {
                continue;
            }
            if (!canFitPassengers(car, passengers)) {
                continue;
            }

            candidateQuotes.add(calculateQuote(car, rentalDays, distance));
        }

        if (candidateQuotes.isEmpty()) {
            return new RentalQuote[0];
        }

        RentalQuote best = candidateQuotes.get(0);

        for (int i = 1; i < candidateQuotes.size(); i++) {
            RentalQuote current = candidateQuotes.get(i);
            if (isBetterChoice(current, best)) {
                best = current;
            }
        }

        java.util.ArrayList<RentalQuote> bestQuotes = new java.util.ArrayList<>();
        for (RentalQuote quote : candidateQuotes) {
            if (isTie(quote, best)) {
                bestQuotes.add(quote);
            }
        }

        return bestQuotes.toArray(new RentalQuote[0]);
    }

    private boolean isBetterChoice(RentalQuote a, RentalQuote b) {
        if (a.getTotalCost() < b.getTotalCost()) {
            return true;
        }
        if (a.getTotalCost() > b.getTotalCost()) {
            return false;
        }

        int comfortA = a.getCar().getCategory().getComfortLevel().ordinal();
        int comfortB = b.getCar().getCategory().getComfortLevel().ordinal();
        return comfortA > comfortB;
    }

    private boolean isTie(RentalQuote a, RentalQuote b) {
        if (Double.compare(a.getTotalCost(), b.getTotalCost()) != 0) {
            return false;
        }

        return a.getCar().getCategory().getComfortLevel() == b.getCar().getCategory().getComfortLevel();
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
