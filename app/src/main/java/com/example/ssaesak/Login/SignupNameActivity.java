package com.example.ssaesak.Login;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
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
import android.widget.Toast;

import com.example.ssaesak.Model.User;
import com.example.ssaesak.R;

import java.nio.file.Watchable;
import java.util.regex.Pattern;

public class SignupNameActivity extends Activity {

    String name;
    TextView tooltip;
    LinearLayout editTextLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_name);

        this.tooltip = findViewById(R.id.tooltip);
        this.editTextLayout = findViewById(R.id.edittext_layout);


        ImageButton backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), SignupTypeActivity.class));
                overridePendingTransition(0, 0);
                finish();
            }
        });

//        Button nextButton = findViewById(R.id.next).findViewById(R.id.button_next);
        Button nextButton = findViewById(R.id.button_next);
        nextButton.setClickable(false);
//        nextButton.setClickable(false);

        EditText editText = findViewById(R.id.edittext);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Pattern pattern = Pattern.compile("^[가-힣]+$");
                if (editText.getText().toString().length() > 7 || !pattern.matcher(editText.getText().toString()).matches()) {
                    Log.e("signupname", "글자수 초과! -> " + editText.toString().length());
                    nextButton.setClickable(false);
                    nextButton.setBackground(getResources().getDrawable(R.drawable.button_gray, null));
                    tooltip.setVisibility(View.VISIBLE);
                    tooltip.setText("1~7자 이내의 한글로 입력해주세요");
                    tooltip.setTextColor(getResources().getColor(R.color.active_red, null));
                    editTextLayout.setBackground(getResources().getDrawable(R.drawable.edittext_red, null));

                } else if(editText.getText().toString().length() > 0) {
                    Log.e("signupname", "글자수 통과!");
                    nextButton.setClickable(true);
                    nextButton.setBackground(getResources().getDrawable(R.drawable.button_primary, null));
                    tooltip.setVisibility(View.INVISIBLE);
                    editTextLayout.setBackground(getResources().getDrawable(R.drawable.edittext_primary, null));
                }

//                if () {
//                    Log.e("signupname", "한글이름 규칙 아님!!");
//                    nextButton.setClickable(false);
//                    nextButton.setBackground(getResources().getDrawable(R.drawable.button_gray, null));
//                    tooltip.setVisibility(View.VISIBLE);
//                    tooltip.setText("한글만 입력해주세요");
//                    tooltip.setTextColor(getResources().getColor(R.color.active_red, null));
//                    editTextLayout.setBackground(getResources().getDrawable(R.drawable.edittext_red, null));
//                } else {
//                    nextButton.setClickable(true);
//                    Log.e("signupname", "한글이름 규칙 통과");
//                    nextButton.setBackground(getResources().getDrawable(R.drawable.button_primary, null));
//                    tooltip.setVisibility(View.INVISIBLE);
//                    editTextLayout.setBackground(getResources().getDrawable(R.drawable.edittext_primary, null));
//                }


            }



            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        this.name = "";


        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (editText.getText().toString().length() < 1) {
                    Toast.makeText(getBaseContext(), "이름을 입력해주세요", Toast.LENGTH_SHORT).show();
                } else {
                    User.getInstance().setName(editText.getText().toString());
                    startActivity(new Intent(getBaseContext(), SignupProfileActivity.class));
                    overridePendingTransition(0, 0);
                    finish();
                }
            }
        });
    }
}
