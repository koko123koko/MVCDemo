package com.leeye.designdemo.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by dllo on 16/10/21.
 */

public class GankApp extends Application {
    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
    }

    public static Context getContext() {
        return sContext;
    }
}
