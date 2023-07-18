package com.example.ssaesak.Board;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.ssaesak.R;

import java.util.ArrayList;
import java.util.List;

public class BoardStoryActivity extends Fragment {


    private List<Button> chipList;
    private String filter;


    public BoardStoryActivity() {
        Log.e("Board", "stroy tab construct start!!");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_board_story, container, false);

        Log.e("Board", "story tab start!!");

        return inflater.inflate(R.layout.activity_board_story, container, false);
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



}
