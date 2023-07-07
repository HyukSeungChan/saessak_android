package com.example.ssaesak.Retrofit;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MyRetrofit  extends Application {
    private static String result;

//    private jsonPlaceHolderApi jsonPlaceHolderApi;

    public static RetrofitAPI getApiService(){return create().create(RetrofitAPI.class);}


    
//    private String result;

    public static Retrofit create() {
        // timeout setting 해주기
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();


        Gson gson = new GsonBuilder().setLenient().create();
        return new Retrofit.Builder()
                .baseUrl(Constatnts_url.BASE_URL_EC2)
//                .baseUrl(Constatnts_url.BASE_URL_MYDEVICE_DH)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
    }
}