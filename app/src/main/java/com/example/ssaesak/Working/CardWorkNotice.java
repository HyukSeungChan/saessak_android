package com.example.ssaesak.Working;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.ssaesak.R;

import java.io.Serializable;

public class CardWorkNotice implements Serializable {

    private View card;


    private LayoutInflater layoutInflater;

    public CardWorkNotice(@NonNull Context context) {
        this.layoutInflater = LayoutInflater.from(context);
        this.card = (LinearLayout)layoutInflater.inflate(R.layout.card_work_notice, null, false);
//        init(context);


//        this.setOnClickListener(v -> {
//            Intent intent = new Intent(context, ColorDetailActivity.class);
//            intent.putExtra("palette", palette);
//            context.startActivity(intent);
//        });
    }

    private void init(Context context){

    }

    public LinearLayout getCard() {
        return (LinearLayout)card;
    }
//    public ImageView getDeleteButton() {return deleteButton;}
}