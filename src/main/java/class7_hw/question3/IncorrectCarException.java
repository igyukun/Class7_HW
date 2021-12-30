package class7_hw.question3;

/*
Custom exception to be thrown in the case of incorrect car name provided
 */

public class IncorrectCarException extends Exception{

    //class constructor
    public IncorrectCarException ()
        {super (String.format("%nIncorrect car name has been selected.%n"));}

    public IncorrectCarException (String customMessage)
        {super (String.format("%n%s: Incorrect car name has been selected.%n",customMessage));}

}
