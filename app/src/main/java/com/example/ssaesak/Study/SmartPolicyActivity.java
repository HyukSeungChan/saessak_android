package com.example.ssaesak.Study;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ssaesak.Dto.PolicySmartResponseDTO;
import com.example.ssaesak.R;
import com.example.ssaesak.Retrofit.ApiResponse;
import com.example.ssaesak.Retrofit.MyRetrofit;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SmartPolicyActivity extends AppCompatActivity {

    private LayoutInflater layoutInflater;

    private LinearLayout movieList;

    private LinearLayout card;

    private LinearLayout layout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_list_smart);

        this.layoutInflater = LayoutInflater.from(getBaseContext());
        this.movieList = findViewById(R.id.movie_list);

        policySmartList();

    }


    private void addMovieCard(PolicySmartResponseDTO dto, int policySmartId, String title, String applyContent, String type) {

        Log.e("addMovieCard","addMovieCard 입장!!!!!!!!!!!!!!!!!!");

        this.card = (LinearLayout) layoutInflater.inflate(R.layout.card_support_smart_project, layout, false);

//        this.card.setOnClickListener(v -> {
//            Intent intent = new Intent(getBaseContext(), NoticeDetailActivity.class);
//            intent.putExtra("workId", workId);
//            startActivity(intent);
//            overridePendingTransition(0, 0);
//        });

        TextView titleTextView = card.findViewById(R.id.title);
        TextView contentTextView = card.findViewById(R.id.content);

        titleTextView.setText(title);
        contentTextView.setText(applyContent);

        card.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), PolicySmartDetailActivity.class);
            intent.putExtra("policy", dto);
            startActivity(intent);
        });

//        // CardView를 noticeList에 추가
//
//        this.card.findViewById(R.id.bookmark).setOnClickListener(v -> {
//            if(this.card.findViewById(R.id.bookmark).isSelected()) {
//                Log.e("card", "press cancel!!");
//                this.card.findViewById(R.id.bookmark).setSelected(false);
//                this.card.findViewById(R.id.bookmark).setBackground(getResources().getDrawable(R.drawable.svg_bookmark, null));
//            } else {
//                Log.e("card", "press !!");
//                this.card.findViewById(R.id.bookmark).setSelected(true);
//                this.card.findViewById(R.id.bookmark).setBackground(getResources().getDrawable(R.drawable.svg_bookmark_select, null));
//            }
//        });

        movieList.addView(card);
    }


    // 스마트 농산사업 전부 받아오기
    private void policySmartList() {
        Log.e("policySmartList", "get policySmartList start!!");
        Call<ApiResponse> call = MyRetrofit.getApiService().policySmartList();
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                Log.e("policySmartList", "policySmartList : ");
                Log.e("policySmartList", "policySmartList : " + response.body());
                ObjectMapper mapper = new ObjectMapper();
                String body = response.body().getData().toString();
                String json = body.substring(1,body.length()-1).replace("\\", "").replace(" ","");
                Log.e("policySmartList", " body -> " + body);
                Log.e("policySmartList", " json -> " + json);
                try {
                    List<PolicySmartResponseDTO> dtos = Arrays.asList(mapper.readValue(json, PolicySmartResponseDTO[].class));
                    Log.e("dtos.size", dtos.size() + "!!!!!!!!!!!");

                    movieList.removeAllViews();

                    // Loop through the WorkListDTOs and add them to the card views
                    for (PolicySmartResponseDTO dto : dtos) {
                        addMovieCard(dto, dto.getPolicySmartId(), dto.getTitle() ,dto.getApplyContent(), dto.getType());
                    }
                } catch (Exception e1) {
                    Log.e("workHome", "Error parsing JSON", e1);
                    e1.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.e("workHome failed", t.getMessage() + "");
            }
        });
    }
}
