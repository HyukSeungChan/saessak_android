package com.example.ssaesak.Board;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.ssaesak.Dto.BoardDetailDTO;
import com.example.ssaesak.R;

import java.io.Serializable;

public class CardBoardHelpImage implements Serializable {

    private LinearLayout card;

//    private TextView agriculture, title, content, time, like_count, comment_count;
//
//    private ImageView image;


    private LayoutInflater layoutInflater;
    private Context context;

    public CardBoardHelpImage(@NonNull Context context) {
        this.layoutInflater = LayoutInflater.from(context);
        this.card = (LinearLayout)layoutInflater.inflate(R.layout.card_board_help_image, null, false);
        this.card.setVisibility(View.VISIBLE);
        this.context = layoutInflater.getContext();

//        init(context);


//        this.setOnClickListener(v -> {
//            Intent intent = new Intent(context, ColorDetailActivity.class);
//            intent.putExtra("palette", palette);
//            context.startActivity(intent);
//        });
    }

    private void init(Context context){
//        this.agriculture = card.findViewById(R.id.agriculture);
//        this.title = card.findViewById(R.id.title);
//        this.content = card.findViewById(R.id.content);
//        this.time = card.findViewById(R.id.time);
//        this.like_count = card.findViewById(R.id.like_count);
//        this.comment_count= card.findViewById(R.id.comment_count);
//        this.image = card.findViewById(R.id.image);
    }

    public LinearLayout getCard() {
        return (LinearLayout)card;
    }

    public View init(BoardDetailDTO notice) {

        ((TextView)card.findViewById(R.id.agriculture)).setText(notice.getAgriculture());
        ((TextView)card.findViewById(R.id.title)).setText(notice.getAgriculture());
        ((TextView)card.findViewById(R.id.content)).setText(notice.getAgriculture());
        if (notice.getImage() == null) {
            ((ImageView)card.findViewById(R.id.image)).setVisibility(View.GONE);
            ((TextView)card.findViewById(R.id.content)).setMaxLines(2);
        } else {
            Glide.with(context)
                    .load(notice.getImage())
                    .diskCacheStrategy(DiskCacheStrategy.ALL) // 캐시 옵션 설정
                    .into(((ImageView)card.findViewById(R.id.image)));

        }
        return this.card;
    }
//    public ImageView getDeleteButton() {return deleteButton;}
}