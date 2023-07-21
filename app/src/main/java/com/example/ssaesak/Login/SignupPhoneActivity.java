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

import com.example.ssaesak.Model.User;
import com.example.ssaesak.R;
import com.example.ssaesak.Retrofit.MyRetrofit;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupPhoneActivity extends Activity {

    String name;
    TextView tooltip;
    LinearLayout editTextLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_phone);
        ImageButton backButton = findViewById(R.id.back_button);

        this.tooltip = findViewById(R.id.tooltip);
        this.editTextLayout = findViewById(R.id.edittext_layout);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), SignupProfileActivity.class));
                overridePendingTransition(0, 0);
                finish();
            }
        });

//        Button nextButton = findViewById(R.id.next).findViewById(R.id.button_next);
        Button nextButton = findViewById(R.id.button_next);
        nextButton.setClickable(false);
//        Button afterButton = findViewById(R.id.after).findViewById(R.id.button_after);
        Button afterButton = findViewById(R.id.button_after);
        afterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup();
                if (User.getInstance().getType().equals("도시농부")) {
                    startActivity(new Intent(getBaseContext(), SignupWorkerPositionActivity.class));
                    overridePendingTransition(0, 0);
                    finish();
                } else {
                    startActivity(new Intent(getBaseContext(), SignupFarmerActivity.class));
                    overridePendingTransition(0, 0);
                    finish();
                }
            }
        });
//        nextButton.setClickable(false);

        EditText editText = findViewById(R.id.edittext);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Pattern pattern = Pattern.compile("^[0-9]+$");
                if (editText.getText().length() != 11 || !pattern.matcher(editText.getText().toString()).matches()) {
                    Log.e("signupname", "전화번호 규칙 아님!!");
                    nextButton.setClickable(false);
                    nextButton.setBackground(getResources().getDrawable(R.drawable.button_gray, null));
                    tooltip.setVisibility(View.VISIBLE);
                    tooltip.setText("11자리 숫자를 입력해주세요");
                    tooltip.setTextColor(getResources().getColor(R.color.active_red, null));
                    editTextLayout.setBackground(getResources().getDrawable(R.drawable.edittext_red, null));
                } else {
                    nextButton.setClickable(true);
                    Log.e("signupname", "전화번호 규칙 통과");
                    nextButton.setBackground(getResources().getDrawable(R.drawable.button_primary, null));
                    tooltip.setVisibility(View.INVISIBLE);
                    editTextLayout.setBackground(getResources().getDrawable(R.drawable.edittext_primary, null));
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                User.getInstance().setPhone(editText.getText().toString());
                signup();
                Log.e("type", User.getInstance().getType() + "!!!!!!");
                if (User.getInstance().getType().equals("도시농부")) {
                    startActivity(new Intent(getBaseContext(), SignupWorkerPositionActivity.class));
                    overridePendingTransition(0, 0);
                    finish();
                } else {
                    startActivity(new Intent(getBaseContext(), SignupFarmerActivity.class));
                    overridePendingTransition(0, 0);
                    finish();
                }
            }
        });
    }


    private void signup() {
        Log.e("signup", "user!! : " + User.getInstance().getUserId());
        Call<User> call = MyRetrofit.getApiService().signupkakao(User.getInstance());
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.e("signup", "success!!");
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("signup", "failed");
            }
        });
    }

}
