package com.example.twenty_sixth_april;

/**
 * 在ChooseActivity中ListView使用的元素
 * 由imageId存储图片的id，name存储显示的字符串
 */
public class ChessBoardItem {
    private int imageId;
    private String name;

    public ChessBoardItem() {
    }

    public ChessBoardItem(int imageId, String name) {
        this.imageId = imageId;
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
