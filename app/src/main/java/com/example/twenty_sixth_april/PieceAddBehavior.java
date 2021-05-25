package com.example.twenty_sixth_april;

import android.graphics.Point;

import java.util.ArrayList;

/**
 * 用于增加一个特定种类的棋子
 */
public class PieceAddBehavior implements PieceBehavior {
    private ArrayList<Point> points;

    @Override
    public void PieceControl(ArrayList<Point> pointArrayList, Point... points) throws Exception {
        pointArrayList.add(points[0]);
    }
}
