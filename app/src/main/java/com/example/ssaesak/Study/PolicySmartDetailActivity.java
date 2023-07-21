package com.example.ssaesak.Study;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ssaesak.Dto.PolicySmartResponseDTO;
import com.example.ssaesak.R;

public class PolicySmartDetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage_policy_smart);

        PolicySmartResponseDTO dto = (PolicySmartResponseDTO) getIntent().getSerializableExtra("policy");


        TextView title = findViewById(R.id.title);
        title.setText(dto.getTitle());

        TextView type = findViewById(R.id.type);
        type.setText(dto.getType());

        TextView outline = findViewById(R.id.outline);
        outline.setText(dto.getOutline());

        TextView condition = findViewById(R.id.condition);
        condition.setText(dto.getApplyContent());

        TextView content = findViewById(R.id.content);
        content.setText(dto.getMainContent());

    }
}
