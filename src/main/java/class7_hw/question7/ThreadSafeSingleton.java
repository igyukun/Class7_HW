package class7_hw.question7;

/*
  ThreadSafeSingleton is a class implementing the Singleton design pattern.
  This class uses synchronization of the access to the class instance
  as well as synchronization to a shared physical resource (text file)
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ThreadSafeSingleton implements Runnable{

    private static volatile ThreadSafeSingleton instance = null; // The class instance defined as volatile to manage
                                                                 // the instance memory synchronization
    private static final Object mutex = new Object();            // mutex object used to lock a synchronized block from
                                                                 // been accessed by another thread

    //Singleton core function with a synchronized block to limit access to the instance for one thread in a time only
    public static ThreadSafeSingleton getInstance(){
        if (instance == null)
            synchronized (mutex) {
                instance = new ThreadSafeSingleton();
            }
        return instance;
    }

    // Override run() method of the Runnable interface. An entry point for a function when started
    // explicitly within a thread
    @Override
    public void run() {
        try {
            writeData();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    //Method writes data into the file in a loop, while pausing between each iteration
    synchronized public void writeData() throws IOException {
        int timeout = 1000;             // iteration timeout
        String message = "New loop";    // message to be written
        String fileName = "Data.txt";   // the output file path

        // Write an initial message and a timestamp
        writeFile(fileName,message,true);

        for (int i = 0; i<5; i++) {
            // Create and write the message with iteration number and thread ID
            message = String.format("Iteration #%d | %s%n", i,
                      Thread.currentThread().getName());
            writeFile(fileName, message, false);
            // Duplicate data to the system output
            System.out.print(message);
            try {
                // Pause the current thread execution for a given number of milliseconds or until interrupted
                Thread.sleep(timeout);
            }catch (InterruptedException e) {
                // Exception will be thrown in the case of interruption of the current thread
                System.out.format("Interrupt happened.");
            }
        }

    }

    // The method that writes to a physical file
    private void writeFile(String fileName, String message, boolean isTimestamp) throws IOException {
        Path path = Paths.get(fileName);
        // Create the file if does not exist
        if (!Files.exists(path))
            Files.createFile(path);

        // Check if a timestamp is required and append the data to the file
        if(isTimestamp)
            Files.writeString(path,String.format("%s: %s%n", getCurrentTimestamp(),
                              message), StandardOpenOption.APPEND);
        else
            Files.writeString(path,message, StandardOpenOption.APPEND);
    }

    // The method returns current timestamp in the format of yyyy/MM/dd HH:mm:ss
    private String getCurrentTimestamp(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
