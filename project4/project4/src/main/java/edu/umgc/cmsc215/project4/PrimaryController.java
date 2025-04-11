package edu.umgc.cmsc215.project4;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Controller class for handling user interactions in the Time Interval Checker application.
 * Manages input validation, interval comparisons, and time checks.
 * 
 * Author: [Your Name]
 * Date: April 5, 2025
 * Purpose: Handle user interactions and logic for the Time Interval Checker application.
 */
public class PrimaryController {

    @FXML
    private TextField interval1Start;
    @FXML
    private TextField interval1End;
    @FXML
    private TextField interval2Start;
    @FXML
    private TextField interval2End;
    @FXML
    private TextField checkTime;
    @FXML
    private TextArea outputArea;

    /**
     * Handles the action of comparing two time intervals.
     * Validates input, creates Interval objects, and determines their relationship.
     */
    @FXML
    private void handleCompare() {
        try {
            // Parse and validate input times
            Time interval1StartTime = new Time(interval1Start.getText());
            Time interval1EndTime = new Time(interval1End.getText());
            Time interval2StartTime = new Time(interval2Start.getText());
            Time interval2EndTime = new Time(interval2End.getText());

            // Create Interval objects
            Interval<Time> interval1 = new Interval<>(interval1StartTime, interval1EndTime);
            Interval<Time> interval2 = new Interval<>(interval2StartTime, interval2EndTime);

            // Determine relationship between intervals
            String result;
            if (interval1.isSubIntervalOf(interval2)) {
                result = "Interval 1 is a sub-interval of Interval 2.";
            } else if (interval2.isSubIntervalOf(interval1)) {
                result = "Interval 2 is a sub-interval of Interval 1.";
            } else if (interval1.overlapsWith(interval2)) {
                result = "The intervals overlap.";
            } else {
                result = "The intervals are disjoint.";
            }

            // Display result
            outputArea.setText(result);

        } catch (InvalidTime e) {
            outputArea.setText("Invalid time: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            outputArea.setText("Invalid interval: " + e.getMessage());
        }
    }

    /**
     * Handles the action of checking if a specific time falls within the defined intervals.
     * Validates input and determines containment within intervals.
     */
    @FXML
    private void handleCheck() {
        try {
            // Parse and validate input time
            Time timeToCheck = new Time(checkTime.getText());

            // Parse and validate interval times
            Time interval1StartTime = new Time(interval1Start.getText());
            Time interval1EndTime = new Time(interval1End.getText());
            Time interval2StartTime = new Time(interval2Start.getText());
            Time interval2EndTime = new Time(interval2End.getText());

            // Create Interval objects
            Interval<Time> interval1 = new Interval<>(interval1StartTime, interval1EndTime);
            Interval<Time> interval2 = new Interval<>(interval2StartTime, interval2EndTime);

            // Check if the time is within the intervals
            boolean inInterval1 = interval1.contains(timeToCheck);
            boolean inInterval2 = interval2.contains(timeToCheck);

            // Determine and display result
            String result;
            if (inInterval1 && inInterval2) {
                result = "Both intervals contain the time " + timeToCheck + ".";
            } else if (inInterval1) {
                result = "Only Interval 1 contains the time " + timeToCheck + ".";
            } else if (inInterval2) {
                result = "Only Interval 2 contains the time " + timeToCheck + ".";
            } else {
                result = "Neither interval contains the time " + timeToCheck + ".";
            }

            outputArea.setText(result);

        } catch (InvalidTime e) {
            outputArea.setText("Invalid time: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            outputArea.setText("Invalid interval: " + e.getMessage());
        }
    }
}
