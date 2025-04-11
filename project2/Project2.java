/**
 * Author: Brendan Laird
 * Project: Project2 - Honor Society Membership Program
 * Date: 09-Mar-2025
 * Description: This is the main driver class that reads student data from an input file, processes the data to create Student objects, computes the GPA threshold for honor society membership, and outputs the students who qualify. The program dynamically sitinguishes between Undergraduate and Graduate students based on the input.
 */

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Project2 {
    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();
        double totalGpa = 0;
        int studentCount = 0;
        final double MAX_GPA = 4.0;

        try {
            File file = new File("students.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim(); // Trim spaces

                // Ensure the line isn't empty
                if (line.isEmpty()) continue;

                // Split into parts
                String[] data = line.split("\\s+");

                // Find the index where numbers start
                int index = 0;
                while (index < data.length && !data[index].matches("\\d+")) {
                    index++; // Move to first number
                }

                // Ensure that after the name, at least three values remain (Credit Hours, Quality Points, Year/Degree)
                if (index == 0 || index + 2 >= data.length) {
                    System.out.println("Invalid input format: " + line);
                    continue;
                }

                // Extract the name (everything before the first number)
                StringBuilder nameBuilder = new StringBuilder();
                for (int i = 0; i < index; i++) {
                    if (i > 0) nameBuilder.append(" ");
                    nameBuilder.append(data[i]);
                }
                String name = nameBuilder.toString();

                // Extract numeric values
                int creditHours;
                int qualityPoints;
                try {
                    creditHours = Integer.parseInt(data[index]);
                    qualityPoints = Integer.parseInt(data[index + 1]);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number format in line: " + line);
                    continue;
                }

                // Extract year or degree
                String lastValue = data[index + 2];

                Student student;

                // Determine if the student is Undergraduate or Graduate
                if (lastValue.equalsIgnoreCase("Freshman") ||
                    lastValue.equalsIgnoreCase("Sophomore") ||
                    lastValue.equalsIgnoreCase("Junior") ||
                    lastValue.equalsIgnoreCase("Senior")) {
                    
                    student = new Undergraduate(name, creditHours, qualityPoints, lastValue);
                } 
                else if (lastValue.equalsIgnoreCase("Masters") || lastValue.equalsIgnoreCase("Doctorate")) {
                    student = new Graduate(name, creditHours, qualityPoints, lastValue);
                } 
                else {
                    System.out.println("Invalid classification for student: " + line);
                    continue;
                }

                // Store student in the ArrayList
                students.add(student);
                totalGpa += student.gpa();
                studentCount++;
            }
            scanner.close();

            // Compute average GPA
            double averageGPA = (studentCount == 0) ? 0.0 : totalGpa / studentCount;

            // Set GPA threshold as midpoint between average and max GPA
            double gpaThreshold = (averageGPA + MAX_GPA) / 2.0;
            Student.setGpaThreshold(gpaThreshold);

            // Print GPA threshold
            System.out.printf("GPA threshold for membership is %.2f%n", gpaThreshold);

            // Print eligible students
            for (Student s : students) {
                if (s.eligibleForHonorSociety()) {
                    System.out.println(s);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
            e.printStackTrace();
        }
    }
}