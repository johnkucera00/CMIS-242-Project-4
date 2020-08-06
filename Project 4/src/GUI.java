/*
* File: GUI.java
* Author: John Kucera
* Date: May 7, 2019
* Purpose: This Java program creates a GUI for a student database. It utilizes
* a HashMap to hold data for students with details including ID, Name,
* Major, and GPA. There are functions for the user to use including
* Inserting a student, Deleting a student, Finding a Student, and Updating
* a student's information.
*/

// import of necessary java classes
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.HashMap;

public class GUI extends JFrame {
    // Instance Variables & Hashmap
    private String id;
    private String name;
    private String major;
    private String select;
    HashMap<String, Student> database = new HashMap<>();
    
    // Window Components
    private static JLabel idLbl = new JLabel ("Id:");
    private static JLabel nameLbl = new JLabel ("Name:");
    private static JLabel majorLbl = new JLabel ("Major:");
    private static JLabel selectLbl = new JLabel ("Choose Selection:");
    private static JTextField idTxt = new JTextField();
    private static JTextField nameTxt = new JTextField();
    private static JTextField majorTxt = new JTextField();
    private static JButton processBtn = new JButton("Process Request");
    private static String[] selectOptions = { "Insert", "Delete", "Find", "Update" };
    private static JComboBox<String> selectCmb = new JComboBox<>(selectOptions);
    private static String[] gradeOptions = { "A", "B", "C", "D", "F" };
    private static String[] creditOptions = { "3", "6" };
    
    // Clear input box
    public void clearInput() {
        idTxt.setText("");
        nameTxt.setText("");
        majorTxt.setText("");
    }
    
    // Process Action Listeners
    class ProcessBtnListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Text Fields -> Listener
            id = idTxt.getText();
            name = nameTxt.getText();
            major = majorTxt.getText();
            select = selectCmb.getSelectedItem().toString();
            try {
                if (id.isEmpty()) { // Handling empty ID Text Field
                    throw new NullPointerException();
                }
                else {
                    switch (select) {
                        case "Insert": // Insert
                            if (database.containsKey(id)) {
                                JOptionPane.showMessageDialog(null, "This student ID already exists in the database. Please try again.");
                                clearInput();
                            }
                            else {
                                database.put(id, new Student(name, major));
                                JOptionPane.showMessageDialog(null, "Success! This student has been added to the database.");
                                clearInput();
                            }
                            break;
                        case "Delete": // Delete
                            if (!database.containsKey(id)) {
                                JOptionPane.showMessageDialog(null, "This ID does not exist in the database. Please try again.");
                                clearInput();
                            }
                            else {
                                database.remove(id);
                                JOptionPane.showMessageDialog(null, "Success! This student has been removed from the database.");
                                clearInput();
                            }
                            break;
                        case "Find": // Find
                            if (!database.containsKey(id)) {
                                JOptionPane.showMessageDialog(null, "This ID does not exist in the database. Please try again.");
                                clearInput();
                            }
                            else {
                                String toString = database.get(id).toString();
                                JOptionPane.showMessageDialog(null, "Success! This student is in the database.\nId: " + id + toString);
                                clearInput();
                            }
                            break;
                        case "Update": // Update
                            if (!database.containsKey(id)) {
                                JOptionPane.showMessageDialog(null, "This ID does not exist in the database. Please try again.");
                                clearInput();
                            }
                            else { // Valid ID
                                String grade = (String)JOptionPane.showInputDialog(null, "Choose grade:","",JOptionPane.QUESTION_MESSAGE,null,gradeOptions,gradeOptions[0]);
                                if (grade != null) { // Valid Grade
                                    String creditHours = (String)JOptionPane.showInputDialog(null, "Choose credits:","",JOptionPane.QUESTION_MESSAGE,null,creditOptions,creditOptions[0]);
                                    if (creditHours != null) { // Valid Credit
                                        database.get(id).courseCompleted(grade, Integer.parseInt(creditHours));
                                        JOptionPane.showMessageDialog(null, "Success! This student's record has updated.");
                                        clearInput();
                                    }
                                    else {
                                        JOptionPane.showMessageDialog(null, "You have canceled the Update.");
                                        clearInput();
                                    }
                                }
                                else {
                                    JOptionPane.showMessageDialog(null, "You have canceled the Update.");
                                    clearInput();
                                }
                            }
                            break;
                    } // End of Switch
                } // End of Outer Else
            } // End of try
            catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(null, "A valid ID must be entered. Please try again.");
                clearInput();
            } // End of catch
        } // End of Method
    } // End of Listener
    
    // Gui Creation
    public GUI() {
        // Frame Creation
        super("John's Student Database"); // Titling frame
        setSize(250, 180);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints layout = new GridBagConstraints();
        layout.fill = GridBagConstraints.HORIZONTAL;
        
        // Labels
        layout.gridx = 0; // ID Label
        layout.gridy = 0;
        add(idLbl, layout);
        
        layout.gridx = 0; // Name Label
        layout.gridy = 1;
        add(nameLbl, layout);
        
        layout.gridx = 0; // Major Label
        layout.gridy = 2;
        add(majorLbl, layout);
        
        layout.gridx = 0; // Select Label
        layout.gridy = 3;
        add(selectLbl, layout);
        
        // Process Button
        layout.gridx = 0;
        layout.gridy = 4;
        add(processBtn, layout);
        processBtn.addActionListener(new ProcessBtnListener());
        
        // Text Fields
        layout.gridx = 1; // ID Text Field
        layout.gridy = 0;
        add(idTxt, layout);
        
        layout.gridx = 1; // Name text Field
        layout.gridy = 1;
        add(nameTxt, layout);
        
        layout.gridx = 1; // Major Text Fied
        layout.gridy = 2;
        add(majorTxt, layout);
        
        // Combo Box
        layout.gridx = 1;
        layout.gridy = 3;
        add(selectCmb, layout);
    }
    
    // Main method
    public static void main(String[] args) {
        GUI gui = new GUI();
    } // end of main method    
} // end of class
