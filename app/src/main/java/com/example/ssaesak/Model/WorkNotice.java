//package com.example.ssaesak.Model;
//
//
//public class WorkNotice {
//
//    private int workId;
//
//    private int farmId;
//
//    private String title;
//
//    private String content;
//
//    private String recruitmentStart;
//
//    private String recruitmentEnd;
//
//    private String recruitmentPerson;
//
//    private String qualification;
//
//    private String preferentialTreatment;
//
//    private int hourWage;
//
//    private int dayWage;
//
//    private String workStartDay;
//
//    private String workEndDay;
//
//    private String workStartTime;
//
//    private String workEndTime;
//
//    private String state;
//    private String etc;
//
//    // 농장 정보
//
//    private String name;
//    private String address;
//    private String farmImage;
//    private int pay;
//    private String phone;
//    private String introduction;
//    private String agriculture;
//    private String crops;
//    private String cropsDetail;
//
//    public WorkNotice(Work work){
//        this.workId = work.getWorkId();
//        this.farmId = work.getFarm().getFarmId();
//        this.title = work.getTitle();
//        this.content = work.getContent();
//        this.recruitmentStart = work.getRecruitmentStart();
//        this.recruitmentEnd = work.getRecruitmentEnd();
//        this.recruitmentPerson = work.getRecruitmentPerson();
//        this.qualification = work.getQualification();
//        this.preferentialTreatment = work.getPreferentialTreatment();
//        this.hourWage = work.getHourWage();
//        this.dayWage = work.getDayWage();
//        this.workStartDay = work.getWorkStartDay();
//        this.workEndDay = work.getWorkEndDay();
//        this.workStartTime = work.getWorkStartTime();
//        this.workEndTime = work.getWorkEndTime();
//        this.state = work.getState();
//        this.etc = work.getEtc();
//    }
//
//    public WorkNotice(Work work, Farm farm) {
//        this.workId = work.getWorkId();
//        this.farmId = work.getFarm().getFarmId();
//        this.title = work.getTitle();
//        this.content = work.getContent();
//        this.recruitmentStart = work.getRecruitmentStart();
//        this.recruitmentEnd = work.getRecruitmentEnd();
//        this.recruitmentPerson = work.getRecruitmentPerson();
//        this.qualification = work.getQualification();
//        this.preferentialTreatment = work.getPreferentialTreatment();
//        this.hourWage = work.getHourWage();
//        this.dayWage = work.getDayWage();
//        this.workStartDay = work.getWorkStartDay();
//        this.workEndDay = work.getWorkEndDay();
//        this.workStartTime = work.getWorkStartTime();
//        this.workEndTime = work.getWorkEndTime();
//        this.state = work.getState();
//        this.etc = work.getEtc();
//
//        this.name = farm.getName();
//        this.address = farm.getAddress();
//        this.farmImage = farm.getFarmImage();
//        this.pay = farm.getPay();
//        this.phone = farm.getPhone();
//        this.introduction = farm.getIntroduction();
//        this.agriculture = farm.getAgriculture();
//        this.crops = farm.getCrops();
//        this.cropsDetail = farm.getCrops_detail();
//
//    }
//
//}
