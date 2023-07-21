package com.example.ssaesak.Study;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.ssaesak.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class BottomsheetCropDialog extends BottomSheetDialogFragment {

    private View view;

    private BottomSheetListener bottomSheetListener;


    List<View> filterList; // 리니어레이아웃 + 텍스트뷰
    List<String> filterTextList;
    List<String> filterNameList; // 선택한 거 이름

    List<List<View>> parentList; // 다 넣은 거

    private String select;
    private String filter;

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInsranceState) {
        view = inflater.inflate(R.layout.bottomsheet_study_crop, container, false);
//        List<ArrayList> filter_list = init(view);
        bottomSheetListener = (BottomSheetListener) getContext();

        filterTextList = new ArrayList<>();
        parentList = new ArrayList<>();

        filterList = new ArrayList<>();
        filterList.add( view.findViewById(R.id.potato));
        filterList.add( view.findViewById(R.id.potato_text));
        parentList.add(filterList);

        filterList = new ArrayList<>();
        filterList.add( view.findViewById(R.id.spicy));
        filterList.add( view.findViewById(R.id.spicy_text));
        parentList.add(filterList);

        filterList = new ArrayList<>();
        filterList.add( view.findViewById(R.id.onion));
        filterList.add( view.findViewById(R.id.onion_text));
        parentList.add(filterList);

        filterList = new ArrayList<>();
        filterList.add( view.findViewById(R.id.garlic));
        filterList.add( view.findViewById(R.id.garlic_text));
        parentList.add(filterList);

        filterList = new ArrayList<>();
        filterList.add( view.findViewById(R.id.cabbage));
        filterList.add( view.findViewById(R.id.cabbage_text));
        parentList.add(filterList);

        filterList = new ArrayList<>();
        filterList.add( view.findViewById(R.id.strawberry));
        filterList.add( view.findViewById(R.id.strawberry_text));
        parentList.add(filterList);

        filterList = new ArrayList<>();
        filterList.add( view.findViewById(R.id.pear));
        filterList.add( view.findViewById(R.id.pear_text));
        parentList.add(filterList);

        filterList = new ArrayList<>();
        filterList.add( view.findViewById(R.id.apple));
        filterList.add( view.findViewById(R.id.apple_text));
        parentList.add(filterList);

        filterList = new ArrayList<>();
        filterList.add( view.findViewById(R.id.watermelon));
        filterList.add( view.findViewById(R.id.watermelon_text));
        parentList.add(filterList);

        filterList = new ArrayList<>();
        filterList.add( view.findViewById(R.id.orrange));
        filterList.add( view.findViewById(R.id.orange_text));
        parentList.add(filterList);

        filterList = new ArrayList<>();
        filterList.add( view.findViewById(R.id.rice));
        filterList.add( view.findViewById(R.id.rice_text));
        parentList.add(filterList);

        filterList = new ArrayList<>();
        filterList.add( view.findViewById(R.id.barley));
        filterList.add( view.findViewById(R.id.barley_text));
        parentList.add(filterList);

        filterList = new ArrayList<>();
        filterList.add( view.findViewById(R.id.corn));
        filterList.add( view.findViewById(R.id.corn_text));
        parentList.add(filterList);

        filterList = new ArrayList<>();
        filterList.add( view.findViewById(R.id.bean));
        filterList.add( view.findViewById(R.id.bean_text));
        parentList.add(filterList);

        filter = "";
        filterNameList = new ArrayList<>();
        Log.e("crop", parentList.size() + "");
        for (List<View> list : parentList) {

//            Log.e("crop", ((TextView) list.get(0)).getId() + "");
            list.get(0).setOnClickListener(v -> {
                bottomSheetListener.onButton(((TextView) list.get(1)).getText().toString());
                dismiss();

            });
        }


//

        return view;
    }

    public interface BottomSheetListener {
        void onButton(String filter);
    }


}
