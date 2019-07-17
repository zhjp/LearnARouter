package com.zjp.component;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.zjp.annotation.BindPath;
import com.zjp.component.view.MyRelativeLayout;
import com.zjp.component.view.MyTextView;
import com.zjp.route.ARouter;

@BindPath("main/main")
public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {

    private static final String TAG = "MainActivity";
    private MyTextView tv_main_view;
    private MyRelativeLayout cl_main_viewgroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    public void jumpActivity(View view) {
//        Intent intent = new Intent(this, LoginActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("bundle", "From MainActivity");
        ARouter.getInstance().jumpActivity("login/login", bundle);
//        startActivity(intent);
    }

    private void initView() {
        tv_main_view = (MyTextView) findViewById(R.id.tv_main_view);
        tv_main_view.setOnClickListener(this);
        cl_main_viewgroup = (MyRelativeLayout) findViewById(R.id.cl_main_viewgroup);
//        cl_main_viewgroup.setOnClickListener(this);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        log("dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    private void log(String msg) {
        Log.d(TAG, msg);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_main_view:
                jumpActivity(tv_main_view);
                break;
        }
        log("onClick");
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        log("onTouch");
        return super.onTouchEvent(motionEvent);
    }
}
