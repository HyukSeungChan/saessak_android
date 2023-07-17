package com.example.ssaesak.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkListDTO {

    private int workId;

    private int farmId;

    private String title;

    private String recruitmentStart;

    private String recruitmentEnd;


    // 농장 정보

    private String name;
    private String address;
    private String farmImage;
    private String crops;
    private String cropsDetail;

    public WorkListDTO() {
    }

    public WorkListDTO(int workId, int farmId, String title, String recruitmentStart, String recruitmentEnd, String name, String address, String farmImage, String crops, String cropsDetail) {
        this.workId = workId;
        this.farmId = farmId;
        this.title = title;
        this.recruitmentStart = recruitmentStart;
        this.recruitmentEnd = recruitmentEnd;
        this.name = name;
        this.address = address;
        this.farmImage = farmImage;
        this.crops = crops;
        this.cropsDetail = cropsDetail;
    }

    public int getWorkId() {
        return workId;
    }

    public void setWorkId(int workId) {
        this.workId = workId;
    }

    public int getFarmId() {
        return farmId;
    }

    public void setFarmId(int farmId) {
        this.farmId = farmId;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFarmImage() {
        return farmImage;
    }

    public void setFarmImage(String farmImage) {
        this.farmImage = farmImage;
    }

    public String getCrops() {
        return crops;
    }

    public void setCrops(String crops) {
        this.crops = crops;
    }

    public String getCropsDetail() {
        return cropsDetail;
    }

    public void setCropsDetail(String cropsDetail) {
        this.cropsDetail = cropsDetail;
    }
}
