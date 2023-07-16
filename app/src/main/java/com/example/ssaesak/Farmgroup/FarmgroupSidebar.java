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

import com.example.ssaesak.R;

import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

public class FarmgroupSidebar extends Activity {
//        implements MapView.CurrentLocationEventListener, MapView.MapViewEventListener {

    int farmId;

    ImageButton backButton;

    Button exitButton;


    private MapView mapView;
    private ViewGroup mapViewContainer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sidebar_activity_farm_group);

        Intent intentData = getIntent();
        this.farmId = intentData.getIntExtra("farmId", -1);

        this.exitButton = findViewById(R.id.exit);

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

}
