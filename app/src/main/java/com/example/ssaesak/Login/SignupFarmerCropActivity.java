package com.example.ssaesak.Login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.ssaesak.R;

public class SignupFarmerCropActivity extends Activity {

    String name;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_worker_crop);
        ImageButton backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), SignupFarmerAgricultureActivity.class));
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
//                startActivity(new Intent(getBaseContext(), SignupFarmerPayActivity.class));
//                overridePendingTransition(0, 0);
//                finish();
//            }
//        });


        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                if (editText.getText().toString().length() < 1) {
//                    Toast.makeText(getBaseContext(), "이름을 입력해주세요", Toast.LENGTH_SHORT);
//                } else {
//                    User.getInstance().setPhone(editText.getText().toString());
                    startActivity(new Intent(getBaseContext(), SignupFarmerPayActivity.class));
                    overridePendingTransition(0, 0);
                    finish();
//                }
            }
        });
    }
}
