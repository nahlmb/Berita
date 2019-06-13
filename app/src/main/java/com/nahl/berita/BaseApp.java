package com.nahl.berita;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;

public class BaseApp extends Application {
    public static String BASE_URL_TOP_HEADLINES  = "https://newsapi.org/v2/top-headlines";
    public static String BESE_URL_EVERYTHING = "https://newsapi.org/v2/everything";

    @Override
    public void onCreate() {
        super.onCreate();

        AndroidNetworking.initialize(this);
    }
}
