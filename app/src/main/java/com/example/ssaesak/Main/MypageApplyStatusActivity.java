package com.example.ssaesak.Main;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ssaesak.Dto.VideoResponseDto;
import com.example.ssaesak.Dto.WorkResumeResponseDto;
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

public class MypageApplyStatusActivity extends Activity {

    TextView before; //지원완료
    TextView complete; //승인완료
    TextView cancel; //지원취소

    //열람, 미열람, 공고마감, 승인완료, 지원취소
    List<WorkResumeResponseDto> dtos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage_apply_status);
        dtos = new ArrayList<>();
        getResume();

        this.before = findViewById(R.id.complete_after);
        this.before.setOnClickListener(v -> {
            setList();
        });
        this.complete = findViewById(R.id.complete_apply);
        this.complete.setOnClickListener(v -> {
            setListComplete();
        });
        this.cancel = findViewById(R.id.cancel);
        this.cancel.setOnClickListener(v -> {
            setListCancel();
        });
    }

    private void setListComplete() {
        List<WorkResumeResponseDto> list = new ArrayList<>();
        for (WorkResumeResponseDto resume : dtos) {
            if(resume.getState().equals("승인완료")) {
                list.add(resume);
            }
        }
    }

    private void setListCancel() {
        List<WorkResumeResponseDto> list = new ArrayList<>();
        for (WorkResumeResponseDto resume : dtos) {
            if(resume.getState().equals("지원취소")) {
                list.add(resume);
            }
        }
    }

    //열람, 미열람, 공고마감, 승인완료, 지원취소
    private void setList() {
        LinearLayout layout = findViewById(R.id.resume_layout);
        for (WorkResumeResponseDto resume : dtos) {
            LayoutInflater layoutInflater = LayoutInflater.from(getBaseContext());
            LinearLayout card = null;
            if(resume.getState().equals("열람")) {
                card = (LinearLayout) layoutInflater.inflate(R.layout.card_work_notice_mypage_after, layout, false);
            } else if(resume.getState().equals("미열람")) {
                card = (LinearLayout) layoutInflater.inflate(R.layout.card_work_notice_mypage_before, layout, false);
            } else if(resume.getState().equals("공고마감")) {
                card = (LinearLayout) layoutInflater.inflate(R.layout.card_work_notice_mypage_end, layout, false);
            } else if(resume.getState().equals("승인완료")) {
                card = (LinearLayout) layoutInflater.inflate(R.layout.card_work_notice_mypage_complete, layout, false);
            } else { // 지원취소
                card = (LinearLayout) layoutInflater.inflate(R.layout.card_work_notice_mypage_cancel, layout, false);
            }
            card.setVisibility(View.VISIBLE);
            ((TextView)card.findViewById(R.id.title)).setText(resume.getTitle());
//            ((TextView)card.findViewById(R.id.address)).setText(resume.get());
//            ((TextView)card.findViewById(R.id.title)).setText(resume.getTitle());
            layout.addView(card);
        }
    }


    private void getResume() {
        Call<ApiResponse> call = MyRetrofit.getApiService().resume(User.getInstance().getUserId());
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {

            try {
                ObjectMapper mapper = new ObjectMapper();
                String body = response.body().getData().toString();
                String json = body.replace("\\", "");
                Log.e("resume", "json !! : " + json);

//                List<BoardDTO> dtos = mapper.readValue(json, BoardDTO[].class);
                    dtos = Arrays.asList(mapper.readValue(json, WorkResumeResponseDto[].class));
                    Log.e("resume", "video count !! : " + dtos.size());

                } catch (NullPointerException e) {
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t)  {Log.e("get video failed", t.getMessage() + "");}
        });
    }
}
