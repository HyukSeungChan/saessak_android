package com.example.ssaesak.Login;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.ssaesak.Model.Farm;
import com.example.ssaesak.R;

import java.util.ArrayList;
import java.util.List;

public class SignupFarmerAgricultureActivity extends Activity {

    String name;
    List<Button> buttonCropList;
    List<Button> buttonInterestList;
    List<Button> cropList; // 농업구분 선택
    List<Button> interestList; // 희망작목 선택

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_worker_agriculture);

        this.buttonCropList = new ArrayList<>();
        this.buttonInterestList = new ArrayList<>();
        this.cropList = new ArrayList<>();
        this.interestList = new ArrayList<>();

        ImageButton backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), SignupFarmerActivity.class));
                overridePendingTransition(0, 0);
                finish();
            }
        });

        Button nextButton = findViewById(R.id.button_next);
        Button afterButton = findViewById(R.id.button_after);
        afterButton.setVisibility(View.INVISIBLE);
//        afterButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getBaseContext(), SignupWorkerCropActivity.class));
//                overridePendingTransition(0, 0);
//                finish();
//            }
//        });

        buttonCropList.add(findViewById(R.id.chip_qkxshdtk));
        buttonCropList.add(findViewById(R.id.chip_rhktn));
        buttonCropList.add(findViewById(R.id.chip_gkdntm));
        buttonCropList.add(findViewById(R.id.chip_shs));
        buttonCropList.add(findViewById(R.id.chip_xmrdydwkranf));
        buttonCropList.add(findViewById(R.id.chip_rlxk_shddjq));
        for (Button button : buttonCropList) {
            button.setOnClickListener(v -> {
                if (cropList.contains(button)) {
                    Log.e("area", "press button!!");
                    button.setPressed(false);
                    button.setBackground(getResources().getDrawable(R.drawable.chip_not_select, null));
                    button.setTextColor(Color.rgb(120, 120, 120));
                    cropList.remove(button);
                } else if (cropList.size() < 3){
                    Log.e("area", "not press button!!");
                    button.setPressed(true);
                    button.setBackground(getResources().getDrawable(R.drawable.chip_select, null));
                    button.setTextColor(Color.rgb(255, 255, 255));
                    cropList.add(button);
                }

                if (cropList.size() > 0 && interestList.size() > 0) {
                    nextButton.setClickable(true);
                    nextButton.setBackground(getResources().getDrawable(R.drawable.button_primary, null));
                } else {
                    nextButton.setClickable(false);
                    nextButton.setBackground(getResources().getDrawable(R.drawable.button_gray, null));
                }
            });
        }

        buttonInterestList.add(findViewById(R.id.chip_shdwkranfcoth));
        buttonInterestList.add(findViewById(R.id.chip_rhktlf));
        buttonInterestList.add(findViewById(R.id.chip_ghkgnpwkranf));
        buttonInterestList.add(findViewById(R.id.chip_rlxk_wkrahr));
        for (Button button : buttonInterestList) {
            button.setOnClickListener(v -> {
                if (interestList.contains(button)) {
                    Log.e("area", "press button!!");
                    button.setPressed(false);
                    button.setBackground(getResources().getDrawable(R.drawable.chip_not_select, null));
                    button.setTextColor(Color.rgb(120, 120, 120));
                    interestList.remove(button);
                } else if (interestList.size() < 3){
                    Log.e("area", "not press button!!");
                    button.setPressed(true);
                    button.setBackground(getResources().getDrawable(R.drawable.chip_select, null));
                    button.setTextColor(Color.rgb(255, 255, 255));
                    interestList.add(button);
                }

                if (cropList.size() > 0 && interestList.size() > 0) {
                    nextButton.setClickable(true);
                    nextButton.setBackground(getResources().getDrawable(R.drawable.button_primary, null));
                } else {
                    nextButton.setClickable(false);
                    nextButton.setBackground(getResources().getDrawable(R.drawable.button_gray, null));
                }
            });
        }

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String crops = "";
                for (Button button : cropList) {
                    crops += button.getText().toString();
                }
                crops = crops.substring(0, crops.length()-1);
                Farm.getInstance().setCrops(crops);

                String agricultures = "";
                for (Button button : interestList) {
                    agricultures += button.getText().toString();
                }
                agricultures = agricultures.substring(0, crops.length()-1);
                Farm.getInstance().setAgriculture(agricultures);

                startActivity(new Intent(getBaseContext(), SignupFarmerCropActivity.class));
                overridePendingTransition(0, 0);
                finish();
            }
        });
    }
}
