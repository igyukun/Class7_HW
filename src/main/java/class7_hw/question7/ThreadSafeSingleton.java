package class7_hw.question7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ThreadSafeSingleton implements Runnable{

    private static volatile ThreadSafeSingleton instance = null;
    private static final Object mutex = new Object();

    public static ThreadSafeSingleton getInstance(){
        if (instance == null)
            synchronized (mutex) {
                instance = new ThreadSafeSingleton();
            }
        return instance;
    }

    @Override
    public void run() {
        try {
            writeData();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    synchronized public void writeData() throws IOException {
        int timeout = 1000;
        String message;
        String fileName = "Data.txt";

        writeFile(fileName,"New loop",true);

        for (int i = 0; i<5; i++) {
            message = String.format("Iteration #%d | %s%n", i,
                    Thread.currentThread().getName());
            writeFile(fileName, message, false);
            System.out.print(message);
            try {
                Thread.sleep(timeout);
            }catch (InterruptedException e) {
                System.out.format("Interrupt happened.");
            }
        }

    }

    private void writeFile(String fileName, String message, boolean isTimestamp) throws IOException {
        Path path = Paths.get(fileName);
        if (!Files.exists(path))
            Files.createFile(path);
        if(isTimestamp)
            Files.writeString(path,String.format("%s: %s%n", getCurrentTimestamp(),
                              message), StandardOpenOption.APPEND);
        else
            Files.writeString(path,message, StandardOpenOption.APPEND);
    }

    private String getCurrentTimestamp(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
