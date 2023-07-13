package com.example.ssaesak.Login;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ssaesak.Model.User;
import com.example.ssaesak.R;

public class SignupTypeActivity extends Activity {

    String type;
    Button nextButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_type);

//        final View view = findViewById(R.id.next);
//        Button nextButton = (Button)view.findViewById(R.id.button_next);
        nextButton = findViewById(R.id.button_next);
        nextButton.setClickable(false);

        LinearLayout worker = findViewById(R.id.worker);
        LinearLayout farmer = findViewById(R.id.farmer);

        this.type = "";

        worker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "도시농부";
                farmer.setBackgroundColor(Color.rgb(238, 238, 238));
                worker.setBackgroundColor(Color.rgb(205, 205, 205));
                nextButton.setClickable(true);
                nextButton.setBackground(getResources().getDrawable(R.drawable.button_primary, null));
            }
        });

        farmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "농장주";
                worker.setBackgroundColor(Color.rgb(238, 238, 238));
                farmer.setBackgroundColor(Color.rgb(205, 205, 205));
                nextButton.setClickable(true);
                nextButton.setBackground(getResources().getDrawable(R.drawable.button_primary, null));
            }
        });

        if (User.getInstance().getType().equals("도시농부")) {
            type = "도시농부";
            farmer.setBackgroundColor(Color.rgb(238, 238, 238));
            worker.setBackgroundColor(Color.rgb(205, 205, 205));
            nextButton.setClickable(true);
        } else if (User.getInstance().getType().equals("농장주")) {
            type = "농장주";
            worker.setBackgroundColor(Color.rgb(238, 238, 238));
            farmer.setBackgroundColor(Color.rgb(205, 205, 205));
            nextButton.setClickable(true);
        }

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User.getInstance().setType(type);
                startActivity(new Intent(getBaseContext(), SignupNameActivity.class));
                overridePendingTransition(0, 0);
                finish();
            }
        });
    }
}
