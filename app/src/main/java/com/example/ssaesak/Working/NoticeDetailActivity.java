package com.example.ssaesak.Working;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ssaesak.R;

public class NoticeDetailActivity extends AppCompatActivity {

    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_notice_detail);

        this.nextButton = findViewById(R.id.button_next);
        this.nextButton.setOnClickListener(v -> {
            Intent intent = new Intent(getBaseContext(), ResumeActivity.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
        });

    }

}
