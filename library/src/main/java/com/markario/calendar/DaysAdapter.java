package com.markario.calendar;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by markzepeda on 6/1/15.
 */
public class DaysAdapter extends ArrayRecyclerAdapter<Day, DaysAdapter.DayViewHolder> {

    private final String TAG = DaysAdapter.class.getSimpleName();

    private int dayTextAppearanceId;
    private int dayHeaderTextAppearanceId;
    private boolean checkable = false;
    private DayClickListener dayClickListener;

    public DaysAdapter(boolean checkable, DayClickListener dayClickListener) {
        this.checkable = checkable;
        this.dayClickListener = dayClickListener;
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    @Override
    public DayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        DayViewHolder vh = new DayViewHolder(LayoutInflater.from(context).inflate(R.layout.view_day, parent, false));
        vh.view.setTextAppearance(context, dayTextAppearanceId);
        return vh;
    }

    @Override
    public void onViewRecycled(DayViewHolder holder) {
        super.onViewRecycled(holder);
        holder.removeDay();
    }

    @Override
    public void onBindViewHolder(DayViewHolder holder, int position) {
        final DayView view = holder.view;

        view.setChecked(false);

        final Day day = getItem(position);
        view.setText(day.label);
        holder.setDay(day);
        day.setDayViewHolder(holder);

        if(checkable) {
            view.setChecked(day.isChecked);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    view.setChecked(true);
                    day.isChecked = true;
                    if (dayClickListener != null) {
                        dayClickListener.onDayClicked(day, view);
                    }
                    Log.i(TAG, "Clicked: " + day.toString());
                }
            });
        } else {
            view.setEnabled(false);
        }
    }

    public static class DayViewHolder extends RecyclerView.ViewHolder {
        final DayView view;
        private Day day;

        public DayViewHolder(View view) {
            super(view);
            this.view = (DayView) view.findViewById(R.id.dayview);
        }

        public void setDay(Day day){
            if(this.day != null && this.day != day){
                this.day.removeDayViewHolder();
            }
            this.day = day;
        }

        public void setChecked(boolean checked, Day day){
            if(view != null && this.day == day){
                view.setChecked(checked);
            }
        }

        public void removeDay(){
            if(day != null) {
                //To avoid accidental recursion.
                Day old = day;
                day = null;
                old.removeDayViewHolder();
            }
        }
    }

    public void setDayTextAppearanceId(int dayTextAppearanceId) {
        this.dayTextAppearanceId = dayTextAppearanceId;
    }

    public void setDayHeaderTextAppearanceId(int dayHeaderTextAppearanceId) {
        this.dayHeaderTextAppearanceId = dayHeaderTextAppearanceId;
    }

    public interface DayClickListener{
        public void onDayClicked(Day day, DayView view);
    }
}
