package com.example.ssaesak.Main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.ssaesak.Board.BoardStoryActivity;
import com.example.ssaesak.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MypageActivityHistoryActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager2 viewPager;

    private LinearLayout movePost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        tabLayout = findViewById(R.id.layout_tab);
        viewPager = findViewById(R.id.pager_content);
        movePost = findViewById(R.id.button_post);

        // ViewPager2 어댑터 설정
        MyPagerAdapter pagerAdapter = new MyPagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);

        // TabLayout과 ViewPager2 연동
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            // 각 탭의 타이틀 설정 (TabLayout에 탭의 이름 설정)
            switch (position) {
                case 0:
                    tab.setText("내가 쓴 글");
                    break;
                case 1:
                    tab.setText("댓글");
                    break;
            }
        }).attach();



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
                    return new MypageActivityHistoryBoard();
                case 1:
                    return new MypageActivityHistoryComment();
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
