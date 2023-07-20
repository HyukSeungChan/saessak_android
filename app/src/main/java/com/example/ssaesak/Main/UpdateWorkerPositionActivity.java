package com.example.ssaesak.Main;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.ssaesak.Dto.WorkerDTO;
import com.example.ssaesak.Login.SignupProfileActivity;
import com.example.ssaesak.Login.SignupWorkerAgricultureActivity;
import com.example.ssaesak.Model.User;
import com.example.ssaesak.Model.Worker;
import com.example.ssaesak.R;
import com.example.ssaesak.Retrofit.MyRetrofit;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateWorkerPositionActivity extends Activity {

    String name;
    TextView tooltip;
    TextView tooltipWarning;
    List<Button> positionList;
    List<Button> buttonList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_worker_position);

        this.tooltip = findViewById(R.id.tooltip);
        this.tooltipWarning = findViewById(R.id.tooltip_warning);
        this.positionList = new ArrayList<>();
        this.buttonList = new ArrayList<>();

        ImageButton backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), SignupProfileActivity.class));
                overridePendingTransition(0, 0);
                finish();
            }
        });

        Button nextButton = findViewById(R.id.button_next);
        nextButton.setClickable(false);
        Button afterButton = findViewById(R.id.button_after);
        afterButton.setOnClickListener(v -> {
            startActivity(new Intent(getBaseContext(), SignupWorkerAgricultureActivity.class));
            overridePendingTransition(0, 0);
            finish();
        });

        buttonList.add(findViewById(R.id.chip_all));
        buttonList.add(findViewById(R.id.chip_rhltks));
        buttonList.add(findViewById(R.id.chip_eksdid));
        buttonList.add(findViewById(R.id.chip_qhdms));
        buttonList.add(findViewById(R.id.chip_dudehd));
        buttonList.add(findViewById(R.id.chip_dhrcjs));
        buttonList.add(findViewById(R.id.chip_dmatjd));
        buttonList.add(findViewById(R.id.chip_wpcjs));
        buttonList.add(findViewById(R.id.chip_wmdvud));
        buttonList.add(findViewById(R.id.chip_wlscjs));
        buttonList.add(findViewById(R.id.chip_cjdwn));
        buttonList.add(findViewById(R.id.chip_cndwn));

        for (Button button : buttonList) {
            button.setOnClickListener(v -> {
                tooltipWarning.setVisibility(View.INVISIBLE);
                if (positionList.contains(button)) {
                    Log.e("area", "press button!!");
                    tooltipWarning.setVisibility(View.INVISIBLE);
                    button.setPressed(false);
                    button.setBackground(getResources().getDrawable(R.drawable.chip_not_select, null));
                    button.setTextColor(Color.rgb(120, 120, 120));
                    positionList.remove(button);
                    tooltip.setText(positionList.size()+"");
                } else if (positionList.size() < 3){
                    Log.e("area", "not press button!!");
                    tooltipWarning.setVisibility(View.INVISIBLE);
                    button.setPressed(true);
                    button.setBackground(getResources().getDrawable(R.drawable.chip_select, null));
                    button.setTextColor(Color.rgb(255, 255, 255));
                    positionList.add(button);
                    tooltip.setText(positionList.size()+"");
                } else {
                    Log.e("area", "outofindex!!");
                    tooltip.setTextColor(getResources().getColor(R.color.active_red, null));
                    tooltipWarning.setVisibility(View.VISIBLE);
                }

                if (positionList.size() > 0) {
                    nextButton.setClickable(true);
                    nextButton.setBackground(getResources().getDrawable(R.drawable.button_primary, null));
                } else {
                    nextButton.setClickable(false);
                    nextButton.setBackground(getResources().getDrawable(R.drawable.button_gray, null));
                }
            });
        }

        String temp = "";
        for (Button button : buttonList) {
            if (Worker.getInstance().getArea().contains(button.getText().toString())) {
                Log.e("signup", button.getText().toString() + " size : " + buttonList.size());
                button.callOnClick();
            }
            //Worker.getInstance().getArea().split(",")
        }

        afterButton.setVisibility(View.GONE);
        nextButton.setOnClickListener(v -> {

            String result = "";
            for (Button b : positionList) {
                result += b.getText().toString() + ",";
            }
            if (positionList.size() > 1)
                result = result.substring(0, result.length()-1);
            Log.e("signup", result);
            Worker.getInstance().setArea(result);

            signup();

            startActivity(new Intent(getBaseContext(), MypageUpdateWorker.class));
            overridePendingTransition(0, 0);
            finish();
        });
    }

    private void signup() {
        Log.e("signup", "worker!! : " + Worker.getInstance().getWorkerId());
        WorkerDTO dto = new WorkerDTO(Worker.getInstance());

        Log.e("signup", "worker user id :!! : " + dto.getUserId());
        Call<WorkerDTO> call = MyRetrofit.getApiService().signupWorker(dto);
        call.enqueue(new Callback<WorkerDTO>() {
            @Override
            public void onResponse(Call<WorkerDTO> call, Response<WorkerDTO> response) {
                Log.e("signup", "success!!");
            }

            @Override
            public void onFailure(Call<WorkerDTO> call, Throwable t) {
                Log.e("signup", "failed");
            }
        });
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(getBaseContext(), MypageUpdateWorker.class));
        overridePendingTransition(0, 0);
        finish();
    }
}
