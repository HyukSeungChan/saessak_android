package com.example.ssaesak.Dto;

public class UserVideoWatchRequestDto {

    private int userVideoWatchId;
    private Long userId;
    private int videoId;


    public UserVideoWatchRequestDto(Long userId, int videoId) {
        this.userVideoWatchId = 0;
        this.userId = userId;
        this.videoId = videoId;
    }

    public int getUserVideoWatchId() {
        return userVideoWatchId;
    }

    public void setUserVideoWatchId(int userVideoWatchId) {
        this.userVideoWatchId = userVideoWatchId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }
}
