/**
 * Author: Brendan Laird
 * Project: Project2 - Honor Society Membership Program
 * Date: 09-Mar-2025
 * Description: This class rextends Student and represents an undergraduate student. It includes an additional attribute for the student's year (e.g., Freshmen or Sophmore) and overrides the eligibility check to require Juniors and Seniors to meed the GPA threshold for honor society membership.
 */

public class Undergraduate extends Student {
    private String year;  // Freshman, Sophmore, Junior, Senior

    /**
     * Constructor for Undergraduate students
     */
    public Undergraduate(String name, int creditHours, int qualityPoints, String year) {
        super(name, creditHours, qualityPoints);
        this.year = year;
    }

    /**
     * Determines whether students are eligible for honor society.
     * Must be a Junior or Senior in addition to meeting GPA requirements
     */
    @Override
    public boolean eligibleForHonorSociety() {
        return (year.equalsIgnoreCase("Junior") || year.equalsIgnoreCase("Senior")) && super.eligibleForHonorSociety();
    }

    /**
     * Returns a formatted string with student details
     */
    @Override
    public String toString() {
        return super.toString() + " " + year.toUpperCase();
    }
}
