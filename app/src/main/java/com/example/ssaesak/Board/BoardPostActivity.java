package com.example.ssaesak.Board;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.ssaesak.Dto.BoardRequestDTO;
import com.example.ssaesak.Dto.ReplyRequestDTO;
import com.example.ssaesak.Farmgroup.FarmgroupReview;
import com.example.ssaesak.Model.User;
import com.example.ssaesak.R;
import com.example.ssaesak.Retrofit.MyRetrofit;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardPostActivity extends AppCompatActivity {

    private static final int REQUEST_PERMISSION = 1;
    private static final int REQUEST_GALLERY = 2;

    private EditText title, content;

    private TextView update, titleLength, contentLength, postComplete;

    private ImageView image;

    private Uri selectedImageUri;

//    private PermissionListener permissionlistener;
    private String filePath;

    private byte[] user_image_binary;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_post);

        init();

        postComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boardCreate(title.getText().toString(), content.getText().toString(), User.getInstance().getUserId());
            }
        });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(BoardPostActivity.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            REQUEST_PERMISSION);
                } else {
                    openGallery();
                }
            }
        });

    }

    private void init() {
        this.title = findViewById(R.id.title);
        this.content = findViewById(R.id.content);
        this.update = findViewById(R.id.update);
        this.image = findViewById(R.id.image);
        this.titleLength = findViewById(R.id.title_length);
        this.contentLength = findViewById(R.id.content_length);
        this.postComplete = findViewById(R.id.post_complete);
    }

    private void boardCreate(String title, String content, Long userId) {


        // 현재 시간을 가져오는 코드
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String currentTime = dateFormat.format(new Date());

        BoardRequestDTO boardRequestDTO = new BoardRequestDTO();
//        boardRequestDTO.setBoardId(boardId);
        boardRequestDTO.setTitle(title);
        boardRequestDTO.setContent(content);
        boardRequestDTO.setUploadTime(currentTime);
        boardRequestDTO.setUserId(userId);



        Call<BoardRequestDTO> call = MyRetrofit.getApiService().boardCreate(boardRequestDTO, null);
        call.enqueue(new Callback<BoardRequestDTO>() {
            @Override
            public void onResponse(Call<BoardRequestDTO> call, Response<BoardRequestDTO> response) {
                if (response.isSuccessful()) {
                    Log.e("response", "response good" + response.body());

                    // 글 생성 성공
//                    Toast.makeText(getApplicationContext(), "글이 성공적으로 생성되었습니다.", Toast.LENGTH_SHORT).show();
                    // 글 목록을 갱신하는 등의 작업 수행
                    // ...
                } else {
                    Log.e("response", "response bad" + response.body());
                    // 글 생성 실패
//                    Toast.makeText(getApplicationContext(), "글 생성에 실패했습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BoardRequestDTO> call, Throwable t) {
                Log.e("fail", t.getMessage());
                // 통신 실패
//                Toast.makeText(getApplicationContext(), "글 생성 중 오류가 발생했습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_GALLERY);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_GALLERY && resultCode == RESULT_OK && data != null) {
            selectedImageUri = data.getData();
            Log.e("imageUrl", selectedImageUri.toString());
            image.setImageURI(selectedImageUri);
        }
    }

}


