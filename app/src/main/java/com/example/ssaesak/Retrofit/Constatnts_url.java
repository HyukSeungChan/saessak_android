package com.example.ssaesak.Retrofit;

public class Constatnts_url {

//    public static final String BASE_URL = "http://localhost:8080/";
    public static final String BASE_URL_EMULATOR = "http://172.30.1.59:8080/";
    public static final String BASE_URL_EC2 = "http://13.209.186.237:8080/saessak-0.0.1-SNAPSHOT/";



    // 유저
    public static final String LOGIN_URL = "user/info/";
    public static final String LOGIN_WORKER_URL = "worker/";
    public static final String LOGIN_GET_FARM_URL = "user/farm/group/";
    public static final String LOGIN_FARMER_URL = "worker/";
    public static final String SIGNUP_URL = "user/";
    public static final String SIGNUP_KAKAO_URL = "user/kakao/";
    public static final String SIGNUP_WORKER_URL = "worker/";
    public static final String SIGNUP_FARMER_URL = "farm/";



    // 홈
    public static final String WORK_RECOMMEND = "work/recommend/";
    public static final String TODO_LIST = "user/todo/all/";
    public static final String RESUME_LIST = "work/resume/worker/";

    // 농장
    public static final String FARM_INFO = "farm/";


    // Board
    public static final String HOT_NOTICE = "board/hot/";

    public static final String BOARD_LIST = "board/all";


    // work
    public static final String WORK_LIST = "work/worker/all/";
    public static final String WORK_LIST_ADDRESS = "work/worker/address/";

    public static final String WORK_LIST_AGRICULTURE = "work/worker/agriculture/";

    public static final String WORK_LIST_CROPS = "work/worker/crops/";

    public static final String WORK_LIST_CAREER = "work/worker/career/";

    // 일자리 즐겨찾기
    public static final String USER_WORK_LIST = "user/work/bookmark/";

    // 해당 일자리 조회
    public static final String WORK_DETAIL = "work/worker/workId/";

    // 도와줘요 전체 조회
    public static final String NOTICE_HELP_LIST = "board/help/";


    //resume

    // 이력서 작성
    public static final String RESUME_CREATE = "resume/";

    // board

    // 글 작성
    public static final String BOARD_CREATE = "board/";




    // study
    public static final String VIDEO_LIST = "video/";
    public static final String ESSENTIAL_VIDEO_TYPE = "농업필수교육";
    public static final String AGRICULTURE_VIDEO_TYPE = "생활농업";
    public static final String CROP_VIDEO_TYPE = "농작물영상";
    public static final String WATCHING_VIDEO = "video/watching/";

}

