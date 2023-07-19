package com.example.ssaesak.Dto;

public class WorkRequestDto {

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

    private String state;

    private float career;
    private String etc;


    public WorkRequestDto() {
    }

    public WorkRequestDto(int workId, int farmId, String title, String content, String recruitmentStart, String recruitmentEnd, int recruitmentPerson, String qualification, String preferentialTreatment, int hourWage, int dayWage, String workStartDay, String workEndDay, String workStartTime, String workEndTime, String state, float career, String etc) {
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
        this.state = state;
        this.career = career;
        this.etc = etc;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public float getCareer() {
        return career;
    }

    public void setCareer(float career) {
        this.career = career;
    }

    public String getEtc() {
        return etc;
    }

    public void setEtc(String etc) {
        this.etc = etc;
    }
}
