package com.example.ssaesak.Main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ssaesak.Board.BoardActivity;
import com.example.ssaesak.Board.BoardDetailActivity;
import com.example.ssaesak.Dto.BoardDetailDTO;
import com.example.ssaesak.Dto.UserTodoFarmDTO;
import com.example.ssaesak.Dto.UserTodoFarmResponseDto;
import com.example.ssaesak.Dto.WorkNoticeRecommendDTO;
import com.example.ssaesak.Farmgroup.FarmgroupActivity;
import com.example.ssaesak.Farmgroup.FarmgroupFarmerActivity;
import com.example.ssaesak.Farmgroup.FarmgroupNullActivity;
import com.example.ssaesak.Login.LoginActivity;
import com.example.ssaesak.Login.SignupTypeActivity;
import com.example.ssaesak.Login.SignupWorkerCropActivity;
import com.example.ssaesak.Model.Farm;
import com.example.ssaesak.Model.User;
import com.example.ssaesak.Model.UserFarm;
import com.example.ssaesak.Model.UserFarmList;
import com.example.ssaesak.Model.Worker;
import com.example.ssaesak.R;
import com.example.ssaesak.Retrofit.ApiResponse;
import com.example.ssaesak.Retrofit.MyRetrofit;
import com.example.ssaesak.Study.SmartPolicyActivity;
import com.example.ssaesak.Study.StudyActivity;
import com.example.ssaesak.Working.WorkingNoticeFarmerActivity;
import com.example.ssaesak.Working.WorkingWorkerActivity;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.kakao.sdk.common.KakaoSdk;
import com.kakao.sdk.user.UserApiClient;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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

    private List<BoardDetailDTO> hotNoticeList;

    private String TAG = "main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getAppKeyHash();

        this.checkPermissionCustom();

//        User.getInstance().setUserId(1L);
//
//        startActivity(new Intent(getApplicationContext(), SignupWorkerCropActivity.class));
//        finish();

        KakaoSdk.init(this, "4caf1a2e579000e6cd8d530264db7aed");
        isKakaologin(this);

        setContentView(R.layout.activity_home);


        ((LinearLayout)findViewById(R.id.support_tab)).setOnClickListener(v -> {
            startActivity(new Intent(getBaseContext(), SmartPolicyActivity.class));
        });

        this.mypageButton = findViewById(R.id.mypage_button);
        this.mypageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (User.getInstance().getType().equals("도시농부")) {
                    startActivity(new Intent(getApplicationContext(), MypageActivity.class));
                    overridePendingTransition(0, 0);
                } else {

                    startActivity(new Intent(getApplicationContext(), MypageFarmerActivity.class));
                    overridePendingTransition(0, 0);
                }
            }
        });

        LinearLayout month_tech = findViewById(R.id.month_tech);
        month_tech.setOnClickListener(v -> {
            startActivity(new Intent(getBaseContext(), AgricultureTechActivity.class));
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
//                Log.e("login", "user !! : " + user_kakao.getId());
//                login(user_kakao.getId());
                if(user_kakao == null) {
                    startActivity(new Intent(getBaseContext(), LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                    overridePendingTransition(0, 0);
                    finish();
                } else {
                    login(user_kakao.getId());
                }
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
                    ((TextView)findViewById(R.id.userId)).setText(User.getInstance().getName());
                } catch (Exception e1) {e1.printStackTrace();}


                if (User.getInstance().getType().equals("도시농부")) {
                    Log.e("main", "도시농부 로그인 !! " + User.getInstance().getUserId());
                    // 도시농부 테이블 받기
                    getWorker();
                    // 농장 정보 받고 없으면 추천 데이터 받기
                    getFarm();
                } else {
                    Log.e("main", "농장주 로그인 !! " + User.getInstance().getUserId());
                    // 지원현황, 일감 가져오기
                    getFarmer();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {Log.e("연결실패", t.getMessage() + "");}
        });
    }



    private List<WorkNoticeRecommendDTO> list = new ArrayList<>();
    private List<WorkNoticeRecommendDTO> getWorkRecommend() {
        Call<ApiResponse> call = MyRetrofit.getApiService().recommendWorkerNotice(Worker.getInstance().getArea(),
                                                                                    Worker.getInstance().getAgriculture(),
                                                                                    Worker.getInstance().getCrops());
        call.enqueue(new Callback<ApiResponse>() {
             @Override
             public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                 findViewById(R.id.recommand_layout).setVisibility(View.VISIBLE);
                 findViewById(R.id.today_work).setVisibility(View.GONE);
                 findViewById(R.id.weather).setVisibility(View.GONE);
                 try {

                 Log.e("main", "getWorkRecommend !! : " + response.body().getData().toString());
                 ObjectMapper mapper = new ObjectMapper();
                 String body = response.body().getData().toString();
                 String json = body.substring(1, body.length() - 1).replace("\\", "");
                 Log.e("main", "getWorkRecommend !! : " + json);
                     List<WorkNoticeRecommendDTO> list = Arrays.asList(mapper.readValue(json, WorkNoticeRecommendDTO[].class));
//                     List<BoardDTO> dtos = Arrays.asList(mapper.readValue(json, BoardDTO[].class));

                     Log.e("main", "getWorkRecommend !! : " + json);

                     //ViewPager2
                     mPager = findViewById(R.id.viewpager);
                     //Adapter

                     pagerAdapter = new MyAdapter(MainActivity.this, num_page, list.get(0), list.get(1));
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

                 } catch (Exception e1) {
                     e1.printStackTrace();
                 }
             }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {Log.e("연결실패", t.getMessage() + "");}
         });



        return list;
    }

    // 도시농부 정보 받아오기
    private void getWorker() {
        Log.e("main", "get worker!!");
        Call<ApiResponse> call = MyRetrofit.getApiService().loginWorker(User.getInstance().getUserId());
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body() == null) {
                    getWorkRecommend();
                    return;
                }


                ObjectMapper mapper = new ObjectMapper();
                String body = response.body().getData().toString();
                String json = body.substring(1, body.length()-1).replace("\\", "");
                Log.e("worker", " string -> " + json);
                try {
                    Worker.setInstance(mapper.readValue(json, Worker.class));
                } catch (Exception e1) {e1.printStackTrace();}
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

            }
        });
    }

    private void getFarm() {
        Log.e("main", "get farm!!");
        Call<ApiResponse> call = MyRetrofit.getApiService().loginGetFarmList(User.getInstance().getUserId());
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                Log.e("main", "my farm count : " + response.body().getData().toString());
                if (response.body().getData().toString().equals("\"[]\"") || response.body().getData().toString().equals(null) || response.body().getData().toString().equals("[]") || response.body().getData().toString() == null) {
                    Log.e("main", "not farm!! start recommend!!");
                    findViewById(R.id.recommand_layout).setVisibility(View.VISIBLE);
                    findViewById(R.id.today_work).setVisibility(View.GONE);
                    findViewById(R.id.weather).setVisibility(View.GONE);

                    getWorkRecommend();
                    return;
                }
                findViewById(R.id.recommand_layout).setVisibility(View.GONE);
                findViewById(R.id.today_work).setVisibility(View.VISIBLE);
                findViewById(R.id.weather).setVisibility(View.VISIBLE);

                ObjectMapper mapper = new ObjectMapper();
                String body = response.body().getData().toString();
                String json = body.substring(1, body.length()-1).replace("\\", "");
                try {
                    List<UserFarm> dtos = Arrays.asList(mapper.readValue(json, UserFarm[].class));
                    Log.e("main", "my farm id : " + dtos.get(0).getFarmId());
                    new UserFarmList(dtos);
                    Log.e("main", "my farm id : " + UserFarmList.getInstance().get(0).getFarmId());

                    getTodoList(dtos.get(0).getFarmId());

                    Log.e("main", "my farm count : " + UserFarmList.getInstance().size());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

            }
        });

    }


    private void getTodoList(int farmId) {
        Log.e("main", "get my farm start!!");
        LinearLayout linearLayout = findViewById(R.id.todo_layout);

        findViewById(R.id.recommand_layout).setVisibility(View.GONE);
        findViewById(R.id.today_work).setVisibility(View.VISIBLE);
        findViewById(R.id.weather).setVisibility(View.VISIBLE);
        linearLayout.removeAllViews();
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");

        Call<ApiResponse> call;
        if (User.getInstance().getType().equals("도시농부")) {
            call = MyRetrofit.getApiService().getTodoList(User.getInstance().getUserId(), farmId);

        } else {
            call = MyRetrofit.getApiService().getTodoList(farmId);

        }
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
//                Log.e("main", "todoList : " + response.body().getData().toString());

                try {
                    ObjectMapper mapper = new ObjectMapper();
                    String body = response.body().getData().toString();
                    String json = body.substring(1,body.length()-1).replace("\\", "");


                    List<UserTodoFarmDTO> dtos = Arrays.asList(mapper.readValue(json, UserTodoFarmDTO[].class));
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    int count = 0;
                        Date nowDate = new Date();
                        String today = formatter.format(nowDate);
                    for (UserTodoFarmDTO dto : dtos) {
                        Log.e("main", "dto date !!!! : " + dto.getDate().equals(today));
                        Log.e("main", "dto date !!!! : " + (dto.getUserId() == User.getInstance().getUserId()));
//                        Log.e("main", "new Date !!!!" + formatter.parse(String.valueOf(new Date())).toString());
                        Log.e("main", "new Date !!!!" + today);



                        if (dto.getDate().equals(today)) {
                            Log.e("main", "일감 있음!!!!");
                            count++;
                            LayoutInflater layoutInflater = LayoutInflater.from(getBaseContext());
                            LinearLayout video = (LinearLayout) layoutInflater.inflate(R.layout.card_todo, linearLayout, false);
                            ((TextView)video.findViewById(R.id.task_name)).setText(dto.getTask());
                            video.setVisibility(View.VISIBLE);
                            linearLayout.addView(video);
                        }

                    }
                    if (count == 0) {
                        Log.e("main", "일감 없음!!!!");
                        LayoutInflater layoutInflater = LayoutInflater.from(getBaseContext());
                        LinearLayout video = (LinearLayout) layoutInflater.inflate(R.layout.card_todo_null, linearLayout, false);
                        linearLayout.addView(video);
                        return;
                    }
                } catch (NullPointerException e) {
                    Log.e("main", "널 포인터!!!!!");
                    LayoutInflater layoutInflater = LayoutInflater.from(getBaseContext());
                    LinearLayout video = (LinearLayout) layoutInflater.inflate(R.layout.card_todo_null, linearLayout, false);
                    linearLayout.addView(video);
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

            }
        });

    }


    // 농장주 정보 받아오기
    private void getFarmer() {
        Call<ApiResponse> call = MyRetrofit.getApiService().loginGetFarm(User.getInstance().getUserId());
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                try {
                ObjectMapper mapper = new ObjectMapper();
                String body = response.body().getData().toString();
                    Log.e("main", "get farmer body :: " + body);
                String json = body.substring(1, body.length()-1).replace("\\", "");
                    Log.e("main", "get farmer read value start !!");
                    Farm.setInstance(mapper.readValue(json, Farm.class));
                    Log.e("main", "get farmer read value end !! " + Farm.getInstance().getFarmId());
//                List<BoardDTO> dtos = mapper.readValue(json, BoardDTO[].class);
//                    List<BoardDetailDTO> dtos = Arrays.asList(mapper.readValue(json, BoardDetailDTO[].class));
                    getTodoList(Farm.getInstance().getFarmId());
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
                ObjectMapper mapper = new ObjectMapper();
                try {

//                List<BoardDTO> dtos = mapper.readValue(json, BoardDTO[].class);
//                List<BoardDetailDTO> dtos = Arrays.asList(mapper.readValue(json, BoardDetailDTO[].class));

                    String body = response.body().getData().toString();
                    String json = body.substring(1, body.length()-1).replace("\\", "");
//                  List<BoardDTO> dtos = mapper.readValue(json, BoardDTO[].class);
                    List<BoardDetailDTO> dtos = Arrays.asList(mapper.readValue(json, BoardDetailDTO[].class));

                    ((TextView)findViewById(R.id.hot_notice1_title)).setText(dtos.get(0).getTitle());
                    ((TextView)findViewById(R.id.hot_notice2_title)).setText(dtos.get(1).getTitle());

                    ((TextView)findViewById(R.id.hot_notice1_content)).setText(dtos.get(0).getContent());
                    ((TextView)findViewById(R.id.hot_notice2_content)).setText(dtos.get(1).getContent());

                    ((TextView)findViewById(R.id.hot_notice1_time)).setText(dtos.get(0).getUploadTime());
                    ((TextView)findViewById(R.id.hot_notice2_time)).setText(dtos.get(1).getUploadTime());

                    ((TextView)findViewById(R.id.hot_notice1_comment_count)).setText(dtos.get(0).getReplies()+"");
                    ((TextView)findViewById(R.id.hot_notice2_comment_count)).setText(dtos.get(1).getReplies()+"");


                    findViewById(R.id.hot_notice1_layout).setOnClickListener(v -> {
                        Intent intent = new Intent(getBaseContext(), BoardDetailActivity.class);
                        intent.putExtra("boardId", dtos.get(0).getBoardId());
                        startActivity(intent);
                    });

                    findViewById(R.id.hot_notice2_layout).setOnClickListener(v -> {
                        Intent intent = new Intent(getBaseContext(), BoardDetailActivity.class);
                        intent.putExtra("boardId", dtos.get(0).getBoardId());
                        startActivity(intent);
                    });

                } catch (Exception e1) {e1.printStackTrace();}

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

                    if(User.getInstance().getType().equals("도시농부")) {
                    Log.e("type", User.getInstance().getType() + "!!!!!!!!!!!!!!!");
                        Intent intent = new Intent(getApplicationContext(), WorkingWorkerActivity.class);
//                    intent.putExtra("bottom", );
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                        return true;
                    } else {
                        Intent intent = new Intent(getApplicationContext(), WorkingNoticeFarmerActivity.class);
//                    intent.putExtra("bottom", );
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                        return true;
                    }

                } else if (item.getItemId() == R.id.fragment_farm) {
                    Log.e("main", "navi ! my farm count : " + UserFarmList.getInstance().size()+"");
                    if(UserFarmList.getInstance().size() < 1 && User.getInstance().getType().equals("도시농부")) {
                        startActivity(new Intent(getApplicationContext(), FarmgroupNullActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    } else if (User.getInstance().getType().equals("농장주")){
                        startActivity(new Intent(getApplicationContext(), FarmgroupFarmerActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    }

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

        private void getAppKeyHash() {
            try {
                PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
                for (Signature signature : info.signatures) {
                    MessageDigest md;
                    md = MessageDigest.getInstance("SHA");
                    md.update(signature.toByteArray());
                    String something = new String(Base64.encode(md.digest(), 0));
                    Log.e("Hash key", something);
                }
            } catch (PackageManager.NameNotFoundException e) {
                Log.e("name not found", e.toString());
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }

        private void getHashKey()
        {
            PackageInfo packageInfo = null;
            try
            {
                packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            }
            catch (PackageManager.NameNotFoundException e)
            {
                e.printStackTrace();
            }
            if (packageInfo == null)
                Log.e("KeyHash", "KeyHash:null");

            for (Signature signature : packageInfo.signatures)
            {
                try
                {
                    MessageDigest md = MessageDigest.getInstance("SHA");
                    md.update(signature.toByteArray());
                    Log.e("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));
                }
                catch (NoSuchAlgorithmException e)
                {
                    Log.e("KeyHash", "Unable to get MessageDigest. signature=" + signature, e);
                }
            }
        }
}