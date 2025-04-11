/**
 * Author: Brendan Laird
 * Project: Project2 - Honor Society Membership Program
 * Date: 09-Mar-2025
 * Description: This class extends Student and represents a graduate student. It includes an additional attribute for the degree sought (i.e., Masters or Doctorate) and overrides the eligibility check to require that the student seeking a Masters degree in addition to meetying the GPA threshold.
 */

public class Graduate extends Student {
    private String degreeSought; // Masters or Doctorate

    /**
     * Constructor for Graduate students
     */
    public Graduate(String name, int creditHours, int qualityPoints, String degreeSought) {
        super(name, creditHours, qualityPoints);
        this.degreeSought = degreeSought;
    }

    /**
     * Determines if the graduate student is eligible for the honor society
     */
    @Override
    public boolean eligibleForHonorSociety() {
        // Only Masters students should be eligible
        if (!degreeSought.equalsIgnoreCase("Masters")) {
            return false;  // Automatically reject Doctorate students
        }
        return super.eligibleForHonorSociety();  // Check GPA threshold
    }

    /**
     * Returns a formatted string with student details
     */
    @Override
    public String toString() {
        return super.toString() + " " + degreeSought.toUpperCase();
    }
}
