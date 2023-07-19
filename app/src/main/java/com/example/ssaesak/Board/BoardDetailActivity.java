package com.example.ssaesak.Board;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.ssaesak.Dto.BoardDetailDTO;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardDetailActivity  extends AppCompatActivity {

    private TextView category, name, time, title, content, likeCount, commentCount;
    private ImageView profileImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_detail);

        int boardId = getIntent().getIntExtra("boardId", 1);

        init();

        boardDetail(boardId);

    }

    private void init() {
        this.category = findViewById(R.id.category);
        this.name = findViewById(R.id.name);
        this.time = findViewById(R.id.time);
        this.title = findViewById(R.id.title);
        this.content = findViewById(R.id.content);
        this.likeCount = findViewById(R.id.like_count);
        this.commentCount = findViewById(R.id.comment_count);
        this.profileImage = findViewById(R.id.profile_image);

    }

    private void boardDetail(int boardId) {
        Log.e("boardDetail", "start!!");
        Call<ApiResponse> call = MyRetrofit.getApiService().boardDetail(boardId);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {

                if(response.code() == 404) {
                    Log.e("boardDetail", "workDetail~ : " + response.code());
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
                Log.e("boardDetail", " string -> " + json);
                try {
                    BoardDetailDTO boardDetailDTO = mapper.readValue(json, BoardDetailDTO.class);
                    category.setText(boardDetailDTO.getCrops());
                    name.setText(boardDetailDTO.getName());
                    time.setText(boardDetailDTO.getUploadTime());
                    title.setText(boardDetailDTO.getTitle());
                    content.setText(boardDetailDTO.getContent());
                    likeCount.setText(boardDetailDTO.getLikes()+"");
                    commentCount.setText((boardDetailDTO.getReplies()+""));
                    Glide.with(getBaseContext())
                            .load(boardDetailDTO.getProfileImage())
                            .diskCacheStrategy(DiskCacheStrategy.ALL) // 캐시 옵션 설정
                            .into(profileImage);

                } catch (Exception e1) {
                    e1.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {Log.e("연결실패", t.getMessage() + "");}
        });
    }
}
