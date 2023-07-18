package com.example.ssaesak.Model;

import android.util.Log;
import android.widget.ImageView;

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
    private int complete;
//    private ImageView profileImageView;




    public static User setInstance(Long userId,
                                    String name,
                                    String profileImage,
                                    String phone,
                                    String type,
                                    int complete) {
        user.setUserId(userId);
        user.setName(name);
        user.setProfileImage(profileImage);
        user.setPhone(phone);
        user.setType(type);
        user.setComplete(complete);

        return user;
    }

    public static User setInstance(User userJson) {
        Log.e("user", " name -> " + userJson.getName());
        user.setUserId(userJson.getUserId());
        user.setName(userJson.getName());
        user.setProfileImage(userJson.getProfileImage());
        user.setPhone(userJson.getPhone());
        user.setType(userJson.getType());
        user.setComplete(userJson.getComplete());

        return user;
    }


    public User() {
        this.userId = null;
        this.name = "";
        this.profileImage = "";
        this.phone = "";
        this.type = "";
        this.complete = 0;
    }

    public User(Long userId,
                String name,
                String profileImage,
                String phone,
                String type,
                int complete) {

        this.userId = userId;
        this.name = name;
        this.profileImage = profileImage;
        this.phone = phone;
        this.type = type;
        this.complete = complete;

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

    public int getComplete() {
        return complete;
    }

    public void setComplete(int complete) {
        this.complete = complete;
    }
}
