
/**
 * Write a description of class bbujn here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.Scanner;       
import java.io.File;            
import java.io.IOException;     

public class ReadingFile2 {    
    public static void main(String[] args) {    

        //  Create a File object pointing to "arrivals.csv" 
        File myFile = new File("arrivals.csv");

        try {
            //  Create a Scanner object to read the file
            Scanner myReader = new Scanner(myFile);

            //  Skip the first line (header line with column names)
            if (myReader.hasNextLine()) {
                myReader.nextLine();  // This line is not data, just the column titles
            }

            //  Loop through each remaining line in the file
            while (myReader.hasNextLine()) {
                // Read one line of data from the file
                String line = myReader.nextLine();

                //  Split the line using tab as the separator
                String[] parts = line.split(",");

                //  Check that we actually got 4 values in this line
                if (parts.length == 4) {
                    //  Convert string values to integers
                    int time = Integer.parseInt(parts[0]);       // Time column
                    int students = Integer.parseInt(parts[1]);   // Students column
                    int staff = Integer.parseInt(parts[2]);      // Staff column
                    int served = Integer.parseInt(parts[3]);     // Served column

                    //  Print the formatted result to the console
                    System.out.println("Time " + time + ": Students = " + students
                            + ", Staff = " + staff + ", Served = " + served);
                }
            }

            //  Close the Scanner to free system resources
            myReader.close();

        } catch (IOException e) {
            //  If something goes wrong (like file not found), show the error
            e.printStackTrace();
        }
    }
}

