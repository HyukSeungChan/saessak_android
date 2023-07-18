package com.example.ssaesak.Model;

public class UserFarm {

    private int userFarmId;

    private String dateStart;

    private String dateEnd;
    private Long userId;
    private int farmId;

    // 유저 정보
    private String name;

    private String profileImage;

    // 농장 정보
    private String farmName;
    private String address;
    private String phone;
    private String introduction;


    private static final UserFarm userFarmList = new UserFarm();

    public static UserFarm setInstance(int userFarmId, String dateStart, String dateEnd, Long userId,
                                       int farmId, String name, String profileImage, String farmName,
                                       String address, String phone, String introduction) {
        userFarmList.setUserFarmId(userFarmId);
        userFarmList.setDateStart(dateStart);
        userFarmList.setDateEnd(dateEnd);
        userFarmList.setUserId(userId);
        userFarmList.setFarmId(farmId);
        userFarmList.setName(name);
        userFarmList.setProfileImage(profileImage);
        userFarmList.setFarmName(farmName);
        userFarmList.setAddress(address);
        userFarmList.setPhone(phone);
        userFarmList.setIntroduction(introduction);

        return userFarmList;
    }


    public static UserFarm setInstance(UserFarm userFarmList1) {
        userFarmList.setUserFarmId(userFarmList1.getUserFarmId());
        userFarmList.setDateStart(userFarmList1.getDateStart());
        userFarmList.setDateEnd(userFarmList1.getDateEnd());
        userFarmList.setUserId(userFarmList1.getUserId());
        userFarmList.setFarmId(userFarmList1.getFarmId());
        userFarmList.setName(userFarmList1.getName());
        userFarmList.setProfileImage(userFarmList1.getProfileImage());
        userFarmList.setFarmName(userFarmList1.getFarmName());
        userFarmList.setAddress(userFarmList1.getAddress());
        userFarmList.setPhone(userFarmList1.getPhone());
        userFarmList.setIntroduction(userFarmList1.getIntroduction());

        return userFarmList;
    }




    public UserFarm() {
    }

    public UserFarm(int userFarmId, String dateStart, String dateEnd, Long userId, int farmId, String name, String profileImage, String farmName, String address, String phone, String introduction) {
        this.userFarmId = userFarmId;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.userId = userId;
        this.farmId = farmId;
        this.name = name;
        this.profileImage = profileImage;
        this.farmName = farmName;
        this.address = address;
        this.phone = phone;
        this.introduction = introduction;
    }

    public int getUserFarmId() {
        return userFarmId;
    }

    public void setUserFarmId(int userFarmId) {
        this.userFarmId = userFarmId;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getFarmName() {
        return farmName;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
}
