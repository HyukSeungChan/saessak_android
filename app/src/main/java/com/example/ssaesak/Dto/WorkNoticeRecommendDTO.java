package com.example.ssaesak.Dto;

public class WorkNoticeRecommendDTO {



    // 농장 정보
    private String name;
    private String address;
    private String farmImage;
    private String agriculture;
    private String crops;

    private String farmerName;


    public WorkNoticeRecommendDTO(WorkNoticeRecommendDTO work) {
        this.name = work.getName();
        this.address = work.getAddress();
        this.farmImage = work.getFarmImage();
        this.agriculture = work.getAgriculture();
        this.crops = work.getCrops();
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
}
