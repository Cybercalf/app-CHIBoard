package com.example.twenty_sixth_april;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class GameChineseChessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_chess_chinese);
    }

    /**
     * 结束GameChineseGameActivity
     * 由“返回”按钮触发
     */
    public void finishGameChineseChessActivity(View view) {
        finishAndRemoveTask();
    }
}