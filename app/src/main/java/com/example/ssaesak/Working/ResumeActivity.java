package com.example.ssaesak.Working;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ssaesak.Dto.ReplyRequestDTO;
import com.example.ssaesak.Dto.ResumeRequestDTO;
import com.example.ssaesak.R;
import com.example.ssaesak.Retrofit.MyRetrofit;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume);

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

//        for (Button chip : agricultureList) {
//            chip.setOnClickListener(v -> {
//                selectFilter(chip);
//
//                Log.e("boardActivity", filter);
//            });
//        }


        init();
    }

    private void init() {
        editTitle = findViewById(R.id.editTitle);
        gender = findViewById(R.id.gender);
        birth = findViewById(R.id.birth);
        phone = findViewById(R.id.phone);
        address = findViewById(R.id.address);
        account = findViewById(R.id.account);
        newestButton = findViewById(R.id.newest_button);
        seniorButton = findViewById(R.id.senior_button);
        editTextCareerYear = findViewById(R.id.edittext_career_year);
        editTextCareerMonth = findViewById(R.id.edittext_career_month);
        editTextLayoutCareerDueStart = findViewById(R.id.edittext_layout_career_due_start);
        editTextCareerDueStart = findViewById(R.id.edittext_career_due_start);
        editTextCareerDueEnd = findViewById(R.id.edittext_career_due_start);
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
//                noticeHelpListFilter(filter);
//            }
//        }
//    }

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

        Call<ResumeRequestDTO> call = MyRetrofit.getApiService().resumeCreate(resumeRequestDTO);
        call.enqueue(new Callback<ResumeRequestDTO>() {
            @Override
            public void onResponse(Call<ResumeRequestDTO> call, Response<ResumeRequestDTO> response) {
                if (response.isSuccessful()) {

//                    Toast.makeText(getApplicationContext(), "이력서 성공적으로 생성되었습니다.", Toast.LENGTH_SHORT).show();

                } else {

//                    Toast.makeText(getApplicationContext(), "이력서 생성에 실패했습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResumeRequestDTO> call, Throwable t) {
                // 통신 실패
                Toast.makeText(getApplicationContext(), "댓글 생성 중 오류가 발생했습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
