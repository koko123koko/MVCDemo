package com.leeye.designdemo.model;

import com.leeye.designdemo.bean.GankBean;

/**
 * Created by dllo on 16/10/21.
 */

public interface IGankModel {

    // 变量 --- 常量
    // 方法 --- public abstract

    void startRequest(String urlStr);
    void insertIntoDB(GankBean gank);
    void queryGankAll();


}
