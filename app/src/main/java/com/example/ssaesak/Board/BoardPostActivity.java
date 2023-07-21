package com.example.ssaesak.Board;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.example.ssaesak.Model.Worker;
import com.example.ssaesak.R;
import com.example.ssaesak.Retrofit.MyRetrofit;
import com.example.ssaesak.Working.BottomsheetAreaDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardPostActivity extends AppCompatActivity implements BottomsheetBoardDialog.BottomSheetListener{

    private static final int REQUEST_PERMISSION = 1;
    private static final int REQUEST_GALLERY = 2;

    List<Button> buttonCropList;
    List<Button> buttonInterestList;
    List<Button> cropList;
    List<Button> interestList;


    private EditText title, content;

    private TextView update, titleLength, contentLength;
    private Button postComplete;

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

        this.buttonCropList = new ArrayList<>();
        this.buttonInterestList = new ArrayList<>();
        this.cropList = new ArrayList<>();
        this.interestList = new ArrayList<>();


        buttonCropList.add(findViewById(R.id.chip_qkxshdtk));
        buttonCropList.add(findViewById(R.id.chip_rhktn));
        buttonCropList.add(findViewById(R.id.chip_gkdntm));
        buttonCropList.add(findViewById(R.id.chip_shs));
        buttonCropList.add(findViewById(R.id.chip_xmrdydwkranf));
        buttonCropList.add(findViewById(R.id.chip_rlxk_shddjq));
        for (Button button : buttonCropList) {
            button.setOnClickListener(v -> {
                if (cropList.contains(button)) {
                    Log.e("area", "press button!!");
                    button.setPressed(false);
                    button.setBackground(getResources().getDrawable(R.drawable.chip_not_select, null));
                    button.setTextColor(Color.rgb(120, 120, 120));
                    cropList.remove(button);
                } else if (cropList.size() < 3){
                    Log.e("area", "not press button!!");
                    button.setPressed(true);
                    button.setBackground(getResources().getDrawable(R.drawable.chip_select, null));
                    button.setTextColor(Color.rgb(255, 255, 255));
                    cropList.add(button);
                }


            });
        }

        buttonInterestList.add(findViewById(R.id.chip_shdwkranfcoth));
        buttonInterestList.add(findViewById(R.id.chip_rhktlf));
        buttonInterestList.add(findViewById(R.id.chip_ghkgnpwkranf));
        buttonInterestList.add(findViewById(R.id.chip_rlxk_wkrahr));
        for (Button button : buttonInterestList) {
            button.setOnClickListener(v -> {
                if (interestList.contains(button)) {
                    Log.e("area", "press button!!");
                    button.setPressed(false);
                    button.setBackground(getResources().getDrawable(R.drawable.chip_not_select, null));
                    button.setTextColor(Color.rgb(120, 120, 120));
                    interestList.remove(button);
                } else if (interestList.size() < 3){
                    Log.e("area", "not press button!!");
                    button.setPressed(true);
                    button.setBackground(getResources().getDrawable(R.drawable.chip_select, null));
                    button.setTextColor(Color.rgb(255, 255, 255));
                    interestList.add(button);
                }


            });
        }


        BottomsheetBoardDialog bottomsheetboardDialog = new BottomsheetBoardDialog();
        Bundle args = new Bundle();
        bottomsheetboardDialog.setArguments(args);
        bottomsheetboardDialog.show(getSupportFragmentManager(), "dd");



        postComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(title.getText().toString() == null || title.getText().toString().equals("") || title.getText().toString().equals(null)
                || content.getText().toString() == null || content.getText().toString().equals("") || content.getText().toString().equals(null)))
                boardCreate(title.getText().toString(), content.getText().toString(), User.getInstance().getUserId());
            }
        });

        image.setVisibility(View.GONE);
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

        String crops = "";
        for (Button button : cropList) {
            crops += button.getText().toString() + ",";
        }
        if (cropList.size() > 1) {
            crops = crops.substring(0, crops.length() - 1);
        }

        String agricultures = "";
        for (Button button : interestList) {
            agricultures += button.getText().toString() + ",";
        }
        Log.e("signup", agricultures.length()+"");
        if(interestList.size() > 1) {
            agricultures = agricultures.substring(0, agricultures.length() - 1);
        }


        BoardRequestDTO boardRequestDTO = new BoardRequestDTO();
//        boardRequestDTO.setBoardId(boardId);
        boardRequestDTO.setTitle(title);
        boardRequestDTO.setContent(content);
        boardRequestDTO.setUploadTime(currentTime);
        boardRequestDTO.setUserId(userId);
        boardRequestDTO.setAgriculture(agricultures);
        boardRequestDTO.setCrops(crops);




        Call<BoardRequestDTO> call = MyRetrofit.getApiService().boardCreate(boardRequestDTO, null);
        call.enqueue(new Callback<BoardRequestDTO>() {
            @Override
            public void onResponse(Call<BoardRequestDTO> call, Response<BoardRequestDTO> response) {
                if (response.isSuccessful()) {
                    Log.e("response", "response good" + response.body());

                    // 글 생성 성공
                    Toast.makeText(getApplicationContext(), "글이 성공적으로 생성되었습니다.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), BoardActivity.class));
                    finish();

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

    @Override
    public void onButton(String filter) {
        LinearLayout linearLayout = findViewById(R.id.category);
        if(filter.equals("도와줘요")) {
            linearLayout.setVisibility(View.VISIBLE);
            ((TextView)findViewById(R.id.type)).setText("도와줘요");
        } else {
            linearLayout.setVisibility(View.GONE);
        }

    }
}


