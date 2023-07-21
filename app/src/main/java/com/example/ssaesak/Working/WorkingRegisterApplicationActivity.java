package com.example.ssaesak.Working;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.ssaesak.Board.BoardDetailActivity;
import com.example.ssaesak.Dto.BoardDetailDTO;
import com.example.ssaesak.Dto.WorkResumeResponseDto;
import com.example.ssaesak.Model.User;
import com.example.ssaesak.R;
import com.example.ssaesak.Retrofit.ApiResponse;
import com.example.ssaesak.Retrofit.MyRetrofit;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WorkingRegisterApplicationActivity extends Fragment {


    private List<Button> chipList;
    private String filter;



    private View view;


    public WorkingRegisterApplicationActivity() {
        Log.e("Board", "stroy tab construct start!!");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.activity_working_farm, container, false);

        Log.e("Board", "story tab start!!");
        getAllNotice();

        return view;
    }


    private void setList(List<WorkResumeResponseDto> list) {
        LinearLayout linearLayout = view.findViewById(R.id.layout_notice);
        linearLayout.removeAllViews();

        Log.e("board", "list size : " + list.size());



//        CardBoardHelpImage card;
        for (WorkResumeResponseDto notice : list) {
            LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(LAYOUT_INFLATER_SERVICE);
            LinearLayout card = (LinearLayout)layoutInflater.inflate(R.layout.card_work_notice, linearLayout, false);
            card.setVisibility(View.VISIBLE);
            ((TextView)card.findViewById(R.id.title)).setText(notice.getTitle());
            ((TextView)card.findViewById(R.id.content)).setText(notice.getName());
            ((TextView)card.findViewById(R.id.comment_count)).setText(notice.getGender());
            ((TextView)card.findViewById(R.id.time)).setText(notice.getWorkPeriod());

            card.setOnClickListener(v -> {
                Intent intent = new Intent(getActivity(), NoticeDetailActivity.class);
//                intent.putExtra("boardId", notice.getBoardId());
                startActivity(intent);
            });

            linearLayout.addView(card);
        }
    }




    private void selectFilter(Button button) {
        for (Button chip : chipList) {
            if (!chip.equals(button)) {
                chip.setBackground(getResources().getDrawable(R.drawable.chip_not_select, null));
                chip.setTextColor(Color.rgb(120, 120, 120));
            } else {
                chip.setBackground(getResources().getDrawable(R.drawable.chip_select, null));
                chip.setTextColor(Color.rgb(255, 255, 255));

                filter = chip.getText().toString();
            }
        }
    }

    // 다 받아온다
    private void getAllNotice() {
        // 초기화 필요

        Call<ApiResponse> call = MyRetrofit.getApiService().myFarmApplication(User.getInstance().getUserId());
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                ObjectMapper mapper = new ObjectMapper();
                String body = response.body().getData().toString();
                String json = body.substring(1, body.length()-1).replace("\\", "");
                Log.e("board", "json -> " + json);
                try {
                    if (response.body().getData().toString() == null) {
                        return;
                    }
//                    List<BoardDetailDTO> dtos = Arrays.asList(mapper.readValue(json, BoardDetailDTO[].class));
                    setList(Arrays.asList(mapper.readValue(json, WorkResumeResponseDto[].class)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

            }
        });
    }



}