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

public class BottomsheetAreaDialog extends BottomSheetDialogFragment {

    private View view;

    private BottomSheetAreaListener bottomSheetListener;

    private Button buttonOk;

    private String filterArea;

    private List<String> areaList;

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInsranceState) {
        view = inflater.inflate(R.layout.bottomsheet_filter_area, container, false);
        List<ArrayList> filter_list = init(view);
        this.areaList = new ArrayList<>();
        Log.e("bottom", getContext().toString());
        bottomSheetListener = (BottomSheetAreaListener) getContext();

        // 레이아웃, 텍스트, 체크버튼 -> 3개가 같이 들어간 게 있고 그걸 여러개 가진 리스트

        this.filterArea = getArguments().getString("area");
//        this.filterAgriculture = getArguments().getString("agriculture");
//        this.filterCrop = getArguments().getString("crop");
//        this.filterCareer = getArguments().getString("career");


        // 전체일 경우 나머지 제외 + 여러개 선택 가능 + 이미 선택되어있는 경우가 문제
        for (ArrayList list : filter_list) {
            LinearLayout linearLayout = (LinearLayout) list.get(0);
            TextView textView = (TextView) list.get(1);
            ImageView imageView = (ImageView) list.get(2);
            linearLayout.setOnClickListener(v -> {
                if (areaList.contains(textView.getText().toString())) {
                    areaList.remove(textView.getText().toString());
                    imageView.setVisibility(View.INVISIBLE);
                } else {
                    areaList.add(textView.getText().toString());
                    imageView.setVisibility(View.VISIBLE);
                }
            });
        }

        buttonOk = view.findViewById(R.id.button_ok);
        buttonOk.setOnClickListener(v -> {
            filterArea = "";
            for (String area : areaList) {
                filterArea += area + ",";
            }
            bottomSheetListener.onButtonArea(filterArea.substring(0, filterArea.length()-1));
            dismiss();
        });

        return view;
    }


    public interface BottomSheetAreaListener {
        void onButtonArea(String filterArea);
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
        temp.add(view.findViewById(R.id.layout_rhltks));
        temp.add(view.findViewById(R.id.text_rhltks));
        temp.add(view.findViewById(R.id.check_rhltks));
        list.add(temp);

        temp = new ArrayList<>(); // layout, text, check
        temp.add(view.findViewById(R.id.layout_eksdid));
        temp.add(view.findViewById(R.id.text_eksdid));
        temp.add(view.findViewById(R.id.check_eksdid));
        list.add(temp);

        temp = new ArrayList<>(); // layout, text, check
        temp.add(view.findViewById(R.id.layout_qhdms));
        temp.add(view.findViewById(R.id.text_qhdms));
        temp.add(view.findViewById(R.id.check_qhdms));
        list.add(temp);

        temp = new ArrayList<>(); // layout, text, check
        temp.add(view.findViewById(R.id.layout_dudehd));
        temp.add(view.findViewById(R.id.text_dudehd));
        temp.add(view.findViewById(R.id.check_dudehd));
        list.add(temp);

        temp = new ArrayList<>(); // layout, text, check
        temp.add(view.findViewById(R.id.layout_dhrcjs));
        temp.add(view.findViewById(R.id.text_dhrcjs));
        temp.add(view.findViewById(R.id.check_dhrcjs));
        list.add(temp);

        temp = new ArrayList<>(); // layout, text, check
        temp.add(view.findViewById(R.id.layout_dmatjd));
        temp.add(view.findViewById(R.id.text_dmatjd));
        temp.add(view.findViewById(R.id.check_dmatjd));
        list.add(temp);

        temp = new ArrayList<>(); // layout, text, check
        temp.add(view.findViewById(R.id.layout_wpcjs));
        temp.add(view.findViewById(R.id.text_wpcjs));
        temp.add(view.findViewById(R.id.check_wpcjs));
        list.add(temp);

        temp = new ArrayList<>(); // layout, text, check
        temp.add(view.findViewById(R.id.layout_wmdvud));
        temp.add(view.findViewById(R.id.text_wmdvud));
        temp.add(view.findViewById(R.id.check_wmdvud));
        list.add(temp);

        temp = new ArrayList<>(); // layout, text, check
        temp.add(view.findViewById(R.id.layout_wlscjs));
        temp.add(view.findViewById(R.id.text_wlscjs));
        temp.add(view.findViewById(R.id.check_wlscjs));
        list.add(temp);

        temp = new ArrayList<>(); // layout, text, check
        temp.add(view.findViewById(R.id.layout_cjdwn));
        temp.add(view.findViewById(R.id.text_cjdwn));
        temp.add(view.findViewById(R.id.check_cjdwn));
        list.add(temp);

        temp = new ArrayList<>(); // layout, text, check
        temp.add(view.findViewById(R.id.layout_cndwn));
        temp.add(view.findViewById(R.id.text_cndwn));
        temp.add(view.findViewById(R.id.check_cndwn));
        list.add(temp);

        return list;
    }
}
