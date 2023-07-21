package com.example.ssaesak.Board;

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

public class BottomsheetBoardDialog extends BottomSheetDialogFragment {

    private View view;

    private BottomSheetListener bottomSheetCareerListener;

    private String select;

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInsranceState) {
        view = inflater.inflate(R.layout.bottomsheet_post, container, false);
//        List<ArrayList> filter_list = init(view);
        bottomSheetCareerListener = (BottomSheetListener) getContext();

        // 레이아웃, 텍스트, 체크버튼 -> 3개가 같이 들어간 게 있고 그걸 여러개 가진 리스트
        ((TextView) view.findViewById(R.id.help)).setOnClickListener(v -> {

            bottomSheetCareerListener.onButton("도와줘요");
            dismiss();
        });
        ((TextView) view.findViewById(R.id.story)).setOnClickListener( v -> {
            bottomSheetCareerListener.onButton("농촌 이야기");
            dismiss();
        });
//

        return view;
    }

    public interface BottomSheetListener {
        void onButton(String filter);
    }


}
