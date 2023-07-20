    package com.example.ssaesak.Main;

    import static android.content.Context.LAYOUT_INFLATER_SERVICE;

    import android.content.Intent;
    import android.os.Bundle;
    import android.util.Log;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.EditText;
    import android.widget.ImageView;
    import android.widget.LinearLayout;
    import android.widget.TextView;
    import android.widget.Toast;

    import androidx.annotation.Nullable;
    import androidx.appcompat.app.AppCompatActivity;
    import androidx.fragment.app.Fragment;

    import com.bumptech.glide.Glide;
    import com.bumptech.glide.load.engine.DiskCacheStrategy;
    import com.example.ssaesak.Dto.BoardDetailDTO;
    import com.example.ssaesak.Dto.BoardLikeRequestDTO;
    import com.example.ssaesak.Dto.ReplyRequestDTO;
    import com.example.ssaesak.Dto.ReplyResponseDTO;
    import com.example.ssaesak.Login.SignupTypeActivity;
    import com.example.ssaesak.Model.User;
    import com.example.ssaesak.R;
    import com.example.ssaesak.Retrofit.ApiResponse;
    import com.example.ssaesak.Retrofit.MyRetrofit;
    import com.fasterxml.jackson.databind.ObjectMapper;

    import java.text.SimpleDateFormat;
    import java.util.Arrays;
    import java.util.Date;
    import java.util.List;
    import java.util.Locale;

    import retrofit2.Call;
    import retrofit2.Callback;
    import retrofit2.Response;

    public class MypageActivityHistoryComment extends Fragment {

        private LinearLayout linearLayout;


        private TextView category, name, time, title, content, commentCount, commentName, commentArea, commentTime, commentContent;
        private ImageView profileImage, commentProfileImage, sendBtn, likeBtn;

        private EditText editText;

        private LayoutInflater layoutInflater;

        private LinearLayout commentLists;

        private int count = 0;

        private View view;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            this.view = inflater.inflate(R.layout.activity_mypage_activity_history_empty, container, false);

            init();
            commentAll();

            return view;
        }

//        @Override
//        protected void onCreate(@Nullable Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.activity_mypage_activity_history_empty);
//
//            init();
//            commentAll();
//
//
//        }

        private void init() {
            this.category = view.findViewById(R.id.category);
            this.name = view.findViewById(R.id.name);
            this.time = view.findViewById(R.id.time);
            this.title = view.findViewById(R.id.title);
            this.content = view.findViewById(R.id.content);
            this.commentCount = view.findViewById(R.id.comment_count);
            this.profileImage = view.findViewById(R.id.profile_image);
            this.editText = view.findViewById(R.id.edittext);
            this.sendBtn = view.findViewById(R.id.send);

        }


        // 댓글 조회 후 추가
        public void addCommentList(ReplyResponseDTO dto, LinearLayout parentLayout) {

            Log.e("addCommentList", "addCommentList 입장!!");
            this.layoutInflater = (LayoutInflater) getActivity().getSystemService(LAYOUT_INFLATER_SERVICE);


            this.commentLists = (LinearLayout) layoutInflater.inflate(R.layout.card_comment, parentLayout, false);


            this.commentName = commentLists.findViewById(R.id.name);
            this.commentProfileImage = commentLists.findViewById(R.id.profile_image);
            this.commentArea = commentLists.findViewById(R.id.area);
            this.commentTime = commentLists.findViewById(R.id.time);
            this.commentContent = commentLists.findViewById(R.id.comment);

            Log.e("addCommentList", "addCommentListDTO 입장!!" + dto.getName() + " " + dto.getContent() + " " + dto.getArea() + " " + dto.getUploadTime() + " " + dto.getProfileImage());

            commentName.setText(dto.getName()+"");

            Glide.with(getActivity().getBaseContext())
                    .load(dto.getProfileImage())
                    .diskCacheStrategy(DiskCacheStrategy.ALL) // 캐시 옵션 설정
                    .into(commentProfileImage);
            commentArea.setText(dto.getArea()+"");
            commentTime.setText(dto.getUploadTime()+"");
            commentContent.setText(dto.getContent()+"");

            parentLayout.addView(commentLists);
        }

        // 댓글 조회
        public void commentAll() {
            Log.e("commentList","commentList 입장!!!!!!!!!!!!!!!!!!");
            Call<ApiResponse> call = MyRetrofit.getApiService().commentListALL(User.getInstance().getUserId());
            call.enqueue(new Callback<ApiResponse>() {
                @Override
                public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                    if(response.body().getData().equals("\"[]\"") || response.body().getData().equals("") || response.body().getData().equals(null)) {
                        return;
                    }
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
                        LinearLayout parentLayout = view.findViewById(R.id.comment_list);
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
