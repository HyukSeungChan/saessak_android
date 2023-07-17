package com.example.ssaesak.Main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.ssaesak.Dto.WorkNoticeRecommendDTO;
import com.example.ssaesak.R;

public class recommendCard1 extends Fragment {

    private WorkNoticeRecommendDTO work;

    private ImageView image;
    private TextView farmName;
    private TextView farmInfo;
    private TextView agriculture;
    private TextView crop;


    public recommendCard1(WorkNoticeRecommendDTO work) {
        this.work = work;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.card_recommend_work_notice, container, false);

        this.image = rootView.findViewById(R.id.image);
        Glide.with(this)
                .load(work.getFarmImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL) // 캐시 옵션 설정
                .into(image);

        this.farmName = rootView.findViewById(R.id.farm_name);
        this.farmName.setText(work.getName());
        this.farmInfo = rootView.findViewById(R.id.farm_info);
        this.farmInfo.setText(work.getAddress());
        this.agriculture = rootView.findViewById(R.id.agriculture);
        this.agriculture.setText(work.getAgriculture());
        this.crop = rootView.findViewById(R.id.crop);
        this.crop.setText(work.getCrops());

        return rootView;
    }

}
