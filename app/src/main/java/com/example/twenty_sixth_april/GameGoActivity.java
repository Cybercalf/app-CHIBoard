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

// TODO:把下子和提子的三个按钮变为一个Spinner

    /**
     * 让黑方下子
     */
    public void setBlackFirst(View view) {
        // 先要让游戏变为下子操作
        panel.setAddPiece(true);
        panel.setWhiteFirst(false);
        Toast.makeText(GameGoActivity.this, "现在是黑方下子！", Toast.LENGTH_SHORT).show();
    }

    /**
     * 让白方下子
     */
    public void setWhiteFirst(View view) {
        // 先要让游戏变为下子操作
        panel.setAddPiece(true);
        panel.setWhiteFirst(true);
        Toast.makeText(GameGoActivity.this, "现在是白方下子！", Toast.LENGTH_SHORT).show();
    }

    /**
     * 变为提子操作
     */
    public void setRemovePiece(View view) {
        panel.setAddPiece(false);
        Toast.makeText(GameGoActivity.this, "现在是提子操作！", Toast.LENGTH_SHORT).show();
    }
}