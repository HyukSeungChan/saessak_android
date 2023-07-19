package com.example.ssaesak.Board;

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

import com.example.ssaesak.Dto.BoardDetailDTO;
import com.example.ssaesak.Dto.WorkDTO;
import com.example.ssaesak.R;
import com.example.ssaesak.Retrofit.ApiResponse;
import com.example.ssaesak.Retrofit.MyRetrofit;
import com.example.ssaesak.Working.NoticeDetailActivity;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardHelpActivity extends Fragment {


    private List<Button> chipList;
    private String filter;

    private View view;


    public BoardHelpActivity() {
        Log.e("Board", "help tab construct start!!");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.activity_board_help, container, false);

        Log.e("Board", "help tab start!!");

        getAllNotice();

        chipList = new ArrayList<>();
        chipList.add(view.findViewById(R.id.chip_all));
        chipList.add(view.findViewById(R.id.chip_vegetable));
        chipList.add(view.findViewById(R.id.chip_fruit));
        chipList.add(view.findViewById(R.id.chip_flower));
        chipList.add(view.findViewById(R.id.chip_other));

        for (Button chip : chipList) {
            chip.setOnClickListener(v -> {
                selectFilter(chip);

                Log.e("boardActivity", filter);
            });
        }

        return view;
    }



    private void setList(List<BoardDetailDTO> list) {
        LinearLayout linearLayout = view.findViewById(R.id.layout_notice);
        linearLayout.removeAllViews();

        Log.e("board", "list size : " + list.size());



//        CardBoardHelpImage card;
        for (BoardDetailDTO notice : list) {
            LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(LAYOUT_INFLATER_SERVICE);
            LinearLayout card = (LinearLayout)layoutInflater.inflate(R.layout.card_board_help_image, linearLayout, false);
            card.setVisibility(View.VISIBLE);
            ((TextView)card.findViewById(R.id.agriculture)).setText(notice.getCrops());
            ((TextView)card.findViewById(R.id.title)).setText(notice.getTitle());
            ((TextView)card.findViewById(R.id.content)).setText(notice.getContent());
            ((TextView)card.findViewById(R.id.like_count)).setText(notice.getLikes()+"");
            ((TextView)card.findViewById(R.id.comment_count)).setText(notice.getReplies()+"");
            ((TextView)card.findViewById(R.id.time)).setText(notice.getUploadTime());

            card.setOnClickListener(v -> {
                Intent intent = new Intent(getActivity(), BoardDetailActivity.class);
                intent.putExtra("boardId", notice.getBoardId());
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
                noticeHelpListFilter(filter);
            }
        }
    }


    // 다 받아온다
    private void getAllNotice() {
        // 초기화 필요

        Call<ApiResponse> call = MyRetrofit.getApiService().noticeHelpList();
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                ObjectMapper mapper = new ObjectMapper();
                String body = response.body().getData().toString();
                String json = body.substring(1, body.length()-1).replace("\\", "");
                Log.e("board", "json -> " + json);
                try {
//                    List<BoardDetailDTO> dtos = Arrays.asList(mapper.readValue(json, BoardDetailDTO[].class));
                    setList(Arrays.asList(mapper.readValue(json, BoardDetailDTO[].class)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

            }
        });
    }

    // 도와줘요 필터링
    private void noticeHelpListFilter(String crops) {
        Log.e("noticeHelpListFilter", "get noticeHelpListFilter start!!");
        Call<ApiResponse> call = MyRetrofit.getApiService().noticeHelpListFilter(crops);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                Log.e("noticeHelpListFilter", "noticeHelpListFilter : ");
                Log.e("noticeHelpListFilter", "noticeHelpListFilter : " + response.body());
                ObjectMapper mapper = new ObjectMapper();
                String body = response.body().getData().toString();
                String json = body.substring(1, body.length()-1).replace("\\", "");
                Log.e("noticeHelpListFilter", " body -> " + body);
                Log.e("noticeHelpListFilter", " json -> " + json);
                try {
//                List<BoardDTO> dtos = mapper.readValue(json, BoardDTO[].class);
                    setList(Arrays.asList(mapper.readValue(json, BoardDetailDTO[].class)));
                } catch (Exception e1) {e1.printStackTrace();}

//                List<UserDTO> dtos = Arrays.asList(mapper.readValue(strList, UserDTO[].class));
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t)  {Log.e("workHome failed", t.getMessage() + "");}
        });
    }




}
