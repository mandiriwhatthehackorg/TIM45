package com.mwth.budgetku.activities.budget;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.widget.Toolbar;

import android.view.View;

import com.mwth.budgetku.R;
import com.mwth.budgetku.activities.base.BaseActivity;
import com.mwth.budgetku.helpers.Prefs;

public class BudgetActivity extends BaseActivity {

    private String mToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mActivity = BudgetActivity.this;
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
        setContentView(R.layout.activity_budget);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void bindData(){
        mToken = Prefs.with(mContext).getToken();
    }

}
