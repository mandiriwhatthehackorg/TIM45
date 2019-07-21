package com.mwth.budgetku.activities.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.mwth.budgetku.R;
import com.mwth.budgetku.activities.base.BaseActivity;
import com.mwth.budgetku.activities.login.LoginActivity;

public class SplashActivity extends BaseActivity {

    private String mToken;
    private int DELAY_TIME = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mActivity = SplashActivity.this;
        initView();
        bindData();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initView() {
        setContentView(R.layout.activity_splash);
    }

    private void bindData(){
        goToLogin();
    }

    private void goToLogin(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mIntent = new Intent(mContext, LoginActivity.class);
                mIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(mIntent);
                finish();
            }
        }, DELAY_TIME);
    }
}
