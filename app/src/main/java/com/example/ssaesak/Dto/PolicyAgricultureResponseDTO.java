package com.example.ssaesak.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PolicyAgricultureResponseDTO implements Serializable {

    private int policyAgricultureId;

    private String title;

    private String content;

    private String qualification;

    private String area;

    private String inquiry;

    private String note;

    public PolicyAgricultureResponseDTO(int policyAgricultureId, String title, String content, String qualification, String area, String inquiry, String note) {
        this.policyAgricultureId = policyAgricultureId;
        this.title = title;
        this.content = content;
        this.qualification = qualification;
        this.area = area;
        this.inquiry = inquiry;
        this.note = note;
    }

    public PolicyAgricultureResponseDTO() {
    }

    public int getPolicyAgricultureId() {
        return policyAgricultureId;
    }

    public void setPolicyAgricultureId(int policyAgricultureId) {
        this.policyAgricultureId = policyAgricultureId;
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

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getInquiry() {
        return inquiry;
    }

    public void setInquiry(String inquiry) {
        this.inquiry = inquiry;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
