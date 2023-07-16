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

public class BottomsheetAgricultureDialog extends BottomSheetDialogFragment {

    private View view;

    private BottomSheetAgricultureListener bottomSheetAgricultureListener;

    private Button buttonOk;

    private String filterAgriculture;

    private List<String> agricultureList;

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInsranceState) {
        view = inflater.inflate(R.layout.bottomsheet_filter_agriculture, container, false);
        List<ArrayList> filter_list = init(view);
        this.agricultureList = new ArrayList<>();
        Log.e("bottom", getContext().toString());
        bottomSheetAgricultureListener = (BottomSheetAgricultureListener) getContext();

        this.filterAgriculture = getArguments().getString("agriculture");


        // 전체일 경우 나머지 제외 + 여러개 선택 가능 + 이미 선택되어있는 경우가 문제
        for (ArrayList list : filter_list) {
            Log.e("bottom", "!! => " + list.get(0).toString());
            LinearLayout linearLayout = (LinearLayout) list.get(0);
            TextView textView = (TextView) list.get(1);
            ImageView imageView = (ImageView) list.get(2);
            linearLayout.setOnClickListener(v -> {
                if (agricultureList.contains(textView.getText().toString())) {
                    agricultureList.remove(textView.getText().toString());
                    imageView.setVisibility(View.INVISIBLE);
                } else {
                    agricultureList.add(textView.getText().toString());
                    imageView.setVisibility(View.VISIBLE);
                }
            });
        }

        buttonOk = view.findViewById(R.id.button_ok);
        buttonOk.setOnClickListener(v -> {
            filterAgriculture = "";
            for (String area : agricultureList) {
                filterAgriculture += area + ",";
            }
            bottomSheetAgricultureListener.onButtonAgiculture(filterAgriculture.substring(0, filterAgriculture.length()-1));
            dismiss();
        });

        return view;
    }


    public interface BottomSheetAgricultureListener {
        void onButtonAgiculture(String filterAgriculture);
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
        temp.add(view.findViewById(R.id.layout_rhktn));
        temp.add(view.findViewById(R.id.text_rhktn));
        temp.add(view.findViewById(R.id.check_rhktn));
        list.add(temp);

        temp = new ArrayList<>(); // layout, text, check
        temp.add(view.findViewById(R.id.layout_qkx));
        temp.add(view.findViewById(R.id.text_qkx));
        temp.add(view.findViewById(R.id.check_qkx));
        list.add(temp);

        temp = new ArrayList<>(); // layout, text, check
        temp.add(view.findViewById(R.id.layout_gkdntm));
        temp.add(view.findViewById(R.id.text_gkdntm));
        temp.add(view.findViewById(R.id.check_gkdntm));
        list.add(temp);

        temp = new ArrayList<>(); // layout, text, check
        temp.add(view.findViewById(R.id.layout_shs));
        temp.add(view.findViewById(R.id.text_shs));
        temp.add(view.findViewById(R.id.check_shs));
        list.add(temp);

        temp = new ArrayList<>(); // layout, text, check
        temp.add(view.findViewById(R.id.layout_xmrdyd));
        temp.add(view.findViewById(R.id.text_xmrdyd));
        temp.add(view.findViewById(R.id.check_xmrdyd));
        list.add(temp);

        temp = new ArrayList<>(); // layout, text, check
        temp.add(view.findViewById(R.id.layout_rlxk));
        temp.add(view.findViewById(R.id.text_rlxk));
        temp.add(view.findViewById(R.id.check_rlxk));
        list.add(temp);

        return list;
    }
}
