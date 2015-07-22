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
    public boolean isChecked = false;
    private DaysAdapter.DayViewHolder dayViewHolder;

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

    public DaysAdapter.DayViewHolder getDayViewHolder() {
        return dayViewHolder;
    }

    public void setDayViewHolder(DaysAdapter.DayViewHolder dayViewHolder) {
        System.out.println("Adding day view holder: "+toString());
        this.dayViewHolder = dayViewHolder;
    }

    public void removeDayViewHolder(){
        System.out.println("Removing day view holder: "+toString());
        if(this.dayViewHolder != null){
            //To avoid accidental recursion.
            DaysAdapter.DayViewHolder old = this.dayViewHolder;
            this.dayViewHolder = null;
            old.removeDay();
        }
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
