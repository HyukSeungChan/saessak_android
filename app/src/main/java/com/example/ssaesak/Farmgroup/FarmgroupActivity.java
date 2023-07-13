package com.example.ssaesak.Farmgroup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.ssaesak.Board.BoardActivity;
import com.example.ssaesak.Main.MainActivity;
import com.example.ssaesak.R;
import com.example.ssaesak.Study.StudyActivity;
import com.example.ssaesak.Working.WorkingActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.navigation.NavigationBarView;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.HashSet;

public class FarmgroupActivity extends Activity {

//    CalendarView calendarView;
    MaterialCalendarView calendarView;
    DatePicker datePicker;

    ImageButton button_sidebar;
    int farmId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm);

        MaterialDatePicker.Builder.datePicker();

        this.calendarView = findViewById(R.id.calendarView);

        ArrayList<CalendarDay> farmdayList = new ArrayList<>();
        farmdayList.add(CalendarDay.from(2023, 6, 15));
        farmdayList.add(CalendarDay.from(2023, 6, 12));
        farmdayList.add(CalendarDay.from(2023, 6, 17));
        farmdayList.add(CalendarDay.from(2023, 6, 1));
        farmdayList.add(CalendarDay.from(2023, 6, 5));

        ArrayList<CalendarDay> workdayList = new ArrayList<>();
        workdayList.add(CalendarDay.from(2023, 6, 13));
        workdayList.add(CalendarDay.from(2023, 6, 12));


        EventDecoratorWorkday eventDecoratorWorkday = new EventDecoratorWorkday(farmdayList, this, new TextView(getApplicationContext()));
        EventDecoratorSelect eventDecoratorSelect = new EventDecoratorSelect(workdayList, this, new TextView(getApplicationContext()));
        this.calendarView.addDecorators(eventDecoratorSelect, eventDecoratorWorkday);


        this.calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                Log.e("selectedDate", calendarView.getSelectedDate() + "");
//                calendarView.removeDecorator();
                HashSet<CalendarDay> dates = new HashSet<>();
                dates.add(date);
                calendarView.addDecorator(new EventDecoratorWorkdaySelect(dates, FarmgroupActivity.this, new TextView(getApplicationContext())));
            }
        });


        this.button_sidebar = findViewById(R.id.sidebar);
        this.button_sidebar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FarmgroupSidebar.class);
                farmId = 1;
                intent.putExtra("farmId", farmId);
                startActivity(intent);
                overridePendingTransition(0, 0);



            }
        });






        // 내비 표시
        BottomNavigationView navigation = findViewById(R.id.fragment_bottom);
        Menu menu = navigation.getMenu();
        for (int i = 0; i < navigation.getMenu().size(); i++) {
            MenuItem menuItem = menu.getItem(i);
            if (menuItem.getItemId() == R.id.fragment_farm) {menuItem.setChecked(true);}
        }

        navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.fragment_home) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                } else if (item.getItemId() == R.id.fragment_working) {
                    startActivity(new Intent(getApplicationContext(), WorkingActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                } else if (item.getItemId() == R.id.fragment_farm) {
//                    startActivity(new Intent(getApplicationContext(), FarmgroupActivity.class));
//                    overridePendingTransition(0, 0);
                    return true;
                } else if (item.getItemId() == R.id.fragment_notice) {
                    startActivity(new Intent(getApplicationContext(), BoardActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                } else if (item.getItemId() == R.id.fragment_study) {
                    startActivity(new Intent(getApplicationContext(), StudyActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                } else {
                    return false;
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        this.startActivity(new Intent(this, MainActivity.class));

        finish();
        overridePendingTransition(0, 0); //애니메이션 없애기
    }

}
