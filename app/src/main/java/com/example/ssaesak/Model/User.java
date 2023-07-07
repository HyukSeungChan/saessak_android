package com.example.ssaesak.Model;

public class User {

    private static final User user = new User();

    private String userId;
    private String name;
    private String profileImage;
    private String gender;
    private String phone;
    private String birthyear;
    private String birthday;
    private String type;



    public static User getInstance() { // 테스트
        return user;
    }


    public static User getInstance(String userId,
                                    String name,
                                    String profileImage,
                                    String gender,
                                    String phone,
                                    String birthyear,
                                    String birthday,
                                    String type) {
        user.setUserId(userId);
        user.setName(name);
        user.setProfileImage(profileImage);
        user.setGender(gender);
        user.setPhone(phone);
        user.setBirthyear(birthyear);
        user.setBirthday(birthday);
        user.setType(type);

        return user;
    }


    public User() {
        this.userId = "";
        this.name = "";
        this.profileImage = "";
        this.gender = "";
        this.phone = "";
        this.birthyear = "";
        this.birthday = "";
        this.type = "";
    }

    public User(String userId,
                String name,
                String profileImage,
                String gender,
                String phone,
                String birthyear,
                String birthday,
                String type) {

        this.userId = userId;
        this.name = name;
        this.profileImage = profileImage;
        this.gender = gender;
        this.phone = phone;
        this.birthyear = birthyear;
        this.birthday = birthday;
        this.type = type;

    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthyear() {
        return birthyear;
    }

    public void setBirthyear(String birthyear) {
        this.birthyear = birthyear;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
