import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * CanteenGUIx creates a graphical interface for simulating the WHS canteen queue.
 * It allows users to simulate different queue behaviors and toggle between dark/light themes.
 */
public class CanteenGUIx extends JFrame {
    // Buttons for user actions
    private JButton normalBtn, skipBtn, exitBtn, toggleThemeBtn;

    // Text area to display simulation output
    private JTextArea outputArea;

    // Boolean flag to track if current mode is dark
    private boolean isDarkMode = true;

    // Panel that holds buttons (needed for theme switching)
    private JPanel buttonPanel;

    /**
     * Constructor that builds the full GUI layout and logic
     */
    public CanteenGUIx() {
        // Set up window properties
        setTitle("WHS Canteen Queue Simulator");
        setSize(700, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setLayout(new BorderLayout());

       
        // Button Panel (Top of Window)
      
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(Color.BLACK); // initial background

        // Create all buttons
        normalBtn = new JButton("Simulate (Staff Wait)");
        skipBtn = new JButton("Simulate (Staff Skip)");
        exitBtn = new JButton("Exit");
        toggleThemeBtn = new JButton("Switch to Light Mode"); // toggle button

        // Apply custom styling to buttons
        styleButton(normalBtn);
        styleButton(skipBtn);
        styleButton(exitBtn);
        styleButton(toggleThemeBtn);

        // Add all buttons to the top panel
        buttonPanel.add(normalBtn);
        buttonPanel.add(skipBtn);
        buttonPanel.add(exitBtn);
        buttonPanel.add(toggleThemeBtn);

        
        // Output Text Area (Center)
      
        outputArea = new JTextArea();
        outputArea.setEditable(false); // Make output read-only
        outputArea.setFont(new Font("Consolas", Font.PLAIN, 14)); // Monospaced for formatting
        outputArea.setMargin(new Insets(10, 10, 10, 10));
        outputArea.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

        // ScrollPane wraps the output area for scrolling
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        scrollPane.getVerticalScrollBar().setUI(new javax.swing.plaf.basic.BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(90, 90, 90);
                this.trackColor = Color.BLACK;
            }
        });

      
        // Button Actions (Event Listeners)
        
        normalBtn.addActionListener(e -> runSimulation(false));// Staff wait
        skipBtn.addActionListener(e -> runSimulation(true)); // Staff skip
        exitBtn.addActionListener(e -> System.exit(0));  // Close program
        toggleThemeBtn.addActionListener(e -> toggleTheme()); // Switch theme

        
        // Add Panels to Window
        
        add(buttonPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Apply initial theme (dark mode)
        applyTheme();

        // Show the window
        setVisible(true);
    }

    /**
     * Apply custom styling to a given button (font, cursor, no focus, etc.)
     */
    private void styleButton(JButton button) {
        button.setFocusPainted(false);
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    /**
     * Runs the simulation and outputs results in the text area
     * @param staffSkip true if staff skip the queue, false if they wait
     */
    private void runSimulation(boolean staffSkip) {
        CanteenSimulator sim = new CanteenSimulator();
        outputArea.setText(""); // Clear previous output
        try {
            sim.simulateWithOutput(staffSkip, outputArea); // Run and display
        } catch (IOException ex) {
            outputArea.setText("Error reading file: " + ex.getMessage());
        }
    }

    /**
     * Toggles between dark mode and light mode and updates UI
     */
    private void toggleTheme() {
        isDarkMode = !isDarkMode; // Flip boolean flag
        applyTheme();             // Re-apply colors
    }

    /**
     * Applies current theme settings to all components
     */
    private void applyTheme() {
        // Choose colors depending on dark mode or light mode
        Color bg = isDarkMode ? Color.BLACK : Color.WHITE;
        Color fg = isDarkMode ? Color.WHITE : Color.BLACK;
        Color btnBg = isDarkMode ? new Color(30, 30, 30) : Color.LIGHT_GRAY;
        Color borderColor = isDarkMode ? Color.WHITE : Color.BLACK;

        // Update background + text colors
        getContentPane().setBackground(bg);
        buttonPanel.setBackground(bg);
        outputArea.setBackground(bg);
        outputArea.setForeground(fg);
        outputArea.setCaretColor(fg);

        // Update all button colors
        JButton[] buttons = { normalBtn, skipBtn, exitBtn, toggleThemeBtn };
        for (JButton btn : buttons) {
            btn.setBackground(btnBg);
            btn.setForeground(fg);
            btn.setBorder(BorderFactory.createLineBorder(borderColor));
        }

        // Update toggle button text
        toggleThemeBtn.setText(isDarkMode ? "Switch to Light Mode" : "Switch to Dark Mode");
    }

    /**
     * Main method to launch the app
     */
    public static void main(String[] args) {
        new CanteenGUIx();
    }
}

    
    
    
 
