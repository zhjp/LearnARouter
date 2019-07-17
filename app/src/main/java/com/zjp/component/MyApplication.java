package com.zjp.component;

import android.app.Application;

import com.zjp.route.ARouter;

/**
 * Created by zjp on 2019-06-05.
 *
 * @author zjp
 * @date 2019-06-05
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.getInstance().init(this);
    }
}
