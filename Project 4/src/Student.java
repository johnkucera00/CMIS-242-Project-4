/*
* File: Student.java
* Author: John Kucera
* Date: May 7, 2019
* Purpose: This Java program processes the functions to be displayed in the GUI:
* insert, delete, find, and update. In the Update function, the calculating
* of GPA occurs here as well.
*/

// import of necessary java classes
import java.text.DecimalFormat;

public class Student {
    // Instance Variables
    private String name;
    private String major;
    private double totalCredits;
    private double gpa = 4.0;
    private double qualityPts;
    private int gradePts;
    private static DecimalFormat round = new DecimalFormat("#0.00");
    
    // Student Constructor
    public Student(String name, String major) {
        this.name = name;
        this.major = major;
        qualityPts = 0.0;
        totalCredits = 0.0;     
    } // end of constructor
    
    // courseCompleted Method
    public void courseCompleted(String grade, int creditHours) {
        switch (grade) {
            case "A":
                gradePts = 4;
                break;
            case "B":
                gradePts = 3;
                break;
            case "C":
                gradePts = 2;
                break;  
            case "D":
                gradePts = 1;
                break; 
            default:
                gradePts = 0;
                break;    
        } // end of switch  
        // Calculation of GPA and adding onto Total Credits
        gradePts *= creditHours;
        qualityPts += gradePts;
        totalCredits += creditHours;
        gpa = qualityPts/totalCredits;
    } // end of method
    
    // toString method
    @Override
    public String toString() {
        return "\nName: \t" + name + "\nMajor: \t" + major + "\nGPA: \t" + round.format(gpa);
    } // end of method
} // end of class
