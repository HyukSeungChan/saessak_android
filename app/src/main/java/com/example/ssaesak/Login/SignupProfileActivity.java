package com.example.ssaesak.Login;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;
import com.example.ssaesak.R;
import com.kakao.sdk.user.UserApi;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.User;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;

public class SignupProfileActivity extends Activity {

    private static final int REQUEST_PERMISSION = 1;
    private static final int REQUEST_GALLERY = 2;

    String name;

    ImageView imageButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_profile);

        ImageButton backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), SignupTypeActivity.class));
                overridePendingTransition(0, 0);
                finish();
            }
        });

        EditText editText = findViewById(R.id.edittext);

        this.name = "";



        imageButton = findViewById(R.id.profile_image);
        UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
            @Override
            public Unit invoke(com.kakao.sdk.user.model.User user_kakao, Throwable throwable) {
                String profileUrl = user_kakao.getKakaoAccount().getProfile().getThumbnailImageUrl();
                ImageView imageView = (ImageView) findViewById(R.id.profile_image);
                Glide.with(getBaseContext()).load(profileUrl).into(imageView);

                com.example.ssaesak.Model.User.getInstance().setProfileImage("kakao");
                Log.e("profile", user_kakao.getKakaoAccount().getProfile().getThumbnailImageUrl() + "");
                return null;
            }
        });




        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //갤러리 호출
                if (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(SignupProfileActivity.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            REQUEST_PERMISSION);
                } else {
                    openGallery();
                }
            }
        });

        Button nextButton = findViewById(R.id.button_next);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//               User.getInstance().setName(editText.getText().toString());
                startActivity(new Intent(getBaseContext(), SignupPhoneActivity.class));
                overridePendingTransition(0, 0);
                finish();
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
            Uri selectedImageUri = data.getData();
            Log.e("image", selectedImageUri.toString());
            imageButton.setImageURI(selectedImageUri);
        }
    }

}
