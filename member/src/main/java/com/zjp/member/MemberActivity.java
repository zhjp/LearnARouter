package com.zjp.member;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zjp.annotation.BindPath;
import com.zjp.route.ARouter;

@BindPath("member/member")
public class MemberActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);
    }

    public void jumpActivity(View view) {
        ARouter.getInstance().jumpActivity("login/login",null);
    }
}
