package com.example.ssaesak.Main;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.ssaesak.Dto.WorkNoticeRecommendDTO;

public class MyAdapter extends FragmentStateAdapter {

    public int mCount;
    public WorkNoticeRecommendDTO work1;
    public WorkNoticeRecommendDTO work2;

    public MyAdapter(FragmentActivity fa, int count, WorkNoticeRecommendDTO work1, WorkNoticeRecommendDTO work2) {
        super(fa);
        mCount = count;
        this.work1 = work1;
        this.work2 = work2;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        int index = getRealPosition(position);

        if(index==0) return new recommendCard1(work1);
        else return new recommendCard1(work2);

    }

    @Override
    public int getItemCount() {
        return 2000;
    }

    public int getRealPosition(int position) { return position % mCount; }

}