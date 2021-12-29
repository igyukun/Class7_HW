package class7_hw.question3;
import class7_hw.question3.cars.Drivable;

/**
 * ClassFactoryTest creates objects of car classes using Factory DP and calls their drive() method
 */

public class FactoryTest {
    public static void main(String[] args) {

        CarFactory car = new CarFactory();

        try {
            Drivable fiat = car.getCar("FiaT");
            fiat.drive();
            Drivable bmw = car.getCar("BMW");
            bmw.drive();
            Drivable porsche = car.getCar("PORSCHE");
            porsche.drive();
            Drivable lada = car.getCar("ZHIGULI");
            porsche.drive();
        } catch (IncorrectCarException e) {
            System.out.println(e.getMessage());
        }
    }
}
