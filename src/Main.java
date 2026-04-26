import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ArrayList<Car> cars = new ArrayList<>();
        RentalCalculator calculator = new RentalCalculator();

        try {
            // ✅ تغطية جميع الفئات المطلوبة

            cars.add(new Car("Toyota", "RAV4", VehicleType.SUV, CarCategory.STANDARD, 30));
            cars.add(new Car("Toyota", "Camry", VehicleType.SEDAN, CarCategory.INTERMEDIATE, 32));
            cars.add(new Car("Ford", "F-150", VehicleType.TRUCK, CarCategory.STANDARD, 22));
            cars.add(new Car("Honda", "Civic Coupe", VehicleType.COUPE, CarCategory.ECONOMY, 31));
            cars.add(new Car("Toyota", "Prius", VehicleType.HYBRID, CarCategory.INTERMEDIATE, 52));
            cars.add(new Car("Honda", "CR-V", VehicleType.CROSSOVER, CarCategory.STANDARD, 28));
            cars.add(new Car("Honda", "Odyssey", VehicleType.VAN_MINIVAN, CarCategory.VAN, 22));

        } catch (IllegalArgumentException e) {
            // 🔐 Fail Securely
            System.out.println("Error adding car: " + e.getMessage());
        }

        // عرض جميع السيارات
        System.out.println("Available Cars:");
        System.out.println();
        for (int i = 0; i < cars.size(); i++) {
            Car car = cars.get(i);
            System.out.println("#" + (i + 1));
            car.displayCar();
            System.out.println("--------------------------------------------------------------------");
        }

        if (cars.isEmpty()) {
            return;
        }

        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println();
            System.out.print("Select car index (1-" + cars.size() + "): ");
            String carIndexRaw = scanner.nextLine();
            int carIndex = Integer.parseInt(carIndexRaw);

            if (carIndex < 1 || carIndex > cars.size()) {
                throw new IllegalArgumentException("Car index out of range");
            }

            Car selectedCar = cars.get(carIndex - 1);

            System.out.print("Enter rental days: ");
            String daysRaw = scanner.nextLine();
            int days = Integer.parseInt(daysRaw);

            System.out.print("Enter number of passengers: ");
            String passengersRaw = scanner.nextLine();
            int passengers = Integer.parseInt(passengersRaw);

            if (!calculator.canFitPassengers(selectedCar, passengers)) {
                throw new IllegalArgumentException("Selected car cannot fit the requested number of passengers");
            }

            RentalQuote quote = calculator.calculateQuote(selectedCar, days);
            System.out.println();
            System.out.println("Quote Summary:");
            System.out.println("Car: " + quote.getCar().getMake() + " " + quote.getCar().getModel());
            System.out.println("Category: " + quote.getCar().getCategory());
            System.out.println("Days: " + quote.getRentalDays());
            System.out.println("Total Cost: " + quote.getTotalCost());

        } catch (NumberFormatException e) {
            System.out.println("Invalid number format. Please enter numeric values only.");
        } catch (IllegalArgumentException e) {
            System.out.println("Input error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}