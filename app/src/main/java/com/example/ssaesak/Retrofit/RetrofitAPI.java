package com.example.ssaesak.Retrofit;


import com.example.ssaesak.Dto.BoardDetailDTO;
import com.example.ssaesak.Dto.BoardRequestDTO;
import com.example.ssaesak.Dto.FarmDTO;
import com.example.ssaesak.Dto.ResumeRequestDTO;
import com.example.ssaesak.Dto.WorkerDTO;
import com.example.ssaesak.Model.Farm;
import com.example.ssaesak.Model.User;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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

    @GET(Constatnts_url.LOGIN_GET_FARM_URL)
    Call<ApiResponse> loginGetFarmList(@Query("userId") Long userId);





    // 홈화면
    @POST(Constatnts_url.SIGNUP_WORKER_URL)
    Call<WorkerDTO> signupWorker(WorkerDTO worker);

    @GET(Constatnts_url.RESUME_LIST)
    Call<ApiResponse> resume(@Query("userId") Long userId);

    @GET(Constatnts_url.WORK_RECOMMEND)
    Call<ApiResponse> recommendWorkerNotice(@Query("address") String address,
                                                             @Query("agriculture") String agriculture,
                                                             @Query("crops") String crops);


    // 농장
    @GET(Constatnts_url.TODO_LIST)
    Call<ApiResponse> getTodoList(@Query("userId") Long userId, @Query("farmId") int farmId);

    // 농장 정보 조회
    @GET(Constatnts_url.FARM_INFO)
    Call<ApiResponse> getFarmInfo(@Query("farmId") int farmId);
   
    @POST(Constatnts_url.SIGNUP_FARMER_URL)
    Call<ApiResponse> signFarmer(@Body FarmDTO farm);

    // Board
    @GET(Constatnts_url.HOT_NOTICE)
    Call<ApiResponse> hotNotice();

    // 일자리 리스트
    @GET(Constatnts_url.NOTICE_HELP_LIST)
    Call<ApiResponse> noticeHelpList();


    // work

    // 일자리 리스트
    @GET(Constatnts_url.WORK_LIST)
    Call<ApiResponse> workList(@Query("userId") Long userId);

    // 일자리 필터링 (지역)
    @GET(Constatnts_url.WORK_LIST_ADDRESS)
    Call<ApiResponse> workListAddress(@Query ("address") String address, @Query("userId") Long userId);

    // 일자리 필터링 (농업기술)
    @GET(Constatnts_url.WORK_LIST_AGRICULTURE)
    Call<ApiResponse> workListAgriculture(@Query ("agriculture") String agriculture, @Query("userId") Long userId);

    // 일자리 필터링 (작목)
    @GET(Constatnts_url.WORK_LIST_CROPS)
    Call<ApiResponse> workListCrops(@Query ("crops") String crops, @Query("userId") Long userId);

    // 일자리 필터링 (경력)
    @GET(Constatnts_url.WORK_LIST_CAREER)
    Call<ApiResponse> workListCareer(@Query ("career") float career, @Query("userId") Long userId);

    // 일자리 즐겨찾기
    @GET(Constatnts_url.USER_WORK_LIST)
    Call<ApiResponse> userWorkList();

    // 해당 일자리 조회
    @GET(Constatnts_url.WORK_DETAIL)
    Call<ApiResponse> workDetail(@Query("workId") int workId);

    // resume

    // 이력서 작성
    @POST(Constatnts_url.RESUME_CREATE)
    Call<ResumeRequestDTO> resumeCreate(@Body ResumeRequestDTO resumeRequestDto);

    // board
    @Headers("Content-Type: multipart/form-data")
    @Multipart
    @POST(Constatnts_url.BOARD_CREATE)
    Call<BoardRequestDTO> boardCreate(@Part BoardRequestDTO boardRequestDTO, @Part MultipartBody.Part image);

    @GET(Constatnts_url.BOARD_LIST)
    Call<BoardDetailDTO> boardList();


    @GET(Constatnts_url.WATCHING_VIDEO)
    Call<ApiResponse> watchingVideo(@Query("videoId") int videoId, @Query("userId") int userId);

    // study
    @GET(Constatnts_url.VIDEO_LIST)
    Call<ApiResponse> videoList(@Query("type") String type);

    @POST(Constatnts_url.VIDEO_LIST)
    Call<ApiResponse> cropVideoList(@Query("type") String type);

    @POST(Constatnts_url.VIDEO_LIST)
    Call<ApiResponse> agricultureVideoList(@Query("type") String type);

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