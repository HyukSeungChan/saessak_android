package com.example.ssaesak.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReplyRequestDTO {
    private int replyId;

    private String content;
    private String uploadTime;

//    private String area;
    private int boardId;
    private Long userId;

    public ReplyRequestDTO() {

    }

    public ReplyRequestDTO(int replyId, String content, String uploadTime, int boardId, Long userId) {
        this.replyId = replyId;
        this.content = content;
        this.uploadTime = uploadTime;
        this.boardId = boardId;
        this.userId = userId;
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
}
