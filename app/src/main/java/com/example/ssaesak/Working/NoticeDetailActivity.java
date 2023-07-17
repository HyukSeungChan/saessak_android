package com.example.ssaesak.Working;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ssaesak.Login.SignupTypeActivity;
import com.example.ssaesak.Model.User;
import com.example.ssaesak.R;
import com.example.ssaesak.Retrofit.ApiResponse;
import com.example.ssaesak.Retrofit.MyRetrofit;
import com.fasterxml.jackson.databind.ObjectMapper;

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
        init();

        workDetail(1);

        this.nextButton = findViewById(R.id.button_next);
        this.nextButton.setOnClickListener(v -> {
            Intent intent = new Intent(getBaseContext(), ResumeActivity.class);
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
    }


    // 해당 일자리 조회
    private void workDetail(int workId) {
        Log.e("workDetail", "start!!");
        Call<ApiResponse> call = MyRetrofit.getApiService().workDetail(workId);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {

                if(response.code() == 404) {
                    Log.e("workDetail", "workDetail~ : " + response.code());
                    startActivity(new Intent(getBaseContext(), SignupTypeActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                    overridePendingTransition(0, 0);
                    return;
                }

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
