package com.example.ssaesak.Farmgroup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.ssaesak.Dto.FarmResponseDTO;
import com.example.ssaesak.Dto.UserFarmResponseDTO;
import com.example.ssaesak.Dto.WorkDTO;
import com.example.ssaesak.Model.User;
import com.example.ssaesak.Model.Worker;
import com.example.ssaesak.R;
import com.example.ssaesak.Retrofit.ApiResponse;
import com.example.ssaesak.Retrofit.MyRetrofit;
import com.example.ssaesak.Working.CardWorkNotice;
import com.example.ssaesak.Working.NoticeDetailActivity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakao.sdk.user.UserApiClient;

import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.util.Arrays;
import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FarmgroupSidebar extends Activity {
//        implements MapView.CurrentLocationEventListener, MapView.MapViewEventListener {

    int farmId;

    ImageButton backButton;

    Button exitButton;

    private TextView textPosition, textFarmer, textPhone, name;

    private ImageView profileImage;

    private LayoutInflater layoutInflater;

    private LinearLayout memberList;

    private MapView mapView;
    private ViewGroup mapViewContainer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sidebar_activity_farm_group);

        init();

//        ImageView imageButton = findViewById(R.id.profile_image);
        profileImage = findViewById(R.id.profile_image);
        UserApiClient.getInstance().me(new Function2<com.kakao.sdk.user.model.User, Throwable, Unit>() {
            @Override
            public Unit invoke(com.kakao.sdk.user.model.User user_kakao, Throwable throwable) {
                String profileUrl = user_kakao.getKakaoAccount().getProfile().getThumbnailImageUrl();
                ImageView imageView = (ImageView) findViewById(R.id.profile_image);
//                Glide.with(getBaseContext()).load(profileUrl).into(imageView);

                com.example.ssaesak.Model.User.getInstance().setProfileImage("kakao");
                Log.e("profile", user_kakao.getKakaoAccount().getProfile().getThumbnailImageUrl() + "");
                return null;
            }
        });

        Intent intentData = getIntent();
        this.farmId = intentData.getIntExtra("farmId", -1);

        this.exitButton = findViewById(R.id.exit);

        getFarmInfo(farmId);
        findMemberList(farmId);

        MapView mapView = new MapView(this);
        ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.kakaomap);
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(37.895378, 127.051985), true);

        mapViewContainer.addView(mapView);






        this.exitButton.setOnClickListener(v -> {
            //데이터 담아서 팝업(액티비티) 호출
            Intent intent = new Intent(this, PopupExit.class);
            startActivityForResult(intent, 1);
        });




        this.backButton = findViewById(R.id.close);
        this.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(0, 0); //애니메이션 없애기
            }
        });

    }

    private void init(){
        this.textPosition = findViewById(R.id.text_position);
        this.textFarmer = findViewById(R.id.text_farmer);
        this.textPhone = findViewById(R.id.text_phone);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1){
            if(resultCode==RESULT_OK){
                //데이터 받기
                Log.e("farmgroup", "나갔어나갔어 그룹을 나가버려써!");
                startActivity(new Intent(getBaseContext(), FarmgroupReview.class));
                String result = data.getStringExtra("exit");
                finish();
                overridePendingTransition(0, 0); //애니메이션 없애기
            }
        }
    }

    // 해당 농장정보 조회
    public void getFarmInfo(int farmId) {
        Call<ApiResponse> call = MyRetrofit.getApiService().getFarmInfo(farmId);
        Log.e("farmInfo", "입장 !! : " + farmId);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                try {
                    ObjectMapper mapper = new ObjectMapper();
                    String body = response.body().getData().toString();
                    String json = body.substring(1, body.length() - 1).replace("\\", "");
                    Log.e("farmInfo", " body -> " + body);
                    Log.e("farmInfo", " string -> " + json);

                    FarmResponseDTO farmResponseDTO = mapper.readValue(json, FarmResponseDTO.class);

                    Log.e("farmInfo", " farmDto -> " + json);
                    textPosition.setText(farmResponseDTO.getAddress());
                    textFarmer.setText(farmResponseDTO.getUserName());
                    textPhone.setText(farmResponseDTO.getPhone());
                } catch (Exception e) {
                    Log.e("addresssss", "aaaaaaaaaaaaaa!!!!!!!!!!!!!!!!!!!!!");
                    e.printStackTrace();
                    Log.e("addresssss", "aaaaaaaaaaaaaa!!!!!!!!!!!!!!!!!!!!!");
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.e("farmInfo", "asdasd" + t);
            }
        });
    }

    // 농장 메이트 추가
    public void addMemberList(UserFarmResponseDTO dto, LinearLayout parentLayout) {

        Log.e("addMemberList", "addMemberList 입장!!");
        this.layoutInflater = LayoutInflater.from(getBaseContext());


        this.memberList = (LinearLayout) layoutInflater.inflate(R.layout.card_worker, parentLayout, false);

        this.name = memberList.findViewById(R.id.name);
        this.profileImage = memberList.findViewById(R.id.profile_image);
        Log.e("name", dto.getName()+"!!");
        name.setText(dto.getName());

        Glide.with(getBaseContext())
                .load(dto.getProfileImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL) // 캐시 옵션 설정
                .into(profileImage);

        parentLayout.addView(memberList);
    }

    // 농장 메이트 조회
    public void findMemberList(int farmId) {
        Log.e("findMemberList","findMemberList 입장!!!!!!!!!!!!!!!!!!");
        Call<ApiResponse> call = MyRetrofit.getApiService().getMemberList(farmId);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                Log.e("findMemberList", "findMemberList : ");
                Log.e("findMemberList", "findMemberList : " + response.body());
                ObjectMapper mapper = new ObjectMapper();
                String body = response.body().getData().toString();
                String json = body.substring(1, body.length()-1).replace("\\", "");
                Log.e("findMemberList", " body -> " + body);
                Log.e("findMemberList", " json -> " + json);
                try {
                    List<UserFarmResponseDTO> dtos = Arrays.asList(mapper.readValue(json, UserFarmResponseDTO[].class));

//                    memberList.removeAllViews();
                    LinearLayout parentLayout = findViewById(R.id.mate_layout);
                    parentLayout.removeAllViews();
                    // Loop through the WorkListDTOs and add them to the card views
                    Log.e("Dto size", dtos.size() + "!!");
                    for (UserFarmResponseDTO dto : dtos) {
                        addMemberList(dto, parentLayout);
                    }
                } catch (Exception e1) {
                    Log.e("findMemberList", "Error parsing JSON", e1);
                    e1.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.e("workHome failed", t.getMessage() + "");
            }
        });

    }

}
