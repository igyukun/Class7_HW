package class7_hw.question3;
/*
Class CarFactory implements the Factory design pattern to create and return
an instance of a specific class, implementing the Drivable interface.
 */
import class7_hw.question3.cars.BMW;
import class7_hw.question3.cars.Drivable;
import class7_hw.question3.cars.Fiat;
import class7_hw.question3.cars.Porsche;

public class CarFactory {

    public Drivable getCar(String car) throws IncorrectCarException {
        switch (car.toLowerCase()){
            case "fiat":
                return new Fiat();
            case "bmw":
                return new BMW();
            case "porsche":
                return new Porsche();
            default:
                //if a provided car name is not listed, throw a relevant exception
                throw new IncorrectCarException(car);
        }
    }
}
