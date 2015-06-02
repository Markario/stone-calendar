package com.markario.calendar;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by markzepeda on 6/2/15.
 */
public class CalendarView extends LinearLayout {

    private ViewPager pager;
    private DaysViewPagerAdapter pagerAdapter;

    public CalendarView(Context context) {
        this(context, null);
    }

    public CalendarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CalendarView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context){
        View.inflate(context, R.layout.view_calendar, this);

        pager = (ViewPager) findViewById(R.id.viewpager);

        pagerAdapter = new DaysViewPagerAdapter(5);

        pager.setAdapter(pagerAdapter);
    }
}
