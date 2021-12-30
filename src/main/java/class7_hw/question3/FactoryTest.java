package class7_hw.question3;
import class7_hw.question3.cars.Drivable;

/**
 * ClassFactoryTest creates objects of car classes using Factory DP and calls their drive() method
 */

public class FactoryTest {
    public static void main(String[] args) {

        CarFactory car = new CarFactory();

        try {
            //Create and drive a Fiat car
            Drivable fiat = car.getCar("FiaT");
            fiat.drive();
            //Create and drive a BMW car
            Drivable bmw = car.getCar("BMW");
            bmw.drive();
            //Create and drive a Porsche car
            Drivable porsche = car.getCar("PORSCHE");
            porsche.drive();
            //Try to create a car with an invalid name to get an exception
            Drivable lada = car.getCar("LADA");
            lada.drive();
        } catch (IncorrectCarException e) {
            //The IncorrectCarException is thrown when an invalid car name is provided
            System.out.println(e.getMessage());
        }
    }
}
