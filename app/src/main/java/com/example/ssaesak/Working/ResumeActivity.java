package com.example.ssaesak.Working;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ssaesak.Dto.BoardDetailDTO;
import com.example.ssaesak.Dto.ReplyRequestDTO;
import com.example.ssaesak.Dto.ResumeDTO;
import com.example.ssaesak.Dto.ResumeRequestDTO;
import com.example.ssaesak.Dto.WorkResumeRequestDTO;
import com.example.ssaesak.Model.User;
import com.example.ssaesak.R;
import com.example.ssaesak.Retrofit.ApiResponse;
import com.example.ssaesak.Retrofit.MyRetrofit;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResumeActivity extends AppCompatActivity {

    private TextView gender, birth, phone, address, account, email;

    private EditText editTitle, editTextCareerYear, editTextCareerMonth, editTextPay, editTextCareerTimeStart, editTextCareerTimeEnd, editTextCareerDueStart, editTextCareerDueEnd;

    private Button newestButton, seniorButton, have, notHave, buttonNext;

    private List<Button> agricultureList;

    private List<Button> cropsList;

    private ImageView calenderStart, check;

    private LinearLayout editTextLayoutCareerDueStart, editTextLayoutCareerDueEnd;

    private String agricultureChoice, cropsChoice, car;

    private float careerFloat;

    private int resumeId, workId;

    private DatePickerDialog datePickerDialog;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume);

        workId = getIntent().getIntExtra("workId", 1);

        init();

        agricultureList = new ArrayList<>();
        agricultureList.add(findViewById(R.id.chip_qkxshdtk));
        agricultureList.add(findViewById(R.id.chip_rhktn));
        agricultureList.add(findViewById(R.id.chip_gkdntm));
        agricultureList.add(findViewById(R.id.chip_shs));
        agricultureList.add(findViewById(R.id.chip_xmrdydwkranf));
        agricultureList.add(findViewById(R.id.chip_rlxk_shddjq));

        cropsList = new ArrayList<>();
        cropsList.add(findViewById(R.id.chip_shdwkranfcoth));
        cropsList.add(findViewById(R.id.chip_rhktlf));
        cropsList.add(findViewById(R.id.chip_ghkgnpwkranf));
        cropsList.add(findViewById(R.id.chip_rlxk_wkrahr));

        for (Button chip : agricultureList) {
            chip.setOnClickListener(v -> {
                agricultureChoice = chip.getText().toString();
                Log.e("agriculture", agricultureChoice);
            });
        }

        for (Button chip : cropsList) {
            chip.setOnClickListener(v -> {
                cropsChoice = chip.getText().toString();
                Log.e("crops", cropsChoice);
            });
        }

        String[] acc = account.getText().toString().split(" ");
        newestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                careerFloat = 99;
                Log.e("newestButton", careerFloat + "!!!!");
            }
        });

        seniorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                careerFloat = Float.parseFloat(editTextCareerYear.getText().toString() + "." + editTextCareerMonth.getText().toString());
            }
        });

        have.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                car = "여";
                Log.e("car여 입장", car + "!!!!");
            }
        });

        notHave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                car = "부";
                Log.e("car부 입장", car + "!!!!");
            }
        });

        editTextLayoutCareerDueStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("editTextLayoutCareerDueStart", "editTextLayoutCareerDueStart 입장!!");
                showDatePickerDialog(1);
            }
        });

        editTextLayoutCareerDueEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(2);
            }
        });




        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    resumeCreate(editTitle.getText().toString(), gender.getText().toString(), birth.getText().toString(), phone.getText().toString(),
                            email.getText().toString(),
                            address.getText().toString(),
                            acc[0],
                            acc[1],
                            careerFloat,
                            agricultureChoice,
                            cropsChoice, editTextCareerDueStart.getText().toString(), editTextCareerDueEnd.getText().toString(),
                            editTextCareerTimeStart.getText().toString(), editTextCareerTimeEnd.getText().toString(), car, User.getInstance().getUserId());

                    Toast.makeText(getApplicationContext(), "지원이 완료되었습니다.", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(new Intent(getBaseContext(), WorkingWorkerActivity.class)));
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "다시 한번 시도해주세요", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(new Intent(getBaseContext(), WorkingWorkerActivity.class)));
                    finish();
                }
            }
        });

    }

    private void init() {
        editTitle = findViewById(R.id.editTitle);
        gender = findViewById(R.id.gender);
        email = findViewById(R.id.email);
        birth = findViewById(R.id.birth);
        phone = findViewById(R.id.phone);
        address = findViewById(R.id.address);
        account = findViewById(R.id.account);
        newestButton = findViewById(R.id.newest_button);
        seniorButton = findViewById(R.id.senior_button);
        editTextCareerYear = findViewById(R.id.edittext_career_year);
        editTextCareerMonth = findViewById(R.id.edittext_career_month);
        editTextLayoutCareerDueStart = findViewById(R.id.edittext_layout_career_due_start);
        editTextLayoutCareerDueEnd = findViewById(R.id.edittext_layout_due_end);
        editTextCareerDueStart = findViewById(R.id.edittext_career_due_start);
        editTextCareerDueEnd = findViewById(R.id.edittext_career_due_end);
        editTextPay = findViewById(R.id.edittext_pay);
        editTextCareerTimeStart = findViewById(R.id.edittext_career_time_start);
        editTextCareerTimeEnd = findViewById(R.id.edittext_career_time_end);
        have = findViewById(R.id.have);
        notHave = findViewById(R.id.nothave);
        check = findViewById(R.id.check);
        buttonNext = findViewById(R.id.button_next);
    }

//    private void selectFilter(Button button) {
//        for (Button chip : agricultureList) {
//            if (!chip.equals(button)) {
//                chip.setBackground(getResources().getDrawable(R.drawable.chip_not_select, null));
//                chip.setTextColor(Color.rgb(120, 120, 120));
//            } else {
//                chip.setBackground(getResources().getDrawable(R.drawable.chip_select, null));
//                chip.setTextColor(Color.rgb(255, 255, 255));
//
//                filter = chip.getText().toString();
//            }
//        }
//    }

//    private void agricultureListFilter(String crops) {
//        Log.e("noticeHelpListFilter", "get noticeHelpListFilter start!!");
//        Call<ApiResponse> call = MyRetrofit.getApiService().noticeHelpListFilter(crops);
//        call.enqueue(new Callback<ApiResponse>() {
//            @Override
//            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
//                Log.e("noticeHelpListFilter", "noticeHelpListFilter : ");
//                Log.e("noticeHelpListFilter", "noticeHelpListFilter : " + response.body());
//                ObjectMapper mapper = new ObjectMapper();
//                String body = response.body().getData().toString();
//                String json = body.substring(1, body.length()-1).replace("\\", "");
//                Log.e("noticeHelpListFilter", " body -> " + body);
//                Log.e("noticeHelpListFilter", " json -> " + json);
//                try {
////                List<BoardDTO> dtos = mapper.readValue(json, BoardDTO[].class);
//                    setList(Arrays.asList(mapper.readValue(json, BoardDetailDTO[].class)));
//                } catch (Exception e1) {e1.printStackTrace();}
//
////                List<UserDTO> dtos = Arrays.asList(mapper.readValue(strList, UserDTO[].class));
//            }
//
//            @Override
//            public void onFailure(Call<ApiResponse> call, Throwable t)  {Log.e("workHome failed", t.getMessage() + "");}
//        });
//    }

    private void showDatePickerDialog(int dateType) {
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
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                        String selectedDate = year + "-" + (month + 1) + "-" + dayOfMonth;
                        if (dateType == 1) {
                            editTextCareerDueStart.setText(selectedDate);
                        } else if (dateType == 2) {
                            editTextCareerDueEnd.setText(selectedDate);
                        }
                    }
                },
                year,
                month,
                dayOfMonth
        );

        // Show the date picker dialog
        datePickerDialog.show();
    }

    // 이력서 생성
    private void resumeCreate(String title, String gender, String birth, String phone, String email, String address,
                              String account, String bank, float career, String agriculture, String crops, String workStartDay,
                              String workEndDay, String workStartTime, String workEndTime, String car, Long userId) {

        ResumeRequestDTO resumeRequestDTO = new ResumeRequestDTO();
        resumeRequestDTO.setTitle(title);
        resumeRequestDTO.setGender(gender);
        resumeRequestDTO.setBirthday(birth);
        resumeRequestDTO.setPhone(phone);
        resumeRequestDTO.setEmail(email);
        resumeRequestDTO.setAddress(address);
        resumeRequestDTO.setAccount(account);
        resumeRequestDTO.setBank(bank);
        resumeRequestDTO.setCareer(career);
        resumeRequestDTO.setAgriculture(agriculture);
        resumeRequestDTO.setCrops(crops);
        resumeRequestDTO.setWorkStartDay(workStartDay);
        resumeRequestDTO.setWorkEndDay(workEndDay);
        resumeRequestDTO.setWorkStartTime(workStartTime);
        resumeRequestDTO.setWorkEndTime(workEndTime);
        resumeRequestDTO.setCar(car);
        resumeRequestDTO.setUserId(userId);

        Call<ResumeDTO> call = MyRetrofit.getApiService().resumeCreate(resumeRequestDTO);
        call.enqueue(new Callback<ResumeDTO>() {
            @Override
            public void onResponse(Call<ResumeDTO> call, Response<ResumeDTO> response) {
                if (response.isSuccessful()) {
//                    ObjectMapper mapper = new ObjectMapper();
//                    String body = response.body().getData().toString();
//                    String json = body.substring(1, body.length()-1).replace("\\", "");
                    try {

//                        Log.e("workId", workId + "!!!!!!!!!!!!");
//                        Log.e("resumeId", dtos.getResumeId() + "!!!!!!!!!!!!");
                        resumeId = response.body().getResumeId();
                        Log.e("resumeId", resumeId + "!!!!!!!");

                        workResumeCreate(workId, resumeId);

                    } catch (Exception e) {

                    }
//
//                    Toast.makeText(getApplicationContext(), "이력서 성공적으로 생성되었습니다.", Toast.LENGTH_SHORT).show();

                } else {

//                    Toast.makeText(getApplicationContext(), "이력서 생성에 실패했습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResumeDTO> call, Throwable t) {
                // 통신 실패
                Toast.makeText(getApplicationContext(), "이력서 생성 중 오류가 발생했습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // 일자리에 지원서 제출 (노동자)
    private void workResumeCreate(int workId, int resumeId) {

        WorkResumeRequestDTO workResumeRequestDTO = new WorkResumeRequestDTO();
        workResumeRequestDTO.setWorkId(workId);
        workResumeRequestDTO.setResumeId(resumeId);


        Call<WorkResumeRequestDTO> call = MyRetrofit.getApiService().workResumeCreate(workResumeRequestDTO);
        call.enqueue(new Callback<WorkResumeRequestDTO>() {
            @Override
            public void onResponse(Call<WorkResumeRequestDTO> call, Response<WorkResumeRequestDTO> response) {
                if (response.isSuccessful()) {

//                    Toast.makeText(getApplicationContext(), "이력서 성공적으로 생성되었습니다.", Toast.LENGTH_SHORT).show();

                } else {

//                    Toast.makeText(getApplicationContext(), "이력서 생성에 실패했습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<WorkResumeRequestDTO> call, Throwable t) {
                // 통신 실패
                Toast.makeText(getApplicationContext(), "이력서 생성 중 오류가 발생했습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // 내가 작성한 이력서 확인


}
