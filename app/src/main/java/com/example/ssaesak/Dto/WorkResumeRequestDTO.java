package com.example.ssaesak.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkResumeRequestDTO {

    private int workResumeId;
    private int workId;
    private int resumeId;
    private String state;

    private String date;

    public WorkResumeRequestDTO() {
    }

    public WorkResumeRequestDTO(int workResumeId, int workId, int resumeId, String state, String date) {
        this.workResumeId = workResumeId;
        this.workId = workId;
        this.resumeId = resumeId;
        this.state = state;
        this.date = date;
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
}
