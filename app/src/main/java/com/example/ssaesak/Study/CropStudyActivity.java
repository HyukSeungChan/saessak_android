package com.example.ssaesak.Study;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ssaesak.Dto.VideoResponseDto;
import com.example.ssaesak.Model.User;
import com.example.ssaesak.R;
import com.example.ssaesak.Retrofit.ApiResponse;
import com.example.ssaesak.Retrofit.Constatnts_url;
import com.example.ssaesak.Retrofit.MyRetrofit;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CropStudyActivity extends Activity {

    private ProgressBar progressBar;
    private List<Button> filterList;

    private LinearLayout videoLayout;
//    private video;

    private List<VideoResponseDto> dtos;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_crop);

        this.videoLayout = findViewById(R.id.layout_video);
        getVideoList();

//        this.filterList = new ArrayList<>();
//        for (Button button : filterList) {
//            button.setOnClickListener(v -> {
//                filter(button);
//                setVideoList(button.getText().toString());
//            });
//        }

    }

    private void setVideoList(String filter) {
        this.videoLayout.removeAllViews();

        int count = 0;
        for(VideoResponseDto dto : dtos) {
            LayoutInflater layoutInflater = LayoutInflater.from(getBaseContext());
            LinearLayout video = (LinearLayout) layoutInflater.inflate(R.layout.card_movie_list, videoLayout, false);
//            video.setId(count);
            count++;
            video.setVisibility(View.VISIBLE);
//            this.videoLayout.addView(video);
            this.videoLayout.addView(setVideo(video, dto));
        }
    }

    private LinearLayout setVideo(LinearLayout video, VideoResponseDto dto) {
//        Glide.with(this)
//        .load(dto.getLink())
//        .diskCacheStrategy(DiskCacheStrategy.ALL) // 캐시 옵션 설정
//        .into((ImageView) video.findViewById(R.id.thumbnail));


        ((TextView)video.findViewById(R.id.title)).setText(dto.getTitle());

        video.setOnClickListener(v -> {
            Intent intent = new Intent(getBaseContext(), EssentialVideoActivity.class);
            intent.putExtra("videoData", dto);
            startActivity(intent);
            finish();
        });

        return video;
    }

    private void filter(Button selectButton) {
        for (Button button : filterList) {
            if (button == selectButton) {
                button.setBackground(getResources().getDrawable(R.drawable.chip_select, null));
                button.setTextColor(getResources().getColor(R.color.gray5, null));
                button.setTypeface(button.getTypeface(), Typeface.BOLD);
            } else {
                button.setBackground(getResources().getDrawable(R.drawable.chip_not_select, null));
                button.setTextColor(getResources().getColor(R.color.black, null));
                button.setTypeface(button.getTypeface(), Typeface.NORMAL);
            }
        }
    }


    private void getVideoList() {
        Call<ApiResponse> call = MyRetrofit.getApiService().videoList(Constatnts_url.CROP_VIDEO_TYPE);
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
}
