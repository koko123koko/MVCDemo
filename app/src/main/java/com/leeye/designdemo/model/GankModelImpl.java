package com.leeye.designdemo.model;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.leeye.designdemo.bean.GankBean;
import com.leeye.designdemo.controller.IGankController;
import com.leeye.designdemo.db.AsyncGank;
import com.leeye.designdemo.db.DataBaseManage;
import com.leeye.designdemo.http.VolleySingleton;

/**
 * Created by dllo on 16/10/21.
 *
 */

public class GankModelImpl implements IGankModel {

    //model 的实现类 需要持有controller 层的对象引用

    private IGankController mController;


    public GankModelImpl(IGankController controller) {
        mController = controller;

    }

    @Override
    public void startRequest(String urlStr) {

        //当开启网络请求时 执行controlle 的showDialog 方法
        mController.showDialog();
        StringRequest request = new StringRequest(urlStr, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response == null || response.equals("")) {
                    mController.onError(new NullPointerException("获取不到网络数据"));
                    mController.dismissDialog();
                    return;
                }

                GankBean gank = new Gson().fromJson(response,GankBean.class);

                if (gank.isError()){
                    mController.onError(new NullPointerException("服务器错误"));
                    mController.dismissDialog();
                    return;
                }
                mController.onResponse(gank);
                mController.dismissDialog();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mController.onError(error);
                mController.dismissDialog();
            }
        });
        VolleySingleton.getInstance().addRequest(request);
    }

    @Override
    public void insertIntoDB(GankBean gank) {
        DataBaseManage.getInstance().insertGank(gank);
    }

    @Override
    public void queryGankAll() {
        DataBaseManage.getInstance().queryGankAll(new AsyncGank.onCompletedListener<GankBean>() {
            @Override
            public void onCompleted(GankBean result) {
                if (result.isError()){
                    mController.onFailed();
                } else {
                    mController.onCompleted(result);
                }
            }
        });
    }
}
