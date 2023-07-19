package com.example.ssaesak.Model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class UserFarmList {

//    List<UserFarmList> userFarmList;

    private static final List<UserFarm> userFarmList = new ArrayList<UserFarm>();

    public static List<UserFarm> getInstance() {return userFarmList;}

    public UserFarmList(List<UserFarm> userFarmList1) {
        userFarmList.removeAll(userFarmList);
        for(UserFarm user : userFarmList1) userFarmList.add(user);
        Log.e("userfarm", userFarmList1.size() + ", " + userFarmList.size());
    }
}
