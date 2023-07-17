package com.example.ssaesak.Retrofit;


import com.example.ssaesak.Dto.BoardDTO;
import com.example.ssaesak.Dto.WorkNoticeRecommendDTO;
import com.example.ssaesak.Dto.WorkerDTO;
import com.example.ssaesak.Model.User;
import com.example.ssaesak.Model.Worker;

import java.lang.reflect.Parameter;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitAPI {

//    // 테스트
//    @POST(Constatnts_url.PERSONAL_COLOR_TEST_URL)
//    Call<String> getPersonalColor(@Body PersonalColorTestDTO param); //이건 바디 요청시 사용하는거
//    @POST(Constatnts_url.PERSONAL_COLOR_TEST_UPDATE_RESULT_URL)
//    Call<Integer> updatePersonalResult(@Query("customerId")int customerId, @Query("result")String result);
//    @GET(Constatnts_url.PSYCHOLOGICAL_COLOR_TEST_QUESTION_URL)
//    Call<List<PsycologicaltestQuestionDTO>> getPsycologicalTestQuestion(); //이건 바디 요청시 사용하는거
//    @GET(Constatnts_url.PSYCHOLOGICAL_COLOR_TEST_ANSWER_URL)
//    Call<List<PsycologicaltestAnswerDTO>> getPsycologicalTestAnswer(); //이건 바디 요청시 사용하는거
//    @POST(Constatnts_url.PSYCHOLOGICAL_COLOR_TEST_UPDATE_RESULT_URL)
//    Call<Integer> updatePsycologicalResult(@Query("customerId")int customerId, @Query("result")String result);
//
    // 유저
    @POST(Constatnts_url.SIGNUP_URL)
    Call<User> signup(@Body User user);

    @POST(Constatnts_url.SIGNUP_KAKAO_URL)
    Call<User> signupkakao(@Body User user);

    @GET(Constatnts_url.LOGIN_URL)
    Call<ApiResponse> login(@Query("userId") Long userId);

    @GET(Constatnts_url.LOGIN_WORKER_URL)
    Call<ApiResponse> loginWorker(@Query("userId") Long userId);





    // 홈화면
    @POST(Constatnts_url.SIGNUP_WORKER_URL)
    Call<WorkerDTO> signupWorker(WorkerDTO worker);

    @GET(Constatnts_url.WORK_RECOMMEND)
    Call<ApiResponse> recommendWorkerNotice(@Query("address") String address,
                                                             @Query("agriculture") String agriculture,
                                                             @Query("crops") String crops);


    // Board
    @GET(Constatnts_url.HOT_NOTICE)
    Call<ApiResponse> hotNotice();

//    @POST(Constatnts_url.LOGIN_SANGHYUK_URL)
//    Call<Customer> loginSanghyuk(@Query("customerId") int customerId);
//    @POST(Constatnts_url.LOGOUT_URL)
//    Call<Integer> logout(@Query("customerId") int customerId);
//    @POST(Constatnts_url.WITHDRAWAL_URL)
//    Call<Integer> withdrawal(@Query("customerId") int customerId);
//
//    // 여행지
//    @GET(Constatnts_url.TOURSPOT_DETAIL)
//    Call<TourSpot> tourspotDetail(int tourSpotId);
//    @POST(Constatnts_url.TOURSPOT_RANDOM)
//    Call<TourSpot> tourspotRandom(@Query("psycolor")String psycolor, @Query("personalcolor")String personalcolor);
//    @GET(Constatnts_url.RECOMMEND_PSYCOLOR)
//    Call<List<TourSpotSummary>> recommendPsycolor(String psy);
//    @GET(Constatnts_url.RECOMMEND_PERSONALCOLOR)
//    Call<List<TourSpotSummary>> recommendPersonalColor(String personal);
//    @GET(Constatnts_url.RANDOM_TOURSPOT)
//    Call<List<TourSpot>> randomTourSpot();
//    @GET(Constatnts_url.PALETTE_DETAIL)
//    Call<Palette> paletteDetail(int paletteId);
//    @GET(Constatnts_url.PALETTE_TOURSPOT)
//    Call<List<TourSpot>> paletteTourspot(@Query("paletteId")int paletteId);
//    @GET(Constatnts_url.PALETTE_LIST)
//    Call<List<Palette>> paletteList(@Path("customerId") int customerId);
//    @POST(Constatnts_url.ADD_PALETTE)
//    Call<Palette> addPalette(@Body Palette palette);
//    @POST(Constatnts_url.DELETE_PALETTE)
//    Call<Integer> deletePalette(@Query("paletteId") int paletteId);
//    @POST(Constatnts_url.PALETTE_ADD)
//    Call<Integer> paletteAdd(@Query("paletteId")int paletteId, @Query("tourSpotId")int tourSpotId);
//    @POST(Constatnts_url.PALETTE_DELETE)
//    Call<Integer> paletteDelete();
//    @POST(Constatnts_url.STAR_ADD)
//    Call<Star> starAdd(@Query("customerId")int customerId, @Query("tourSpotId")int tourSpotId);
//    @POST(Constatnts_url.STAR_DELETE)
//    Call<Integer> starDelete(@Query("starId")int starId);
//    @GET(Constatnts_url.STAR_CHECK)
//    Call<Star> starCheck(@Query("customerId")int customerId, @Query("tourSpotId")int tourSpotId);
//    @POST(Constatnts_url.STAR_TOURSPOTLIST)
//    Call<List<TourSpot>> starTourspotList(@Query("customerId")int customerId);

    //@FormUrlEncoded
    //@POST("/auth/overlapChecker")
    //Call<String> postOverlapCheck(@Field("phone") String phoneNum, @Field("message") String message); //이건 요청시 사용하는거 (*데이터를 보낼때)
}