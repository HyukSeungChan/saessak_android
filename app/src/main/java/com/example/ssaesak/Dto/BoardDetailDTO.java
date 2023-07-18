package com.example.ssaesak.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BoardDetailDTO implements Serializable {

    private int boardId;
    private String title;

    private String content;

    private String image;

    private String uploadTime;

    private String area;

    private int likes;

    private int replies;

    private String agriculture;

    private Long userId;

    private Boolean bookmark;

    public BoardDetailDTO() {

    }

    public BoardDetailDTO(int boardId, String title, String content, String image, String uploadTime, String area, int likes, int replies, String agriculture, Long userId, Boolean bookmark) {
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.image = image;
        this.uploadTime = uploadTime;
        this.area = area;
        this.likes = likes;
        this.replies = replies;
        this.agriculture = agriculture;
        this.userId = userId;
        this.bookmark = bookmark;
    }

    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getBookmark() {
        return bookmark;
    }

    public void setBookmark(Boolean bookmark) {
        this.bookmark = bookmark;
    }
}
