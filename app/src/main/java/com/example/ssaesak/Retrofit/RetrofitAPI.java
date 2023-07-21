package com.example.ssaesak.Retrofit;


import com.example.ssaesak.Dto.BoardDetailDTO;
import com.example.ssaesak.Dto.BoardLikeRequestDTO;
import com.example.ssaesak.Dto.BoardRequestDTO;
import com.example.ssaesak.Dto.FarmDTO;
import com.example.ssaesak.Dto.ReplyRequestDTO;
import com.example.ssaesak.Dto.ResumeDTO;
import com.example.ssaesak.Dto.ResumeRequestDTO;
import com.example.ssaesak.Dto.UserVideoWatchRequestDto;
import com.example.ssaesak.Dto.WorkDTO;
import com.example.ssaesak.Dto.WorkRequestDto;
import com.example.ssaesak.Dto.WorkResumeRequestDTO;
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
    Call<WorkerDTO> signupWorker(@Body WorkerDTO worker);

    @GET(Constatnts_url.RESUME_LIST)
    Call<ApiResponse> resume(@Query("userId") Long userId);

    @GET(Constatnts_url.WORK_RECOMMEND)
    Call<ApiResponse> recommendWorkerNotice(@Query("address") String address,
                                                             @Query("agriculture") String agriculture,
                                                             @Query("crops") String crops);


    // 농장
    @GET(Constatnts_url.TODO_LIST_DATE)
    Call<ApiResponse> getTodoList(@Query("userId") Long userId, @Query("farmId") int farmId, @Query("date") String date);
    @GET(Constatnts_url.TODO_LIST_USER)
    Call<ApiResponse> getTodoList(@Query("userId") Long userId, @Query("farmId") int farmId);
    @GET(Constatnts_url.TODO_LIST_ALL)
    Call<ApiResponse> getTodoList(@Query("farmId") int farmId);

    // 농장 정보 조회
    @GET(Constatnts_url.FARM_INFO)
    Call<ApiResponse> getFarmInfo(@Query("farmId") int farmId);

    // 농장 메이트 조회
    @GET(Constatnts_url.FARM_MEMBER_LIST)
    Call<ApiResponse> getMemberList(@Query("farmId") int farmId);
   
    @POST(Constatnts_url.SIGNUP_FARMER_URL)
    Call<ApiResponse> signFarmer(@Body FarmDTO farm);

    // Board

    @POST(Constatnts_url.BOARD_CREATE)
    @Multipart
    Call<BoardRequestDTO> boardCreate(
            @Part("boardRequestDto") BoardRequestDTO boardRequestDto,
            @Part MultipartBody.Part file);


    // 이미지만 넘기기
    @POST(Constatnts_url.SAVE_IMAGE)
    @Multipart
    Call<String> saveImage(@Body String url);

    // 인기 게시글
    @GET(Constatnts_url.HOT_NOTICE)
    Call<ApiResponse> hotNotice();

    @GET(Constatnts_url.MY_NOTICE)
    Call<ApiResponse> myNotice(@Query("userId") Long userId);

    // 도와줘요 리스트
    @GET(Constatnts_url.NOTICE_HELP_LIST)
    Call<ApiResponse> noticeHelpList();

    @GET(Constatnts_url.NOTICE_STORY_LIST)
    Call<ApiResponse> noticeStoryList();

    // 도와줘요 필터링
    @GET(Constatnts_url.NOTICE_HELP_LIST_FILTER)
    Call<ApiResponse> noticeHelpListFilter(@Query("crops") String crops);

    // 상세 글 조회
    @GET(Constatnts_url.BOARD_DETAIL)
    Call<ApiResponse> boardDetail(@Query("boardId") int boardId);

    // 댓글 조회
    @GET(Constatnts_url.COMMENT_LIST)
    Call<ApiResponse> commentList(@Query("boardId") int boardId);
    @GET(Constatnts_url.COMMENT_LIST_ALL)
    Call<ApiResponse> commentListALL(@Query("userId") Long userId);

    // 댓글 생성
    @POST(Constatnts_url.COMMENT_CREATE)
    Call<ReplyRequestDTO> commentCreate(@Body ReplyRequestDTO replyRequestDTO);

    // 좋아요 증가
    @POST(Constatnts_url.LIKE_INCREASE)
    Call<Boolean> likeIncrease(@Body BoardLikeRequestDTO boardLikeRequestDTO);

    // 좋아요 감소
    @POST(Constatnts_url.LIKE_DECREASE)
    Call<Boolean> likeDecrease(@Body BoardLikeRequestDTO boardLikeRequestDTO);

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
//    @GET(Constatnts_url.USER_WORK_LIST)
//    Call<ApiResponse> userWorkList();

    // 해당 일자리 조회
    @GET(Constatnts_url.WORK_DETAIL)
    Call<ApiResponse> workDetail(@Query("workId") int workId);

    // resume

    // 이력서 작성
    @POST(Constatnts_url.RESUME_CREATE)
    Call<ResumeDTO> resumeCreate(@Body ResumeRequestDTO resumeRequestDto);

    // 일자리에 이력서 제출(노동자)
    @POST(Constatnts_url.WORK_RESUME_CREATE)
    Call<WorkResumeRequestDTO> workResumeCreate(@Body WorkResumeRequestDTO workResumeRequestDTO);

    @GET(Constatnts_url.My_RESUME)
    Call<ApiResponse> myResume(@Query("userId") Long userId);

    // 일자리 공고 작성(농장주)
    @POST(Constatnts_url.WORK_NOTICE_FARMER)
    Call<WorkRequestDto> workNoticeFarmer(@Body WorkRequestDto workRequestDto);



//    @GET(Constatnts_url.BOARD_LIST)
//    Call<BoardDetailDTO> boardList();


    @GET(Constatnts_url.WATCHING_VIDEO)
    Call<ApiResponse> watchingVideo(@Query("userId") Long userId);

    // study
    @GET(Constatnts_url.VIDEO_LIST)
    Call<ApiResponse> videoList(@Query("type") String type);

    @POST(Constatnts_url.POST_WATCHING_VIDEO)
    Call<ApiResponse> postWatchingVideo(@Body UserVideoWatchRequestDto dto);

    @POST(Constatnts_url.VIDEO_LIST)
    Call<ApiResponse> cropVideoList(@Query("type") String type);

    @POST(Constatnts_url.VIDEO_LIST)
    Call<ApiResponse> agricultureVideoList(@Query("type") String type);

    // myPage

    // 일자리 지원현황
    @GET(Constatnts_url.WORKER_APPLICATION_LIST)
    Call<ApiResponse> workerApplicationList(@Query("userId") Long userId);

    // 일자리 지원 취소
    @GET(Constatnts_url.WORKER_APPLICATION_DELETE)
    Call<ApiResponse> workerApplicationDelete(@Query("workResumeId") int workResumeId);

    // help(도움터)

    @GET(Constatnts_url.POLICY_SMART_LIST)
    Call<ApiResponse> policySmartList();

    @GET(Constatnts_url.POLICY_AGRICULTURE_LIST)
    Call<ApiResponse> policyAgricultureList();

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