package com.markario.calendar.sample;

import android.app.Activity;
import android.os.Bundle;

import com.markario.calendar.CalendarView;
import com.markario.calendar.Day;
import com.markario.calendar.DayGridRecyclerView;

import java.util.ArrayList;
import java.util.List;


public class TestActivity extends Activity {

    public DayGridRecyclerView dayGridRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        CalendarView calendarView = (CalendarView) findViewById(R.id.calendar);
    }
}

