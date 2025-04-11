package edu.umgc.cmsc215.project4;

/**
 * Represents a specific time in a 12-hour format with AM/PM notation.
 * Provides validation and comparison capabilities.
 * 
 * Author: Laird, Brendan M.
 * Date: April 5, 2025
 * Purpose: Define and validate time inputs for the Time Interval Checker application.
 */
public class Time implements Comparable<Time> {
    private int hour;
    private int minute;
    private String meridian;

    /**
     * Constructs a Time object by parsing a time string in the format "hh:mm AM/PM".
     *
     * @param time A string representing the time (e.g., "10:30 AM").
     * @throws InvalidTime if the time string is null, empty, or has invalid components.
     */
    public Time(String time) throws InvalidTime {
        if (time == null || time.trim().isEmpty()) {
            throw new InvalidTime("Time string cannot be null or empty.");
        }

        String[] parts = time.trim().split("\\s+");
        if (parts.length != 2) {
            throw new InvalidTime("Time must be in the format 'hh:mm AM/PM'.");
        }

        String timePart = parts[0];
        this.meridian = parts[1].toUpperCase();

        if (!meridian.equals("AM") && !meridian.equals("PM")) {
            throw new InvalidTime("Meridian must be 'AM' or 'PM'.");
        }

        String[] timeComponents = timePart.split(":");
        if (timeComponents.length != 2) {
            throw new InvalidTime("Time must include both hour and minute separated by ':'.");
        }

        try {
            this.hour = Integer.parseInt(timeComponents[0]);
            this.minute = Integer.parseInt(timeComponents[1]);
        } catch (NumberFormatException e) {
            throw new InvalidTime("Hour and minute must be valid integers.");
        }

        if (hour < 1 || hour > 12) {
            throw new InvalidTime("Hour must be between 1 and 12.");
        }
        if (minute < 0 || minute > 59) {
            throw new InvalidTime("Minute must be between 0 and 59.");
        }
    }

    /**
     * Compares this Time object with another for ordering.
     *
     * @param other The Time object to be compared.
     * @return A negative integer, zero, or a positive integer as this Time is less than, equal to, or greater than the specified Time.
     */
    @Override
    public int compareTo(Time other) {
        int this24Hour = to24HourFormat(this.hour, this.meridian);
        int other24Hour = to24HourFormat(other.hour, other.meridian);

        if (this24Hour != other24Hour) {
            return Integer.compare(this24Hour, other24Hour);
        }
        return Integer.compare(this.minute, other.minute);
    }

    /**
     * Converts a 12-hour time to a 24-hour format.
     *
     * @param hour The hour in 12-hour format.
     * @param meridian The meridian ("AM" or "PM").
     * @return The hour in 24-hour format.
     */
    private int to24HourFormat(int hour, String meridian) {
        if (meridian.equals("AM")) {
            return (hour == 12) ? 0 : hour;
        } else {
            return (hour == 12) ? 12 : hour + 12;
        }
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d %s", hour, minute, meridian);
    }
}
