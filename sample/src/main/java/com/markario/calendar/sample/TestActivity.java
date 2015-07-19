package com.markario.calendar.sample;

import android.app.Activity;
import android.os.Bundle;

import com.markario.calendar.CalendarView;
import com.markario.calendar.WeeksView;

import java.util.Calendar;


public class TestActivity extends Activity {

    public WeeksView weeksView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        CalendarView calendarView = (CalendarView) findViewById(R.id.calendar);

        Calendar calendar = Calendar.getInstance();

        Calendar lastDay = Calendar.getInstance();
        lastDay.roll(Calendar.YEAR, 2);

        calendarView.load(new String[]{"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"}, Calendar.SUNDAY, 2, calendar, lastDay);
    }
}

