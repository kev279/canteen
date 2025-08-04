
/**
 * Write a description of class gvuj here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.Scanner;       
import java.io.File;           
import java.io.IOException;     


public class ReadingFile {
    public static void main(String[] args) {
        // Set up file
        File myFile = new File("arrivals.csv");  // My file name

        try {
            Scanner myReader = new Scanner(myFile); //  Create Scanner for the file

            //  Read each line in the file
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                System.out.println(line); // Print each line
            }

            myReader.close(); // Always close the reader
        } catch (IOException e) { //  Catch error if file not found or can't read
            e.printStackTrace();
        }
    }
}

