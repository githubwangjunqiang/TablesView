package com.xiaoqiang.tablesview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.xiaoqiang.xqtablesview.MaiTablesView;

public class MainActivity extends AppCompatActivity {

    private MaiTablesView mMaiTablesView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMaiTablesView = findViewById(R.id.main_view_mai);
        //设置进度值
        mMaiTablesView.setProgressValue(2);
    }

    public void doClick(View view) {
        mMaiTablesView.startRandomRoate();
    }

    @Override
    protected void onDestroy() {
        mMaiTablesView.closeRandomRoate();
        super.onDestroy();
    }
}
