import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<Car> cars = new ArrayList<>();

        try {
            // ✅ تغطية جميع الفئات المطلوبة

            cars.add(new Car("Toyota", "RAV4", "SUV", 30, 5));
            cars.add(new Car("Toyota", "Camry", "Sedan", 32, 5));
            cars.add(new Car("Ford", "F-150", "Truck", 22, 5));
            cars.add(new Car("Honda", "Civic Coupe", "Coupe", 31, 5));
            cars.add(new Car("Toyota", "Prius", "Hybrid", 52, 5));
            cars.add(new Car("Honda", "CR-V", "Crossover", 28, 5));
            cars.add(new Car("Honda", "Odyssey", "Van/Minivan", 22, 8));

        } catch (IllegalArgumentException e) {
            // 🔐 Fail Securely
            System.out.println("Error adding car: " + e.getMessage());
        }

        // عرض جميع السيارات
        System.out.println("Available Cars:");
        System.out.println();
        for (Car car : cars) {
            car.displayCar();
            System.out.println("--------------------------------------------------------------------");
        }
    }
}