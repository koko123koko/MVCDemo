package com.leeye.designdemo.controller;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.leeye.designdemo.bean.GankBean;
import com.leeye.designdemo.model.GankModelImpl;
import com.leeye.designdemo.model.IGankModel;
import com.leeye.designdemo.R;

public class MainActivity extends AppCompatActivity implements IGankController, View.OnClickListener {

    private static final String URL_GANK = "http://gank.io/api/data/%E7%A6%8F%E5%88%A9/10/1";
    private TextView mTextView;
    private ProgressDialog mDialog;

    private IGankModel mModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();

    }



    private void initView() {

        mTextView = (TextView) findViewById(R.id.text_show);
        mDialog = createDialog();

    }
    private void initData() {
        mTextView.setOnClickListener(this);
        mModel = new GankModelImpl(this);
    }
    private ProgressDialog createDialog() {
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("数据加载...");
        dialog.setMessage("请稍后");
        return dialog;
    }

    @Override
    public void showDialog() {
        mDialog.show();
    }

    @Override
    public void dismissDialog() {
        mDialog.dismiss();
    }

    @Override
    public void onResponse(GankBean gank) {
        mTextView.setText(gank.toString());
        mModel.insertIntoDB(gank);

    }

    @Override
    public void onError(Throwable error) {
        Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show();
        mModel.queryGankAll();
    }

    @Override
    public void onCompleted(GankBean gank) {
        mTextView.setText(gank.toString());

    }

    @Override
    public void onFailed() {
        throw new NullPointerException("哪都没有数据");
    }

    @Override
    public void onClick(View v) {
        mModel.startRequest(URL_GANK);
    }
}
