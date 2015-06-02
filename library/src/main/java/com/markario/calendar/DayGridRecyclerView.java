package com.markario.calendar;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by markzepeda on 6/1/15.
 */
public class DayGridRecyclerView extends RecyclerView {

    private static final String TAG = DayGridRecyclerView.class.getSimpleName();

    private GridLayoutManager layoutManager;
    private int numDayColumns = 7;

    private int dayTextAppearanceId;
    private int dayHeaderTextAppearanceId;
    private int monthTextAppearanceId;

    public DayGridRecyclerView(Context context) {
        this(context, null);
    }

    public DayGridRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, R.attr.calendarViewStyle);
    }

    public DayGridRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        TypedArray a = null;

        try {
            a = getContext().obtainStyledAttributes(attrs, R.styleable.CalendarView, defStyle, R.style.BaseCalendarStyle);

            setDayTextAppearanceId(a.getResourceId(
                    R.styleable.CalendarView_dayTextAppearance,
                    R.style.TextAppearance_AppCompat_Small_Day
            ));

            setDayHeaderTextAppearanceId(a.getResourceId(
                    R.styleable.CalendarView_dayHeaderTextAppearance,
                    R.style.TextAppearance_AppCompat_Small_DayHeader
            ));

            setMonthTextAppearanceId(a.getResourceId(
                    R.styleable.CalendarView_monthTextAppearance,
                    R.style.TextAppearance_AppCompat_Large_Month
            ));
        } catch (Exception e) {
        } finally {
            if (a != null)
                a.recycle();
        }
    }

    public void setDayTextAppearanceId(int dayTextAppearanceId) {
        this.dayTextAppearanceId = dayTextAppearanceId;
    }

    public void setDayHeaderTextAppearanceId(int dayHeaderTextAppearanceId) {
        this.dayHeaderTextAppearanceId = dayHeaderTextAppearanceId;
    }

    public void setMonthTextAppearanceId(int monthTextAppearanceId) {
        this.monthTextAppearanceId = monthTextAppearanceId;
    }

    public final void init(String[] daysOfWeek) {
        this.numDayColumns = daysOfWeek.length;
        layoutManager = new GridLayoutManager(getContext(), numDayColumns);
        setLayoutManager(layoutManager);
        DaysRecyclerAdapter adapter = new DaysRecyclerAdapter(daysOfWeek);
        adapter.setDayTextAppearanceId(dayTextAppearanceId);
        adapter.setDayHeaderTextAppearanceId(dayHeaderTextAppearanceId);
        setAdapter(adapter);
    }

    @Override
    public DaysRecyclerAdapter getAdapter() {
        return (DaysRecyclerAdapter) super.getAdapter();
    }

    /**
     * Created by markzepeda on 6/1/15.
     */
    public class DaysRecyclerAdapter extends ArrayRecyclerAdapter<Day, DaysRecyclerAdapter.DayViewHolder> {

        private final String TAG = DaysRecyclerAdapter.class.getSimpleName();

        private static final int TYPE_HEADER = 0;
        private static final int TYPE_ITEM = 1;

        private String[] daysOfWeek;

        private int dayTextAppearanceId;
        private int dayHeaderTextAppearanceId;

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
            Context context = parent.getContext();
            DayViewHolder vh = new DayViewHolder(LayoutInflater.from(context).inflate(R.layout.view_day, parent, false));

            if (viewType == TYPE_HEADER) {
                vh.view.setTextAppearance(context, dayHeaderTextAppearanceId);
                vh.view.setClickable(false);
            } else if (viewType == TYPE_ITEM) {
                vh.view.setTextAppearance(context, dayTextAppearanceId);
            }

            return vh;
        }

        @Override
        public void onBindViewHolder(DayViewHolder holder, int position) {
            final DayView view = holder.view;

            view.setChecked(false);

            if (isPositionHeader(position)) {
                view.setText(getDayOfWeekString(position));
            } else {
                final Day day = getItem(position);
                view.setText(day.dayOfMonth + "");
                view.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        view.setChecked(true);
                        Log.i(TAG, "Clicked: " + day.dayOfMonth);
                    }
                });
            }
        }

        public class DayViewHolder extends ViewHolder {
            final DayView view;

            public DayViewHolder(View view) {
                super(view);
                this.view = (DayView) view.findViewById(R.id.dayview);
            }
        }

        public void setDayTextAppearanceId(int dayTextAppearanceId) {
            this.dayTextAppearanceId = dayTextAppearanceId;
        }

        public void setDayHeaderTextAppearanceId(int dayHeaderTextAppearanceId) {
            this.dayHeaderTextAppearanceId = dayHeaderTextAppearanceId;
        }
    }
}
