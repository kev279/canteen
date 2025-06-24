
/**
 * Write a description of class Simulator here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.LinkedList;
import java.util.Queue;

public class Simulator {
    private Queue<Person> queue;  // Queue to hold people waiting in line
    private int currentTime;      // Track current minute of simulation

    // Constructor to initialize the queue and time
    public Simulator() {
        queue = new LinkedList<>();  // LinkedList implements Queue
        currentTime = 0;             // Start at minute 0
    }
    //Method runs the simulation
    public void run() {
        //Run simulation for 10 min(can increase later)
        for (currentTime = 0; currentTime < 10 ; currentTime++){
            // Each minute , these people arrive:
            queue.add(new Person("Student", currentTime));//1st  student  arrives
            quene.add(new Person("Student", currentTime)):// 2nd 
            
            
            
        }
    }
 