package com.example.ssaesak.Working;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.ssaesak.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class BottomsheetCropDialog extends BottomSheetDialogFragment {

    private View view;

    private BottomSheetCropListener bottomSheetCropListener;

    private Button buttonOk;

    private String filter;

    private List<String> selectList;

    private LinearLayout allLayout;
    private TextView allTextview;
    private ImageView allCheck;

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInsranceState) {
        view = inflater.inflate(R.layout.bottomsheet_filter_crop, container, false);
        List<ArrayList> filter_list = init(view);
//        this.selectList = new ArrayList<>();

        allLayout = (view.findViewById(R.id.layout_all));
        allTextview = (view.findViewById(R.id.text_all));
        allCheck = (view.findViewById(R.id.check_all));
        this.selectList = new ArrayList<>();
        this.selectList.add("작목 전체");
        allLayout.setSelected(true);
        allCheck.setVisibility(View.VISIBLE);
        Log.e("bottom", getContext().toString());

        allLayout.setOnClickListener( v -> {


            Log.e("filter", "전체 필터 클릭!! 이미 선택중!!");
            allLayout.setSelected(true);
            allCheck.setVisibility(View.VISIBLE);
            selectList = new ArrayList<>();
            selectList.add("충청북도 전체");
            for (ArrayList listOther : filter_list) {
                ((LinearLayout) listOther.get(0)).setSelected(false);
//                            ((TextView) list.get(1));
                ((ImageView) listOther.get(2)).setVisibility(View.INVISIBLE);
            }
        });

        bottomSheetCropListener = (BottomSheetCropListener) getContext();
        this.filter = getArguments().getString("crop");

        for (String s : filter.split(",")) {
            for (ArrayList list : filter_list) {
                LinearLayout linearLayout = (LinearLayout) list.get(0);
                TextView textView = (TextView) list.get(1);
                ImageView imageView = (ImageView) list.get(2);
                if (textView.getText().toString().equals(s)) {
                    selectList.add(textView.getText().toString());
                    allLayout.setSelected(false);
                    allCheck.setVisibility(View.INVISIBLE);
                    linearLayout.setSelected(true);
                    imageView.setVisibility(View.VISIBLE);
                }
            }
        }

        // 전체일 경우 나머지 제외 + 여러개 선택 가능 + 이미 선택되어있는 경우가 문제
        for (ArrayList list : filter_list) {
            LinearLayout linearLayout = (LinearLayout) list.get(0);
            TextView textView = (TextView) list.get(1);
            ImageView imageView = (ImageView) list.get(2);
            linearLayout.setOnClickListener(v -> {
                if (selectList.contains(textView.getText().toString())) {
//                if (filterArea.contains(textView.getText().toString())) {
//                    filterArea.replace(textView.getText().toString(), "");
                    allLayout.setSelected(false);
                    allCheck.setVisibility(View.INVISIBLE);
                    selectList.remove(textView.getText().toString());
                    imageView.setVisibility(View.INVISIBLE);
                } else {
                    selectList.remove("작목 전체");
                    allLayout.setSelected(false);
                    allCheck.setVisibility(View.INVISIBLE);
//                    filterArea += textView.getText().toString();
                    selectList.add(textView.getText().toString());
                    imageView.setVisibility(View.VISIBLE);
                }
            });
        }

        buttonOk = view.findViewById(R.id.button_ok);
        buttonOk.setOnClickListener(v -> {
            filter = "";
            for (String area : selectList) {
                filter += area + ",";
            }

            if (filter.length() > 0) {
                bottomSheetCropListener.onButtonCrop(filter.substring(0, filter.length() - 1));
            } else {
                filter = "충청북도 전체";
            }

//            bottomSheetCropListener.onButtonCrop(filterCrop.substring(0, filterCrop.length()-1));
            dismiss();
        });

        return view;
    }

    public interface BottomSheetCropListener {
        void onButtonCrop(String filterCrop);
    }


    private List<ArrayList> init(View view) {
        List<ArrayList> list = new ArrayList<>();
        ArrayList<View> temp;
//        temp = new ArrayList<>(); // layout, text, check
//        temp.add(view.findViewById(R.id.layout_all));
//        temp.add(view.findViewById(R.id.text_all));
//        temp.add(view.findViewById(R.id.check_all));
//        list.add(temp);

        temp = new ArrayList<>(); // layout, text, check
        temp.add(view.findViewById(R.id.layout_shdwkranf));
        temp.add(view.findViewById(R.id.text_shdwkranf));
        temp.add(view.findViewById(R.id.check_shdwkranf));
        list.add(temp);

        temp = new ArrayList<>(); // layout, text, check
        temp.add(view.findViewById(R.id.layout_rhktlf));
        temp.add(view.findViewById(R.id.text_rhktlf));
        temp.add(view.findViewById(R.id.check_rhktlf));
        list.add(temp);

        temp = new ArrayList<>(); // layout, text, check
        temp.add(view.findViewById(R.id.layout_ghkgnp));
        temp.add(view.findViewById(R.id.text_ghkgnp));
        temp.add(view.findViewById(R.id.check_ghkgnp));
        list.add(temp);


        temp = new ArrayList<>(); // layout, text, check
        temp.add(view.findViewById(R.id.layout_rlxk));
        temp.add(view.findViewById(R.id.text_rlxk));
        temp.add(view.findViewById(R.id.check_rlxk));
        list.add(temp);

        return list;
    }
}
