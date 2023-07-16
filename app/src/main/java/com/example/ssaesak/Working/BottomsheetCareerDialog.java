package com.example.ssaesak.Working;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.ssaesak.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class BottomsheetCareerDialog extends BottomSheetDialogFragment {

    private View view;

    private BottomSheetCareerListener bottomSheetCareerListener;

    private String filterCareer;

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInsranceState) {
        view = inflater.inflate(R.layout.bottomsheet_filter_career, container, false);
        List<ArrayList> filter_list = init(view);
        bottomSheetCareerListener = (BottomSheetCareerListener) getContext();

        // 레이아웃, 텍스트, 체크버튼 -> 3개가 같이 들어간 게 있고 그걸 여러개 가진 리스트

        this.filterCareer = getArguments().getString("career");



        // 전체일 경우 나머지 제외 + 여러개 선택 가능 + 이미 선택되어있는 경우가 문제
        for (ArrayList list : filter_list) {
            LinearLayout linearLayout = (LinearLayout) list.get(0);
            TextView textView = (TextView) list.get(1);
            ImageView imageView = (ImageView) list.get(2);
            linearLayout.setOnClickListener(v -> {
                bottomSheetCareerListener.onButtonCareer(textView.getText().toString());
                dismiss();
            });

        }

        return view;
    }

    public interface BottomSheetCareerListener {
        void onButtonCareer(String filterCareer);
    }

    private List<ArrayList> init(View view) {
        List<ArrayList> list = new ArrayList<>();
        ArrayList<View> temp;
        temp = new ArrayList<>(); // layout, text, check
        temp.add(view.findViewById(R.id.layout_all));
        temp.add(view.findViewById(R.id.text_all));
        temp.add(view.findViewById(R.id.check_all));
        list.add(temp);

        temp = new ArrayList<>(); // layout, text, check
        temp.add(view.findViewById(R.id.layout_012));
        temp.add(view.findViewById(R.id.text_012));
        temp.add(view.findViewById(R.id.check_012));
        list.add(temp);

        temp = new ArrayList<>(); // layout, text, check
        temp.add(view.findViewById(R.id.layout_13));
        temp.add(view.findViewById(R.id.text_13));
        temp.add(view.findViewById(R.id.check_13));
        list.add(temp);

        temp = new ArrayList<>(); // layout, text, check
        temp.add(view.findViewById(R.id.layout_35));
        temp.add(view.findViewById(R.id.text_35));
        temp.add(view.findViewById(R.id.check_35));
        list.add(temp);

        temp = new ArrayList<>(); // layout, text, check
        temp.add(view.findViewById(R.id.layout_5));
        temp.add(view.findViewById(R.id.text_5));
        temp.add(view.findViewById(R.id.check_5));
        list.add(temp);

        return list;
    }
}
