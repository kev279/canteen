
/**
 * Write a description of class bi here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.Scanner;

/**
 * Simple text menu interface to run the canteen simulator in console
 */
public class TextMenu {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in); // Create a Scanner objects to read user input from the console
        CanteenSimulator sim = new CanteenSimulator();// Creat new conteensimulator object to run the simulation 
        
        while (true) { // loop foreve until choose to exit
            System.out.println("\n=== WHS Canteen Queue Simulator ===");
            System.out.println("1. Simulate with staff waiting in queue");
            System.out.println("2. Simulate with staff jumping queue");
            System.out.println("3. Exit");
            System.out.println("Choose option: ");
            
            //Read the mene choice
            int choice = sc.nextInt();
            
            // Option 1: Run simulation where staff ait like students
            if (choice == 1) {
                sim.simulate(false);// false= no staff skip
            }
            // Option 2 : Run simulation where staff jump the queue
            else if (choice == 2 ) {
                sim.simulate(true); //true = staff skip
            }
            
            // Option 3 : Exit the program 
            else{
                System.out.println("Goodbye");
                break; //Exit the loop
            }
        }
         
        // close the scanner to clean up resources
        sc.close();
    }
}
