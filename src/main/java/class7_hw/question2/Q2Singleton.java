package class7_hw.question2;

/**
 * Class Q2Singleton implements the Singleton design pattern and allows creating of
 * only one instance.
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Q2Singleton {
    private static Q2Singleton instance = null;
    private static Path filePath = Paths.get("log.txt");


    public Q2Singleton() {}

    public static Q2Singleton getInstance(){
        if (instance == null)
            return new Q2Singleton();
        return instance;
    }

    public void writeLog(String sData, boolean isNewFile) throws IOException {
        if (!isNewFile && Files.exists(filePath)){
            Files.writeString(filePath, sData, StandardOpenOption.APPEND);
        } else{
            Files.writeString(filePath, sData);
        }
    }

    public String readLog() throws IOException {
        if (Files.exists(filePath))
            return Files.readString(filePath);
        else
            return (String.format("File '%s' does not exist.", filePath));
    }
}
