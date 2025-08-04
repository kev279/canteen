
/**
 * Write a description of class vf here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
// Import the Swing library for GUI components (buttons, panels, text area, etc.)
import javax.swing.*;

// Import AWT for GUI layout, colors, fonts, and event handling
import java.awt.*;

// Import for exception handling related to input/output operations
import java.io.IOException;

/**
 * CanteenGUIx creates a graphical user interface for the WHS Canteen Queue Simulator.
 * It allows users to simulate queue behavior where staff either wait or skip,
 * and provides a toggle to switch between dark and light themes for accessibility.
 */
public class CanteenGUIx extends JFrame {

    // Buttons to run simulations or exit the program
    private JButton normalBtn, skipBtn, exitBtn, toggleThemeBtn;

    // Text area to display simulation results
    private JTextArea outputArea;

    // Flag to track whether the current theme is dark mode
    private boolean isDarkMode = true;

    // Panel that holds all the control buttons
    private JPanel buttonPanel;

    /**
     * Constructor sets up the full GUI layout, logic, and theme
     */
    public CanteenGUIx() {
        // Set the title and size of the main window
        setTitle("WHS Canteen Queue Simulator");
        setSize(700, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setLayout(new BorderLayout()); // Use border layout for regions

        // Initialize the button panel at the top of the window
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(Color.BLACK); // Initial theme = dark

        // Create buttons for simulating staff wait or skip behavior
        normalBtn = new JButton("Simulate (Staff Wait)");
        skipBtn = new JButton("Simulate (Staff Skip)");
        exitBtn = new JButton("Exit");
        toggleThemeBtn = new JButton("Switch to Light Mode"); // Toggles UI theme

        // Style all buttons with custom fonts, borders, etc.
        styleButton(normalBtn);
        styleButton(skipBtn);
        styleButton(exitBtn);
        styleButton(toggleThemeBtn);

        // Add all the buttons to the top panel
        buttonPanel.add(normalBtn);
        buttonPanel.add(skipBtn);
        buttonPanel.add(exitBtn);
        buttonPanel.add(toggleThemeBtn);

        // Initialize the output area (center of window)
        outputArea = new JTextArea();
        outputArea.setEditable(false); // Make text read-only
        outputArea.setFont(new Font("Consolas", Font.PLAIN, 14)); // Use monospaced font for clean layout
        outputArea.setMargin(new Insets(10, 10, 10, 10)); // Padding inside
        outputArea.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY)); // Border around text area

        // Scroll pane wraps the text area to allow scrolling when output is long
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        // Custom dark scrollbar colors
        scrollPane.getVerticalScrollBar().setUI(new javax.swing.plaf.basic.BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(90, 90, 90);  // Grey scrollbar thumb
                this.trackColor = Color.BLACK;           // Black track
            }
        });

        // Event listeners for each button
        normalBtn.addActionListener(e -> runSimulation(false));      // Staff wait normally
        skipBtn.addActionListener(e -> runSimulation(true));         // Staff skip the queue
        exitBtn.addActionListener(e -> System.exit(0));              // Exit program
        toggleThemeBtn.addActionListener(e -> toggleTheme());        // Switch UI theme

        // Add panels to frame: buttons on top, output in center
        add(buttonPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Apply the initial theme (dark mode by default)
        applyTheme();

        // Make the window visible
        setVisible(true);
    }

    /**
     * Custom styling for each button: font, colors, borders, hover effect
     * @param button the JButton to style
     */
    private void styleButton(JButton button) {
        button.setFocusPainted(false);                                 // Remove focus border
        button.setFont(new Font("SansSerif", Font.BOLD, 14));          // Bold font
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));              // Hand cursor on hover
    }

    /**
     * Runs the queue simulation and displays results in the output area
     * @param staffSkip true if staff skip the queue, false if they wait
     */
    private void runSimulation(boolean staffSkip) {
        CanteenSimulator sim = new CanteenSimulator(); // Create simulation instance
        outputArea.setText(""); // Clear old output before new run
        try {
            sim.simulateWithOutput(staffSkip, outputArea); // Run and print results to GUI
        } catch (IOException ex) {
            outputArea.setText("Error reading file: " + ex.getMessage()); // Display error
        }
    }

    /**
     * Switches between dark mode and light mode when the toggle button is clicked
     */
    private void toggleTheme() {
        isDarkMode = !isDarkMode; // Flip the current theme state
        applyTheme();             // Apply updated colors
    }

    /**
     * Applies the selected theme (dark or light) to all components
     */
    private void applyTheme() {
        // Select colors based on the current mode
        Color bg = isDarkMode ? Color.BLACK : Color.WHITE;
        Color fg = isDarkMode ? Color.WHITE : Color.BLACK;
        Color btnBg = isDarkMode ? new Color(30, 30, 30) : Color.LIGHT_GRAY;
        Color borderColor = isDarkMode ? Color.WHITE : Color.BLACK;

        // Apply background and text colors to main UI elements
        getContentPane().setBackground(bg);
        buttonPanel.setBackground(bg);
        outputArea.setBackground(bg);
        outputArea.setForeground(fg);
        outputArea.setCaretColor(fg);

        // Loop through buttons and update styling for theme
        JButton[] buttons = { normalBtn, skipBtn, exitBtn, toggleThemeBtn };
        for (JButton btn : buttons) {
            btn.setBackground(btnBg);
            btn.setForeground(fg);
            btn.setBorder(BorderFactory.createLineBorder(borderColor));
        }

        // Update label of the toggle button
        toggleThemeBtn.setText(isDarkMode ? "Switch to Light Mode" : "Switch to Dark Mode");
    }

    /**
     * Main method that launches the GUI application
     */
    public static void main(String[] args) {
        new CanteenGUIx(); // Create and show GUI
    }
}


