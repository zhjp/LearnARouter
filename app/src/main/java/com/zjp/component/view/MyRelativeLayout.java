package com.zjp.component.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by zjp on 2019-07-05.
 *
 * @author zjp
 * @date 2019-07-05
 */
public class MyRelativeLayout extends RelativeLayout implements View.OnClickListener, View.OnTouchListener {

    private static final String TAG = "MyRelativeLayout";

    public MyRelativeLayout(Context context) {
        super(context);
    }

    public MyRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d(TAG,"dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onClick(View view) {
        Log.d(TAG,"onClick");
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        Log.d(TAG,"onClick");
        return super.onTouchEvent(motionEvent);
    }
}
