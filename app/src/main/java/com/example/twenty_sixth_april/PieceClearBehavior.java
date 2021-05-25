package com.example.twenty_sixth_april;

import android.graphics.Point;
import java.util.ArrayList;

/**
 * 用于清除一种棋子在棋盘上所有的位置
 */
public class PieceClearBehavior implements PieceBehavior {
    private ArrayList<Point> points;

    @Override
    public void PieceControl(ArrayList<Point> pointArrayList, Point... points) throws Exception {
        for (Point point : pointArrayList) {
            pointArrayList.remove(point);
        }
    }
}
