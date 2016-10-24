package com.leeye.designdemo.db;

import android.os.AsyncTask;

/**
 * Created by dllo on 16/10/21.
 */

public abstract class AsyncGank<T,E> extends AsyncTask<T,Void,E> {

    //给子类使用
    protected DataBaseManage mManage;

    private onCompletedListener<E> mOnCompletedListener;

    public void setOnCompletedListener(onCompletedListener<E> onCompletedListener) {
        mOnCompletedListener = onCompletedListener;
    }

    public AsyncGank(DataBaseManage manage) {
        mManage = manage;
    }

    @Override
    protected E doInBackground(T... params) {
        return doSomething(params[0]);
    }

    protected abstract E doSomething(T param) ;

    @Override
    protected void onPostExecute(E e) {
        super.onPostExecute(e);
        if (mOnCompletedListener != null){
            mOnCompletedListener.onCompleted(e);
        }
    }

    public interface onCompletedListener<RS>{

        void onCompleted(RS result);

    }
}
