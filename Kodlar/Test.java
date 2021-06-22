import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Test {
    public static void main(String[] args)
    {

        try {
            DataBase dataBase = new DataBase();
            dataBase.initialMenu();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}
