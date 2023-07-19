package com.example.ssaesak.Dto;

public class ReplyResponseDTO {

    private int replyId;
    private String content;

    private String area;
    private String uploadTime;
    private int boardId;
    private Long userId;

    // 유저정보
    private String name;
    private String profileImage;

    public ReplyResponseDTO() {

    }

    public ReplyResponseDTO(int replyId, String content, String area, String uploadTime, int boardId, Long userId, String name, String profileImage) {
        this.replyId = replyId;
        this.content = content;
        this.area = area;
        this.uploadTime = uploadTime;
        this.boardId = boardId;
        this.userId = userId;
        this.name = name;
        this.profileImage = profileImage;
    }

    public int getReplyId() {
        return replyId;
    }

    public void setReplyId(int replyId) {
        this.replyId = replyId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}
