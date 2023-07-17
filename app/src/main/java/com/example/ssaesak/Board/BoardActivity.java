package com.example.ssaesak.Board;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.ssaesak.Farmgroup.FarmgroupActivity;
import com.example.ssaesak.Main.MainActivity;
import com.example.ssaesak.R;
import com.example.ssaesak.Study.StudyActivity;
import com.example.ssaesak.Working.WorkingActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class BoardActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager2 viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        tabLayout = findViewById(R.id.layout_tab);
        viewPager = findViewById(R.id.pager_content);

        // ViewPager2 어댑터 설정
        MyPagerAdapter pagerAdapter = new MyPagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);

        // TabLayout과 ViewPager2 연동
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            // 각 탭의 타이틀 설정 (TabLayout에 탭의 이름 설정)
            switch (position) {
                case 0:
                    tab.setText("농촌 이야기");
                    break;
                case 1:
                    tab.setText("도와줘요");
                    break;
            }
        }).attach();

        // 내비 표시
        BottomNavigationView navigation = findViewById(R.id.fragment_bottom);
        Menu menu = navigation.getMenu();
        for (int i = 0; i < navigation.getMenu().size(); i++) {
            MenuItem menuItem = menu.getItem(i);
            if (menuItem.getItemId() == R.id.fragment_notice) {menuItem.setChecked(true);}
        }

        navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.fragment_home) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                } else if (item.getItemId() == R.id.fragment_working) {
                    startActivity(new Intent(getApplicationContext(), WorkingActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                } else if (item.getItemId() == R.id.fragment_farm) {
                    startActivity(new Intent(getApplicationContext(), FarmgroupActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                } else if (item.getItemId() == R.id.fragment_notice) {
//                    startActivity(new Intent(getApplicationContext(), BoardActivity.class));
//                    overridePendingTransition(0, 0);
                    return true;
                } else if (item.getItemId() == R.id.fragment_study) {
                    startActivity(new Intent(getApplicationContext(), StudyActivity.class));
                    overridePendingTransition(0, 0);
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


    // ViewPager 어댑터 정의
    private static class MyPagerAdapter extends FragmentStateAdapter {
        public MyPagerAdapter(@NonNull AppCompatActivity activity) {
            super(activity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            // 각 탭에 대응하는 프래그먼트를 반환
            switch (position) {
                case 0:
                    return new BoardStoryActivity();
                case 1:
                    return new BoardHelpActivity();
                default:
                    return null;
            }
        }

        @Override
        public int getItemCount() {
            // 탭의 개수 반환
            return 2;
        }
    }
}
