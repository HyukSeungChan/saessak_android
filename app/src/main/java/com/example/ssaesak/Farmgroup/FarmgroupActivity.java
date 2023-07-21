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
import com.example.ssaesak.Dto.UserTodoFarmDTO;
import com.example.ssaesak.Dto.UserTodoFarmResponseDto;
import com.example.ssaesak.Dto.VideoResponseDto;
import com.example.ssaesak.Main.MainActivity;
import com.example.ssaesak.Model.Farm;
import com.example.ssaesak.Model.User;
import com.example.ssaesak.Model.UserFarmList;
import com.example.ssaesak.R;
import com.example.ssaesak.Retrofit.ApiResponse;
import com.example.ssaesak.Retrofit.Constatnts_url;
import com.example.ssaesak.Retrofit.MyRetrofit;
import com.example.ssaesak.Study.StudyActivity;
import com.example.ssaesak.Working.WorkingFarmerActivity;
import com.example.ssaesak.Working.WorkingWorkerActivity;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.navigation.NavigationBarView;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
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

    EventDecoratorWorkday eventDecoratorWorkday;

    ImageButton button_sidebar;
    int farmId;

    List<UserTodoFarmDTO> dtos;

    private EventDecoratorWorkdaySelect workdaySelect;
    private List<UserTodoFarmDTO> todoList;
    private ArrayList<CalendarDay> calendarDayList;

    private LinearLayout linearLayout;

//    private List<>

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm);
        linearLayout = findViewById(R.id.todo_layout);

        MaterialDatePicker.Builder.datePicker();

        this.calendarView = findViewById(R.id.calendarView);
        farmId = UserFarmList.getInstance().get(0).getFarmId();
//        getFarmInfo(farmId);

        this.calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                Log.e("selectedDate", calendarView.getSelectedDate() + "");
                linearLayout.removeAllViews();


//                HashSet<CalendarDay> dates = new HashSet<>();
//                dates.add(date);
                ArrayList<CalendarDay> calendarDay = new ArrayList<>();
                calendarDay.add(date);
//
                if (workdaySelect != null) calendarView.removeDecorator(workdaySelect);
                calendarView.addDecorator(eventDecoratorWorkday);

                int count = 0;
                if(calendarDayList.contains(date)) {
                    workdaySelect = new EventDecoratorWorkdaySelect(calendarDay, FarmgroupActivity.this, new TextView(getApplicationContext()));
                    calendarView.addDecorator(workdaySelect);

                    for (UserTodoFarmDTO dto : dtos) {
                        if (dto.getCalendarDay().equals(date)) {
                            count++;
                            LayoutInflater layoutInflater = LayoutInflater.from(getBaseContext());
                            LinearLayout video = (LinearLayout) layoutInflater.inflate(R.layout.card_todo, linearLayout, false);
                            ((TextView)video.findViewById(R.id.task_name)).setText(dto.getTask());
                            video.setVisibility(View.VISIBLE);
                            linearLayout.addView(video);
                        }
                    }
                    }
                Log.e("calender", "count : " + count);
                if (count == 0) {
                    LayoutInflater layoutInflater = LayoutInflater.from(getBaseContext());
                    LinearLayout video = (LinearLayout) layoutInflater.inflate(R.layout.card_todo_null, linearLayout, false);
                    linearLayout.addView(video);
                }

//                if (workdaySelect != null) calendarView.removeDecorator(workdaySelect);
//                workdaySelect = new EventDecoratorWorkdaySelect(calendarDayList, FarmgroupActivity.this, new TextView(getApplicationContext()));
//                calendarView.addDecorator(workdaySelect);
            }
        });

        getTodoList(farmId);

//        MaterialCalendarView materialCalendarView = findViewById(R.id.calendarView);
//        materialCalendarView.setSelectedDate(CalendarDay.today());
//
//        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
//              @Override
//              public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
//              }
//          });




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

                    if(User.getInstance().getType().equals("도시농부")) {
                        Intent intent = new Intent(getApplicationContext(), WorkingWorkerActivity.class);
//                    intent.putExtra("bottom", );
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                        return true;
                    } else {
                        Intent intent = new Intent(getApplicationContext(), WorkingFarmerActivity.class);
//                    intent.putExtra("bottom", );
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                        return true;
                    }


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
        Call<ApiResponse> call = MyRetrofit.getApiService().loginGetFarm(User.getInstance().getUserId());
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

                    Farm.setInstance(mapper.readValue(json, Farm.class));
                    getTodoList(Farm.getInstance().getFarmId());

                    Log.e("farmInfo", " farmDto -> " + json);
                    TextView farmName = findViewById(R.id.title);
                    farmName.setText(Farm.getInstance().getName());
                    TextView textPosition = findViewById(R.id.area);
                    textPosition.setText(Farm.getInstance().getAddress());
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

        Call<ApiResponse> call;
        if(User.getInstance().getType().equals("농장주")) {

            call = MyRetrofit.getApiService().getTodoList(farmId);
        } else {

            call = MyRetrofit.getApiService().getTodoList(User.getInstance().getUserId(), farmId);
        }

        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                Log.e("main", "get todo onersponse");

                try {
//                    todoList = new ArrayList<>();
                    ObjectMapper mapper = new ObjectMapper();
                    String body = response.body().getData().toString();
                    String json = body.substring(1, body.length()-1).replace("\\", "");

                    dtos = Arrays.asList(mapper.readValue(json, UserTodoFarmDTO[].class));
                    calendarDayList = new ArrayList<>();
                    for (UserTodoFarmDTO todo : dtos) {
                        calendarDayList.add(todo.getCalendarDay());
                        Log.e("main", "calendarList" + todo.getCalendarDay().toString());
                    }

                    Log.e("main", "calendarList size" + calendarDayList.size());
                    eventDecoratorWorkday = new EventDecoratorWorkday(calendarDayList, FarmgroupActivity.this, new TextView(getApplicationContext()));
                    calendarView.addDecorator(eventDecoratorWorkday);

                } catch (NullPointerException e) {
                    Log.e("main", "get todo nullpointer");
                    todoList = new ArrayList<>();
                } catch (Exception e) {
                    Log.e("main", "get todo exception : " + e.getMessage());
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

            }
        });

    }


}
