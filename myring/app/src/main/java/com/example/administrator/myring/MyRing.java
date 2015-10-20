package com.example.administrator.myring;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ScrollerCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.internal.widget.ViewUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;
import android.widget.ViewAnimator;

public class MyRing extends ViewGroup {
    private Scroller mScroller;
    private  ScrollerCompat scrollCompat;

    public MyRing(Context context) {
        super(context);
    }

    public MyRing(Context context, AttributeSet attrs) {
        super(context, attrs);
        scrollCompat = ScrollerCompat.create(context);
        mScroller = new Scroller(context);
    }

    public MyRing(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private int getPx(int idp) {
        DisplayMetrics ccc = new DisplayMetrics();
        ((Activity) getContext())
                .getWindowManager().getDefaultDisplay().getMetrics(ccc);
        return (int) (ccc.density * (float)idp + 0.5f);
    }


    int xixi =0;
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xixi = (int) ev.getX();
                break;

            case MotionEvent.ACTION_MOVE:
                int mx = (int) ev.getX();
                int deltaX = mx - xixi;

                if(aaa == 0){
                    if(deltaX > 10){
                        return true;
                    }
                }else {
                    if(deltaX < -10){
                        return true;
                    }
                }
                break;

            case MotionEvent.ACTION_UP:

                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    private ViewGroup child1;
    private ViewGroup child2;

    int leftLimitScrollX = 0;
    int rightLimitscrollX = 0;
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        child1 = (ViewGroup) getChildAt(0);
        child2 = (ViewGroup) getChildAt(1);


        int w = MeasureSpec.getSize(widthMeasureSpec);
        int child1W = w - getPx(80);


        child2.measure(widthMeasureSpec, heightMeasureSpec);
        child1.measure(MeasureSpec.makeMeasureSpec(child1W, MeasureSpec.EXACTLY), heightMeasureSpec);

        leftLimitScrollX = -child1.getMeasuredWidth();
        rightLimitscrollX = 0;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        child2.layout(l, t, r, b);
        child1.layout(-child1.getMeasuredWidth(), t, 0, b);
    }

    private int getDisdance(){
        return Math.abs(leftLimitScrollX - rightLimitscrollX);
    }

    int iRecordX = 0;

    private int aaa = 0;


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                iRecordX = (int) event.getX();
                break;

            case MotionEvent.ACTION_MOVE:
                int moveX = (int) event.getX();
                int deltaX = iRecordX - moveX;
                scrollBy(deltaX, 0);
                iRecordX = moveX;

                int currentScrollX = getScrollX();
                if(currentScrollX <= leftLimitScrollX){
                    scrollTo(leftLimitScrollX,0);
                }else if(currentScrollX >= 0){
                    scrollTo(0,0);
                }

                break;

            case MotionEvent.ACTION_UP:
                currentScrollX = getScrollX();
                if(currentScrollX < -getDisdance()*19/20){
                    //scrollCompat.startScroll(currentScrollX, 0, leftLimitScrollX - currentScrollX, 0);
                    mScroller.startScroll(currentScrollX, 0, leftLimitScrollX - currentScrollX, 0);
                    aaa = -1;
                }
                if (currentScrollX > -getDisdance()/20){
                    mScroller.startScroll(currentScrollX, 0, 0 - currentScrollX, 0);
                    aaa = 0;
                }

                if(currentScrollX > -getDisdance()*19/20 && currentScrollX < -getDisdance()/20){
                    if(aaa == 0){
                        mScroller.startScroll(currentScrollX, 0, leftLimitScrollX - currentScrollX, 0);
                        aaa =-1;
                    }else if(aaa == -1){
                        mScroller.startScroll(currentScrollX, 0, 0 - currentScrollX, 0);
                        aaa = 0;
                    }
                }

                break;

        }
        invalidate();
        return true;
    }

    @Override
    public void computeScroll() {
        if(mScroller.computeScrollOffset()){
            scrollTo(mScroller.getCurrX(),0);
            invalidate();
        }
    }
}
