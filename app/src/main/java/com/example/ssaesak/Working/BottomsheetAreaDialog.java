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

    private String filter;

    private List<String> selectList;

    private LinearLayout allLayout;
    private TextView allTextview;
    private ImageView allCheck;

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInsranceState) {
        view = inflater.inflate(R.layout.bottomsheet_filter_area, container, false);
        List<ArrayList> filter_list = init(view);

        allLayout = (view.findViewById(R.id.layout_all));
        allTextview = (view.findViewById(R.id.text_all));
        allCheck = (view.findViewById(R.id.check_all));
        this.selectList = new ArrayList<>();
        this.selectList.add("충청북도 전체");
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


        bottomSheetListener = (BottomSheetAreaListener) getContext();
        this.filter = getArguments().getString("area");
        Log.e("filter", "start filter!! " + filter);

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
                        allLayout.setSelected(false);
                        allCheck.setVisibility(View.INVISIBLE);
                        selectList.remove(textView.getText().toString());
                        imageView.setVisibility(View.INVISIBLE);
//                    }

                } else {
                        selectList.remove("충청북도 전체");
                        allLayout.setSelected(false);
                        allCheck.setVisibility(View.INVISIBLE);
                        selectList.add(textView.getText().toString());
                        imageView.setVisibility(View.VISIBLE);
//                    }
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
                bottomSheetListener.onButtonArea(filter.substring(0, filter.length() - 1));
            } else {
                filter = "충청북도 전체";
            }
            Log.e("filter", "end filter!! " + filter);
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
//        temp = new ArrayList<>(); // layout, text, check
//        list.add(temp);

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
