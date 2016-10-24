package com.leeye.designdemo.controller;

import com.leeye.designdemo.bean.GankBean;

/**
 * Created by dllo on 16/10/21.
 */

public interface IGankController {

    void showDialog();

    void dismissDialog();

    void onResponse(GankBean gank);

    void onError(Throwable error);

    void onCompleted(GankBean gank);

    void onFailed();

}
