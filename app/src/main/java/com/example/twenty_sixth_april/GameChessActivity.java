package com.example.twenty_sixth_april;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class GameChessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_chess);
    }

    /**
     * finish GameChessActivity and go back to last Activity
     */
    public void finishGameChessActivity(View view) {
        finishAndRemoveTask();
    }
}