package com.example.ssaesak.Model;

public class Farm {

    private static final Farm farm = new Farm();

    public static Farm getInstance() { // 테스트
        return farm;
    }

    // 농장 ID
    private int farmId;

    // 농장 이름
    private String name;

    // 농장 주소
    private String address;

    // 농장 사진
    private String farmImage;

    // 일급
    private int pay;

    // 농장 전화번호
    private String phone;

    // 농장 상세 설명, 필수x
    private String introduction;

    private String agriculture;

    // 작목 구분
    private String crops;

    // 작목 상세정보
    private String crops_detail;

    public Farm() {
    }

    public Farm(int farmId, String name, String address, String farmImage, int pay, String phone, String introduction, String agriculture, String crops, String crops_detail) {
        this.farmId = farmId;
        this.name = name;
        this.address = address;
        this.farmImage = farmImage;
        this.pay = pay;
        this.phone = phone;
        this.introduction = introduction;
        this.agriculture = agriculture;
        this.crops = crops;
        this.crops_detail = crops_detail;
    }

    public static Farm setInstance(int farmId, String name, String address, String farmImage, int pay, String phone, String introduction, String agriculture, String crops, String crops_detail) {
        farm.setFarmId(farmId);
        farm.setName(name);
        farm.setAddress(address);
        farm.setFarmImage(farmImage);
        farm.setPay(pay);
        farm.setPhone(phone);
        farm.setIntroduction(introduction);
        farm.setAgriculture(agriculture);
        farm.setCrops(crops);
        farm.setCrops_detail(crops_detail);

        return farm;
    }
    public static Farm setInstance(Farm farm1) {
        farm.setFarmId(farm1.getFarmId());
        farm.setName(farm1.getName());
        farm.setAddress(farm1.getAddress());
        farm.setFarmImage(farm1.getFarmImage());
        farm.setPay(farm1.getPay());
        farm.setPhone(farm1.getPhone());
        farm.setIntroduction(farm1.getIntroduction());
        farm.setAgriculture(farm1.getAgriculture());
        farm.setCrops(farm1.getCrops());
        farm.setCrops_detail(farm1.getCrops_detail());

        return farm;
    }

    public int getFarmId() {
        return farmId;
    }

    public void setFarmId(int farmId) {
        this.farmId = farmId;
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

    public String getCrops_detail() {
        return crops_detail;
    }

    public void setCrops_detail(String crops_detail) {
        this.crops_detail = crops_detail;
    }
}
