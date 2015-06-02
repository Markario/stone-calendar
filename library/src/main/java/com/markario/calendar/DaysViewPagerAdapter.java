package com.markario.calendar;

import android.support.v4.util.Pools;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by markzepeda on 6/1/15.
 */
public class DaysViewPagerAdapter extends PagerAdapter {

    private Pools.SynchronizedPool<DayGridRecyclerView> dayGridPool;

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }
}
