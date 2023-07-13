package com.example.ssaesak.Login;

import android.app.Activity;
import android.content.Intent;
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

    String name;


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

        Button afterButton = findViewById(R.id.button_after);
        afterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), SignupPhoneActivity.class));
                overridePendingTransition(0, 0);
                finish();
            }
        });

        EditText editText = findViewById(R.id.edittext);

        this.name = "";



        ImageView imageButton = findViewById(R.id.profile_image);
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
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                intent.setAction(Intent.ACTION_PICK);
//                launcher_gallery.launch(intent);
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


}
