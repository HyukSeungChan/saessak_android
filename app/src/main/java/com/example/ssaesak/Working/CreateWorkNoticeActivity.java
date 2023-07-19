package com.example.ssaesak.Working;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.ssaesak.Dto.WorkRequestDto;
import com.example.ssaesak.Model.Farm;
import com.example.ssaesak.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

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

    private int select_chip_string;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        title_length = findViewById(R.id.title_length);
        edittext_title = findViewById(R.id.edittext_title);

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

        edittext_want_due_start = findViewById(R.id.edittext_want_due_start);
        edittext_want_due_end = findViewById(R.id.edittext_want_due_end);

        edittext_pay = findViewById(R.id.edittext_pay);

        button_next = findViewById(R.id.button_next);
        button_next.setOnClickListener(v -> {
            WorkRequestDto workRequestDto = new WorkRequestDto();
            workRequestDto.setFarmId(Farm.getInstance().getFarmId());
            workRequestDto.setTitle(edittext_title.getText().toString());
//            workRequestDto.setContent();
            workRequestDto.setRecruitmentStart(edittext_notice_due_start.getText().toString());
            workRequestDto.setRecruitmentEnd(edittext_notice_due_end.getText().toString());
            workRequestDto.setRecruitmentPerson(Integer.parseInt(edittext_people.getText().toString()));
            workRequestDto.setQualification(edittext_quality.getText().toString());
            workRequestDto.setPreferentialTreatment(edittext_good.getText().toString());
            workRequestDto.setWorkStartTime(edittext_want_due_start.getText().toString());
            workRequestDto.setWorkEndTime(edittext_want_due_end.getText().toString());
            workRequestDto.setEtc(edittext_pay.getText().toString());
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
}
