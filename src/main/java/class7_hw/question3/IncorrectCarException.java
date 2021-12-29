package class7_hw.question3;

public class IncorrectCarException extends Exception{

    public IncorrectCarException ()
    {super (String.format("%nIncorrect car name has been selected.%n"));}

}
