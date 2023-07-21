package com.example.ssaesak.Main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ssaesak.Login.SignupWorkerPositionActivity;
import com.example.ssaesak.Model.User;
import com.example.ssaesak.R;
import com.example.ssaesak.Retrofit.MyRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MypageUpdateWorker  extends Activity {

    private TextView name;
    private TextView phone;
    private TextView email;

    private Button nameButton;
    private Button phoneButton;
    private Button emailButton;

    private ImageView areaButton;
    private ImageView agricultureButton;
    private ImageView cropButton;
    private ImageView payButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage_update_worker);

        name = findViewById(R.id.name);
        name.setText(User.getInstance().getName());
        phone = findViewById(R.id.phone);
        if(User.getInstance().getPhone() != null) {
            phone.setText(User.getInstance().getPhone());
        }
        email = findViewById(R.id.email);
//        if(User.getInstance().get() != null) {
//            email.setText(User.getInstance().getPhone());
//        }

        nameButton = findViewById(R.id.update_name);
        nameButton.setOnClickListener(v -> {
            User.getInstance().setName(name.getText().toString());
            signup();
            Toast.makeText(getApplicationContext(), "변경사항이 저장되었습니다.", Toast.LENGTH_SHORT).show();
        });
        phoneButton = findViewById(R.id.update_phone);
        phoneButton.setOnClickListener(v -> {
            User.getInstance().setPhone(phone.getText().toString());
            signup();
            Toast.makeText(getApplicationContext(), "변경사항이 저장되었습니다.", Toast.LENGTH_SHORT).show();
        });
        emailButton = findViewById(R.id.update_email);
        emailButton.setOnClickListener(v -> {
//            User.getInstance().setName(name.getText().toString());
            signup();
            Toast.makeText(getApplicationContext(), "변경사항이 저장되었습니다.", Toast.LENGTH_SHORT).show();
        });



        areaButton = findViewById(R.id.area);
        areaButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), UpdateWorkerPositionActivity.class);
            startActivity(intent);
            finish();
        });
        agricultureButton = findViewById(R.id.agriculture);
        agricultureButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), UpdateWorkerAgricultureActivity.class);
            startActivity(intent);
            finish();
        });
        cropButton = findViewById(R.id.crop);
        cropButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), UpdateWorkerCropActivity.class);
            startActivity(intent);
            finish();
        });
        payButton = findViewById(R.id.pay);
        payButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), UpdateWorkerPayActivity.class);
            startActivity(intent);
            finish();
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
