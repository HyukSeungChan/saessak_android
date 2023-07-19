package com.example.ssaesak.Board;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.ssaesak.Dto.BoardDetailDTO;
import com.example.ssaesak.Dto.ReplyResponseDTO;
import com.example.ssaesak.Dto.UserFarmResponseDTO;
import com.example.ssaesak.Dto.WorkDTO;
import com.example.ssaesak.Login.SignupTypeActivity;
import com.example.ssaesak.Model.User;
import com.example.ssaesak.R;
import com.example.ssaesak.Retrofit.ApiResponse;
import com.example.ssaesak.Retrofit.MyRetrofit;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardDetailActivity  extends AppCompatActivity {

    private TextView category, name, time, title, content, likeCount, commentCount, commentName, commentArea, commentTime, commentContent;
    private ImageView profileImage, commentProfileImage;

    private LayoutInflater layoutInflater;

    private LinearLayout commentLists;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_detail);

        int boardId = getIntent().getIntExtra("boardId", 1);

        init();

        boardDetail(boardId);
        commentAll(boardId);

    }

    private void init() {
        this.category = findViewById(R.id.category);
        this.name = findViewById(R.id.name);
        this.time = findViewById(R.id.time);
        this.title = findViewById(R.id.title);
        this.content = findViewById(R.id.content);
        this.likeCount = findViewById(R.id.like_count);
        this.commentCount = findViewById(R.id.comment_count);
        this.profileImage = findViewById(R.id.profile_image);

    }

    private void boardDetail(int boardId) {
        Log.e("boardDetail", "start!!");
        Call<ApiResponse> call = MyRetrofit.getApiService().boardDetail(boardId);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {

                if(response.code() == 404) {
                    Log.e("boardDetail", "workDetail~ : " + response.code());
                    startActivity(new Intent(getBaseContext(), SignupTypeActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                    overridePendingTransition(0, 0);
                    return;
                }

                if (!response.isSuccessful()) {
                    Toast.makeText( getBaseContext(), "연결 상태가 좋지 않습니다. 다시 시도해주세요", Toast.LENGTH_SHORT);
                    Log.e("연결이 비정상적 : ", "error code : " + response.code());
                    return;
                }

                ObjectMapper mapper = new ObjectMapper();
                String body = response.body().getData().toString();
                String json = body.substring(1, body.length()-1).replace("\\", "");
                Log.e("boardDetail", " string -> " + json);
                try {
                    BoardDetailDTO boardDetailDTO = mapper.readValue(json, BoardDetailDTO.class);
                    if (boardDetailDTO.getCrops() == null) {
                        category.setVisibility(View.GONE);
                    }
                    category.setText(boardDetailDTO.getCrops());
                    name.setText(boardDetailDTO.getName());
                    time.setText(boardDetailDTO.getUploadTime());
                    title.setText(boardDetailDTO.getTitle());
                    content.setText(boardDetailDTO.getContent());
                    likeCount.setText(boardDetailDTO.getLikes()+"");
                    commentCount.setText((boardDetailDTO.getReplies()+""));
                    Glide.with(getBaseContext())
                            .load(boardDetailDTO.getProfileImage())
                            .diskCacheStrategy(DiskCacheStrategy.ALL) // 캐시 옵션 설정
                            .into(profileImage);

                } catch (Exception e1) {
                    e1.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {Log.e("연결실패", t.getMessage() + "");}
        });
    }

    // 댓글 조회 후 추가
    public void addCommentList(ReplyResponseDTO dto, LinearLayout parentLayout) {

        Log.e("addCommentList", "addCommentList 입장!!");
        this.layoutInflater = LayoutInflater.from(getBaseContext());


        this.commentLists = (LinearLayout) layoutInflater.inflate(R.layout.card_comment, parentLayout, false);


        this.commentName = commentLists.findViewById(R.id.name);
        this.commentProfileImage = commentLists.findViewById(R.id.profile_image);
        this.commentArea = commentLists.findViewById(R.id.area);
        this.commentTime = commentLists.findViewById(R.id.time);
        this.commentContent = commentLists.findViewById(R.id.comment);

        Log.e("addCommentList", "addCommentListDTO 입장!!" + dto.getName() + " " + dto.getContent() + " " + dto.getArea() + " " + dto.getUploadTime() + " " + dto.getProfileImage());

        commentName.setText(dto.getName()+"");

        Glide.with(getBaseContext())
                .load(dto.getProfileImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL) // 캐시 옵션 설정
                .into(commentProfileImage);
        commentArea.setText(dto.getArea()+"");
        commentTime.setText(dto.getUploadTime()+"");
        commentContent.setText(dto.getContent()+"");

        parentLayout.addView(commentLists);
    }

    // 댓글 조회
    public void commentAll(int boardId) {
        Log.e("commentList","commentList 입장!!!!!!!!!!!!!!!!!!");
        Call<ApiResponse> call = MyRetrofit.getApiService().commentList(boardId);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                Log.e("commentList", "findMemberList : ");
                Log.e("commentList", "findMemberList : " + response.body());
                ObjectMapper mapper = new ObjectMapper();
                String body = response.body().getData().toString();
                String json = body.substring(1, body.length()-1).replace("\\", "");
                Log.e("commentList", " body -> " + body);
                Log.e("commentList", " json -> " + json);
                try {
                    List<ReplyResponseDTO> dtos = Arrays.asList(mapper.readValue(json, ReplyResponseDTO[].class));

//                    memberList.removeAllViews();
                    LinearLayout parentLayout = findViewById(R.id.comment_list);
                    parentLayout.removeAllViews();
                    // Loop through the WorkListDTOs and add them to the card views
                    Log.e("Dto size", dtos.size() + "!!");
                    for (ReplyResponseDTO dto : dtos) {
                        addCommentList(dto, parentLayout);
                    }
                } catch (Exception e1) {
                    Log.e("findMemberList", "Error parsing JSON", e1);
                    e1.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.e("workHome failed", t.getMessage() + "");
            }
        });



    }
}
