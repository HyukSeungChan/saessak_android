package com.example.ssaesak.Main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ssaesak.Dto.WorkerDTO;
import com.example.ssaesak.Login.SignupWorkerPositionActivity;
import com.example.ssaesak.Model.User;
import com.example.ssaesak.Model.Worker;
import com.example.ssaesak.R;
import com.example.ssaesak.Retrofit.MyRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MypageUpdateFarmer extends Activity {

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
        setContentView(R.layout.activity_mypage_update_farmer);
    }

}
