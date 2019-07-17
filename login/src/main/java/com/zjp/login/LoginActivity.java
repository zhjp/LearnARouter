package com.zjp.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.zjp.annotation.BindPath;
import com.zjp.route.ARouter;

@BindPath("login/login")
public class LoginActivity extends AppCompatActivity {
    TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tvLogin = findViewById(R.id.tv_login);

    }

    public void jumpActivity(View view) {
//        Intent intent = new Intent(this,MainActivity.class);
//        startActivity(intent);
        ARouter.getInstance().jumpActivity("member/member",null);
    }
}