
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
        setSize(700, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.BLACK);// whole frame background
        
        //Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10 ));
        buttonPanel.setBackground(Color.BLACK);
        
        // Initialize button with lables 
        normalBtn = new JButton("Simulate(Staff wait)");
        skipBtn = new JButton("Simulate(Staff Skip)");
        exitBtn = new JButton("Exit");
        
        //Style buttons
        styleButton(normalBtn);
        styleButton(skipBtn);
        styleButton(exitBtn);
        
        //Add button to the panel
        buttonPanel.add(normalBtn);
        buttonPanel.add(skipBtn);
        buttonPanel.add(exitBtn);
        
        //Output area setup
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setBackground(Color.BLACK);
        outputArea.setForeground(Color.WHITE); // white text
        outputArea.setCaretColor(Color.WHITE);
        outputArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        outputArea.setMargin(new Insets(10, 10, 10, 10));
        outputArea.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        
        // Scroll pane for output
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        scrollPane.getViewport().setBackground(Color.BLACK);
        scrollPane.setBackground(Color.BLACK);
        
        // Scrollbar styling
        scrollPane.getVerticalScrollBar().setUI(new javax.swing.plaf.basic.BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(90, 90, 90);
                this.trackColor = Color.BLACK;
            }
        
        
        });
        
        //Add action to buttons
        //Button 1 simulate with staff waiting normally
        normalBtn.addActionListener(e -> runSimulation(false)); //simulate normal 
        //Button 2 simulatte with staff skippign queue
        skipBtn.addActionListener( e-> runSimulation(true));// simulate staff skip
        //Button  exit the program 
        exitBtn.addActionListener(e -> System.exit(0));// exit app
        
        // Add panels to frame
        add(buttonPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        
        // Make GUI visible
        setVisible(true);
        
    }
    
    /**
     * Style a button with dark bacckgroundand cyan text
     * @param button the JButton to be styled
     */
    private void styleButton(JButton button) {
        button.setBackground(new Color(30, 30, 30));// dark grey
        button.setForeground(Color.WHITE);// white text
        button.setFocusPainted(false);// no outline on focus
        button.setFont(new Font("SansSerif", Font.BOLD, 14)); //bold text
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }   
    
    /**
     * Runs the simulation and display results in the text area
     * @param staffSkip true if staff should skip the queue , false otherwise 
     */
    private void runSimulation(boolean staffSkip) {
        CanteenSimulator sim = new CanteenSimulator();
        outputArea.setText("");// clear previous output
        try {
            //run simulation with output redirected to GUI text area
            sim.simulateWithOutput(staffSkip, outputArea);
            
        } catch (IOException ex) {
            // Show error if file reading fails 
            outputArea.setText("Error reading file " + ex.getMessage());
        }    
    }
    
    /**
     * Main meted to launch the GUI 
     */
    public static void main(String[] args) {
        new CanteenGUI();
    }
}
        
    
    
    
 
