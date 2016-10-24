package com.leeye.designdemo.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.leeye.designdemo.app.GankApp;
import com.leeye.designdemo.bean.GankBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/10/21.
 */
public class DataBaseManage {

    private static final String TAG = "DatabaseManager";

    //静态代码块 加载类
    //static{}
    //代码块 加载对象
    //{}

    private GankOpenHelper mHelper;

    //给包用
     void _insertGank(GankBean gankBean) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        List<GankBean.ResultsBean> resultsBeen = gankBean.getResults();

        ContentValues contentValues = new ContentValues();
        int size = resultsBeen.size();

        for (int i = 0; i < size; i++) {
            GankBean.ResultsBean results = resultsBeen.get(i);
            contentValues.put("desc", results.getDesc());
            contentValues.put("type", results.getType());
            contentValues.put("url", results.getUrl());
            db.insert("gank_fuli", null, contentValues);
        }
        db.close();
    }

    //使外边能用
    public void insertGank(GankBean gankBean){
        AsynInsertGank insertGank = new AsynInsertGank(this);
        insertGank.execute(gankBean);
    }

     GankBean _queryGankAll() {

        GankBean gank = new GankBean();
        SQLiteDatabase db = mHelper.getWritableDatabase();
        Cursor cursor = db.query("gank_fuli", null, null, null, null, null, null);

        if (cursor == null) {
            gank.setError(true);
        } else if (!cursor.moveToFirst()) {
            gank.setError(true);
        } else {
            gank.setError(false);

            List<GankBean.ResultsBean> results = new ArrayList<>();

            int indexDesc = cursor.getColumnIndex("desc");
            int indexType = cursor.getColumnIndex("type");
            int indexUrl = cursor.getColumnIndex("url");
            do {
                String desc = cursor.getString(indexDesc);
                String type = cursor.getString(indexType);
                String url = cursor.getString(indexUrl);

                GankBean.ResultsBean resultsBean = new GankBean.ResultsBean();
                resultsBean.setDesc(desc);
                resultsBean.setType(type);
                resultsBean.setUrl(url);
                results.add(resultsBean);

            } while (cursor.moveToNext());
            gank.setResults(results);
        }

        if (cursor != null){
            cursor.close();
        }
        db.close();

        return gank;
    }

    public void queryGankAll(AsyncGank.onCompletedListener<GankBean> listener){
        AsynQueueGankAll queueGankAll = new AsynQueueGankAll(this);
        queueGankAll.setOnCompletedListener(listener);
        queueGankAll.execute(0);

    }

    private static final class SingletonHolder {
        private static final DataBaseManage sInstance = new DataBaseManage();
    }

    public static DataBaseManage getInstance() {
        return SingletonHolder.sInstance;
    }


    private DataBaseManage() {
        mHelper = new GankOpenHelper(GankApp.getContext());
    }
}
