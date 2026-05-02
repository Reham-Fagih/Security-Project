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
            System.out.print("Enter rental days: ");
            String daysRaw = scanner.nextLine();
            int days = Integer.parseInt(daysRaw);

            System.out.print("Enter distance: ");
            String distanceRaw = scanner.nextLine();
            double distance = Double.parseDouble(distanceRaw);

            System.out.print("Enter number of passengers: ");
            String passengersRaw = scanner.nextLine();
            int passengers = Integer.parseInt(passengersRaw);

            RentalQuote[] bestQuotes = calculator.findBestCars(cars, days, distance, passengers);
            if (bestQuotes.length == 0) {
                throw new IllegalArgumentException("No available car can fit the requested number of passengers");
            }

            System.out.println();
            System.out.println("Best Option(s):");
            for (RentalQuote quote : bestQuotes) {
                System.out.println("Car: " + quote.getCar().getMake() + " " + quote.getCar().getModel());
                System.out.println("Rental Category: " + quote.getCar().getCategory());
                System.out.println("Days: " + quote.getRentalDays());
                System.out.println("Distance: " + quote.getDistance());
                System.out.println("Rental Cost: " + quote.getRentalCost());
                System.out.println("Fuel Cost: " + quote.getFuelCost());
                System.out.println("Total Cost: " + quote.getTotalCost());
                System.out.println("Comfort: " + quote.getCar().getComfortLevel());
                System.out.println("--------------------------------------------");
            }

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