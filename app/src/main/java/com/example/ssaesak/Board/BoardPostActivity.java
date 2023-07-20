package com.example.ssaesak.Board;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.ssaesak.Dto.BoardRequestDTO;
import com.example.ssaesak.Dto.ReplyRequestDTO;
import com.example.ssaesak.Model.User;
import com.example.ssaesak.R;
import com.example.ssaesak.Retrofit.MyRetrofit;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardPostActivity extends AppCompatActivity {

    private EditText title, content;

    private TextView update, titleLength, contentLength;

    private ImageView image;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_post);

        init();

//        boardCreate(title.getText().toString(), content.getText().toString(), User.getInstance().getUserId());
    }

    private void init() {
        this.title = findViewById(R.id.title);
        this.content = findViewById(R.id.content);
        this.update = findViewById(R.id.update);
        this.image = findViewById(R.id.image);
        this.titleLength = findViewById(R.id.title_length);
        this.contentLength = findViewById(R.id.content_length);
    }

    private void boardCreate(String title, String content, Long userId, MultipartBody.Part file) {
        // 현재 시간을 가져오는 코드
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String currentTime = dateFormat.format(new Date());

        BoardRequestDTO boardRequestDTO = new BoardRequestDTO();
//        boardRequestDTO.setBoardId(boardId);
        boardRequestDTO.setTitle(title);
        boardRequestDTO.setContent(content);
        boardRequestDTO.setUploadTime(currentTime);
        boardRequestDTO.setUserId(userId);



        Call<BoardRequestDTO> call = MyRetrofit.getApiService().boardCreate(boardRequestDTO, image);
        call.enqueue(new Callback<BoardRequestDTO>() {
            @Override
            public void onResponse(Call<BoardRequestDTO> call, Response<BoardRequestDTO> response) {
                if (response.isSuccessful()) {
                    // 댓글 생성 성공
//                    Toast.makeText(getApplicationContext(), "글이 성공적으로 생성되었습니다.", Toast.LENGTH_SHORT).show();
                    // 댓글 목록을 갱신하는 등의 작업 수행
                    // ...
                } else {
                    // 댓글 생성 실패
//                    Toast.makeText(getApplicationContext(), "글 생성에 실패했습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BoardRequestDTO> call, Throwable t) {
                // 통신 실패
//                Toast.makeText(getApplicationContext(), "글 생성 중 오류가 발생했습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
