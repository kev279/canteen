
/**
 * Write a description of class gui here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Main simulator class that reads arrivals.csv and runs the queue simulation.
 */
public class CanteenSimulator {
    /**
     * Runs the simulation with or wihtout staff jumping the queue
     * Reads the arrivals file line by line, simulates arrivals and serving
     * 
     * @param staffSkip If tru staff jump the queue; if false staff wait normally
     * @throws IOException if file reading fails
     */
    public void simulate(boolean staffSkip) throws IOException {
        // Load the CSV ffile that contains minute by minute simulation data
        File myFile = new File("arrivals.csv");
        Scanner myReader = new Scanner(myFile);
        
        // Skip the first line of the CSV it usually contains hearder minute,students,staff,served
        if(myReader.hasNextLine()) {
            myReader.nextLine();// Skips the CSV header line
        }
        
        // Create queue manager with max size 10 (for 2m distancing)
        QueueManager queue = new QueueManager(10);
        //Creats stats tracker to record wait times and hungry people
        StatsTracker stats = new StatsTracker();
        
        //Read each minute line and simulate arrivals and serving 
        while (myReader.hasNextLine()) {
            String line = myReader.nextLine();
            String[] parts = line.split(",");// Split the line by commas into parts: [minute,students,staff,serve]
            if (parts.length !=4) continue;// skip malformed lines
            
            int time = Integer.parseInt(parts[0]);// current minute
            int students = Integer.parseInt(parts[1]);// no.of student arrivin
            int staff = Integer.parseInt(parts[2]);// no.of staff
            int served = Integer.parseInt(parts[3]); // no.of people that be served this minute
            
            //Add arriving students to queue
            for (int i = 0; i < students; i++) {
                queue.enqueue(new Person("student", time), staffSkip);// time is used to record when this student arrived
            }
            // Add arriving staff to queu (with or without jump)
            for (int i = 0; i < staff; i++) {
                queue.enqueue(new Person("staff", time),staffSkip);// If staffSkip is true they will get priority  placement
            }
            //Serve people form the queue
            for(int i = 0; i < served; i++) {
                Person p = queue.dequeue();//Dequeue the next person in line (or null if queue is empty)
                if (p != null) {// to check if get tha tperson in queue 
                     int waitTime = time - p.getArrivalTime();// calculate how long they waited in line
                    stats.addWait(p,waitTime);// Record that wait time in the stats tracker
                }
            }
        }
            stats.prinStats(queue.getHungryCount());
    }
}
