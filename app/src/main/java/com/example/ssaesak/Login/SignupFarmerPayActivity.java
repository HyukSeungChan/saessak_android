package com.example.ssaesak.Login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ssaesak.Dto.FarmDTO;
import com.example.ssaesak.Dto.WorkerDTO;
import com.example.ssaesak.Main.MainActivity;
import com.example.ssaesak.Model.Farm;
import com.example.ssaesak.Model.Worker;
import com.example.ssaesak.R;
import com.example.ssaesak.Retrofit.ApiResponse;
import com.example.ssaesak.Retrofit.MyRetrofit;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupFarmerPayActivity extends Activity {

    String name;
    TextView tooltip;
    LinearLayout editTextLayout;
    EditText editText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_worker_pay);

        this.tooltip = findViewById(R.id.tooltip);
        this.editTextLayout = findViewById(R.id.edittext_layout);

        ImageButton backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), SignupFarmerCropActivity.class));
                overridePendingTransition(0, 0);
                finish();
            }
        });

        Button nextButton = findViewById(R.id.button_next);
        Button afterButton = findViewById(R.id.button_after);
        afterButton.setVisibility(View.INVISIBLE);
//        afterButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                signup();
//                startActivity(new Intent(getBaseContext(), MainActivity.class));
//                overridePendingTransition(0, 0);
//                finish();
//            }
//        });


        editText = findViewById(R.id.edittext);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Pattern pattern = Pattern.compile("^[0-9]+$");
                if (!pattern.matcher(editText.getText().toString()).matches()) {
                    Log.e("signup", "숫자 아님!!");
                    nextButton.setClickable(false);
                    nextButton.setBackground(getResources().getDrawable(R.drawable.button_gray, null));
                    tooltip.setText("숫자를 입력해주세요");
                    tooltip.setTextColor(getResources().getColor(R.color.active_red, null));
                    editTextLayout.setBackground(getResources().getDrawable(R.drawable.edittext_red, null));
                } else {
                    nextButton.setClickable(true);
                    Log.e("signup", "일급 규칙 통과");
                    nextButton.setBackground(getResources().getDrawable(R.drawable.button_primary, null));
                    tooltip.setText("일급 기준으로 입력해 주세요");
                    tooltip.setTextColor(getResources().getColor(R.color.gray5, null));
                    editTextLayout.setBackground(getResources().getDrawable(R.drawable.edittext_normal, null));
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup();
                startActivity(new Intent(getBaseContext(), MainActivity.class));
                overridePendingTransition(0, 0);
                finish();
            }
        });
    }

    private void signup() {
        Farm.getInstance().setPay(Integer.parseInt(editText.getText().toString()));

        Log.e("signup", "worker!! : " + Farm.getInstance().getFarmId());
//        Farm.setInstance()

        FarmDTO.setInstance(Farm.getInstance());
        Call<ApiResponse> call = MyRetrofit.getApiService().signFarmer(FarmDTO.getInstance());
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                Log.e("signup", "success!!");
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.e("signup", "failed");
            }
        });
    }
}