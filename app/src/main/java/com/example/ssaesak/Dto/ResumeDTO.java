package com.example.ssaesak.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResumeDTO {

    private int resumeId;


    private String title;


    private String gender;


    public ResumeDTO() {
    }

    public ResumeDTO(int resumeId, String title, String gender) {
        this.resumeId = resumeId;
        this.title = title;
        this.gender = gender;
    }

    public int getResumeId() {
        return resumeId;
    }

    public void setResumeId(int resumeId) {
        this.resumeId = resumeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
