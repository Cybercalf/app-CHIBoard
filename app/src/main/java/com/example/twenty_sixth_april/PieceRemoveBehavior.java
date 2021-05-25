package com.example.twenty_sixth_april;

import android.graphics.Point;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * 用于移除某类棋子的其中一个
 */
public class PieceRemoveBehavior implements PieceBehavior {
    private ArrayList<Point> points;

    @Override
    public void PieceControl(ArrayList<Point> pointArrayList, Point... points) throws Exception {
        pointArrayList.remove(points[0]);
    }
}
