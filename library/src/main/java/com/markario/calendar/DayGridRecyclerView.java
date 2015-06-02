package com.markario.calendar;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * Created by markzepeda on 6/1/15.
 */
public class DayGridRecyclerView extends RecyclerView {

    private GridLayoutManager layoutManager;
    private int numDayColumns = 7;

    public DayGridRecyclerView(Context context) {
        this(context, null);
    }

    public DayGridRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DayGridRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public final void init(String[] daysOfWeek) {
        this.numDayColumns = daysOfWeek.length;
        layoutManager = new GridLayoutManager(getContext(), numDayColumns);
        setLayoutManager(layoutManager);
        setAdapter(new DaysRecyclerAdapter(daysOfWeek));
    }

    @Override
    public DaysRecyclerAdapter getAdapter() {
        return (DaysRecyclerAdapter) super.getAdapter();
    }

    /**
     * Created by markzepeda on 6/1/15.
     */
    public class DaysRecyclerAdapter extends ArrayRecyclerAdapter<Day, DaysRecyclerAdapter.DayViewHolder> {

        private static final int TYPE_HEADER = 0;
        private static final int TYPE_ITEM = 1;

        private String[] daysOfWeek;

        public DaysRecyclerAdapter(String[] daysOfWeek) {
            this.daysOfWeek = daysOfWeek;
        }

        @Override
        public int getItemViewType(int position) {
            if (isPositionHeader(position))
                return TYPE_HEADER;

            return TYPE_ITEM;
        }

        private boolean isPositionHeader(int position) {
            return position < daysOfWeek.length;
        }

        @Override
        public int getItemCount() {
            return super.getItemCount() + daysOfWeek.length;
        }

        @Override
        public Day getItem(int position) {
            return super.getItem(position - daysOfWeek.length);
        }

        public String getDayOfWeekString(int position) {
            return daysOfWeek[position % (daysOfWeek.length - 1)];
        }

        @Override
        public DayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            DayView v = (DayView) LayoutInflater.from(parent.getContext()).inflate(R.layout.view_day, parent, false);
            return new DayViewHolder(v);
        }

        @Override
        public void onBindViewHolder(DayViewHolder holder, int position) {
            final DayView view = holder.view;

            if (isPositionHeader(position)) {
                view.setText(getDayOfWeekString(position));
            } else {
                final Day day = getItem(position);
                view.setText(day.dayOfMonth+"");
            }
        }

        public class DayViewHolder extends ViewHolder {
            final DayView view;

            public DayViewHolder(DayView view) {
                super(view);
                this.view = view;
            }
        }
    }
}
