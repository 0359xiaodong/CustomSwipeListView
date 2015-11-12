package com.lastwarmth.swipelistview;

import android.content.Context;
import android.support.v4.widget.ScrollerCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.AbsListView;
import android.widget.FrameLayout;


public class SwipeItemLayout extends FrameLayout {
    private View contentView = null;
    private View menuView = null;
    private ScrollerCompat mOpenScroller;
    private ScrollerCompat mCloseScroller;
    private int mBaseX;
    private int mDownX;
    private boolean isMenuShowing = false;
    private VelocityTracker mVelocityTracker;

    public SwipeItemLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SwipeItemLayout(View contentView, View menuView) {
        super(contentView.getContext());
        this.contentView = contentView;
        this.menuView = menuView;
        init();
    }

    private void createVelocityTracker(MotionEvent event) {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);
    }

    private void recycleVelocityTracker() {
        mVelocityTracker.recycle();
        mVelocityTracker = null;
    }

    private int getScrollVelocity() {
        mVelocityTracker.computeCurrentVelocity(1000);
        int velocity = (int) mVelocityTracker.getXVelocity();
        return Math.abs(velocity);
    }

    private void init() {
        setLayoutParams(new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT));
        mCloseScroller = ScrollerCompat.create(getContext());
        mOpenScroller = ScrollerCompat.create(getContext());
        LayoutParams contentParams = new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        contentView.setLayoutParams(contentParams);
        menuView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT));
        addView(contentView);
        addView(menuView);
    }

    public boolean onSwipe(MotionEvent event) {
        createVelocityTracker(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = (int) event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                int dis = (int) (mDownX - event.getX());
                if (isMenuShowing) {
                    dis += menuView.getWidth();
                }
                swipe(dis);
                break;
            case MotionEvent.ACTION_UP:
                if ((mDownX - event.getX()) > (menuView.getWidth() / 2)
                        || ((getScrollVelocity() > 200) && (mDownX - event.getX()) > 0)) {
                    smoothOpenMenu();
                } else {
                    smoothCloseMenu();
                }
                recycleVelocityTracker();
                break;
        }
        return true;
    }

    public boolean isOpen() {
        return isMenuShowing;
    }

    private void swipe(int dis) {
        if (dis > menuView.getWidth()) {
            dis = menuView.getWidth();
        }
        if (dis < 0) {
            dis = 0;
        }
        contentView.layout(-dis, contentView.getTop(),
                contentView.getWidth() - dis, getMeasuredHeight());
        menuView.layout(contentView.getWidth() - dis, menuView.getTop(),
                contentView.getWidth() + menuView.getWidth() - dis,
                menuView.getBottom());
    }

    @Override
    public void computeScroll() {
        if (isMenuShowing) {
            if (mOpenScroller.computeScrollOffset()) {
                swipe(mOpenScroller.getCurrX());
                postInvalidate();
            }
        } else {
            if (mCloseScroller.computeScrollOffset()) {
                swipe(mBaseX - mCloseScroller.getCurrX());
                postInvalidate();
            }
        }
    }

    public void smoothCloseMenu() {
        isMenuShowing = false;
        mBaseX = -contentView.getLeft();
        mCloseScroller.startScroll(0, 0, mBaseX, 0, 350);
        postInvalidate();
    }

    public void smoothOpenMenu() {
        isMenuShowing = true;
        mOpenScroller.startScroll(-contentView.getLeft(), 0,
                menuView.getWidth(), 0, 350);
        postInvalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        menuView.measure(MeasureSpec.makeMeasureSpec(0,
                MeasureSpec.UNSPECIFIED), MeasureSpec.makeMeasureSpec(
                getMeasuredHeight(), MeasureSpec.EXACTLY));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        contentView.layout(0, 0, getMeasuredWidth(),
                contentView.getMeasuredHeight());
        menuView.layout(getMeasuredWidth(), 0,
                getMeasuredWidth() + menuView.getMeasuredWidth(),
                contentView.getMeasuredHeight());
    }
}
