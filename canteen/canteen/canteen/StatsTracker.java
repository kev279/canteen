
/**
 * Write a description of class hj here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.ArrayList;
//Tracks wait times for students and staff and counts hungry people
public class StatsTracker {
    private ArrayList<Integer> studentWaits = new ArrayList<>();// Stores wait times for each student who got served
    private ArrayList<Integer> staffWaits = new ArrayList<>();// Stores wait times for each staff member who got served
    
    /**
     * Add a wait time for served perosn
     * @param p Person served 
     * @param waitTime Time waited in queue (min)
     */
    public void addWait(Person p, int waitTime) {
        if(p.getType().equals("student")) {// Check if the peron is a student or staff and add their wait time to correct list
            studentWaits.add(waitTime);
        } else {
            staffWaits.add(waitTime);
        
        }
    }
    
    /**
     * Prints average wait times and number of hungry people 
     * @param hungry Number of people who left becuase queue was full
     */
    public void prinStats(int hungry) {
        System.out.println("Mean student wait: " + mean(studentWaits));
        System.out.println("Mean staff wait: " + mean(staffWaits));
        System.out.println("Hungry people (queue full): " + hungry);
    }
    /**
     * Returns formatted statistics text for GUI display
     * @param hungry Number of people who left because queue was full
     * @return Formatted statistics string
     */
    public String getStatsText(int hungry) {
        return "Mean student wait:" + mean(studentWaits) + "\n" + // show average student wiat time
               "Mean staff wait:" + mean(staffWaits) + "\n" + // shwo total staff wait time
               "Hungry people (queue full): " + hungry + "\n"; // Show total number of people who could't join the queue
    }
    /**
     * @param list List of wait times
     * @return Average wait time, or 00 if empty
     */
    private double mean(ArrayList<Integer> list) {
        if (list.isEmpty()) return 0;// Return 0 if no people were served list is empty
        int sum = 0;// Add all wait times together
        for (int i : list) sum += i;
        return (double) sum / list.size();// Return the average by dividing the total by the number of people
    }
}
  