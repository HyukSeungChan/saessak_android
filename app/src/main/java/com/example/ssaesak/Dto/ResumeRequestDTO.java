package com.example.ssaesak.Dto;

public class ResumeRequestDTO {

    private int resumeId;
    private String title;
    private String gender;
    private String birthday;
    private String phone;
    private String email;
    private String address;

    private float career;

    private String account;

    private String bank;
    private String agriculture;
    private String crops;
    private String workStartDay;
    private String workEndDay;
    private String workStartTime;
    private String workEndTime;
    private String car;
    private Long userId;

    public ResumeRequestDTO(int resumeId, String title, String gender, String birthday, String phone, String email, String address, float career, String account, String bank, String agriculture, String crops, String workStartDay, String workEndDay, String workStartTime, String workEndTime, String car, Long userId) {
        this.resumeId = resumeId;
        this.title = title;
        this.gender = gender;
        this.birthday = birthday;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.career = career;
        this.account = account;
        this.bank = bank;
        this.agriculture = agriculture;
        this.crops = crops;
        this.workStartDay = workStartDay;
        this.workEndDay = workEndDay;
        this.workStartTime = workStartTime;
        this.workEndTime = workEndTime;
        this.car = car;
        this.userId = userId;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getCareer() {
        return career;
    }

    public void setCareer(float career) {
        this.career = career;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
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

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
