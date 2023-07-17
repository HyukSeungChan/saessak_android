package com.example.ssaesak.Board;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.ssaesak.Farmgroup.FarmgroupActivity;
import com.example.ssaesak.Main.MainActivity;
import com.example.ssaesak.R;
import com.example.ssaesak.Study.StudyActivity;
import com.example.ssaesak.Working.WorkingActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class BoardHelpActivity extends Fragment {


    private List<Button> chipList;
    private String filter;


    public BoardHelpActivity() {
        Log.e("Board", "help tab construct start!!");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_board_help, container, false);

        Log.e("Board", "help tab start!!");

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
