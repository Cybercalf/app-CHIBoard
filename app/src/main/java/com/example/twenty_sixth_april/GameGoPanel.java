package com.example.twenty_sixth_april;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class GameGoPanel extends View {

    // width of chessboard
    private int mPanelWidth;
    //  棋盘格子的行高(声明为int会因不能整除造成误差)
    private float mLineHeight;
    // 棋盘最大行列数，即横竖线的个数
    private final int MAX_LINE_NUM = 19;

    // 画笔绘制棋盘格子
    private Paint mPaint = new Paint();

    // 黑白棋子和参考点Bitmap
    private Bitmap mWhitePiece;
    private Bitmap mBlackPiece;
    private Bitmap mDotPiece;

    //棋子的缩放比例(行高的3/4)
    private final float pieceScaleRatio = 3 * 1.0f / 4;

    // 存储参考点与黑白棋子的位置
    private ArrayList<Point> mWhiteArray = new ArrayList<>();
    private ArrayList<Point> mBlackArray = new ArrayList<>();
    private final ArrayList<Point> mDotArray = new ArrayList<>();

    // 哪方下子
    private boolean isBlackFirst = true;

    // 下子还是提子
    private boolean isAddPiece = true;

    // 游戏是否结束
//    private boolean isGameOver;
    // 确定赢家
//    private boolean isWhiteWinner = false;
    // 游戏结束监听
//    private OnGameOverListener onGameOverListener;

    //添加或移除棋子用的接口
    private PieceBehavior pieceBehavior;

    /**
     * 构造方法
     *
     * @param context
     */
    public GameGoPanel(Context context) {
        this(context, null);
    }

    /**
     * 构造方法
     *
     * @param context
     */
    public GameGoPanel(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * 构造方法
     *
     * @param context
     */
    public GameGoPanel(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 初始化设置
     */
    private void init() {
        //初始化画笔
        mPaint.setColor(0x88000000);
        //设置抗锯齿
        mPaint.setAntiAlias(true);
        //设置防抖动
        mPaint.setDither(true);
        //设置为空心(画线)
        mPaint.setStyle(Paint.Style.STROKE);

        //初始化棋子
        mWhitePiece = BitmapFactory.decodeResource(getResources(), R.drawable.game_icon_go_white_piece);
        mBlackPiece = BitmapFactory.decodeResource(getResources(), R.drawable.game_icon_go_black_piece);
        mDotPiece = BitmapFactory.decodeResource(getResources(), R.drawable.game_icon_go_dot_piece);

        // 初始化参考点的位置，它们是不会变化的
        mDotArray.add(new Point(3, 3));
        mDotArray.add(new Point(3, 9));
        mDotArray.add(new Point(3, 15));
        mDotArray.add(new Point(9, 3));
        mDotArray.add(new Point(9, 9));
        mDotArray.add(new Point(9, 15));
        mDotArray.add(new Point(15, 3));
        mDotArray.add(new Point(15, 9));
        mDotArray.add(new Point(15, 15));
    }

    /**
     * 测量
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int width = Math.min(widthSize, heightSize);
        // 此处的逻辑判断是处理当我们自定义的View被嵌套在ScrollView中时,获得的测量模式
        // 会是UNSPECIFIED
        // 使得到的widthSize或者heightSize为0
        if (widthMode == MeasureSpec.UNSPECIFIED) {
            width = heightSize;
        } else if (heightMode == MeasureSpec.UNSPECIFIED) {
            width = widthSize;
        }
        //调用此方法使我们的测量结果生效
        setMeasuredDimension(width, width);
    }

    /**
     * 当宽高发生变化时回调此方法
     *
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //此处的参数w就是在onMeasure()方法中设置的自定义View的大小
        //计算出棋盘宽度和行高
        mPanelWidth = w;
        mLineHeight = mPanelWidth * 1.0f / MAX_LINE_NUM;

        //将棋子根据行高变化
        int pieceWidth = (int) (pieceScaleRatio * mLineHeight);
        mWhitePiece = Bitmap.createScaledBitmap(mWhitePiece, pieceWidth, pieceWidth, false);
        mBlackPiece = Bitmap.createScaledBitmap(mBlackPiece, pieceWidth, pieceWidth, false);
        mDotPiece = Bitmap.createScaledBitmap(mDotPiece, pieceWidth / 2, pieceWidth / 2, false);

    }

    /**
     * 进行绘制工作
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 绘制棋盘
        drawBoard(canvas);

        // 绘制用户已经下的所有棋子
        drawPieces(canvas);

        // 判断游戏是否结束
        // checkGameOver();
    }

    /**
     * 处理用户手势操作
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // if (isGameOver) return false;
        int action = event.getAction();
        //手指抬起后处理
        if (action == MotionEvent.ACTION_UP) {
            // 拦截事件自己处理
            int x = (int) event.getX();
            int y = (int) event.getY();
            Point point = getValidPoint(x, y);

            try {
                // 落子的操作
                if (isAddPiece) {
                    // 首先判断所点击的位置是不是已经有棋子
                    if (mWhiteArray.contains(point) || mBlackArray.contains(point)) {
                        return false;
                    }
                    pieceBehavior = new PieceAddBehavior();
                    // 判断是白棋下子还是黑棋下子
                    if (isBlackFirst) {
                        pieceBehavior.PieceControl(mBlackArray, point);
                    } else {
                        pieceBehavior.PieceControl(mWhiteArray, point);
                    }
                    invertIsBlackFirst(); // 切换下棋的一方
                }
                // 提子的操作
                else {
                    // 剔除的子颜色不确定，所以在黑白中各找一遍
                    pieceBehavior = new PieceRemoveBehavior();
                    pieceBehavior.PieceControl(mWhiteArray, point);
                    pieceBehavior.PieceControl(mBlackArray, point);
                }
            } catch (Exception e) {
                System.err.println(e.toString());
            }

            // 调用重绘
            invalidate();
        }
        return true;
    }


    /**
     * 改变下棋的一方
     */
    public void setBlackFirst(boolean blackFirst) {
        isBlackFirst = blackFirst;
    }

    /**
     * 改变落子还是提子
     */
    public void setAddPiece(boolean addPiece) {
        isAddPiece = addPiece;
    }

    public boolean isBlackFirst() {
        return isBlackFirst;
    }

    public boolean isAddPiece() {
        return isAddPiece;
    }

    public void invertIsBlackFirst() {
        this.isBlackFirst = !this.isBlackFirst;
    }

    /**
     * 将用户点击的位置的Point转换为类似于(0,0)d的坐标
     *
     * @param x
     * @param y
     * @return
     */
    private Point getValidPoint(int x, int y) {
        return new Point((int) (x / mLineHeight), (int) (y / mLineHeight));
    }

    /**
     * 绘制棋盘
     *
     * @param canvas
     */
    private void drawBoard(Canvas canvas) {
        int w = mPanelWidth;
        float lineHeight = mLineHeight;

        for (int i = 0; i < MAX_LINE_NUM; i++) {
            int startX = (int) (lineHeight / 2);
            int endX = (int) (w - lineHeight / 2);

            int y = (int) ((0.5 + i) * lineHeight);
            //画横线
            canvas.drawLine(startX, y, endX, y, mPaint);
            //画竖线
            canvas.drawLine(y, startX, y, endX, mPaint);
        }
    }

    /**
     * 绘制棋子
     *
     * @param canvas
     */
    private void drawPieces(Canvas canvas) {
        //绘制参考点
        for (int i = 0, n = mDotArray.size(); i < n; i++) {
            Point dotPoint = mDotArray.get(i);
            // 和黑白棋子的绘制位置略有不同
            // TODO:研究参考点mDotPiece的位置画法，使其在不同手机上均能与棋盘交叉点契合
            canvas.drawBitmap(mDotPiece,
                    (dotPoint.x + (1 - pieceScaleRatio) + 0.05f) * mLineHeight,
                    (dotPoint.y + (1 - pieceScaleRatio) + 0.05f) * mLineHeight, null);
        }
        //绘制白棋子
        for (int i = 0, n = mWhiteArray.size(); i < n; i++) {
            Point whitePoint = mWhiteArray.get(i);
            //棋子之间的间隔为1/4行高,棋子距离左右边框的距离为1/8行高
            canvas.drawBitmap(mWhitePiece,
                    (whitePoint.x + (1 - pieceScaleRatio) / 2) * mLineHeight,
                    (whitePoint.y + (1 - pieceScaleRatio) / 2) * mLineHeight, null);
        }
        //绘制黑棋子
        for (int i = 0, n = mBlackArray.size(); i < n; i++) {
            Point blackPoint = mBlackArray.get(i);
            //棋子之间的间隔为1/4行高,棋子距离左右边框的距离为1/8行高
            canvas.drawBitmap(mBlackPiece,
                    (blackPoint.x + (1 - pieceScaleRatio) / 2) * mLineHeight,
                    (blackPoint.y + (1 - pieceScaleRatio) / 2) * mLineHeight, null);
        }
    }
}