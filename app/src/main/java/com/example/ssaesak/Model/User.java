package com.example.ssaesak.Model;

import java.util.ArrayList;
import java.util.List;

public class User {

    private static final User user = new User();

    public static User getInstance() { // 테스트
        return user;
    }

    private Long userId;
    private String name;
    private String profileImage;
    private String phone;
    private String type;




    public static User setInstance(Long userId,
                                    String name,
                                    String profileImage,
                                    String phone,
                                    String type) {
        user.setUserId(userId);
        user.setName(name);
        user.setProfileImage(profileImage);
        user.setPhone(phone);
        user.setType(type);

        return user;
    }

    public static User setInstance(User user) {
        user.setUserId(user.getUserId());
        user.setName(user.getName());
        user.setProfileImage(user.getProfileImage());
        user.setPhone(user.getPhone());
        user.setType(user.getType());

        return user;
    }


    public User() {
        this.userId = null;
        this.name = "";
        this.profileImage = "";
        this.phone = "";
        this.type = "";
    }

    public User(Long userId,
                String name,
                String profileImage,
                String phone,
                String type) {

        this.userId = userId;
        this.name = name;
        this.profileImage = profileImage;
        this.phone = phone;
        this.type = type;

    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
