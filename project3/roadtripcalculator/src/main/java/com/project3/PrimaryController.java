package com.project3;

import com.project3.TripCost;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Controller for the Road Trip Calculator application.
 * Handles all UI interaction, input validation, and delegates calculations to the TripCost model.
 */
public class PrimaryController {

    @FXML private TextField distanceField;
    @FXML private TextField mpgField;
    @FXML private TextField gasPriceField;
    @FXML private TextField daysField;
    @FXML private TextField hotelField;
    @FXML private TextField foodField;
    @FXML private TextField attractionField;

    @FXML private ComboBox<String> distanceUnitBox;
    @FXML private ComboBox<String> efficiencyUnitBox;

    @FXML private Label resultLabel;
    @FXML private Button calculateButton;

    /**
     * Initializes the UI by setting up default values for unit selection ComboBoxes.
     */
    @FXML
    public void initialize() {
        distanceUnitBox.getItems().addAll("Miles", "Kilometers");
        efficiencyUnitBox.getItems().addAll("MPG", "km/L", "km/gal", "mi/L");

        distanceUnitBox.setValue("Miles");
        efficiencyUnitBox.setValue("MPG");
    }

    /**
     * Handles the calculation logic when the user clicks the Calculate button.
     * Validates input, performs unit conversion, and uses TripCost to compute the total cost.
     */
    @FXML
    private void handleCalculateButton() {
        String distanceText = distanceField.getText().trim();
        String mpgText = mpgField.getText().trim();
        String gasPriceText = gasPriceField.getText().trim();
        String daysText = daysField.getText().trim();
        String hotelText = hotelField.getText().trim();
        String foodText = foodField.getText().trim();
        String attractionText = attractionField.getText().trim();

        if (distanceText.isEmpty() || mpgText.isEmpty() || gasPriceText.isEmpty()
                || daysText.isEmpty() || hotelText.isEmpty()
                || foodText.isEmpty() || attractionText.isEmpty()) {
            resultLabel.setText("Please fill in all fields.");
            return;
        }

        try {
            double rawDistance = Double.parseDouble(distanceText);
            double rawEfficiency = Double.parseDouble(mpgText);
            double gasPrice = Double.parseDouble(gasPriceText);
            int days = Integer.parseInt(daysText);
            double hotelCost = Double.parseDouble(hotelText);
            double foodCost = Double.parseDouble(foodText);
            double attractionCost = Double.parseDouble(attractionText);

            if (rawDistance <= 0 || rawEfficiency <= 0 || gasPrice <= 0 || days <= 0
                    || hotelCost < 0 || foodCost < 0 || attractionCost < 0) {
                resultLabel.setText("Values must be positive numbers.");
                return;
            }

            String distanceUnit = distanceUnitBox.getValue();
            String efficiencyUnit = efficiencyUnitBox.getValue();

            double distance = convertToMiles(rawDistance, distanceUnit);
            double mpg = convertToMPG(rawEfficiency, efficiencyUnit);

            TripCost trip = new TripCost(distance, mpg, gasPrice, days, hotelCost, foodCost, attractionCost);

            resultLabel.setText(String.format(
                "Fuel needed: %.2f gallons%nFuel cost: $%.2f%nHotel: $%.2f%nFood: $%.2f%nAttractions: $%.2f%n%nTotal Trip Cost: $%.2f",
                trip.getGallonsNeeded(), trip.getFuelCost(), trip.getHotelCost(),
                trip.getFoodCost(), trip.getAttractionCost(), trip.getTotalCost()
            ));
        } catch (NumberFormatException ex) {
            resultLabel.setText("Please enter valid numeric values.");
        }
    }

    /**
     * Converts distance to miles based on selected unit.
     * @param value input distance
     * @param unit selected unit ("Miles" or "Kilometers")
     * @return converted distance in miles
     */
    private double convertToMiles(double value, String unit) {
        switch (unit) {
            case "Kilometers":
                return value * 0.621371;
            case "Miles":
            default:
                return value;
        }
    }

    /**
     * Converts fuel efficiency to MPG based on selected unit.
     * @param value input efficiency
     * @param unit selected unit ("MPG", "km/L", "km/gal", or "mi/L")
     * @return converted efficiency in MPG
     */
    private double convertToMPG(double value, String unit) {
        switch (unit) {
            case "km/L":
                return value * 2.35215;
            case "km/gal":
                return value * 0.621371;
            case "mi/L":
                return value * 3.78541;
            case "MPG":
            default:
                return value;
        }
    }
}
