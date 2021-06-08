package com.example.twenty_sixth_april;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class
GameGoActivity extends AppCompatActivity {

    private GameGoPanel panel;
    private ImageView imBlackPiece;
    private ImageView imWhitePiece;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_go);
        // GameGoPanel
        panel = (GameGoPanel) findViewById(R.id.game_go_panel);
        // two ImageView in activity_game_go
        imBlackPiece = findViewById(R.id.im_game_go_black_piece);
        imWhitePiece = findViewById(R.id.im_game_go_white_piece);
        // 初始化两个ImageView的src
        if (panel.isBlackFirst()) {
            setImageViewSrc(true, false);
        } else {
            setImageViewSrc(false, true);
        }
        // 点击imBlackPiece时
        imBlackPiece.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                panel.setAddPiece(true);
                panel.setBlackFirst(true);
                setImageViewSrc(true, false);
            }
        });
        // 点击imWhitePiece时
        imWhitePiece.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                panel.setAddPiece(true);
                panel.setBlackFirst(false);
                setImageViewSrc(false, true);
            }
        });
    }

    /**
     * 当玩家下棋时，棋盘两端的棋子图片会做相应的变化
     * 应注意，当黑方下过棋后，白方的棋子应被选中，以此类推
     * 这里有一个问题，当点击棋盘时，先处理了这个函数，然后再处理了GameGoPanel的OnTouchEvent,
     * 所以这里的判断条件需要反过来
     * TODO：修改判断条件需要反过来的设计缺陷
     */
    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        // 根据下棋的一方，切换两个ImageView的图片展示，注意这里的判断条件反过来了，原因见注释
        if (panel.isAddPiece()) {
            if (panel.isBlackFirst()) {
                setImageViewSrc(false, true);
            } else {
                setImageViewSrc(true, false);
            }
        }
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
        if (panel.isBlackFirst()) {
            setImageViewSrc(true, false);
        } else {
            setImageViewSrc(false, true);
        }
    }

    /**
     * 变为提子操作
     */
    public void removePiece(View view) {
        panel.setAddPiece(false);
        // 修改两个imageView的图片展示
        setImageViewSrc(false, false);
    }

    /**
     * 切换两个ImageView图片的src
     *
     * @param blackChosen 是否让imBlackPiece切换到代表被选中的图片
     * @param whiteChosen 是否让imWhitePiece切换到代表被选中的图片
     */
    public void setImageViewSrc(boolean blackChosen, boolean whiteChosen) {
        int blackImageId, whiteImageId;
        if (blackChosen) {
            blackImageId = R.drawable.game_icon_go_black_piece_chosen;
        } else {
            blackImageId = R.drawable.game_icon_go_black_piece;
        }
        if (whiteChosen) {
            whiteImageId = R.drawable.game_icon_go_white_piece_chosen;
        } else {
            whiteImageId = R.drawable.game_icon_go_white_piece;
        }
        imBlackPiece.setImageResource(blackImageId);
        imWhitePiece.setImageResource(whiteImageId);
    }
}