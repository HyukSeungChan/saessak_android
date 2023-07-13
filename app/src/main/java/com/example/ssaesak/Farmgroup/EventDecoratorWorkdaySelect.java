package com.example.ssaesak.Farmgroup;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

import com.example.ssaesak.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.Collection;
import java.util.HashSet;

public class EventDecoratorWorkdaySelect implements DayViewDecorator {


    private final Drawable drawable;
    private int color;
    private HashSet<CalendarDay> dates;
    private TextView textView;
    public EventDecoratorWorkdaySelect(Collection<CalendarDay> dates, Activity context, TextView textView) {
        drawable = context.getResources().getDrawable(R.drawable.calendar_workday_select_layer, null);

        this.dates = new HashSet<>(dates);
        this.textView = textView;
    }

    @Override
    public boolean shouldDecorate(CalendarDay day){
        return dates.contains(day);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.setSelectionDrawable(drawable);
    }

    public void setText(String text){
        textView.setText(text);
    }

}