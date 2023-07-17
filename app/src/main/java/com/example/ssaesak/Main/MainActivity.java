package com.example.ssaesak.Main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.ssaesak.Board.BoardActivity;
import com.example.ssaesak.Dto.BoardDTO;
import com.example.ssaesak.Dto.WorkNoticeRecommendDTO;
import com.example.ssaesak.Farmgroup.FarmgroupActivity;
import com.example.ssaesak.Login.LoginActivity;
import com.example.ssaesak.Login.SignupProfileActivity;
import com.example.ssaesak.Login.SignupTypeActivity;
import com.example.ssaesak.Login.SignupWorkerPositionActivity;
import com.example.ssaesak.Model.User;
import com.example.ssaesak.Model.Worker;
import com.example.ssaesak.R;
import com.example.ssaesak.Retrofit.ApiResponse;
import com.example.ssaesak.Retrofit.MyObjectMapper;
import com.example.ssaesak.Retrofit.MyRetrofit;
import com.example.ssaesak.Study.StudyActivity;
import com.example.ssaesak.Working.WorkingActivity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.gson.JsonObject;
import com.kakao.sdk.common.KakaoSdk;
import com.kakao.sdk.user.UserApiClient;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import me.relex.circleindicator.CircleIndicator3;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

    public class MainActivity extends AppCompatActivity {

    private ViewPager2 mPager;
    private FragmentStateAdapter pagerAdapter;
    private int num_page = 2;
    private CircleIndicator3 mIndicator;

    private ImageButton mypageButton;

    private List<BoardDTO> hotNoticeList;

    private String TAG = "main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.checkPermissionCustom();

        KakaoSdk.init(this, "4caf1a2e579000e6cd8d530264db7aed");
        User.getInstance().setUserId(2905062448L);
//        isKakaologin(this);

        setContentView(R.layout.activity_home);

        this.mypageButton = findViewById(R.id.mypage_button);
        this.mypageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MypageActivity.class));
                overridePendingTransition(0, 0);
            }
        });


        this.initBottomNavigation();
        this.hotNotice();

    }

    @Override
    public void onBackPressed() {
//        this.startActivity(new Intent(this, MainActivity.class));

        finish();
        overridePendingTransition(0, 0); //애니메이션 없애기
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grandResults) {
        // READ_PHONE_STATE의 권한 체크 결과를 불러온다
        super.onRequestPermissionsResult(requestCode, permissions, grandResults);
        if (requestCode == 1000) {
            boolean check_result = true;

            // 모든 퍼미션을 허용했는지 체크
            for (int result : grandResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    check_result = false;
                    break;
                }
            }

            // 권한 체크에 동의를 하지 않으면 안드로이드 종료
            if (check_result == false) {
                finish();
            }
        }
    }



    private void isKakaologin(MainActivity view) {
        UserApiClient.getInstance().me(new Function2<com.kakao.sdk.user.model.User, Throwable, Unit>() {
            @Override
            public Unit invoke(com.kakao.sdk.user.model.User user_kakao, Throwable throwable) {
                login(user_kakao.getId());
//                if(user_kakao == null) {
//                    startActivity(new Intent(getBaseContext(), LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
//                    overridePendingTransition(0, 0);
//                    finish();
//                } else {
//                    login(user_kakao.getId());
//                }
                    return null;
            }
        });
    }


    private void login(Long id) {
        Log.e("login", "start!!");
        User.getInstance().setUserId(id);
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

                ObjectMapper mapper = new ObjectMapper();
                String body = response.body().getData().toString();
                String json = body.substring(1, body.length()-1).replace("\\", "");
                Log.e("login", " string -> " + json);
                try {
                    User.setInstance(mapper.readValue(json, User.class));
                } catch (Exception e1) {e1.printStackTrace();}


                if (User.getInstance().getType().equals("도시농부")) {
                    // 도시농부 테이블 받기
                    getWorker();
                    // 농장 정보 받고 없으면 추천 데이터 받기
                    getWorkRecommend(MainActivity.this);
                } else {
                    // 지원현황, 일감 가져오기
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {Log.e("연결실패", t.getMessage() + "");}
        });
    }



    private List<WorkNoticeRecommendDTO> list = new ArrayList<>();
    private List<WorkNoticeRecommendDTO> getWorkRecommend(MainActivity mainActivity) {
        Call<List<WorkNoticeRecommendDTO>> call = MyRetrofit.getApiService().recommendWorkerNotice(Worker.getInstance().getArea(),
                                                                                            Worker.getInstance().getAgriculture(),
                                                                                            Worker.getInstance().getCrops());
        Log.e("main", "getWorkRecommend !! : " + Worker.getInstance().getArea());
        call.enqueue(new Callback<List<WorkNoticeRecommendDTO>>() {
            @Override
            public void onResponse(Call<List<WorkNoticeRecommendDTO>> call, Response<List<WorkNoticeRecommendDTO>> response) {
                list = response.body();
                Log.e("main", "getWorkRecommend !! : " + response.body());

                //ViewPager2
                mPager = findViewById(R.id.viewpager);
                //Adapter

                pagerAdapter = new MyAdapter(mainActivity, num_page, list.get(0), list.get(1));
                mPager.setAdapter(pagerAdapter);
                //Indicator
                mIndicator = findViewById(R.id.indicator);
                mIndicator.setViewPager(mPager);
                mIndicator.createIndicators(num_page,0);
                //ViewPager Setting
                mPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);

                /**
                 * 이 부분 조정하여 처음 시작하는 이미지 설정.
                 * 2000장 생성하였으니 현재위치 1002로 설정하여
                 * 좌 우로 슬라이딩 할 수 있게 함. 거의 무한대로
                 */

                mPager.setCurrentItem(1000); //시작 지점
                mPager.setOffscreenPageLimit(2); //최대 이미지 수

                mPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                        super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                        if (positionOffsetPixels == 0) {
                            mPager.setCurrentItem(position);
                        }
                    }

                    @Override
                    public void onPageSelected(int position) {
                        super.onPageSelected(position);
                        mIndicator.animatePageSelected(position%num_page);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<WorkNoticeRecommendDTO>> call, Throwable t) {Log.e("연결실패", t.getMessage() + "");}
        });

        return list;
    }

    // 도시농부 정보 받아오기
    private void getWorker() {
        Call<ApiResponse> call = MyRetrofit.getApiService().loginWorker(User.getInstance().getUserId());
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {

            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

            }
        });
    }


    // 농장주 정보 받아오기
    private void getFarmer() {
        Call<ApiResponse> call = MyRetrofit.getApiService().loginWorker(User.getInstance().getUserId());
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {

            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

            }
        });
    }


//    @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@2
//    실시간 인기 글
    private void hotNotice() {
        Log.e("hotnotice", "get hotNotice start!!");
        Call<ApiResponse> call = MyRetrofit.getApiService().hotNotice();
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                Log.e("hotnotice", "hotNoticeList : ");
                Log.e("hotnotice", "hotNoticeList : " + response.body());
                ObjectMapper mapper = new ObjectMapper();
                String body = response.body().getData().toString();
                String json = body.substring(1, body.length()-1).replace("\\", "");
                Log.e("hotnotice", " body -> " + body);
                Log.e("hotnotice", " json -> " + json);
                try {
//                List<BoardDTO> dtos = mapper.readValue(json, BoardDTO[].class);
                List<BoardDTO> dtos = Arrays.asList(mapper.readValue(json, BoardDTO[].class));
                
                } catch (Exception e1) {e1.printStackTrace();}

//                List<UserDTO> dtos = Arrays.asList(mapper.readValue(strList, UserDTO[].class));
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t)  {Log.e("hotnotice failed", t.getMessage() + "");}
        });
    }




//    @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@2
//    바텀내비 init
    private void initBottomNavigation() {

        // 내비 표시
        BottomNavigationView navigation = findViewById(R.id.fragment_bottom);
        Menu menu = navigation.getMenu();
        for (int i = 0; i < navigation.getMenu().size(); i++) {
            MenuItem menuItem = menu.getItem(i);
            if (menuItem.getItemId() == R.id.fragment_home) {menuItem.setChecked(true);}
        }

        navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.fragment_home) {
//                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                    overridePendingTransition(0, 0);
                    return true;
                } else if (item.getItemId() == R.id.fragment_working) {

                    Intent intent = new Intent(getApplicationContext(), WorkingActivity.class);
//                    intent.putExtra("bottom", );
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                    return true;
                } else if (item.getItemId() == R.id.fragment_farm) {
                    startActivity(new Intent(getApplicationContext(), FarmgroupActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                } else if (item.getItemId() == R.id.fragment_notice) {
                    startActivity(new Intent(getApplicationContext(), BoardActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                } else if (item.getItemId() == R.id.fragment_study) {
                    startActivity(new Intent(getApplicationContext(), StudyActivity.class));
                    overridePendingTransition(0, 0);
//                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_screen, fragment_study).commitAllowingStateLoss();
                    return true;
                } else {
                    return false;
                }
            }
        });
    }


    private void checkPermissionCustom() {
        // 권한ID를 가져옵니다
        int permission = ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET);
        int permission2 = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        int permission3 = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);

        // 권한이 열려있는지 확인
        if (permission == PackageManager.PERMISSION_DENIED || permission2 == PackageManager.PERMISSION_DENIED || permission3 == PackageManager.PERMISSION_DENIED) {
            // 마쉬멜로우 이상버전부터 권한을 물어본다
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                // 권한 체크(READ_PHONE_STATE의 requestCode를 1000으로 세팅
                requestPermissions(
                        new String[]{Manifest.permission.INTERNET, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                        1000);
            }
            return;
        }
    }
}