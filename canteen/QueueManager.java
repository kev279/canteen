
/**
 * Write a description of class nn here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.LinkedList;

/**
 * Manages the queue of people waiting in the canteen
 * Supports staff queue jumping and limts queue size for social distancing
 */
public class QueueManager {
    private LinkedList<Person> queue = new LinkedList<>();
    private int maxSize; // Maximum allowed queue size ( for 2m distancing)
    private int hungryCount = 0; //Number of people who leftbecuase queue was full
    
    /**
     * Constructor sets the maximum queue size allowed
     * @param maxSize Maximum number of people allowed in queue
     */
    public QueueManager(int maxSize) {
        this.maxSize = maxSize;// Assign the given max size value to the instance variable
    }
    
    /**
     * Adds a person to the queue
     * If staffSkip is true , staff jump after the first person
     * If the queue is full , the person leaves hungry (not added)
     * 
     * @param p The person joining the queue
     * @param staffSkip If true , staff can jump the queue
     */
    public void enqueue(Person p, boolean staffSkip) {
        if (queue.size() >= maxSize) {// Check fi the queue is already full ( due to 2m distancing limit)
            hungryCount++; // Queue full, person leaves hungry
            return;
        }
        
        if (staffSkip && p.getType().equals("staff") && queue.size() > 0) {// If staff skipping eneable  and the person is a staff member
            queue.add(1, p); //Staff jump after first person
        } else {
            queue.add(p); // Normal enqueue at end
        }
    }
    
    /**
     * Removes and returns the first person in the queue when served
     * @return The person served or null if queue empty 
     */
    public Person dequeue() { 
        if(!queue.isEmpty()) { // Remove and return the person at the front of the queue
            return queue.removeFirst();
        }
        return null;// If the queue is empty return null no one to serve 
        
    }
    
    /**
     * Returns how many people left hungry due to full queue
     * @return Number of hungry peopl
     */
    public int getHungryCount() {
        return hungryCount;// Gets the number of people who couldn't join the queue 
    }
}