
/**
 * Write a description of class simulator here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

/**
 * Gui interface for the WHS Canteen Queue Simulator 
 * Provides a black themed interface with buttons to simulator queue behaviors
 */
public class CanteenGUI extends JFrame { // buttons for user interaction
    private JButton normalBtn, skipBtn, exitBtn;
    // Text area to display simulation output
    private JTextArea outputArea;
    
    /**
     * Constructor to set up the GUI layout, styling and components
     */
    public CanteenGUI() {
        // Set title size close operation andcenter the windows
        setTitle ("WHS Canteen Queue Simulator");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        //Create the top button panel with black background
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout ());
        buttonPanel.setBackground(Color.BLACK);
 
        
        // Initialize button with lables 
        normalBtn = new JButton("Simulate(Staff wait)");
        skipBtn = new JButton("Simulate(Staff Skip)");
        exitBtn = new JButton("Exit");
        
        //Add the output area inside a scroll pane
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(null); //remove default border
         
        //Add action to buttons
        //Button 1 simulate with staff waiting normally
        normalBtn.addActionListener(e -> runSimulation(false));
        //Button 2 simulatte with staff skippign queue
        normalBtn.addActionListener( e-> runSimulation(true));
        //Button  exit the program 
        normalBtn.addActionListener(e -> System.exit(0));
        
    }
    
    /**
     * Style a button with dark bacckgroundand cyan text
     * @param button the JButton to be styled
     */
    private void styleButton(JButton button) {
        button.setBackground(new Color(30, 30, 30));// dark grey
        button.setForeground(Color.CYAN);// cyan text
        button.setFocusPainted(false);// no outline on focus
        button.setFont(new Font("SansSerif", Font.BOLD, 14)); //bold text
        
    /**
     * Runs the simulation and display results in the text area
     * @param staffSkip true if staff should skip the queue , false otherwise 
     */
    private void runSimulation(boolean staffSkip) {
        CanteenSimulation sim = new CanteenSimulator();
        outputArea.setText("");// clear previous output
        try {
            //run simulation with output redirected to GUI text area
            sim.simulateWithOutput(staffSkip, outputArea);
        } catch (IOException ex) {
            // Show error if file reading fails 
            outputArea.setText("Error reading file " + ex.getMessage());
        }    
    }
}
        
    
    
    
 
