package com.example.ssaesak.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkDTO {

    private int workId;

    private int farmId;

    private String title;

    private String content;

    private String recruitmentStart;

    private String recruitmentEnd;

    private int recruitmentPerson;

    private String qualification;

    private String preferentialTreatment;

    private int hourWage;

    private int dayWage;

    private String workStartDay;

    private String workEndDay;

    private String workStartTime;

    private String workEndTime;

    private float career;
    private String state;
    private String etc;

    // 농장 정보

    private String name;
    private String address;
    private String farmImage;
    private int pay;
    private String phone;
    private String introduction;
    private String agriculture;
    private String crops;
    private String cropsDetail;

    private Boolean bookmark;

    public WorkDTO() {

    }

    public WorkDTO(int workId, int farmId, String title, String content, String recruitmentStart, String recruitmentEnd, int recruitmentPerson, String qualification, String preferentialTreatment, int hourWage, int dayWage, String workStartDay, String workEndDay, String workStartTime, String workEndTime, float career, String state, String etc, String name, String address, String farmImage, int pay, String phone, String introduction, String agriculture, String crops, String cropsDetail, Boolean bookmark) {
        this.workId = workId;
        this.farmId = farmId;
        this.title = title;
        this.content = content;
        this.recruitmentStart = recruitmentStart;
        this.recruitmentEnd = recruitmentEnd;
        this.recruitmentPerson = recruitmentPerson;
        this.qualification = qualification;
        this.preferentialTreatment = preferentialTreatment;
        this.hourWage = hourWage;
        this.dayWage = dayWage;
        this.workStartDay = workStartDay;
        this.workEndDay = workEndDay;
        this.workStartTime = workStartTime;
        this.workEndTime = workEndTime;
        this.career = career;
        this.state = state;
        this.etc = etc;
        this.name = name;
        this.address = address;
        this.farmImage = farmImage;
        this.pay = pay;
        this.phone = phone;
        this.introduction = introduction;
        this.agriculture = agriculture;
        this.crops = crops;
        this.cropsDetail = cropsDetail;
        this.bookmark = bookmark;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public int getRecruitmentPerson() {
        return recruitmentPerson;
    }

    public void setRecruitmentPerson(int recruitmentPerson) {
        this.recruitmentPerson = recruitmentPerson;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getPreferentialTreatment() {
        return preferentialTreatment;
    }

    public void setPreferentialTreatment(String preferentialTreatment) {
        this.preferentialTreatment = preferentialTreatment;
    }

    public int getHourWage() {
        return hourWage;
    }

    public void setHourWage(int hourWage) {
        this.hourWage = hourWage;
    }

    public int getDayWage() {
        return dayWage;
    }

    public void setDayWage(int dayWage) {
        this.dayWage = dayWage;
    }

    public String getWorkStartDay() {
        return workStartDay;
    }

    public void setWorkStartDay(String workStartDay) {
        this.workStartDay = workStartDay;
    }

    public String getWorkEndDay() {
        return workEndDay;
    }

    public void setWorkEndDay(String workEndDay) {
        this.workEndDay = workEndDay;
    }

    public String getWorkStartTime() {
        return workStartTime;
    }

    public void setWorkStartTime(String workStartTime) {
        this.workStartTime = workStartTime;
    }

    public String getWorkEndTime() {
        return workEndTime;
    }

    public void setWorkEndTime(String workEndTime) {
        this.workEndTime = workEndTime;
    }

    public float getCareer() {
        return career;
    }

    public void setCareer(float career) {
        this.career = career;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getEtc() {
        return etc;
    }

    public void setEtc(String etc) {
        this.etc = etc;
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

    public int getPay() {
        return pay;
    }

    public void setPay(int pay) {
        this.pay = pay;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
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

    public String getCropsDetail() {
        return cropsDetail;
    }

    public void setCropsDetail(String cropsDetail) {
        this.cropsDetail = cropsDetail;
    }

    public Boolean getBookmark() {
        return bookmark;
    }

    public void setBookmark(Boolean bookmark) {
        this.bookmark = bookmark;
    }
}
