package com.leeye.designdemo.http;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.leeye.designdemo.app.GankApp;

/**
 * Created by dllo on 16/10/21.
 *
 */

public class VolleySingleton {

    private static final String TAG = "VolleySingleton";

    private VolleySingleton() {
        mRequestQueue = Volley.newRequestQueue(GankApp.getContext());
    }

    private static final class SingletonHolder {
        private static final VolleySingleton sInstance = new VolleySingleton();
    }

    public static VolleySingleton getInstance() {
        return SingletonHolder.sInstance;
    }

    private RequestQueue mRequestQueue;

    public <T> void addRequest(Request<T> request){

        mRequestQueue.add(request);

    }


}
