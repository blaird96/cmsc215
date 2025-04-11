package edu.umgc.cmsc215.project4;

/**
 * A generic, immutable class representing a closed interval [start, end]
 * where T is any type that implements Comparable.
 * 
 * Author: Laird, Brendan M.
 * Date: 05-Apr-2025
 * Purpose: Defines a generic interval with comparison and range functionalities.
 */
public class Interval<T extends Comparable<T>> implements Comparable<Interval<T>> {
    private final T start;
    private final T end;

    /**
     * Constructs an Interval with the specified start and end.
     * Throws IllegalArgumentException if start is after end.
     */
    public Interval(T start, T end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("Start and end cannot be null.");
        }
        if (start.compareTo(end) > 0) {
            throw new IllegalArgumentException("Start must not be after end.");
        }
        this.start = start;
        this.end = end;
    }

    /** @return the start value of the interval */
    public T getStart() {
        return start;
    }

    /** @return the end value of the interval */
    public T getEnd() {
        return end;
    }

    /**
     * Determines if a value is within this interval (inclusive).
     */
    public boolean contains(T value) {
        return value.compareTo(start) >= 0 && value.compareTo(end) <= 0;
    }

    /**
     * Determines if the given interval is fully contained within this interval.
     */
    public boolean isSubIntervalOf(Interval<T> other) {
        return this.start.compareTo(other.start) >= 0 && this.end.compareTo(other.end) <= 0;
    }

    /**
     * Determines if the given interval overlaps with this interval.
     */
    public boolean overlapsWith(Interval<T> other) {
        return !(other.end.compareTo(this.start) < 0 || other.start.compareTo(this.end) > 0);
    }

    /**
     * Compares intervals based on start time, breaking ties with end time.
     */
    @Override
    public int compareTo(Interval<T> other) {
        int startComparison = this.start.compareTo(other.start);
        return (startComparison != 0) ? startComparison : this.end.compareTo(other.end);
    }

    @Override
    public String toString() {
        return "[" + start + " - " + end + "]";
    }
}
