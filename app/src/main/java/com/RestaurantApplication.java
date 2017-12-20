package com;

import android.app.Application;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;


public class RestaurantApplication extends Application {
    public static String PACKAGE_NAME = "com.restaurantproxyserver";
    public static String CLASS_NAME = "com.restaurantproxyserver.service.FetchDataService";

    @Override
    public void onCreate() {
        super.onCreate();
    }






}
