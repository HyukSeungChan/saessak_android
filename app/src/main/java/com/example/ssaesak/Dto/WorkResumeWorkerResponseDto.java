package com.example.ssaesak.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkResumeWorkerResponseDto {

    private int workResumeId;

    private int workId;

    private int resumeId;

    private String state;
    private String date;

    // 일자리 정보
    private String address;
    private String title;
    private String recruitmentStart;
    private String recruitmentEnd;

    public WorkResumeWorkerResponseDto() {
    }

    public WorkResumeWorkerResponseDto(int workResumeId, int workId, int resumeId, String state, String date, String address, String title, String recruitmentStart, String recruitmentEnd) {
        this.workResumeId = workResumeId;
        this.workId = workId;
        this.resumeId = resumeId;
        this.state = state;
        this.date = date;
        this.address = address;
        this.title = title;
        this.recruitmentStart = recruitmentStart;
        this.recruitmentEnd = recruitmentEnd;
    }

    public int getWorkResumeId() {
        return workResumeId;
    }

    public void setWorkResumeId(int workResumeId) {
        this.workResumeId = workResumeId;
    }

    public int getWorkId() {
        return workId;
    }

    public void setWorkId(int workId) {
        this.workId = workId;
    }

    public int getResumeId() {
        return resumeId;
    }

    public void setResumeId(int resumeId) {
        this.resumeId = resumeId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRecruitmentStart() {
        return recruitmentStart;
    }

    public void setRecruitmentStart(String recruitmentStart) {
        this.recruitmentStart = recruitmentStart;
    }

    public String getRecruitmentEnd() {
        return recruitmentEnd;
    }

    public void setRecruitmentEnd(String recruitmentEnd) {
        this.recruitmentEnd = recruitmentEnd;
    }
}
