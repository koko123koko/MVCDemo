package com.leeye.designdemo.db;

import com.leeye.designdemo.bean.GankBean;

/**
 * Created by dllo on 16/10/21.
 */

public class AsynInsertGank extends AsyncGank<GankBean,Integer> {

    public AsynInsertGank(DataBaseManage manage) {
        super(manage);
    }

    @Override
    protected Integer doSomething(GankBean param) {
        mManage._insertGank(param);
        return 0;
    }
}
