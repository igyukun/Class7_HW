package class7_hw.question2;

/**
 * Class Q2 main instantiates the class Q2Singleton,
 * gets a user input and writes it into a log file using
 * the relevant method of the Singleton class.
 */

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Q2Main {
    public static void main(String[] args) {

        int iAge = -1; //User's age

        //Read a user's name and write it into a log
        System.out.printf("Please enter the personal data:%n");
        System.out.printf("Name: ");
        writeDataIntoLog(String.format("Name: %s | ", getStrFromUser()));

        //Iterate until a correct input is provided
        while (true){
            try{
                //Read user's age, check input correctness and write into a log
                System.out.printf("Age (positive integer number between 0 and 120): ");
                iAge = getIntFromUser();
                if (iAge < 0 || iAge > 120) {
                    printErrorMessage();
                    continue;
                } else {
                    writeDataIntoLog(String.format(" Age: %s | ", String.valueOf(iAge)));
                    break;
                }
            }catch (InputMismatchException e) {
                printErrorMessage();
            }
        }

        //Read a user's address and write it into a log
        System.out.printf("Address: ");
        writeDataIntoLog(String.format("  Address: %s%n", getStrFromUser()));

        //Read the log file contents
        System.out.printf("%nThe data written is: %n=========%n");
        readDataFromLog();
    }

    //Getting an integer input from user
    static int getIntFromUser() throws InputMismatchException {
        Scanner scan = new Scanner(System.in);
        return scan.nextInt();
    }

    //Getting a string input from user
    static String getStrFromUser() throws InputMismatchException {
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }

    //Instantiate the Q2Singleton class object if doesn't exist and write data into the log
    static void writeDataIntoLog(String sData){
        Q2Singleton logger = Q2Singleton.getInstance();
        try {
            logger.writeLog(sData, false); //always append a data to a log file
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //Instantiate the Q2Singleton class object if doesn't exist and read data from the log
    static void readDataFromLog(){
        Q2Singleton logger = Q2Singleton.getInstance();
        try {
            System.out.printf(logger.readLog());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //Print error message
    static void printErrorMessage(){
        System.out.printf("Incorrect input.%n");
    }
}
