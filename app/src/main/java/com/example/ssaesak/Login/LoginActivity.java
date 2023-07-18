package com.example.ssaesak.Login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.example.ssaesak.R;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.User;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;

public class LoginActivity extends Activity {

    ImageButton loginButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);


        loginButton = findViewById(R.id.kakao_login);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("LoginActivity", "로그인 !!");
                if (UserApiClient.getInstance().isKakaoTalkLoginAvailable(LoginActivity.this)) {

                    try {
                        UserApiClient.getInstance().loginWithKakaoTalk(LoginActivity.this, (oAuthToken, error) -> {
                            if(error.toString().contains("302")) {
                                loginKakaoAccount();
                            }
                            if (error != null) {
                                Log.e("LoginActivity", "로그인 실패", error);
                            } else if (oAuthToken != null) {
                                Log.i("LoginActivity", "로그인 성공(토큰) : " + oAuthToken.getAccessToken());
                                UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
                                    @Override
                                    public Unit invoke(com.kakao.sdk.user.model.User user_kakao, Throwable throwable) {
                                        com.example.ssaesak.Model.User.getInstance().setUserId(user_kakao.getId());
                                        startActivity(new Intent(LoginActivity.this, SignupTypeActivity.class));
                                        return null;
                                    }
                                });
                            }
                            return null;
                        });
                    } catch (Exception e) {

                    }
                } else {
                    loginKakaoAccount();
                }
            }
        });

    }


    private void loginKakaoAccount() {
        UserApiClient.getInstance().loginWithKakaoAccount(getBaseContext(),(oAuthToken, error) -> {
            if (error != null) {
                Log.e("LoginActivity", "로그인 실패", error);
            } else if (oAuthToken != null) {
                Log.i("LoginActivity", "로그인 성공(토큰) : " + oAuthToken.getAccessToken());
                UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
                    @Override
                    public Unit invoke(com.kakao.sdk.user.model.User user_kakao, Throwable throwable) {
                        com.example.ssaesak.Model.User.getInstance().setUserId(user_kakao.getId());
                        Log.e("userId", user_kakao.getId() + "");
                        startActivity(new Intent(LoginActivity.this, SignupTypeActivity.class));
                        return null;
                    }
                });
            }
            return null;
        });
    }


}
