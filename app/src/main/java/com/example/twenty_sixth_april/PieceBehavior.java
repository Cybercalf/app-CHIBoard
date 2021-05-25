package com.example.twenty_sixth_april;

import android.graphics.Point;

import java.util.ArrayList;

/**
 * 用于对棋子存储的位置做一定的处理
 */
public interface PieceBehavior {

    /**
     * 对棋子的操作
     *
     * @param pointArrayList 存储point的ArrayList
     * @param points 传入的可变数量的point
     */
    void PieceControl(ArrayList<Point> pointArrayList, Point... points) throws Exception;
}
