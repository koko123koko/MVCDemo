package com.leeye.designdemo.db;

import com.leeye.designdemo.bean.GankBean;

/**
 * Created by dllo on 16/10/21.
 */

class AsynQueueGankAll extends AsyncGank<Integer,GankBean> {

    public AsynQueueGankAll(DataBaseManage manage) {
        super(manage);
    }

    @Override
    protected GankBean doSomething(Integer param) {
        return  mManage._queryGankAll();
    }
}
