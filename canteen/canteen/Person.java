
/**
 * Write a description of class person here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

// Person 
public class Person {
    public String type;       // Student or Staff
    public int arrivalTime;   // minute they arrive

    public Person(String type, int arrivalTime) {
        this.type = type;
        this.arrivalTime = arrivalTime;
    }

    // Getter for type 
    public String getType() {
        return type;
    
    }
    
    //Setter for type 
    public void setType(String type) {
        this.type = type;
    }
    
    // Getter for arrivalTime() {
    public int getArrivalTime() {
        return arrivalTime;
    }
    
    //Setter for arrivalTime
    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}

