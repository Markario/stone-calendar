package com.markario.calendar;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.Calendar;

/**
 * Created by markzepeda on 6/2/15.
 */
public class CalendarView extends LinearLayout {

    private ViewPager pager;
    private WeeksViewPagerAdapter pagerAdapter;
    private WeeksView dayHeader;
    private DaysAdapter dayHeaderAdapter;

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

    private void init(Context context) {
        View.inflate(context, R.layout.view_calendar, this);
        pager = (ViewPager) findViewById(R.id.viewpager);
        dayHeader = (WeeksView) findViewById(R.id.day_header);
        setOrientation(LinearLayout.VERTICAL);
    }

    public void load(String[] dayLabels, int firstWeekDay, int weeksToDisplay, Calendar calendar, Calendar lastDay) {
        pagerAdapter = new WeeksViewPagerAdapter(5, dayLabels, firstWeekDay, weeksToDisplay, calendar, lastDay);
        pager.setAdapter(pagerAdapter);
        pager.setOverScrollMode(View.OVER_SCROLL_NEVER);

        dayHeader.init(dayLabels);

        for(int i = 0; i < dayLabels.length; i++){
            dayHeader.getAdapter().add(new Day(dayLabels[i]));
        }

        float dayViewHeight = dayHeight();

        ViewGroup.LayoutParams params = dayHeader.getLayoutParams();
        params.height = (int) dayViewHeight;
        dayHeader.setLayoutParams(params);

        params = pager.getLayoutParams();
        params.height = (int) (dayViewHeight * (weeksToDisplay));
        pager.setLayoutParams(params);
    }

    public float dayHeight() {
        Resources res = getResources();
        return res.getDimension(R.dimen.min_touch) + (2 * res.getDimension(R.dimen.margin_quarter));
    }
}
