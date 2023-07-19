package com.example.ssaesak.Study;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;

import com.example.ssaesak.Board.BoardActivity;
import com.example.ssaesak.Dto.VideoResponseDto;
import com.example.ssaesak.Farmgroup.FarmgroupActivity;
import com.example.ssaesak.Main.MainActivity;
import com.example.ssaesak.Model.User;
import com.example.ssaesak.Model.Worker;
import com.example.ssaesak.R;
import com.example.ssaesak.Retrofit.ApiResponse;
import com.example.ssaesak.Retrofit.Constatnts_url;
import com.example.ssaesak.Retrofit.MyRetrofit;
import com.example.ssaesak.Working.WorkingActivity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//        vv.getDuration(); //총 재생시간을 ms 가져옴
//        vv.getCurrentPosition(); //현재 재생 부분 가져옴  ms
//        vv.seekTo(1000);  // 해당하는 재생 위치로 이동 ms
//        vv.pause(); //일시 정지
//        vv.stopPlayback(); //완전 스톱
public class StudyActivity extends Activity {

    private ProgressBar progressBar;
    private Button essentialButton;
    private ImageView returnImage;
    private ImageView smartImage;
    private View intrestingCrop;
    private View Agriculture;

    private ImageButton cropButton;
    private ImageButton agricultureButton;

    private List<VideoResponseDto> dtos;

    private LinearLayout cropLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);
        getVideoList();

        this.cropLayout = findViewById(R.id.interesting_crop_layout);
        Log.e("study", "user interesting crop :: " + Worker.getInstance().getInterestCrops());
        if(!Worker.getInstance().getInterestCrops().equals("")) {
            // 일러스트 나오면 추가
        }

        this.progressBar = findViewById(R.id.progressbar);
//        Log.e("study", "user interesting crop :: " + User.getInstance().getComplete()/15*100);
        this.progressBar.setProgress(User.getInstance().getComplete()*100/15);

        this.essentialButton = findViewById(R.id.essential_button);
        this.essentialButton.setOnClickListener(v -> {
            startActivity(new Intent(getBaseContext(), EssentialStudyActivity.class));
        });

        this.cropButton = findViewById(R.id.crop);
        this.cropButton.setOnClickListener(v -> {
            startActivity(new Intent(getBaseContext(), CropStudyActivity.class));
        });
        this.agricultureButton = findViewById(R.id.agriculture);
        this.agricultureButton.setOnClickListener(v -> {
            startActivity(new Intent(getBaseContext(), AgricultureStudyActivity.class));
        });

//        Uri url= Uri.parse("https://saessak-s3.s3.ap-northeast-2.amazonaws.com/powderedrice.mp4");
//        final VideoView videoview=(VideoView)findViewById(R.id.videoView1);
//        videoview.setVideoURI(url);
//        //비디오 컨트롤바.
//        videoview.setMediaController(new MediaController(this));
//        videoview.start();


        // 내비 표시
        BottomNavigationView navigation = findViewById(R.id.fragment_bottom);
        Menu menu = navigation.getMenu();
        for (int i = 0; i < navigation.getMenu().size(); i++) {
            MenuItem menuItem = menu.getItem(i);
            if (menuItem.getItemId() == R.id.fragment_study) {menuItem.setChecked(true);}
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
                    startActivity(new Intent(getApplicationContext(), WorkingActivity.class));
                    overridePendingTransition(0, 0);
                    finish();
                    return true;
                } else if (item.getItemId() == R.id.fragment_farm) {
                    startActivity(new Intent(getApplicationContext(), FarmgroupActivity.class));
                    overridePendingTransition(0, 0);
                    finish();
                    return true;
                } else if (item.getItemId() == R.id.fragment_notice) {
                    startActivity(new Intent(getApplicationContext(), BoardActivity.class));
                    overridePendingTransition(0, 0);
                    finish();
                    return true;
                } else if (item.getItemId() == R.id.fragment_study) {
//                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_screen, fragment_study).commitAllowingStateLoss();
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


    private void getVideoList() {
        Call<ApiResponse> call = MyRetrofit.getApiService().videoList(Constatnts_url. AGRICULTURE_VIDEO_TYPE);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                ObjectMapper mapper = new ObjectMapper();
                String body = response.body().getData().toString();
                String json = body.substring(1, body.length()-1).replace("\\", "");
                Log.e("crop", "json !! : " + json);
                try {

//                List<BoardDTO> dtos = mapper.readValue(json, BoardDTO[].class);
                    dtos = Arrays.asList(mapper.readValue(json, VideoResponseDto[].class));
                    Log.e("crop", "video count !! : " + dtos.size());
                    setVideoList("전체");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t)  {Log.e("get video failed", t.getMessage() + "");}
        });
    }

    private void setVideoList(String filter) {
        LinearLayout videoLayout = findViewById(R.id.agriculture_movie_layout);
        videoLayout.removeAllViews();

        int count = 0;
        for(VideoResponseDto dto : dtos) {
            LayoutInflater layoutInflater = LayoutInflater.from(getBaseContext());
            LinearLayout video = (LinearLayout) layoutInflater.inflate(R.layout.card_agriculture_movie_list, videoLayout, false);
//            video.setId(count);
            count++;
            video.setVisibility(View.VISIBLE);
            video.setOnClickListener(v -> {
                Intent intent = new Intent(getBaseContext(), EssentialVideoActivity.class);
                intent.putExtra("videoData", dto);
                startActivity(intent);
            });
//            this.videoLayout.addView(video);
            ((TextView)video.findViewById(R.id.title)).setText(dto.getTitle());
            videoLayout.addView(video);
        }
    }
}
