package com.markario.calendar;

import android.support.v4.util.Pools;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by markzepeda on 6/1/15.
 */
public class WeeksViewPagerAdapter extends PagerAdapter {

    private static final String TAG = WeeksViewPagerAdapter.class.getSimpleName();

    public static final int MONTH_WEEKS = 0;
    public static final int ONE_WEEK = 1;
    public static final int TWO_WEEKS = 2;

    private Pools.SynchronizedPool<WeeksView> weeksViewPool;

    private int weeksToDisplay;
    private String[] dayLabels;
    int firstWeekDay;
    int daysInWeeksView;
    private Date actualFirstDate;
    private DaysAdapter.DayClickListener dayClickListener;

    List<Day> days = new ArrayList<>();

    public WeeksViewPagerAdapter(int poolSize, String[] dayLabels, int firstWeekDay, int weeksToDisplay, Calendar calendar, Calendar lastDay, Calendar dayToSelect, DaysAdapter.DayClickListener dayClickListener) {
        this.weeksViewPool = new Pools.SynchronizedPool<>(poolSize);
        this.dayLabels = dayLabels;
        this.firstWeekDay = firstWeekDay;
        this.weeksToDisplay = weeksToDisplay;
        this.dayClickListener = dayClickListener;

        while(calendar.get(Calendar.DAY_OF_WEEK) != firstWeekDay){
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            actualFirstDate = calendar.getTime();
        }

        while(calendar.before(lastDay)){
            Day day = new Day(calendar);
            if(dayToSelect != null) {
                if (calendar.get(Calendar.DAY_OF_YEAR) == dayToSelect.get(Calendar.DAY_OF_YEAR) && calendar.get(Calendar.YEAR) == dayToSelect.get(Calendar.YEAR)) {
                    day.isChecked = true;
                }
            }
            days.add(day);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        daysInWeeksView = dayLabels.length * weeksToDisplay;
    }

    @Override
    public int getCount() {
        return days.size()/daysInWeeksView;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        WeeksView view = weeksViewPool.acquire();

        if(view != null){
            Log.i(TAG, "View was recycled from pool");
        }

        if(view == null){
            view = new WeeksView(container.getContext());
            view.init(dayLabels, true, dayClickListener);
        }

        container.addView(view);

        view.getAdapter().clear();

        int first = getFirstDayIndexForViewPosition(position);
        int last = first + daysInWeeksView;
        for(int i = first; i < last; i++){
            view.getAdapter().add(days.get(i));
        }

        return view;
    }

    private int getFirstDayIndexForViewPosition(int position){
        return position * daysInWeeksView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        WeeksView view = (WeeksView) object;
        container.removeView(view);
        weeksViewPool.release(view);
    }

    public int getPositionForDate(Date target){
        int i = 0;
        Calendar start = Calendar.getInstance();
        start.setTime(actualFirstDate);
        Calendar targetCal = Calendar.getInstance();
        targetCal.setTime(target);

        while(start.before(targetCal)){
            start.add(Calendar.DAY_OF_MONTH, 1);
            i++;
        }

        return i;
    }

    public Day getDayForDate(Date target){
        int pos = getPositionForDate(target);
        return days.get(pos);
    }

    public Day getDayForPage(int page){
        Day day = days.get(getFirstDayIndexForViewPosition(page));
        return day;
    }

    public int getPageForDay(Day day){
        int dayIndex = days.indexOf(day);
        return dayIndex/daysInWeeksView;
    }
}
