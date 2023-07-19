package com.example.ssaesak.Farmgroup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ssaesak.Dto.FarmResponseDTO;
import com.example.ssaesak.Model.User;
import com.example.ssaesak.Model.Worker;
import com.example.ssaesak.R;
import com.example.ssaesak.Retrofit.ApiResponse;
import com.example.ssaesak.Retrofit.MyRetrofit;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FarmgroupSidebar extends Activity {
//        implements MapView.CurrentLocationEventListener, MapView.MapViewEventListener {

    int farmId;

    ImageButton backButton;

    Button exitButton;

    TextView textPosition, textFarmer, textPhone;


    private MapView mapView;
    private ViewGroup mapViewContainer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sidebar_activity_farm_group);

        init();


        Intent intentData = getIntent();
        this.farmId = intentData.getIntExtra("farmId", -1);

        this.exitButton = findViewById(R.id.exit);

        getFarmInfo(1);

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
        Log.e("farmInfo", "입장 !!");
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

                    textPosition.setText(farmResponseDTO.getAddress());
                    textFarmer.setText(farmResponseDTO.getUserName());
                    textPhone.setText(farmResponseDTO.getPhone());
                } catch (Exception e) {
                    Log.e("addresssss", "aaaaaaaaaaaaaa!!!!!!!!!!!!!!!!!!!!!");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.e("farmInfo", "asdasd" + t);
            }
        });
    }

}
