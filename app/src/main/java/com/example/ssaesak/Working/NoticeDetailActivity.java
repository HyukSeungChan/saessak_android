package com.example.ssaesak.Working;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_notice_detail);

        this.nextButton = findViewById(R.id.button_next);
        this.nextButton.setOnClickListener(v -> {
            Intent intent = new Intent(getBaseContext(), ResumeActivity.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
        });

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
