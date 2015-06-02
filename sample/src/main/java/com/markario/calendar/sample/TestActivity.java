package com.markario.calendar.sample;

import android.app.Activity;
import android.os.Bundle;

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

        DayGridRecyclerView view = (DayGridRecyclerView) findViewById(R.id.day_grid);

        view.init(new String[]{"S", "M", "T", "W", "TH", "F"});

        List<Day> days = new ArrayList<Day>();

        for(int i = 0; i < 31; i ++){
            days.add(new Day(i+1));
        }

        view.getAdapter().addAll(days);
    }
}

