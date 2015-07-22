package com.markario.calendar;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by markzepeda on 6/1/15.
 */
public class WeeksView extends RecyclerView {

    private static final String TAG = WeeksView.class.getSimpleName();

    private GridLayoutManager layoutManager;
    private int numDayColumns;

    private int dayTextAppearanceId;
    private int dayHeaderTextAppearanceId;
    private int monthTextAppearanceId;

    public WeeksView(Context context) {
        this(context, null);
    }

    public WeeksView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, R.attr.calendarViewStyle);
    }

    public WeeksView(Context context, @Nullable AttributeSet attrs, int defStyle) {
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

        setOverScrollMode(View.OVER_SCROLL_NEVER);
    }

    public final void init(String[] daysOfWeek, boolean checkable, DaysAdapter.DayClickListener dayClickListener) {
        this.numDayColumns = daysOfWeek.length;
        layoutManager = new GridLayoutManager(getContext(), numDayColumns);
        setLayoutManager(layoutManager);
        DaysAdapter adapter = new DaysAdapter(checkable, dayClickListener);
        adapter.setDayTextAppearanceId(dayTextAppearanceId);
        adapter.setDayHeaderTextAppearanceId(dayHeaderTextAppearanceId);
        setAdapter(adapter);
    }

    @Override
    public DaysAdapter getAdapter() {
        return (DaysAdapter) super.getAdapter();
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

    SavedState mPendingSavedState;

//    @Override
//    protected Parcelable onSaveInstanceState() {
//        SavedState state = new SavedState(super.onSaveInstanceState());
//        if (mPendingSavedState != null) {
//            state.copyFrom(mPendingSavedState);
//        } else if (mLayout != null) {
//            state.mLayoutState = mLayout.onSaveInstanceState();
//        } else {
//            state.mLayoutState = null;
//        }
//
//        return state;
//    }
//
//    @Override
//    protected void onRestoreInstanceState(Parcelable state) {
//        mPendingSavedState = (SavedState) state;
//        super.onRestoreInstanceState(mPendingSavedState.getSuperState());
//        if (mLayout != null && mPendingSavedState.mLayoutState != null) {
//            mLayout.onRestoreInstanceState(mPendingSavedState.mLayoutState);
//        }
//    }

    static class SavedState extends android.view.View.BaseSavedState {

        Parcelable mLayoutState;

        /**
         * called by CREATOR
         */
        SavedState(Parcel in) {
            super(in);
            mLayoutState = in.readParcelable(LayoutManager.class.getClassLoader());
        }

        /**
         * Called by onSaveInstanceState
         */
        SavedState(Parcelable superState) {
            super(superState);
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeParcelable(mLayoutState, 0);
        }

        private void copyFrom(SavedState other) {
            mLayoutState = other.mLayoutState;
        }

        public static final Parcelable.Creator<SavedState> CREATOR
                = new Parcelable.Creator<SavedState>() {
            @Override
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            @Override
            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
    }
}
