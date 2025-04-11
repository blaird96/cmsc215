/**
 * Author: Brendan Laird
 * Project: Project2 - Honor Society Membership Program
 * Date: 09-Mar-2025
 * Description: This class represents a student and provides methods for calculating GPA, checking eligibility for the honor society, and setting the GPA threshold. It serves as the base class for Undergraduate and Graduate students.
 */

import java.text.DecimalFormat;

public class Student {
    private String name;
    private int creditHours;
    private int qualityPoints;
    private static double gpaThreshold;

    /**
     * Constructor to initialize student data
     */
    public Student(String name, int creditHours, int qualityPoints) {
        this.name = name;
        this.creditHours = creditHours;
        this.qualityPoints = qualityPoints;
    }

    /**
     * Computes and returns the student's GPA
     */
    public double gpa() {
        return (creditHours == 0) ? 0.0 : (double) qualityPoints / creditHours;
    }

    /**
     * Determines if the student is eligible for the honor society
     */
    public boolean eligibleForHonorSociety() {
        return gpa() >= gpaThreshold;
    }

    /**
     * Returns a string with the student's name and GPA
     */
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");
        return "Name: " + name + " | Credit Hours: " + creditHours +
            " | Quality Points: " + qualityPoints +
            " | GPA: " + df.format(gpa());
    }


    /**
     * Sets the GPA threshold for honor society membership
     */
    public static void setGpaThreshold(double threshold) {
        gpaThreshold = threshold;
    }
}