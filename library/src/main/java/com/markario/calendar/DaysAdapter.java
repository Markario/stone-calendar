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
    public void onBindViewHolder(DayViewHolder holder, int position) {
        final DayView view = holder.view;

        view.setChecked(false);

        final Day day = getItem(position);
        view.setText(day.label);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.setChecked(true);
                Log.i(TAG, "Clicked: " + day.toString());
            }
        });
    }

    public static class DayViewHolder extends RecyclerView.ViewHolder {
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
