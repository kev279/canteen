
/**
 * Write a description of class gui here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JTextArea;
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
    
    /**
     * GUI simulation method: reads arrivals.csv and writes output to a JTextArea
     * @param staffskip if true, staff skip the queue
     * @param outputArea JText where output is show in the GUI 
     * @throws IOException if file reading fails
     */
    public void simulateWithOutput(boolean staffSkip, JTextArea outputArea) throws IOException {//Load the input CSV file containing time number of student , staff , and how many get seerved 
        File myFile = new File("arrivals.csv");
        Scanner myReader = new Scanner(myFile);
        
        if (myReader.hasNextLine()) {
            myReader.nextLine(); //Skip header line
        }
        // Initialize queue with a max size of 10 people 
        QueueManager queue = new QueueManager(10); // Create queue with max sixe 10
        //StatsTracker object will t track the total wait times and counts
        StatsTracker stats = new StatsTracker(); //Track wit times and statistics
        // Process each line (representing a time sstep) in the CSV
        while (myReader.hasNextLine()) {
            String line = myReader.nextLine();
            String[] parts = line.split(",");
            //Skip lines that don't have exactly 4 values 
            if (parts.length != 4) continue; // Skip if line isn't correctly formatted
            
            // Parse CSV columns
            int time = Integer. parseInt(parts[0]);// Current minute
            int students = Integer.parseInt(parts[1]);//No.of students arriving 
            int staff = Integer.parseInt(parts[2]);// No.of staff arriving 
            int served = Integer.parseInt(parts[3]);//No.of people to be served this minute
            
            //Enqueue each student into the queue 
            for (int i = 0; i < students; i++) {
                queue.enqueue(new Person("student", time), staffSkip);
            }
            
            // Enqueue each staff member in the queue 
            for (int i = 0; i < staff; i++) {
                queue.enqueue(new Person("staff", time), staffSkip);
            }
            
            //Serve from queue and write results to GUI 
            for (int i = 0; i < served; i++) {
                Person p = queue.dequeue();
                if (p != null) {// id a person is dequeued , calculate their wait time and log it 
                    int waitTime = time - p.getArrivalTime();//Calculate waiting time
                    stats.addWait(p, waitTime);
                    // Display output in the GUI text area 
                    outputArea.append("Minuted " + time + ": " + p.getType() +
                        " served, waited " + waitTime + "minutes\n");
                        
                    
                }
            }
        }   
    }
            
}            
            
 
