package com.markario.calendar;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Calendar;

/**
 * Created by markzepeda on 6/1/15.
 */
public class Day implements Parcelable {
    public int month;
    public int year;
    public int dayOfMonth;
    public int dayOfWeek;
    public long time;
    public String label;
    public boolean isChecked = false;
    public boolean clickListenerAdded = false;
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

    protected Day(Parcel in) {
        month = in.readInt();
        year = in.readInt();
        dayOfMonth = in.readInt();
        dayOfWeek = in.readInt();
        time = in.readLong();
        label = in.readString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Day)) return false;

        Day day = (Day) o;

        if (month != day.month) return false;
        if (year != day.year) return false;
        if (dayOfMonth != day.dayOfMonth) return false;
        if (dayOfWeek != day.dayOfWeek) return false;
        return !(label != null ? !label.equals(day.label) : day.label != null);

    }

    @Override
    public int hashCode() {
        int result = month;
        result = 31 * result + year;
        result = 31 * result + dayOfMonth;
        result = 31 * result + dayOfWeek;
        result = 31 * result + (label != null ? label.hashCode() : 0);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(month);
        dest.writeInt(year);
        dest.writeInt(dayOfMonth);
        dest.writeInt(dayOfWeek);
        dest.writeLong(time);
        dest.writeString(label);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Day> CREATOR = new Parcelable.Creator<Day>() {
        @Override
        public Day createFromParcel(Parcel in) {
            return new Day(in);
        }

        @Override
        public Day[] newArray(int size) {
            return new Day[size];
        }
    };
}