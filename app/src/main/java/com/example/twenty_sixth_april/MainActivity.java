package com.example.twenty_sixth_april;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 加载ChooseActivity
     * 由MainActivity的一个按钮触发
     */
    public void goToChooseActivity(View view) {
        Intent intentOfBt_start = new Intent(this, ChooseActivity.class);
        startActivity(intentOfBt_start);
    }

    /**
     * 加载AboutActivity
     * 由MainActivity的一个按钮触发
     */
    public void goToAboutActivity(View view) {
        Intent intentOfBt_start = new Intent(this, AboutActivity.class);
        startActivity(intentOfBt_start);
    }

    /**
     * 结束MainActivity并退出系统
     * 由MainActivity的一个按钮触发
     */
    public void exitApp(View view) {
        finishAndRemoveTask();
        System.exit(0);
    }
}