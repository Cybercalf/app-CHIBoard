package com.example.twenty_sixth_april;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class GameGoActivity extends AppCompatActivity {

    private GameGoPanel panel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_go);
        // GameGopanel
        panel = (GameGoPanel) findViewById(R.id.game_go_panel);

    }

    /**
     * finish GameGoActivity and go back to last Activity
     */
    public void finishGameGoActivity(View view) {
        finishAndRemoveTask();
    }

    /**
     * 让游戏变为下子操作
     */
    public void addPiece(View view) {
        panel.setAddPiece(true);
    }

    /**
     * 变为提子操作
     */
    public void removePiece(View view) {
        panel.setAddPiece(false);
    }
}