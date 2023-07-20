package com.example.ssaesak.Main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ssaesak.Dto.VideoResponseDto;
import com.example.ssaesak.Dto.WorkResumeResponseDto;
import com.example.ssaesak.Dto.WorkResumeWorkerResponseDto;
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

    TextView applyCancel;

    //열람, 미열람, 공고마감, 승인완료, 지원취소
    List<WorkResumeWorkerResponseDto> dtos;

    LinearLayout card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage_apply_status);
        dtos = new ArrayList<>();
        getResume();


        this.before = findViewById(R.id.complete_after);
        this.before.setOnClickListener(v -> {
            Log.e("before", "before Click");
            setList();
        });
        this.complete = findViewById(R.id.complete_apply);
        this.complete.setOnClickListener(v -> {
            Log.e("complete", "complete Click");
            setListComplete();
        });
        this.cancel = findViewById(R.id.cancel);
        this.cancel.setOnClickListener(v -> {
            Log.e("cancel", "cancel Click");
            setListCancel();
        });

    }

    private void setListComplete() {
        List<WorkResumeWorkerResponseDto> list = new ArrayList<>();
        for (WorkResumeWorkerResponseDto resume : dtos) {
            if(resume.getState().equals("승인완료")) {
                list.add(resume);
                Log.e("list", list.get(0)+"!!!!!!!!!");
            }
        }
        setListState(list);
    }

    private void setListCancel() {
        List<WorkResumeWorkerResponseDto> list = new ArrayList<>();
        for (WorkResumeWorkerResponseDto resume : dtos) {
            if(resume.getState().equals("지원취소")) {
                list.add(resume);
            }
        }
        setListState(list);
    }

    //열람, 미열람, 공고마감, 승인완료, 지원취소
    private void setList() {
        LinearLayout layout = findViewById(R.id.resume_layout);
        layout.removeAllViews();
        for (WorkResumeWorkerResponseDto resume : dtos) {
            LayoutInflater layoutInflater = LayoutInflater.from(getBaseContext());
            if (resume.getState().equals("열람")) {
                card = (LinearLayout) layoutInflater.inflate(R.layout.card_work_notice_mypage_after, layout, false);
                applyCancel = card.findViewById(R.id.cancel);
                applyCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        workerApplicationDelete(resume.getWorkResumeId());
                    }
                });
            } else if (resume.getState().equals("미열람")) {
                card = (LinearLayout) layoutInflater.inflate(R.layout.card_work_notice_mypage_before, layout, false);
                applyCancel = card.findViewById(R.id.cancel);
                applyCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        workerApplicationDelete(resume.getWorkResumeId());
                    }
                });
            } else if (resume.getState().equals("공고마감")) {
                card = (LinearLayout) layoutInflater.inflate(R.layout.card_work_notice_mypage_end, layout, false);

            } else if (resume.getState().equals("승인완료")) {
                card = (LinearLayout) layoutInflater.inflate(R.layout.card_work_notice_mypage_complete, layout, false);
                applyCancel = card.findViewById(R.id.cancel);
                applyCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        workerApplicationDelete(resume.getWorkResumeId());
                    }
                });
            } else { // 지원취소
                card = (LinearLayout) layoutInflater.inflate(R.layout.card_work_notice_mypage_cancel, layout, false);
            }

            card.setVisibility(View.VISIBLE);
            ((TextView) card.findViewById(R.id.title)).setText(resume.getTitle());
            // 상태에 따라 추가적인 정보 설정
            if (card.findViewById(R.id.address) != null) {
                ((TextView) card.findViewById(R.id.address)).setText(resume.getAddress());
            }
            if (card.findViewById(R.id.date) != null) {
                ((TextView) card.findViewById(R.id.date)).setText(resume.getDate());
            }

            layout.addView(card);

        }
    }

    private void setListState(List<WorkResumeWorkerResponseDto> list) {
        LinearLayout layout = findViewById(R.id.resume_layout);
        layout.removeAllViews();
        for (WorkResumeWorkerResponseDto resume : list) {
            LayoutInflater layoutInflater = LayoutInflater.from(getBaseContext());
            if (resume.getState().equals("승인완료")) {
                card = (LinearLayout) layoutInflater.inflate(R.layout.card_work_notice_mypage_complete, layout, false);
                applyCancel = card.findViewById(R.id.cancel);
                applyCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        workerApplicationDelete(resume.getWorkResumeId());
                    }
                });
            } else { // 지원취소
                card = (LinearLayout) layoutInflater.inflate(R.layout.card_work_notice_mypage_cancel, layout, false);
            }

            card.setVisibility(View.VISIBLE);
            ((TextView) card.findViewById(R.id.title)).setText(resume.getTitle());
            // 상태에 따라 추가적인 정보 설정
            if (card.findViewById(R.id.address) != null) {
                ((TextView) card.findViewById(R.id.address)).setText(resume.getAddress());
            }
            if (card.findViewById(R.id.date) != null) {
                ((TextView) card.findViewById(R.id.date)).setText(resume.getDate());
            }

            layout.addView(card);

        }
    }


    private void getResume() {
        Call<ApiResponse> call = MyRetrofit.getApiService().workerApplicationList(User.getInstance().getUserId());
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {

            try {
                ObjectMapper mapper = new ObjectMapper();
                String body = response.body().getData().toString();
                String json = body.substring(1,body.length()-1).replace("\\", "");
                Log.e("resume", "json !! : " + json);

//                List<BoardDTO> dtos = mapper.readValue(json, BoardDTO[].class);
                    dtos = Arrays.asList(mapper.readValue(json, WorkResumeWorkerResponseDto[].class));
                    Log.e("resume", "video count !! : " + dtos.size());
                    setList();
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

    // 지원 취소
    private void workerApplicationDelete(int workResumeId) {
        Call<ApiResponse> call = MyRetrofit.getApiService().workerApplicationDelete(workResumeId);
        Log.e("workerApplicationDelete", "workerApplicationDelete 입장!!");
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                Log.e("workerApplicationDelete", "workerApplicationDelete onResponse 입장!!");


                try {
                    Log.e("workerApplicationDelete", "workerApplicationDelete try 입장!!");
                    Log.e("response.body", response.body().getData().toString() + "!!!!!!!!!!!");
                    ObjectMapper mapper = new ObjectMapper();
                    String body = response.body().getData().toString();
                    String json = body.replace("\\", "");
                    Log.e("resume", "json !! : " + body);


//                    dtos = new ArrayList<>();
//                    dtos = Arrays.asList(mapper.readValue(json, WorkResumeWorkerResponseDto[].class));
//                    Intent intent = getIntent();
//                    finish(); //현재 액티비티 종료 실시
//                    overridePendingTransition(0, 0); //인텐트 애니메이션 없애기
//                    startActivity(intent); //현재 액티비티 재실행 실시
//                    overridePendingTransition(0, 0); //인텐트 애니메이션 없애기
//                List<BoardDTO> dtos = mapper.readValue(json, BoardDTO[].class);


                } catch (NullPointerException e) {
                    Log.e("workerApplicationDelete", "workerApplicationDelete 널익셉션!!!!");
                    e.printStackTrace();
                } catch (Exception e) {
                    Log.e("workerApplicationDelete", "workerApplicationDelete 익셉션!!!!");
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t)  {Log.e("get video failed", t.getMessage() + "");}
        });
    }


}
