package com.example.ssaesak.Working;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.annotation.NonNull;

import com.example.ssaesak.Adapter.AgricultureAdapter;
import com.example.ssaesak.Adapter.CareerAdapter;
import com.example.ssaesak.Adapter.CropAdapter;
import com.example.ssaesak.Common.Constants;
import com.example.ssaesak.MainActivity;
import com.example.ssaesak.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WorkingActivity  extends Activity {

    private final String TAG = this.getClass().getSimpleName();

    private Spinner spinnerAgriculture;
    private Spinner spinnerCrop;
    private Spinner spinnerCareer;
    private AgricultureAdapter adapterAgriculture;
    private CropAdapter adapterCrop;
    private CareerAdapter adapterCareer;
    private String selectedAgriculture;
    private String selectedCrop;
    private String selectedCareer;

    private LinearLayout noticeList;
    private LayoutInflater layoutInflater;
    private LinearLayout card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_notice);

        spinnerAgriculture = findViewById(R.id.spinner_agriculture);
        spinnerCrop = findViewById(R.id.spinner_crop);
        spinnerCareer = findViewById(R.id.spinner_career);

        this.noticeList = findViewById(R.id.layout_notice);

        this.layoutInflater = LayoutInflater.from(getApplicationContext());
        CardWorkNotice cardView = new CardWorkNotice(getApplicationContext());

        List<CardWorkNotice> cardViewList = new ArrayList<>();
        cardViewList.add(cardView);
//        this.card = (ConstraintLayout)layoutInflater.inflate(R.layout.card_work_notice, null, false);

//        this.layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
//        this.card = (LinearLayout)layoutInflater.inflate(R.layout.card_work_notice, this.noticeList, false);
//        this.card.setVisibility(View.VISIBLE);

        this.noticeList.addView(cardView.getCard());

        // 스피너에 붙일 어댑터 초기화
        adapterAgriculture = new AgricultureAdapter(this, new ArrayList<>(Arrays.asList(Constants.agricultureList)));
        adapterCrop = new CropAdapter(this, new ArrayList<>(Arrays.asList(Constants.cropList)));
        adapterCareer = new CareerAdapter(this, new ArrayList<>(Arrays.asList(Constants.careerList)));

        spinnerAgriculture.setAdapter(adapterAgriculture);
        spinnerCrop.setAdapter(adapterCrop);
        spinnerCareer.setAdapter(adapterCareer);

        // 스피너 클릭 리스너
        spinnerAgriculture.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedAgriculture = adapterAgriculture.getItem();
//                String otherItem = (String) spinner.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        spinnerCrop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCrop = adapterCrop.getItem();
//                String otherItem = (String) spinner.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });


        spinnerCareer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCareer = adapterCareer.getItem();
//                String otherItem = (String) spinner.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });



        // 내비 표시
        BottomNavigationView navigation = findViewById(R.id.fragment_bottom);
        Menu menu = navigation.getMenu();
        for (int i = 0; i < navigation.getMenu().size(); i++) {
            MenuItem menuItem = menu.getItem(i);
            if (menuItem.getItemId() == R.id.fragment_working) {menuItem.setChecked(true);}
        }



        // 바텀 네비게이션 객체 선언
        navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.fragment_home) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    overridePendingTransition(0, 0);
                    finish();
                    return true;
                } else if (item.getItemId() == R.id.fragment_working) {
//                    startActivity(new Intent(getApplicationContext(), WorkingActivity.class));
//                    overridePendingTransition(0, 0);
                    return true;
                } else if (item.getItemId() == R.id.fragment_farm) {
//                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_screen, fragment_farm).commitAllowingStateLoss();
                    return true;
                } else if (item.getItemId() == R.id.fragment_notice) {
//                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_screen, fragment_notice).commitAllowingStateLoss();
                    return true;
                } else if (item.getItemId() == R.id.fragment_study) {
//                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_screen, fragment_study).commitAllowingStateLoss();
                    return true;
                } else {
                    return false;
                }
            }
        });


    }

    @Override
    public void onBackPressed() {
        this.startActivity(new Intent(this, MainActivity.class));

        finish();
        overridePendingTransition(0, 0); //애니메이션 없애기
    }
}
