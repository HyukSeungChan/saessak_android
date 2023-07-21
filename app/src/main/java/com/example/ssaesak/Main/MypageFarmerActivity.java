package com.example.ssaesak.Main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ssaesak.Login.LoginActivity;
import com.example.ssaesak.Model.User;
import com.example.ssaesak.R;
import com.kakao.sdk.user.UserApiClient;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;

public class MypageFarmerActivity extends Activity {

    ImageButton backButton;

    TextView apply;
    TextView resume;

    LinearLayout activityHistory;
    LinearLayout useButton;
    LinearLayout privacyButton;

    TextView logout;
    TextView signout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage_farmer);
        TextView d;

        ImageView image = findViewById(R.id.image);
        Glide.with(getBaseContext()).load("https://saessak-s3.s3.ap-northeast-2.amazonaws.com/download.jpg").into(image);


        ImageView imageButton = findViewById(R.id.profile_image);
        imageButton = findViewById(R.id.profile_image);
        UserApiClient.getInstance().me(new Function2<com.kakao.sdk.user.model.User, Throwable, Unit>() {
            @Override
            public Unit invoke(com.kakao.sdk.user.model.User user_kakao, Throwable throwable) {
                String profileUrl = user_kakao.getKakaoAccount().getProfile().getThumbnailImageUrl();
                ImageView imageView = (ImageView) findViewById(R.id.profile_image);
                Glide.with(getBaseContext()).load(profileUrl).into(imageView);

                User.getInstance().setProfileImage("kakao");
                Log.e("profile", user_kakao.getKakaoAccount().getProfile().getThumbnailImageUrl() + "");
                return null;
            }
        });

        ((TextView) findViewById(R.id.name)).setText(User.getInstance().getName());
//        if (User.getInstance().getType().equals("도시농부")) {
//            ((TextView) findViewById(R.id.address)).setText(Worker.getInstance().get());
//        } else {
//            ((TextView) findViewById(R.id.address)).setText(User.getInstance().get());
//        }


        this.activityHistory = findViewById(R.id.activity);
        this.activityHistory.setOnClickListener(v -> {startActivity(new Intent(getBaseContext(), MypageActivityHistoryActivity.class));});
        this.privacyButton = findViewById(R.id.privacy);
        this.privacyButton.setOnClickListener(v -> {startActivity(new Intent(getBaseContext(), MypagePrivacyActivity.class));});

        this.logout = findViewById(R.id.logout);
        this.logout.setOnClickListener(v -> {
            UserApiClient.getInstance().logout(throwable -> {

                Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                User.setInstance(new User());
                startActivity(intent);
                return null;
            });
        });


        this.signout = findViewById(R.id.signout);


        ImageButton update = findViewById(R.id.update_profile);
//        update.setOnClickListener(v -> {
//            startActivity(new Intent(getBaseContext(), MypageUpdateWorker.class));
//        });

        this.backButton = findViewById(R.id.back_button);
        this.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(0, 0); //애니메이션 없애기
            }
        });

    }

}
