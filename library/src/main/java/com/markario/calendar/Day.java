package com.markario.calendar;

/**
 * Created by markzepeda on 6/1/15.
 */
public class Day {
    public int month;
    public int year;
    public int dayOfMonth;
    public int dayOfWeek;

    public Day(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public Day(int month, int year, int dayOfMonth, int dayOfWeek) {
        this.month = month;
        this.year = year;
        this.dayOfMonth = dayOfMonth;
        this.dayOfWeek = dayOfWeek;
    }
}
