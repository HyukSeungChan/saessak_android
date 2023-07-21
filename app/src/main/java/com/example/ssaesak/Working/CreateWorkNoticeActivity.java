package com.example.ssaesak.Working;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.ssaesak.Dto.BoardRequestDTO;
import com.example.ssaesak.Dto.WorkRequestDto;
import com.example.ssaesak.Model.Farm;
import com.example.ssaesak.R;
import com.example.ssaesak.Retrofit.MyRetrofit;
import com.google.android.material.tabs.TabLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateWorkNoticeActivity extends AppCompatActivity {

    private TextView title_length;
    private EditText edittext_title;

    private EditText edittext_notice_due_start;
    private ImageView calender_start_notice;
    private EditText edittext_notice_due_end;
    private ImageView calender_end_notice;

    private EditText edittext_working_due_start;
    private ImageView calender_start_working;
    private EditText edittext_working_due_end;
    private ImageView calender_end_working;

    private EditText edittext_people;
    private EditText edittext_quality;
    private EditText edittext_good;

    private float select_chip_string;
    private Button chip_all;
    private Button chip_012;
    private Button chip_13;
    private Button chip_35;
    private Button chip_5;

    private EditText edittext_want_due_start;
    private EditText edittext_want_due_end;

    private EditText edittext_pay;

    private Button button_next;

    private List<Button> filterList;

    private DatePickerDialog datePickerDialog;

    private LinearLayout edittext_layout_notice_due_start, edittext_layout_notice_due_end, edittext_layout_working_due_start, edittext_layout_working_due_end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_notice_create);

        title_length = findViewById(R.id.title_length);
        edittext_title = findViewById(R.id.edittext_title);

        edittext_layout_notice_due_start = findViewById(R.id.edittext_layout_notice_due_start);
        edittext_layout_notice_due_end = findViewById(R.id.edittext_layout_due_end_notice);
        edittext_layout_working_due_start = findViewById(R.id.edittext_layout_working_due_start);
        edittext_layout_working_due_end = findViewById(R.id.edittext_layout_working_due_end);
        edittext_want_due_start = findViewById(R.id.edittext_want_due_start);
        edittext_want_due_end = findViewById(R.id.edittext_want_due_end);

        edittext_notice_due_start = findViewById(R.id.edittext_notice_due_start);
        edittext_notice_due_start.setClickable(false);
        calender_start_notice = findViewById(R.id.calender_start_notice);
        edittext_notice_due_end = findViewById(R.id.edittext_notice_due_end);
        edittext_notice_due_end.setClickable(false);
        calender_end_notice = findViewById(R.id.calender_end_notice);

        edittext_working_due_start = findViewById(R.id.edittext_working_due_start);
        edittext_working_due_start.setClickable(false);
        calender_start_working = findViewById(R.id.calender_start_working);
        edittext_working_due_end = findViewById(R.id.edittext_working_due_end);
        edittext_working_due_end.setClickable(false);
        calender_end_working = findViewById(R.id.calender_end_working);

        edittext_people = findViewById(R.id.edittext_people);
        edittext_quality = findViewById(R.id.edittext_quality);
        edittext_good = findViewById(R.id.edittext_good);
        this.filterList = new ArrayList<>();
        chip_all = findViewById(R.id.chip_all);
        chip_012 = findViewById(R.id.chip_012);
        chip_13 = findViewById(R.id.chip_13);
        chip_35 = findViewById(R.id.chip_35);
        chip_5 = findViewById(R.id.chip_5);
        this.filterList.add(chip_all);
        this.filterList.add(chip_012);
        this.filterList.add(chip_13);
        this.filterList.add(chip_35);
        this.filterList.add(chip_5);

        for (Button button : filterList) {
            button.setOnClickListener(v -> {
                filter(button);
//                setVideoList();
            });
        }



        edittext_layout_notice_due_start.setOnClickListener(v -> showDatePickerDialog(edittext_notice_due_start));
        edittext_layout_notice_due_end.setOnClickListener(v -> showDatePickerDialog(edittext_notice_due_end));
        edittext_layout_working_due_start.setOnClickListener(v -> showDatePickerDialog(edittext_working_due_start));
        edittext_layout_working_due_end.setOnClickListener(v -> showDatePickerDialog(edittext_working_due_end));

        edittext_pay = findViewById(R.id.edittext_pay);

        button_next = findViewById(R.id.button_next);
        button_next.setOnClickListener(v -> {
            workNoticeFarmer();

        });

    }

    private void filter(Button selectButton) {
        for (Button button : filterList) {
            if (button == selectButton) {
                button.setBackground(getResources().getDrawable(R.drawable.chip_select, null));
                button.setTextColor(getResources().getColor(R.color.gray5, null));
                button.setTypeface(button.getTypeface(), Typeface.BOLD);
                if (button.getText().toString().equals("경력 무관")) {
                    select_chip_string = 99;
                } else if (button.getText().toString().equals("0~12개월")) {
                    select_chip_string = 1;
                } else if (button.getText().toString().equals("1~3년")) {
                    select_chip_string = 3;
                } else if (button.getText().toString().equals("3~5년")) {
                    select_chip_string = 5;
                } else {
                    select_chip_string = 7;
                }

            } else {
                button.setBackground(getResources().getDrawable(R.drawable.chip_not_select, null));
                button.setTextColor(getResources().getColor(R.color.black, null));
                button.setTypeface(button.getTypeface(), Typeface.NORMAL);
            }
        }
    }

    private void workNoticeFarmer() {

        try {

            WorkRequestDto workRequestDto = new WorkRequestDto();
            workRequestDto.setFarmId(Farm.getInstance().getFarmId());
            if (Farm.getInstance().getFarmId() == 0) workRequestDto.setFarmId(1);
            workRequestDto.setTitle(edittext_title.getText().toString());
//        workRequestDto.setContent();
            workRequestDto.setRecruitmentStart(edittext_notice_due_start.getText().toString());
            workRequestDto.setRecruitmentEnd(edittext_notice_due_end.getText().toString());
            workRequestDto.setCareer(1.2f);
            workRequestDto.setRecruitmentPerson(Integer.parseInt(edittext_people.getText().toString()));
            workRequestDto.setQualification(edittext_quality.getText().toString());
            workRequestDto.setPreferentialTreatment(edittext_good.getText().toString());
            workRequestDto.setWorkStartTime(edittext_want_due_start.getText().toString());
            workRequestDto.setWorkEndTime(edittext_want_due_end.getText().toString());
            workRequestDto.setDayWage(Integer.parseInt(edittext_pay.getText().toString()));

            Log.e("farmId", workRequestDto.getFarmId() + "!!!!!!!!");
            Log.e("title", workRequestDto.getRecruitmentStart() + "!!!!!!!!");
            Log.e("RecruimentStart", workRequestDto.getRecruitmentEnd() + "!!!!!!!!");
            Log.e("RecruimentEnd", workRequestDto.getFarmId() + "!!!!!!!!");
            Log.e("Qual", workRequestDto.getQualification() + "!!!!!!!!");
            Log.e("startTime", workRequestDto.getWorkStartTime() + "!!!!!!!!");
            Log.e("endTime", workRequestDto.getWorkEndTime() + "!!!!!!!!");
            Log.e("dayWage", workRequestDto.getDayWage() + "!!!!!!!!");


            Call<WorkRequestDto> call = MyRetrofit.getApiService().workNoticeFarmer(workRequestDto);
            call.enqueue(new Callback<WorkRequestDto>() {
                @Override
                public void onResponse(Call<WorkRequestDto> call, Response<WorkRequestDto> response) {
                    if (response.isSuccessful()) {
                        Log.e("response", "response good" + response.body());
                        Toast.makeText(getApplicationContext(), "지원이 완료되었습니다.", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(new Intent(getBaseContext(), WorkingNoticeFarmerActivity.class)));
                        finish();
                        // 글 생성 성공
//                    Toast.makeText(getApplicationContext(), "글이 성공적으로 생성되었습니다.", Toast.LENGTH_SHORT).show();
                        // 글 목록을 갱신하는 등의 작업 수행
                        // ...
                    } else {
                        Log.e("response", "response bad" + response.body());
                        // 글 생성 실패
//                    Toast.makeText(getApplicationContext(), "글 생성에 실패했습니다.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<WorkRequestDto> call, Throwable t) {
                    Log.e("fail", t.getMessage());
                    // 통신 실패
//                Toast.makeText(getApplicationContext(), "글 생성 중 오류가 발생했습니다.", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "다시 한번 시도해주세요", Toast.LENGTH_LONG).show();
            startActivity(new Intent(new Intent(getBaseContext(), WorkingNoticeFarmerActivity.class)));
            finish();
        }

    }

    private void showDatePickerDialog(EditText editText) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // Update the appropriate EditText with the selected date
                        // Make sure to format the date as desired
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
                        String selectedDate = year + "-" + (month + 1) + "-" + dayOfMonth;
                        editText.setText(selectedDate);
                    }
                },
                year,
                month,
                dayOfMonth
        );

        // Show the date picker dialog
        datePickerDialog.show();
    }

}
