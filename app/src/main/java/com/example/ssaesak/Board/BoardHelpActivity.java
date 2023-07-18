package com.example.ssaesak.Board;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.ssaesak.Dto.BoardDTO;
import com.example.ssaesak.Dto.WorkListDTO;
import com.example.ssaesak.Farmgroup.FarmgroupActivity;
import com.example.ssaesak.Main.MainActivity;
import com.example.ssaesak.R;
import com.example.ssaesak.Retrofit.ApiResponse;
import com.example.ssaesak.Retrofit.MyRetrofit;
import com.example.ssaesak.Study.StudyActivity;
import com.example.ssaesak.Working.WorkingActivity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

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

        return inflater.inflate(R.layout.activity_board_help, container, false);
    }



    private void setList(List<BoardDTO> list) {
        LinearLayout linearLayout = view.findViewById(R.id.layout_notice);
        linearLayout.removeAllViews();
        LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(LAYOUT_INFLATER_SERVICE);

        Log.e("board", "list size : " + list.size());
//        CardBoardHelpImage card;
        for (BoardDTO notice : list) {
            LinearLayout card = (LinearLayout)layoutInflater.inflate(R.layout.card_board_help_image, linearLayout, false);
            card.setVisibility(View.VISIBLE);
            ((TextView)card.findViewById(R.id.agriculture)).setText(notice.getAgriculture());
            ((TextView)card.findViewById(R.id.title)).setText(notice.getTitle());
            ((TextView)card.findViewById(R.id.content)).setText(notice.getContent());
//            if (notice.getImage() == null) {
//                ((ImageView)card.findViewById(R.id.image)).setVisibility(View.GONE);
//                ((TextView)card.findViewById(R.id.content)).setMaxLines(2);
//            } else {
//                Glide.with(getContext())
//                        .load(notice.getImage())
//                        .diskCacheStrategy(DiskCacheStrategy.ALL) // 캐시 옵션 설정
//                        .into(((ImageView)card.findViewById(R.id.image)));
//
//            }

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


    private void getAllNotice() {
        // 초기화 필요

        Call<ApiResponse> call = MyRetrofit.getApiService().noticeHelpList();
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                ObjectMapper mapper = new ObjectMapper();
                String body = response.body().getData().toString();
                String json = body.replace("\\", "");
                Log.e("board", "json -> " + json);
                try {
                    setList(Arrays.asList(mapper.readValue(json, BoardDTO[].class)));
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
