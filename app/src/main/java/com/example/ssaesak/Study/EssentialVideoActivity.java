package com.example.ssaesak.Study;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;

import com.example.ssaesak.Dto.UserVideoWatchRequestDto;
import com.example.ssaesak.Dto.VideoResponseDto;
import com.example.ssaesak.Main.MainActivity;
import com.example.ssaesak.Model.User;
import com.example.ssaesak.R;
import com.example.ssaesak.Retrofit.ApiResponse;
import com.example.ssaesak.Retrofit.Constatnts_url;
import com.example.ssaesak.Retrofit.MyRetrofit;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EssentialVideoActivity extends Activity {

    private VideoResponseDto dto;

    private TextView title;
    private TextView referenceName;

    private VideoView videoview;
    private Button playBt;
    private SeekBar seekBar;

    private boolean isPrepared = false;
    private boolean isTouch = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_videoview);
        dto = (VideoResponseDto) getIntent().getSerializableExtra("videoData");

        Uri url = Uri.parse("https://saessak-s3.s3.ap-northeast-2.amazonaws.com/powderedrice.mp4");
        videoview = (VideoView) findViewById(R.id.videoView1);
        videoview.setVideoURI(url);
        //비디오 컨트롤바.
        videoview.setMediaController(new MediaController(this));
        videoview.start();

        this.title = findViewById(R.id.title);
        this.title.setText(dto.getTitle());
        this.referenceName = findViewById(R.id.reference_name);
        this.referenceName.setText(dto.getSource());

        MediaController controller = new MediaController(this);
        videoview.setMediaController(controller);


        videoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                Log.e("study", "영상 시청 완료!! : " + dto.getTitle());
                 watchingVideo(); // 이거 서버 만들고나서 하기
            }
        });

    }


    private void watchingVideo() {
        if(dto.isWatching()) {
            return;
        }
        UserVideoWatchRequestDto temp = new UserVideoWatchRequestDto(User.getInstance().getUserId(), dto.getVideoId());
        retrofit2.Call<ApiResponse> call = MyRetrofit.getApiService().postWatchingVideo(temp);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(retrofit2.Call<ApiResponse> call, Response<ApiResponse> response) {

            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t)  {Log.e("get video failed", t.getMessage() + "");}
        });
    }


    @Override
    public void onBackPressed() {
        this.startActivity(new Intent(this, StudyActivity.class));
        overridePendingTransition(0, 0); //애니메이션 없애기
        finish();
    }

}
