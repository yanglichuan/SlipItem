package com.example.administrator.myring;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class Myist extends ListView {


    public Myist(Context context) {
        super(context);
    }

    public Myist(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Myist(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    int mmmx = 0;
    int mmmy = 0;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mmmy = (int) ev.getY();
                mmmx = (int) ev.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                int mx = (int) ev.getX();
                int my = (int) ev.getY();
                int delax = mx - mmmx;
                int delay = my - mmmy;

                if (Math.abs(delax) < 10 && Math.abs(delay) > 10) {
                    return true;
                }else  if (Math.abs(delax) > 10 && Math.abs(delay) < 10) {
                    return false;
                }else  if (Math.abs(delax) > 10 && Math.abs(delay) > 10) {
                    return false;
                }else{
                    return false;
                }
            case MotionEvent.ACTION_UP:

                break;
        }
        return super.onInterceptTouchEvent(ev);
    }
}
