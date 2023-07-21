package com.example.ssaesak.Farmgroup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.ssaesak.Board.BoardActivity;
import com.example.ssaesak.Dto.FarmResponseDTO;
import com.example.ssaesak.Dto.TodoRequestDTO;
import com.example.ssaesak.Dto.UserTodoFarmDTO;
import com.example.ssaesak.Dto.UserTodoFarmResponseDto;
import com.example.ssaesak.Main.MainActivity;
import com.example.ssaesak.Model.Farm;
import com.example.ssaesak.Model.User;
import com.example.ssaesak.Model.UserFarmList;
import com.example.ssaesak.R;
import com.example.ssaesak.Retrofit.ApiResponse;
import com.example.ssaesak.Retrofit.MyRetrofit;
import com.example.ssaesak.Study.StudyActivity;
import com.example.ssaesak.Working.WorkingFarmerActivity;
import com.example.ssaesak.Working.WorkingWorkerActivity;
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

public class FarmgroupFarmerActivity extends Activity {

    int status = 0;

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
    private LinearLayout newTodoLayout;

    private String selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm_farmer);

        MaterialDatePicker.Builder.datePicker();

        this.calendarView = findViewById(R.id.calendarView);
        farmId = Farm.getInstance().getFarmId();

        linearLayout = findViewById(R.id.todo_layout);
        newTodoLayout = findViewById(R.id.new_todo);
        EditText todoEdit = findViewById(R.id.task_name);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //원하는 데이터 포맷 지정
        selectedDate = simpleDateFormat.format(new Date());

        Button addButton = findViewById(R.id.add_todo);
        addButton.setOnClickListener(v -> {

            if (status == 0) {
                status = 1;
                newTodoLayout.setVisibility(View.VISIBLE);
                Log.e("todo", todoEdit.getText().toString());
            } else {
                status = 0;
                newTodoLayout.setVisibility(View.GONE);
                if(todoEdit.getText().toString().length() > 1) {
                    Log.e("todo", todoEdit.getText().toString());

                    TodoRequestDTO todo = new TodoRequestDTO(0, selectedDate, todoEdit.getText().toString());
                    addTodo(todo);
                    LayoutInflater layoutInflater = LayoutInflater.from(getBaseContext());
                    LinearLayout video = (LinearLayout) layoutInflater.inflate(R.layout.card_todo, linearLayout, false);
                    ((TextView)video.findViewById(R.id.task_name)).setText(todo.getTask());
                    video.setVisibility(View.VISIBLE);
                    linearLayout.addView(video);
                    Toast.makeText(getApplicationContext(), "일감추가 완료", Toast.LENGTH_SHORT);
                } else {
                    Toast.makeText(getApplicationContext(), "일감을 입력해주세요", Toast.LENGTH_SHORT);
                }
            }


//            todoEdit.setVisibility(View.VISIBLE);

        });

        calendarView.callOnClick();
//        this.calendarView.addDecorator(new );
        this.calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                Log.e("selectedDate", calendarView.getSelectedDate() + "");
                linearLayout.removeAllViews();

                selectedDate = date.getDate().toString();
//                HashSet<CalendarDay> dates = new HashSet<>();
//                dates.add(date);
                ArrayList<CalendarDay> calendarDay = new ArrayList<>();
                calendarDay.add(date);
//
                if (workdaySelect != null) calendarView.removeDecorator(workdaySelect);
                calendarView.addDecorator(eventDecoratorWorkday);

                int count = 0;
                if(calendarDayList.contains(date)) {
                    workdaySelect = new EventDecoratorWorkdaySelect(calendarDay, FarmgroupFarmerActivity.this, new TextView(getApplicationContext()));
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



    private void getTodoList(int farmId) {
        Log.e("main", "farmgroup start get todo!!" + farmId);
        LinearLayout linearLayout = findViewById(R.id.todo_layout);
        linearLayout.removeAllViews();
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        Call<ApiResponse> call = MyRetrofit.getApiService().getTodoList(Farm.getInstance().getFarmId());
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
                    eventDecoratorWorkday = new EventDecoratorWorkday(calendarDayList, FarmgroupFarmerActivity.this, new TextView(getApplicationContext()));
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


    private void addTodo(TodoRequestDTO todo) {
        Log.e("main", "farmgroup start get todo!!" + farmId);
        Call<TodoRequestDTO> call = MyRetrofit.getApiService().addTodo(todo);
        call.enqueue(new Callback<TodoRequestDTO>() {
            @Override
            public void onResponse(Call<TodoRequestDTO> call, Response<TodoRequestDTO> response) {
                Log.e("main", "add todo onersponse");


            }

            @Override
            public void onFailure(Call<TodoRequestDTO> call, Throwable t) {

            }
        });

    }
}
