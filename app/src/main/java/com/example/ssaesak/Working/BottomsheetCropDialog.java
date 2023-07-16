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
import androidx.fragment.app.FragmentManager;

import com.example.ssaesak.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BottomsheetCropDialog extends BottomSheetDialogFragment {

    private View view;

    private BottomSheetCropListener bottomSheetCropListener;

    private Button buttonOk;

    private String filterCrop;

    private List<String> cropList;

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInsranceState) {
        view = inflater.inflate(R.layout.bottomsheet_filter_crop, container, false);
        List<ArrayList> filter_list = init(view);
        this.cropList = new ArrayList<>();
        bottomSheetCropListener = (BottomSheetCropListener) getContext();

        // 레이아웃, 텍스트, 체크버튼 -> 3개가 같이 들어간 게 있고 그걸 여러개 가진 리스트

        this.filterCrop = getArguments().getString("crop");
        String[] selected = this.filterCrop.split(",");
        for (String s : selected) {
            cropList.add(s);
        }
        for (ArrayList list : filter_list) {
            for (String s : cropList) {
                if (((TextView)list.get(1)).toString().equals(s)) {
                    ((ImageView)list.get(2)).setVisibility(View.VISIBLE);
                }
            }
        }

        // 전체일 경우 나머지 제외 + 여러개 선택 가능 + 이미 선택되어있는 경우가 문제
        for (ArrayList list : filter_list) {
            LinearLayout linearLayout = (LinearLayout) list.get(0);
            TextView textView = (TextView) list.get(1);
            ImageView imageView = (ImageView) list.get(2);
            linearLayout.setOnClickListener(v -> {
                if (cropList.contains(textView.getText().toString())) {
//                if (filterArea.contains(textView.getText().toString())) {
//                    filterArea.replace(textView.getText().toString(), "");
                    cropList.remove(textView.getText().toString());
                    imageView.setVisibility(View.INVISIBLE);
                } else {
//                    filterArea += textView.getText().toString();
                    cropList.add(textView.getText().toString());
                    imageView.setVisibility(View.VISIBLE);
                }
            });
        }

        buttonOk = view.findViewById(R.id.button_ok);
        buttonOk.setOnClickListener(v -> {
            filterCrop = "";
            for (String area : cropList) {
                filterCrop += area + ",";
            }
            bottomSheetCropListener.onButtonCrop(filterCrop.substring(0, filterCrop.length()-1));
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
        temp = new ArrayList<>(); // layout, text, check
        temp.add(view.findViewById(R.id.layout_all));
        temp.add(view.findViewById(R.id.text_all));
        temp.add(view.findViewById(R.id.check_all));
        list.add(temp);

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
