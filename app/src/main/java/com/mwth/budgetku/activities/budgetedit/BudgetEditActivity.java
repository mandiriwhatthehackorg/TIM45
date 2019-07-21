package com.mwth.budgetku.activities.budgetedit;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;

import com.mwth.budgetku.R;
import com.mwth.budgetku.activities.base.BaseActivity;
import com.mwth.budgetku.helpers.Prefs;

public class BudgetEditActivity extends BaseActivity {

    private String mToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mActivity = BudgetEditActivity.this;
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
        setContentView(R.layout.activity_budget_edit);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void bindData(){
        mToken = Prefs.with(mContext).getToken();
    }

}
