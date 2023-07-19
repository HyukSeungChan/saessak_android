    package com.example.ssaesak.Board;

    import android.content.Intent;
    import android.os.Bundle;
    import android.util.Log;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.widget.EditText;
    import android.widget.ImageView;
    import android.widget.LinearLayout;
    import android.widget.TextView;
    import android.widget.Toast;

    import androidx.annotation.Nullable;
    import androidx.appcompat.app.AppCompatActivity;

    import com.bumptech.glide.Glide;
    import com.bumptech.glide.load.engine.DiskCacheStrategy;
    import com.example.ssaesak.Dto.BoardDetailDTO;
    import com.example.ssaesak.Dto.BoardLikeRequestDTO;
    import com.example.ssaesak.Dto.ReplyRequestDTO;
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
        private ImageView profileImage, commentProfileImage, sendBtn, likeBtn;

        private EditText editText;

        private LayoutInflater layoutInflater;

        private LinearLayout commentLists;

        private int count = 0;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_board_detail);

            int boardId = getIntent().getIntExtra("boardId", 1);

            init();

            boardDetail(boardId);
            commentAll(boardId);

            if (editText.toString().length() == 0) {
                sendBtn.setVisibility(View.INVISIBLE);
            } else {
                sendBtn.setVisibility(View.VISIBLE);
            }

            // 댓글 생성
            sendBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    commentCreate(editText.getText().toString(), boardId, User.getInstance().getUserId());
                    Intent intent = getIntent();
                    finish(); //현재 액티비티 종료 실시
                    overridePendingTransition(0, 0); //인텐트 애니메이션 없애기
                    startActivity(intent); //현재 액티비티 재실행 실시
                    overridePendingTransition(0, 0); //인텐트 애니메이션 없애기
                }
            });

            // 좋아요 누르기
            likeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (count  == 0) {
                        likeIncrease(boardId);
                        Intent intent = getIntent();
                        finish(); //현재 액티비티 종료 실시
                        overridePendingTransition(0, 0); //인텐트 애니메이션 없애기
                        startActivity(intent); //현재 액티비티 재실행 실시
                        overridePendingTransition(0, 0); //인텐트 애니메이션 없애기
                        count = 1;
                    } else {
                        likeDecrease(boardId);
                        Intent intent = getIntent();
                        finish(); //현재 액티비티 종료 실시
                        overridePendingTransition(0, 0); //인텐트 애니메이션 없애기
                        startActivity(intent); //현재 액티비티 재실행 실시
                        overridePendingTransition(0, 0); //인텐트 애니메이션 없애기
                        count = 0;
                    }

                }
            });



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

            this.editText = findViewById(R.id.edittext);
            this.sendBtn = findViewById(R.id.send);
            this.likeBtn = findViewById(R.id.like_button);

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

        // 댓글 생성
        private void commentCreate(String content, int boardId, Long userId) {
            // 현재 시간을 가져오는 코드
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            String currentTime = dateFormat.format(new Date());

            ReplyRequestDTO replyRequestDTO = new ReplyRequestDTO();
            replyRequestDTO.setContent(content);
            replyRequestDTO.setUploadTime(currentTime);
            replyRequestDTO.setBoardId(boardId);
            replyRequestDTO.setUserId(userId);

            Call<ReplyRequestDTO> call = MyRetrofit.getApiService().commentCreate(replyRequestDTO);
            call.enqueue(new Callback<ReplyRequestDTO>() {
                @Override
                public void onResponse(Call<ReplyRequestDTO> call, Response<ReplyRequestDTO> response) {
                    if (response.isSuccessful()) {
                        // 댓글 생성 성공
                        Toast.makeText(getApplicationContext(), "댓글이 성공적으로 생성되었습니다.", Toast.LENGTH_SHORT).show();
                        // 댓글 목록을 갱신하는 등의 작업 수행
                        // ...
                    } else {
                        // 댓글 생성 실패
                        Toast.makeText(getApplicationContext(), "댓글 생성에 실패했습니다.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ReplyRequestDTO> call, Throwable t) {
                    // 통신 실패
                    Toast.makeText(getApplicationContext(), "댓글 생성 중 오류가 발생했습니다.", Toast.LENGTH_SHORT).show();
                }
            });
        }

        // 좋아요 증가
        private void likeIncrease(int boardId) {

            BoardLikeRequestDTO boardLikeRequestDTO = new BoardLikeRequestDTO();
            boardLikeRequestDTO.setBoardId(boardId);

            Call<Boolean> call = MyRetrofit.getApiService().likeIncrease(boardLikeRequestDTO);
            call.enqueue(new Callback<Boolean>() {
                @Override
                public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                    if (response.isSuccessful()) {

                    } else {

                    }
                }

                @Override
                public void onFailure(Call<Boolean> call, Throwable t) {

                }
            });
        }

        // 좋아요 감소
        private void likeDecrease(int boardId) {

            BoardLikeRequestDTO boardLikeRequestDTO = new BoardLikeRequestDTO();
            boardLikeRequestDTO.setBoardId(boardId);

            Call<Boolean> call = MyRetrofit.getApiService().likeDecrease(boardLikeRequestDTO);
            call.enqueue(new Callback<Boolean>() {
                @Override
                public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                    if (response.isSuccessful()) {

                    } else {

                    }
                }

                @Override
                public void onFailure(Call<Boolean> call, Throwable t) {

                }
            });
        }

    }
