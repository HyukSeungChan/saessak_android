package com.example.ssaesak.Study;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ssaesak.Dto.PolicyAgricultureResponseDTO;
import com.example.ssaesak.Dto.PolicySmartResponseDTO;
import com.example.ssaesak.R;

public class PolicyAgricultureDetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage_policy_agriculture);

        PolicyAgricultureResponseDTO dto = (PolicyAgricultureResponseDTO) getIntent().getSerializableExtra("policy");
//        area
//                content
//        inquiry
//                note
//        title
//                qualification

        TextView area = findViewById(R.id.title);
        area.setText(dto.getTitle());

        TextView content = findViewById(R.id.content);
        content.setText(dto.getTitle());

        TextView inquiry = findViewById(R.id.inquiry);
        inquiry.setText(dto.getInquiry());

        TextView note = findViewById(R.id.note);
        note.setText(dto.getNote());

        TextView title = findViewById(R.id.title);
        title.setText(dto.getTitle());

        TextView qualification = findViewById(R.id.qualification);
        qualification.setText(dto.getQualification());

    }
}
