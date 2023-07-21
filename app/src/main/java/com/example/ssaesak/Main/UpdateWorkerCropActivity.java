package com.example.ssaesak.Main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ssaesak.Dto.WorkerDTO;
import com.example.ssaesak.Login.SignupWorkerAgricultureActivity;
import com.example.ssaesak.Login.SignupWorkerPayActivity;
import com.example.ssaesak.Model.Worker;
import com.example.ssaesak.R;
import com.example.ssaesak.Retrofit.MyRetrofit;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateWorkerCropActivity extends Activity {

    List<View> filterList; // 리니어레이아웃 + 텍스트뷰
    List<String> filterTextList;
    List<String> filterNameList; // 선택한 거 이름

    List<List<View>> parentList; // 다 넣은 거

    String filter;
    int i;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_worker_crop);


        filterTextList = new ArrayList<>();
        parentList = new ArrayList<>();

        filterList = new ArrayList<>();
        filterList.add(findViewById(R.id.potato));
        filterList.add(findViewById(R.id.potato_text));
        parentList.add(filterList);

        filterList = new ArrayList<>();
        filterList.add(findViewById(R.id.spicy));
        filterList.add(findViewById(R.id.spicy_text));
        parentList.add(filterList);

        filterList = new ArrayList<>();
        filterList.add(findViewById(R.id.onion));
        filterList.add(findViewById(R.id.onion_text));
        parentList.add(filterList);

        filterList = new ArrayList<>();
        filterList.add(findViewById(R.id.garlic));
        filterList.add(findViewById(R.id.garlic_text));
        parentList.add(filterList);

        filterList = new ArrayList<>();
        filterList.add(findViewById(R.id.cabbage));
        filterList.add(findViewById(R.id.cabbage_text));
        parentList.add(filterList);

        filterList = new ArrayList<>();
        filterList.add(findViewById(R.id.strawberry));
        filterList.add(findViewById(R.id.strawberry_text));
        parentList.add(filterList);

        filterList = new ArrayList<>();
        filterList.add(findViewById(R.id.pear));
        filterList.add(findViewById(R.id.pear_text));
        parentList.add(filterList);

        filterList = new ArrayList<>();
        filterList.add(findViewById(R.id.apple));
        filterList.add(findViewById(R.id.apple_text));
        parentList.add(filterList);

        filterList = new ArrayList<>();
        filterList.add(findViewById(R.id.watermelon));
        filterList.add(findViewById(R.id.watermelon_text));
        parentList.add(filterList);

        filterList = new ArrayList<>();
        filterList.add(findViewById(R.id.orrange));
        filterList.add(findViewById(R.id.orange_text));
        parentList.add(filterList);

        filterList = new ArrayList<>();
        filterList.add(findViewById(R.id.rice));
        filterList.add(findViewById(R.id.rice_text));
        parentList.add(filterList);

        filterList = new ArrayList<>();
        filterList.add(findViewById(R.id.barley));
        filterList.add(findViewById(R.id.barley_text));
        parentList.add(filterList);

        filterList = new ArrayList<>();
        filterList.add(findViewById(R.id.corn));
        filterList.add(findViewById(R.id.corn_text));
        parentList.add(filterList);

        filterList = new ArrayList<>();
        filterList.add(findViewById(R.id.bean));
        filterList.add(findViewById(R.id.bean_text));
        parentList.add(filterList);

        filter = "";
        filterNameList = new ArrayList<>();
        Log.e("crop", parentList.size() + "");
        for (List<View> list : parentList) {

//            Log.e("crop", ((TextView) list.get(0)).getId() + "");
            list.get(0).setOnClickListener(v -> {
                Log.e("crop", ((TextView) list.get(1)).getText().toString());
                if (((LinearLayout) list.get(0)).isSelected()) {
                    filterNameList.remove(((TextView) list.get(1)).getText().toString());
                    ((LinearLayout) list.get(0)).setSelected(false);
                    ((LinearLayout) list.get(0)).setBackground(getResources().getDrawable(R.drawable.background_gray_ellipse, null));
                } else {
                    filterNameList.add(((TextView) list.get(1)).getText().toString());
                    ((LinearLayout) list.get(0)).setSelected(true);
                    ((LinearLayout) list.get(0)).setBackground(getResources().getDrawable(R.drawable.background_primary_ellipse, null));

                }
            });
        }


//        for (i = 0; i < filterList.size(); i++) {
//            Log.e("crop", "i before : " + i);
//            filterList.get(i).setOnClickListener(v -> {
//                Log.e("crop", "i after : " + i);
//                Log.e("crop", "isselect : " + filterList.get(i).isSelected());
//                Log.e("crop", "name : " + filterTextList.get(i));
//                if (filterList.get(i).isSelected()) {
//                    filterNameList.remove(filterTextList.get(i));
//                    filterList.get(i).setSelected(false);
//                    filterList.get(i).setBackground(getResources().getDrawable(R.drawable.background_gray_ellipse, null));
//                } else {
//                    filterNameList.add(filterTextList.get(i));
//                    filterList.get(i).setSelected(true);
//                    filterList.get(i).setBackground(getResources().getDrawable(R.drawable.background_primary_ellipse, null));
//                }
//            });
//        }
















        ImageButton backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), SignupWorkerAgricultureActivity.class));
                overridePendingTransition(0, 0);
                finish();
            }
        });

        Button nextButton = findViewById(R.id.button_next);
        Button afterButton = findViewById(R.id.button_after);
        afterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), SignupWorkerPayActivity.class));
                overridePendingTransition(0, 0);
                finish();
            }
        });


        for (String s : filterNameList) {
            if (Worker.getInstance().getAgriculture().contains(s)) {
                Log.e("signup", s + " size : " + filterNameList.size());

            }
            //Worker.getInstance().getArea().split(",")
        }



        for (List<View> list : parentList) {

            for (String s : Worker.getInstance().getInterestCrops().split(",")) {
                if (((TextView) list.get(1)).getText().toString().contains(s)) {
                    Log.e("signup", s + " size : " + filterNameList.size());
                    ((LinearLayout) list.get(0)).setSelected(true);
                    ((LinearLayout) list.get(0)).setBackground(getResources().getDrawable(R.drawable.background_primary_ellipse, null));

                }
                //Worker.getInstance().getArea().split(",")
            }
            
        }

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                if (editText.getText().toString().length() < 1) {
//                    Toast.makeText(getBaseContext(), "이름을 입력해주세요", Toast.LENGTH_SHORT);
//                } else {
//                    User.getInstance().setPhone(editText.getText().toString());
                String filter = "";
                for (String s : filterNameList) {
                    filter += s + ",";
                }


                if (filterNameList.size() > 1) filter = filter.substring(0, filter.length()-1);
                Log.e("signup", filter);
                Worker.getInstance().setInterestCrops(filter);



                startActivity(new Intent(getBaseContext(), SignupWorkerPayActivity.class));
                overridePendingTransition(0, 0);
                finish();
//                }
            }
        });
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(getBaseContext(), MypageUpdateWorker.class));
        overridePendingTransition(0, 0);
        finish();
    }
}