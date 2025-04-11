package edu.umgc.cmsc215.project4;

/**
 * A checked exception for handling invalid time input.
 * 
 * Author: Laird, Brendan M.
 * Date: April 5, 2025
 * Purpose: Thrown when invalid time data is passed to the Time class.
 */
public class InvalidTime extends Exception {

    /**
     * Constructs a new InvalidTime exception with the specified detail message.
     *
     * @param message the detail message.
     */
    public InvalidTime(String message) {
        super(message);
    }

    /**
     * Constructs a new InvalidTime exception with the specified detail message and cause.
     *
     * @param message the detail message.
     * @param cause the cause of the exception.
     */
    public InvalidTime(String message, Throwable cause) {
        super(message, cause);
    }
}
