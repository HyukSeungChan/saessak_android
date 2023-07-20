package com.example.ssaesak.Main;

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
import com.example.ssaesak.Model.User;
import com.example.ssaesak.R;
import com.example.ssaesak.Retrofit.ApiResponse;
import com.example.ssaesak.Retrofit.MyRetrofit;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MypageActivityHistoryBoard extends Fragment {


    private List<Button> chipList;
    private String filter;

    private View view;


    public MypageActivityHistoryBoard() {
        Log.e("Board", "help tab construct start!!");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.activity_mypage_activity_history_empty, container, false);

        Log.e("Board", "help tab start!!");

        getAllNotice();


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




    // 다 받아온다
    private void getAllNotice() {
        // 초기화 필요

        Call<ApiResponse> call = MyRetrofit.getApiService().myNotice(User.getInstance().getUserId());
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


}
