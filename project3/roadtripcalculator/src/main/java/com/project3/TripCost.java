package com.project3;

/**
 * TripCost is a data model class for calculating total road trip costs.
 * It includes fuel, lodging, food, and attraction costs.
 */
public class TripCost {
    private double distanceMiles;
    private double mpg;
    private double gasPrice;
    private int days;
    private double hotelPerNight;
    private double foodPerDay;
    private double attractionCost;

    /**
     * Constructor for TripCost.
     * @param distanceMiles Total trip distance in miles.
     * @param mpg Fuel efficiency in miles per gallon.
     * @param gasPrice Gas price per gallon.
     * @param days Number of days for the trip.
     * @param hotelPerNight Hotel cost per night.
     * @param foodPerDay Food cost per day.
     * @param attractionCost Total cost of attractions.
     */
    public TripCost(double distanceMiles, double mpg, double gasPrice,
                    int days, double hotelPerNight, double foodPerDay, double attractionCost) {
        this.distanceMiles = distanceMiles;
        this.mpg = mpg;
        this.gasPrice = gasPrice;
        this.days = days;
        this.hotelPerNight = hotelPerNight;
        this.foodPerDay = foodPerDay;
        this.attractionCost = attractionCost;
    }
    /**
     * Calculates total gallons needed based on distance and fuel economy.
     * @return total gallons needed.
     */
    public double getGallonsNeeded() {
        return distanceMiles / mpg;
    }

    /**
     * @return Total fuel cost based on distance and mpg.
     */
    public double getFuelCost() {
        return getGallonsNeeded() * gasPrice;
    }

    /**
     * @return Total hotel cost based on number of nights.
     */
    public double getHotelCost() {
        return days * hotelPerNight;
    }

    /**
     * @return Total food cost based on number of days.
     */
    public double getFoodCost() {
        return days * foodPerDay;
    }

     /**
     * @return Total attraction cost.
     */
    public double getAttractionCost() {
        return attractionCost;
    }

    /**
     * @return The total estimated cost of the road trip.
     */
    public double getTotalCost() {
        return getFuelCost() + getHotelCost() + getFoodCost() + getAttractionCost();
    }
}
