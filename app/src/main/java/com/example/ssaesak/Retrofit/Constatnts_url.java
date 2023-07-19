package com.example.ssaesak.Retrofit;

public class Constatnts_url {

//    public static final String BASE_URL = "http://localhost:8080/";
    public static final String BASE_URL_EMULATOR = "http://172.30.1.43:8080/";
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
    public static final String TODO_LIST = "user/todo/";
    public static final String TODO_ALL_LIST = "user/todo/all/";
    public static final String RESUME_LIST = "work/resume/worker/";

    // 농장

    // 농장 정보 조회
    public static final String FARM_INFO = "farm/";

    // 농장 메이트 조회
    public static final String FARM_MEMBER_LIST = "user/farm/";


    // Board
    public static final String HOT_NOTICE = "board/hot/";

    // 도와줘요 전체 조회
    public static final String NOTICE_HELP_LIST = "board/help/";

    // 농촌이야기 전체 조회
    public static final String NOTICE_STORY_LIST = "board/story/";

    // 전체 조회
    public static final String NOTICE_HELP_LIST_FILTER = "board/help/crops/";

    // 글 작성
    public static final String BOARD_CREATE = "board/";

    // 상세 글 조회
    public static final String BOARD_DETAIL = "board/detail/";

    // 댓글 조회
    public static final String COMMENT_LIST = "reply/";

    // 댓글 생성
    public static final String COMMENT_CREATE = "reply/";

    // 좋아요 증가
    public static final String LIKE_INCREASE = "board/like/";

    // 좋아요 감소
    public static final String LIKE_DECREASE = "board/unlike/";





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




    //resume

    // 이력서 작성
    public static final String RESUME_CREATE = "resume/";



    // study
    public static final String VIDEO_LIST = "video/";
    public static final String ESSENTIAL_VIDEO_TYPE = "농업필수교육";
    public static final String AGRICULTURE_VIDEO_TYPE = "생활농업";
    public static final String CROP_VIDEO_TYPE = "농작물영상";
    public static final String WATCHING_VIDEO = "video/watching/";
    public static final String POST_WATCHING_VIDEO = "user/video/watch/";

}

