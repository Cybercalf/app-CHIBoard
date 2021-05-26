tech.md 说明了我们是如何将课上所学运用到项目开发中去的。
## 我们应用的技术
### Javadoc
```java
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
```
### Singleton Pattern
```java
private static AboutLicenseFragment singletonInstance;
    ...
    /**
     * 使用单例模式
     * @return singletonInstance of AboutLicenceFragment
     */
    public static AboutLicenseFragment getSingletonInstance() {
        if (singletonInstance == null) {
            singletonInstance = new AboutLicenseFragment();
        }
        return singletonInstance;
    }
```
### Strategy Pattern(Program to an interface)
```java
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
```
**Note**: We have drawn heavily on the knowledge shared by other developers in writing the board drawing and play operations, so please allow us to sincerely thank those who have shared their knowledge here.

Links to reference content： https://www.jianshu.com/p/28cab8a75270
```java
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
```
### 匿名内部类
```java
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
```
## 我们尚未应用的技术
#### 包管理
#### 异常处理
#### IO操作
#### 观察者模式
#### 装饰者模式
#### 完整的UML设计