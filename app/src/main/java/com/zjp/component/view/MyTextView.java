package com.zjp.component.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by zjp on 2019-07-05.
 *
 * @author zjp
 * @date 2019-07-05
 */
public class MyTextView extends android.support.v7.widget.AppCompatTextView implements View.OnTouchListener, View.OnClickListener {

    private static final String TAG = "MyTextView";

    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d(TAG,"dispatchTouchEvent");
        return super.dispatchTouchEvent(event);
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
