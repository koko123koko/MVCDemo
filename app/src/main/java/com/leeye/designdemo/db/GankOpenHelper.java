package com.leeye.designdemo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dllo on 16/10/21.
 */

public class GankOpenHelper extends SQLiteOpenHelper {


    public GankOpenHelper(Context context) {
        super(context, "gank.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql ="create table gank_fuli (_id integer primary key autoincrement, desc text, type text,url text);";

        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
