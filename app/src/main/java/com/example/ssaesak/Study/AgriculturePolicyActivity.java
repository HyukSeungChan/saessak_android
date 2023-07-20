package com.example.ssaesak.Study;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ssaesak.Dto.PolicyAgricultureResponseDTO;
import com.example.ssaesak.R;
import com.example.ssaesak.Retrofit.ApiResponse;
import com.example.ssaesak.Retrofit.MyRetrofit;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgriculturePolicyActivity extends AppCompatActivity {

    private LayoutInflater layoutInflater;

    private LinearLayout movieList;

    private LinearLayout card;

    private LinearLayout layout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_list_return);

        this.layoutInflater = LayoutInflater.from(getBaseContext());
        this.movieList = findViewById(R.id.movie_list);

        policyAgricultureList();

    }


    private void addMovieCard(int policyAgricultureId, String title, String applyContent) {

        Log.e("addMovieCard","addMovieCard 입장!!!!!!!!!!!!!!!!!!");

        this.card = (LinearLayout) layoutInflater.inflate(R.layout.card_support_project, layout, false);

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
    private void policyAgricultureList() {
        Log.e("policyAgricultureList", "get policyAgricultureList start!!");
        Call<ApiResponse> call = MyRetrofit.getApiService().policyAgricultureList();
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                Log.e("policyAgricultureList", "policySmartList : ");
                Log.e("policyAgricultureList", "policySmartList : " + response.body());
                ObjectMapper mapper = new ObjectMapper();
                String body = response.body().getData().toString();
                String json = body.substring(1,body.length()-1).replace("\\", "").replace(" ","");
                Log.e("policyAgricultureList", " body -> " + body);
                Log.e("policyAgricultureList", " json -> " + json);
                try {
                    List<PolicyAgricultureResponseDTO> dtos = Arrays.asList(mapper.readValue(json, PolicyAgricultureResponseDTO[].class));
                    Log.e("dtos.size", dtos.size() + "!!!!!!!!!!!");

                    movieList.removeAllViews();

                    // Loop through the WorkListDTOs and add them to the card views
                    for (PolicyAgricultureResponseDTO dto : dtos) {
                        addMovieCard(dto.getPolicyAgricultureId(), dto.getTitle() ,dto.getContent());
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
