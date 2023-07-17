package com.example.ssaesak.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Worker {

    private static final Worker worker = new Worker();

    public static Worker getInstance() { // 테스트
        return worker;
    }

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

    public static Worker setInstance(int workerId, String certificate, String area, String agriculture, String pay,
                  String crops, String interestCrops, String badge) {
        worker.setWorkerId(workerId);
        worker.setCertificate(certificate);
        worker.setArea(area);
        worker.setAgriculture(agriculture);
        worker.setPay(pay);
        worker.setCrops(crops);
        worker.setInterestCrops(interestCrops);
        worker.setBadge(badge);

        return worker;
    }

    public static Worker setInstance(Worker workerJson) {
        worker.setWorkerId(workerJson.getWorkerId());
        worker.setCertificate(workerJson.getCertificate());
        worker.setArea(workerJson.getArea());
        worker.setAgriculture(workerJson.getAgriculture());
        worker.setPay(workerJson.getPay());
        worker.setCrops(workerJson.getCrops());
        worker.setInterestCrops(workerJson.getInterestCrops());
        worker.setBadge(workerJson.getBadge());

        return worker;
    }

    public Worker() {
        this.workerId = 0;
        this.certificate = "";
        this.area = "";
        this.agriculture = "";
        this.pay = "";
        this.crops = "";
        this.interestCrops = "";
        this.badge = "";
    }

    public Worker(int workerId, String certificate, String area, String agriculture, String pay,
                           String crops, String interestCrops, String badge) {
        this.workerId = workerId;
        this.certificate = certificate;
        this.area = area;
        this.agriculture = agriculture;
        this.pay = pay;
        this.crops = crops;
        this.interestCrops = interestCrops;
        this.badge = badge;
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
}
