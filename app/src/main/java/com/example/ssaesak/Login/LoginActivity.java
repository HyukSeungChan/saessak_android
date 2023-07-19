package com.example.ssaesak.Login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ssaesak.Main.MainActivity;
import com.example.ssaesak.R;
import com.example.ssaesak.Retrofit.ApiResponse;
import com.example.ssaesak.Retrofit.MyRetrofit;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.User;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends Activity {

    ImageButton loginButton;
    TextView loginButtonAccount;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);


        loginButton = findViewById(R.id.kakao_login);
        loginButtonAccount = findViewById(R.id.account_login);
        loginButtonAccount.setOnClickListener(v -> {
            loginKakaoAccount();
        });


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
                                        login(user_kakao.getId());
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
                        login(user_kakao.getId());
                        Log.e("userId", user_kakao.getId() + "");
                        login(user_kakao.getId());
                        startActivity(new Intent(LoginActivity.this, SignupTypeActivity.class));
                        return null;
                    }
                });
            }
            return null;
        });
    }


    private void login(Long id) {
        Log.e("login", "start!!");
        com.example.ssaesak.Model.User.getInstance().setUserId(id);
        Call<ApiResponse> call = MyRetrofit.getApiService().login(id);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {

                if(response.code() == 404) {
                    Log.e("login", "처음 들어온 유저입니다~ : " + response.code());
                    startActivity(new Intent(getBaseContext(), SignupTypeActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                    overridePendingTransition(0, 0);
                    return;
                }

                if (!response.isSuccessful()) {
                    Toast.makeText( getBaseContext(), "연결 상태가 좋지 않습니다. 다시 시도해주세요", Toast.LENGTH_SHORT);
                    Log.e("연결이 비정상적 : ", "error code : " + response.code());
                    return;
                }

                if(response.body().getData() != null) finish();

                ObjectMapper mapper = new ObjectMapper();
                String body = response.body().getData().toString();
                String json = body.substring(1, body.length()-1).replace("\\", "");
                Log.e("login", " string -> " + json);
                try {
                    startActivity(new Intent(getBaseContext(), MainActivity.class));
                    finish();
//                    com.example.ssaesak.Model.User.setInstance(mapper.readValue(json, com.example.ssaesak.Model.User.class));
                } catch (Exception e1) {e1.printStackTrace();}


//                if (com.example.ssaesak.Model.User.getInstance().getType().equals("도시농부")) {
//                    // 도시농부 테이블 받기
//                    getWorker();
//                    // 농장 정보 받고 없으면 추천 데이터 받기
//                    getFarm();
//                } else {
//                    // 지원현황, 일감 가져오기
//                    getFarmer();
//                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {Log.e("연결실패", t.getMessage() + "");}
        });
    }



}
