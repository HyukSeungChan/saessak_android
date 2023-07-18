package com.example.ssaesak.Main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ssaesak.Login.LoginActivity;
import com.example.ssaesak.Model.User;
import com.example.ssaesak.R;
import com.kakao.sdk.user.UserApiClient;

public class MypageActivity extends Activity {

    ImageButton backButton;

    TextView apply;
    TextView resume;
    TextView bookmark;

    LinearLayout activityHistory;
    LinearLayout useButton;
    LinearLayout privacyButton;

    TextView logout;
    TextView signout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);
        TextView d;

        this.apply = findViewById(R.id.apply);
        this.apply.setOnClickListener(v -> {
            startActivity(new Intent(getBaseContext(), MypageApplyStatusActivity.class));
        });
        this.resume = findViewById(R.id.resume);
        this.bookmark = findViewById(R.id.bookmark);

        this.activityHistory = findViewById(R.id.activity);
        this.activityHistory.setOnClickListener(v -> {startActivity(new Intent(getBaseContext(), MypageActivityHistory.class));});
        this.useButton = findViewById(R.id.use);
        this.useButton.setOnClickListener(v -> {startActivity(new Intent(getBaseContext(), MypageUseActivity.class));});
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
