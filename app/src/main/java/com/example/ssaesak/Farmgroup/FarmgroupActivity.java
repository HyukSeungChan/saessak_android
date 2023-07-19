package com.example.ssaesak.Farmgroup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.ssaesak.Board.BoardActivity;
import com.example.ssaesak.Dto.FarmResponseDTO;
import com.example.ssaesak.Dto.UserTodoFarmResponseDto;
import com.example.ssaesak.Main.MainActivity;
import com.example.ssaesak.Model.Farm;
import com.example.ssaesak.Model.User;
import com.example.ssaesak.Model.UserFarm;
import com.example.ssaesak.Model.UserFarmList;
import com.example.ssaesak.R;
import com.example.ssaesak.Retrofit.ApiResponse;
import com.example.ssaesak.Retrofit.MyRetrofit;
import com.example.ssaesak.Study.StudyActivity;
import com.example.ssaesak.Working.WorkingActivity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.navigation.NavigationBarView;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        Log.e("main", "farm activity!! : " + UserFarmList.getInstance().size());
        farmId = UserFarmList.getInstance().get(0).getFarmId();
//        farmId = userFarmList.get(0).getFarmId();
        getFarmInfo(farmId);
        getTodoList(farmId);

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
                    finish();
                    return true;
                } else if (item.getItemId() == R.id.fragment_working) {
                    startActivity(new Intent(getApplicationContext(), WorkingActivity.class));
                    overridePendingTransition(0, 0);
                    finish();
                    return true;
                } else if (item.getItemId() == R.id.fragment_farm) {
//                    startActivity(new Intent(getApplicationContext(), FarmgroupActivity.class));
//                    overridePendingTransition(0, 0);
                    return true;
                } else if (item.getItemId() == R.id.fragment_notice) {
                    startActivity(new Intent(getApplicationContext(), BoardActivity.class));
                    overridePendingTransition(0, 0);
                    finish();
                    return true;
                } else if (item.getItemId() == R.id.fragment_study) {
                    startActivity(new Intent(getApplicationContext(), StudyActivity.class));
                    overridePendingTransition(0, 0);
                    finish();
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


    // 해당 농장정보 조회
    public void getFarmInfo(int farmId) {
        Call<ApiResponse> call = MyRetrofit.getApiService().getFarmInfo(farmId);
        Log.e("farmInfo", "입장 !!");
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                try {
                    ObjectMapper mapper = new ObjectMapper();
                    String body = response.body().getData().toString();
                    String json = body.substring(1, body.length() - 1).replace("\\", "");
                    Log.e("farmInfo", " body -> " + body);
                    Log.e("farmInfo", " string -> " + json);

                    FarmResponseDTO farmResponseDTO = mapper.readValue(json, FarmResponseDTO.class);

                    Log.e("farmInfo", " farmDto -> " + json);
                    TextView farmName = findViewById(R.id.title);
                    farmName.setText(farmResponseDTO.getName());
                    TextView textPosition = findViewById(R.id.area);
                    textPosition.setText(farmResponseDTO.getAddress());
                } catch (Exception e) {
                    Log.e("addresssss", "aaaaaaaaaaaaaa!!!!!!!!!!!!!!!!!!!!!");
                    e.printStackTrace();
                    Log.e("addresssss", "aaaaaaaaaaaaaa!!!!!!!!!!!!!!!!!!!!!");
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.e("farmInfo", "asdasd" + t);
            }
        });
    }




    private void getTodoList(int farmId) {
        Log.e("main", "get my farm start!!");
        LinearLayout linearLayout = findViewById(R.id.todo_layout);
        linearLayout.removeAllViews();
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        Call<ApiResponse> call = MyRetrofit.getApiService().getTodoList(User.getInstance().getUserId(), farmId, date.format(new Date()));
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                Log.e("main", "todoList : " + response.body().getData().toString());
                if (response.body().getData() == null || response.body().getData().toString().length() < 10) {
                    LayoutInflater layoutInflater = LayoutInflater.from(getBaseContext());
                    LinearLayout video = (LinearLayout) layoutInflater.inflate(R.layout.card_todo_null, linearLayout, false);
                    linearLayout.addView(video);
                    return;
                }

                ObjectMapper mapper = new ObjectMapper();
                String body = response.body().getData().toString();
                String json = body.replace("\\", "");

                try {
                    List<UserTodoFarmResponseDto> dtos = Arrays.asList(mapper.readValue(json, UserTodoFarmResponseDto[].class));

                    for (UserTodoFarmResponseDto dto : dtos) {
                        LayoutInflater layoutInflater = LayoutInflater.from(getBaseContext());
                        LinearLayout video = (LinearLayout) layoutInflater.inflate(R.layout.card_todo, linearLayout, false);
                        ((TextView)video.findViewById(R.id.task_name)).setText(dto.getTask());
                        video.setVisibility(View.VISIBLE);
                        linearLayout.addView(video);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

            }
        });

    }
}
