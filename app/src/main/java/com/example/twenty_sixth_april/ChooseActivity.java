package com.example.twenty_sixth_april;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ChooseActivity extends AppCompatActivity {
    // all data needed for ListView
    private String[] data;
    private int[] imageId;
    private List<ChessBoardItem> chessBoardItemList;
    private ListView lvInChooseActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        initData();
        lvInChooseActivity = findViewById(R.id.lv_choose);
        lvInChooseActivity.setAdapter(new ChessBoardAdapter());
        // 为lvInChooseActivity设置监听器
        lvInChooseActivity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!goToOneOfGameActivities(position)) {
                    Toast.makeText(ChooseActivity.this,
                            "目前没有可以跳转的页面！",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * finish ChooseActivity and go back to last Activity
     */
    public void finishChooseActivity(View view) {
        finishAndRemoveTask();
    }

    /**
     * initialize data of ListView: lvInChooseActivity
     */
    private void initData() {
        // initialize data in data:String[]
        data = new String[]{"围棋棋盘",
                "中国象棋棋盘", "国际象棋棋盘",
                "斗兽棋棋盘", "标准跳棋棋盘",
                "标准独立钻石棋棋盘", "四子棋棋盘"};

        // initialize imageId:int[]
        imageId = new int[]{
                R.drawable.mini_go,
        R.drawable.mini_chinesechess,
        R.drawable.mini_chess,
        R.drawable.mini_beast,
        R.drawable.mini_jump,
        R.drawable.mini_solitaire,
        R.drawable.mini_four };

        // initialize data in chessBoardItemList
        chessBoardItemList = new ArrayList<ChessBoardItem>();
        for (int i = 0; i < data.length; i++) {
            chessBoardItemList.add(new ChessBoardItem(imageId[i], data[i]));
        }
    }

    /**
     * adapter for ListView.
     */
    private class ChessBoardAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return chessBoardItemList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ChessBoardItem chessBoardItem = chessBoardItemList.get(position);
            View view = View.inflate(ChooseActivity.this,
                    R.layout.chessboard_item, null);

            ImageView iv = view.findViewById(R.id.iv_chessboard_item);
            TextView tv = view.findViewById(R.id.tv_chessboard_item);
            iv.setImageResource(chessBoardItem.getImageId());
            tv.setText(chessBoardItem.getName());
            return view;
        }
    }

    /**
     * Go to one of Game**Activity.
     * If there isn't an Activity that can be gone to, return false.
     */
    public boolean goToOneOfGameActivities(int choose) {
        Intent intent;
        switch (choose) {
            case 0:
                intent = new Intent(this, GameGoActivity.class);
                break;
            case 1:
                intent = new Intent(this, GameChineseChessActivity.class);
                break;
            case 2:
                intent = new Intent(this, GameChessActivity.class);
                break;
            case 4:
                intent = new Intent(this, GameJumpActivity.class);
                break;
            default:
                return false;
        }
        startActivity(intent);
        return true;
    }
}