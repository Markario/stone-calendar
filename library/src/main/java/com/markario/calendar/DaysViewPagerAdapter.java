package com.markario.calendar;

import android.support.v4.util.Pools;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by markzepeda on 6/1/15.
 */
public class DaysViewPagerAdapter extends PagerAdapter {

    private static final String TAG = DaysViewPagerAdapter.class.getSimpleName();

    private Pools.SynchronizedPool<DayGridRecyclerView> dayGridPool;

    public static enum Mode{
        WEEK, MONTH
    }

    List<Day> days = new ArrayList<Day>();

    public DaysViewPagerAdapter(int poolSize) {
        this.dayGridPool = new Pools.SynchronizedPool<>(poolSize);

        for(int i = 0; i < 31; i ++){
            days.add(new Day(i+1));
        }
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        DayGridRecyclerView view = dayGridPool.acquire();

        if(view != null){
            Log.i(TAG, "View was recycled from pool");
        }

        if(view == null){
            view = new DayGridRecyclerView(container.getContext());
        }
        container.addView(view);
        view.init(new String[]{"S", "M", "T", "W", "TH", "F", "S"});
        view.getAdapter().clear();
        view.getAdapter().addAll(days);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        DayGridRecyclerView view = (DayGridRecyclerView) object;
        container.removeView(view);
        dayGridPool.release(view);
    }
}
