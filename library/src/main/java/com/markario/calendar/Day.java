package com.markario.calendar;

import java.util.Calendar;

/**
 * Created by markzepeda on 6/1/15.
 */
public class Day {
    public int month;
    public int year;
    public int dayOfMonth;
    public int dayOfWeek;
    public long time;
    public String label;

    public Day(String label) {
        this.label = label;
    }

    public Day(Calendar calendar){
        this.dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        this.dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        this.year = calendar.get(Calendar.YEAR);
        this.month = calendar.get(Calendar.MONTH);
        this.label = dayOfMonth + "";
        time = calendar.getTimeInMillis();
        //System.out.println(toString());
    }

    @Override
    public String toString() {
        return "Day{" +
                "month=" + month +
                ", year=" + year +
                ", dayOfMonth=" + dayOfMonth +
                ", dayOfWeek=" + dayOfWeek +
                ", time=" + time +
                ", label='" + label + '\'' +
                '}';
    }
}
