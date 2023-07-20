package com.example.ssaesak.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BoardRequestDTO {

//    private int boardId;

    private String title;

    private String content;

    private String uploadTime;

    private String area;

    private String image;

    private int likes;

    private int replies;

    private String agriculture;

    private String crops;
    private Long userId;

    public BoardRequestDTO() {

    }

    public BoardRequestDTO(String title, String content, String uploadTime, String area, String image, int likes, int replies, String agriculture, String crops, Long userId) {
//        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.uploadTime = uploadTime;
        this.area = area;
        this.image = image;
        this.likes = likes;
        this.replies = replies;
        this.agriculture = agriculture;
        this.crops = crops;
        this.userId = userId;
    }

//    public int getBoardId() {
//        return boardId;
//    }
//
//    public void setBoardId(int boardId) {
//        this.boardId = boardId;
//    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getReplies() {
        return replies;
    }

    public void setReplies(int replies) {
        this.replies = replies;
    }

    public String getAgriculture() {
        return agriculture;
    }

    public void setAgriculture(String agriculture) {
        this.agriculture = agriculture;
    }

    public String getCrops() {
        return crops;
    }

    public void setCrops(String crops) {
        this.crops = crops;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
