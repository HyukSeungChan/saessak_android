package com.example.ssaesak.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkResumeResponseDto {

    private int workResumeId;

    private int workId;

    private int resumeId;

    private String state;
    private String date;

    // 이력서 정보
    private String title;
    private String name;
    private String gender;
    private int workPeriod;
    private long workHour;
    private long workMinute;

    public WorkResumeResponseDto() {
    }

    public WorkResumeResponseDto(int workResumeId, int workId, int resumeId, String state, String date, String title, String name, String gender, int workPeriod, long workHour, long workMinute) {
        this.workResumeId = workResumeId;
        this.workId = workId;
        this.resumeId = resumeId;
        this.state = state;
        this.date = date;
        this.title = title;
        this.name = name;
        this.gender = gender;
        this.workPeriod = workPeriod;
        this.workHour = workHour;
        this.workMinute = workMinute;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getWorkPeriod() {
        return workPeriod;
    }

    public void setWorkPeriod(int workPeriod) {
        this.workPeriod = workPeriod;
    }

    public long getWorkHour() {
        return workHour;
    }

    public void setWorkHour(long workHour) {
        this.workHour = workHour;
    }

    public long getWorkMinute() {
        return workMinute;
    }

    public void setWorkMinute(long workMinute) {
        this.workMinute = workMinute;
    }
}
