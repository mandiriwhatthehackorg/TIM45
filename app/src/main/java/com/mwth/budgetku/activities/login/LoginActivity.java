package com.mwth.budgetku.activities.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.biometric.BiometricPrompt;
import androidx.fragment.app.FragmentActivity;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.mwth.budgetku.R;
import com.mwth.budgetku.activities.base.BaseActivity;
import com.mwth.budgetku.activities.main.MainActivity;
import com.mwth.budgetku.common.Constants;
import com.mwth.budgetku.helpers.Prefs;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class LoginActivity extends BaseActivity {

    private EditText editUserId, editPassword;
    private Button btnLogin, btnForgot;
    private CheckBox checkBox;

    private boolean isSaved;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mActivity = LoginActivity.this;
        initView();
        bindData();
        setBiomteric();
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
        setContentView(R.layout.activity_login);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editUserId = findViewById(R.id.edit_user_id);
        editPassword = findViewById(R.id.edit_password);

        btnLogin = findViewById(R.id.button_login);
        btnForgot = findViewById(R.id.button_forgot);
        checkBox = findViewById(R.id.check_box);

        btnForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLogin();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Constants.LOGIN_USER.equals(editUserId.getText().toString()) && Constants.LOGIN_PASS.equals(editPassword.getText().toString())){
                    if (!isSaved){
                        onLogin();
                    }
                }
            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    isSaved = true;
                } else {
                    isSaved = false;
                }
            }
        });


    }

    private void bindData(){
        mToken = Prefs.with(mContext).getToken();
    }

    private void onLogin(){
        Prefs.with(mContext).setUserId(Constants.LOGIN_USER);
        Prefs.with(mContext).setUserPass(Constants.LOGIN_PASS);

        mIntent = new Intent(mContext, MainActivity.class);
        mIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        mIntent.putExtra("hasLogin", true);
        startActivity(mIntent);
        finish();
    }

    private void setBiomteric(){
        Executor executor = Executors.newSingleThreadExecutor();

        FragmentActivity activity = this;

        final BiometricPrompt biometricPrompt = new BiometricPrompt(activity, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                if (errorCode == BiometricPrompt.ERROR_NEGATIVE_BUTTON) {

                }
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                if (Prefs.with(mContext).getUserId() != null && Prefs.with(mContext).getUserPass() != null){
                    Prefs.with(mContext).setUserId(Constants.LOGIN_USER);
                    Prefs.with(mContext).setUserPass(Constants.LOGIN_PASS);
                    onLogin();
                }
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                if (Prefs.with(mContext).getUserId() != null && Prefs.with(mContext).getUserPass() != null){
                    Prefs.with(mContext).setUserId(Constants.LOGIN_USER);
                    Prefs.with(mContext).setUserPass(Constants.LOGIN_PASS);
                    onLogin();
                }
            }
        });

        final BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Login Cepat")
                .setDescription("Daftarkan finger ID untuk login yang lebih mudah")
                .setNegativeButtonText("Login menggunakan User ID")
                .build();

        findViewById(R.id.button_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSaved){
                    biometricPrompt.authenticate(promptInfo);
                }
            }
        });
    }
}
