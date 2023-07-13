package com.example.ssaesak.Dto;

import com.example.ssaesak.Model.User;
import com.example.ssaesak.Model.Worker;

public class WorkerDTO {

    // 노동자ID
    private int workerId;

    // 수료증
    private String certificate;

    // 희망위치
    private String area;

    // 희망농업
    private String agriculture;

    // 희망시급
    private String pay;

    // 희망작물
    private String crops;

    // 관심 농작물
    private String interestCrops;

    // 뱃지
    private String badge;

    private Long userId;

    public WorkerDTO(Worker worker) {
        this.setWorkerId(worker.getWorkerId());
        this.setCertificate(worker.getCertificate());
        this.setArea(worker.getArea());
        this.setAgriculture(worker.getAgriculture());
        this.setPay(worker.getPay());
        this.setCrops(worker.getCrops());
        this.setInterestCrops(worker.getInterestCrops());
        this.setBadge(worker.getBadge());
        this.setUserId(User.getInstance().getUserId());
    }

    public WorkerDTO() {
        this.workerId = 0;
        this.certificate = "";
        this.area = "";
        this.agriculture = "";
        this.pay = "";
        this.crops = "";
        this.interestCrops = "";
        this.badge = "";
    }


    public int getWorkerId() {
        return workerId;
    }

    public void setWorkerId(int workerId) {
        this.workerId = workerId;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAgriculture() {
        return agriculture;
    }

    public void setAgriculture(String agriculture) {
        this.agriculture = agriculture;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public String getCrops() {
        return crops;
    }

    public void setCrops(String crops) {
        this.crops = crops;
    }

    public String getInterestCrops() {
        return interestCrops;
    }

    public void setInterestCrops(String interestCrops) {
        this.interestCrops = interestCrops;
    }

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
