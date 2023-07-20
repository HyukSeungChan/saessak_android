package com.example.ssaesak.Working;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.ssaesak.Dto.WorkDTO;
import com.example.ssaesak.Login.SignupTypeActivity;
import com.example.ssaesak.Model.User;
import com.example.ssaesak.R;
import com.example.ssaesak.Retrofit.ApiResponse;
import com.example.ssaesak.Retrofit.MyRetrofit;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoticeDetailActivity extends AppCompatActivity {

    private Button nextButton;
    private TextView due, title, farmName, pay, period, time, content, endDay, count, qualification, good,
    career, payDetail, dueDetail, timeDetail, other, textPosition, phone, welcome, agriculture, list, listDetail;

    private ImageView farmImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_notice_detail);

        int workId = getIntent().getIntExtra("workId", 1);

        init();

        workDetail(workId);



        nextButton.setOnClickListener(v -> {
            Intent intent = new Intent(getBaseContext(), ResumeActivity.class);
            intent.putExtra("workId", workId);
            startActivity(intent);
            overridePendingTransition(0, 0);
        });

    }

    private void init() {
        due = findViewById(R.id.due);
        title = findViewById(R.id.title);
        farmName = findViewById(R.id.farm_name);
        pay = findViewById(R.id.pay);
        period = findViewById(R.id.period);
        time = findViewById(R.id.time);
        content = findViewById(R.id.content);
        endDay = findViewById(R.id.end_day);
        count = findViewById(R.id.count);
        qualification = findViewById(R.id.qualification);
        good = findViewById(R.id.good);
        career = findViewById(R.id.career);
        payDetail = findViewById(R.id.pay_detail);
        dueDetail = findViewById(R.id.due_detail);
        timeDetail = findViewById(R.id.time_detail);
        other = findViewById(R.id.other);
        textPosition = findViewById(R.id.text_position);
        phone = findViewById(R.id.phone);
        farmImage = findViewById(R.id.farm_image);
        welcome = findViewById(R.id.welcome);
        agriculture = findViewById(R.id.agriculture);
        list = findViewById(R.id.list);
        listDetail = findViewById(R.id.list_detail);
        nextButton = findViewById(R.id.button_next);
    }


    // 해당 일자리 조회
    private void workDetail(int workId) {
        Log.e("workDetail", "start!! : " + workId);
        Call<ApiResponse> call = MyRetrofit.getApiService().workDetail(workId);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {

//                if(response.code() == 404) {
//                    Log.e("workDetail", "workDetail~ : " + response.code());
//                    startActivity(new Intent(getBaseContext(), SignupTypeActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
//                    overridePendingTransition(0, 0);
//                    return;
//                }

                if (!response.isSuccessful()) {
                    Toast.makeText( getBaseContext(), "연결 상태가 좋지 않습니다. 다시 시도해주세요", Toast.LENGTH_SHORT);
                    Log.e("연결이 비정상적 : ", "error code : " + response.code());
                    return;
                }

                ObjectMapper mapper = new ObjectMapper();
                String body = response.body().getData().toString();
                String json = body.substring(1, body.length()-1).replace("\\", "");
                Log.e("login", " string -> " + json);
                try {
                    WorkDTO workDTO = mapper.readValue(json, WorkDTO.class);
                    due.setText(workDTO.getRecruitmentStart() + "~" + workDTO.getRecruitmentEnd());
                    title.setText(workDTO.getTitle());
                    farmName.setText(workDTO.getName());
                    pay.setText(workDTO.getPay()+"만원");

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                    try {
                        Date startDate = sdf.parse(workDTO.getWorkStartDay());
                        Date endDate = sdf.parse(workDTO.getWorkEndDay());

                        Calendar startCalendar = Calendar.getInstance();
                        startCalendar.setTime(startDate);
                        int startYear = startCalendar.get(Calendar.YEAR);
                        int startMonth = startCalendar.get(Calendar.MONTH);

                        Calendar endCalendar = Calendar.getInstance();
                        endCalendar.setTime(endDate);
                        int endYear = endCalendar.get(Calendar.YEAR);
                        int endMonth = endCalendar.get(Calendar.MONTH);

                        int diffMonths = (endYear - startYear) * 12 + (endMonth - startMonth);
                        Log.e("diff", diffMonths+"!!!!!!!!!!!!");

                        period.setText(diffMonths + "개월");
                    } catch (ParseException e) {
                        Log.e("cattttt", "cattttttt!!!!!!!!!!!!");
                        e.printStackTrace();
                    }

                    time.setText(workDTO.getWorkStartTime()+"~"+workDTO.getWorkEndTime());
                    content.setText(workDTO.getContent());
                    endDay.setText(workDTO.getRecruitmentEnd());
                    count.setText(workDTO.getRecruitmentPerson()+"명");
                    qualification.setText(workDTO.getQualification());
                    good.setText(workDTO.getPreferentialTreatment());
                    Log.e("career!!!!!!", workDTO.getCareer()+"!!!!!!!!!!!!!!!!!!!");
                    if (workDTO.getCareer() == 99) {
                        career.setText("경력무관");
                    } else if (workDTO.getCareer() < 1) {
                        career.setText("0~12개월");
                    } else if (workDTO.getCareer() < 3) {
                        career.setText("1년~3년");
                    } else if (workDTO.getCareer() < 5) {
                        career.setText("3년~5년");
                    } else if (workDTO.getCareer() > 5) {
                        career.setText("5년 이상");
                    }

                    SimpleDateFormat sdfPay = new SimpleDateFormat("HH:mm", Locale.getDefault());
                    try {
                        Date startTime = sdfPay.parse(workDTO.getWorkStartTime());
                        Date endTime = sdfPay.parse(workDTO.getWorkEndTime());

                        long diffMillis = endTime.getTime() - startTime.getTime();

                        long diffHours = diffMillis / (60 * 60 * 1000);

                        payDetail.setText("시급: " + (workDTO.getPay() * 10000 / (int) diffHours) + "원" + ", " + "일급: " + workDTO.getPay() + "만원");
                        timeDetail.setText("1일" + " " + diffHours+"시간"+"("+workDTO.getWorkStartTime()+"~"+workDTO.getWorkEndTime()+")");
                    } catch (ParseException e) {
                        Log.e("cat!!!!!!!!!!!!!", "cat!!!!!!!!!!!!");
                        e.printStackTrace();
                    }
                    dueDetail.setText(workDTO.getWorkStartDay() + "~" + workDTO.getWorkEndDay());
                    other.setText(workDTO.getEtc());
                    textPosition.setText(workDTO.getAddress());
                    phone.setText(workDTO.getPhone());
                    Glide.with(getBaseContext())
                    .load(workDTO.getFarmImage())
                    .diskCacheStrategy(DiskCacheStrategy.ALL) // 캐시 옵션 설정
                    .into(farmImage);
                    welcome.setText(workDTO.getName() + "에 오신걸 환영합니다~!!");
                    agriculture.setText(workDTO.getAgriculture());
                    list.setText(workDTO.getCrops());
                    listDetail.setText(workDTO.getCropsDetail());
                    User.setInstance(mapper.readValue(json, User.class));
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {Log.e("연결실패", t.getMessage() + "");}
        });
    }

}
