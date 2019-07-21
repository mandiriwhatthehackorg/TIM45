package com.mwth.budgetku.activities.main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.mwth.budgetku.R;
import com.mwth.budgetku.activities.base.BaseActivity;
import com.mwth.budgetku.activities.login.LoginActivity;
import com.mwth.budgetku.common.Constants;
import com.mwth.budgetku.helpers.Prefs;
import com.mwth.budgetku.helpers.RestHelper;
import com.mwth.budgetku.helpers.RestHelperCallback;

import androidx.appcompat.widget.Toolbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;

public class MainActivity extends BaseActivity {

    private TextView textBalance;
    private ImageButton btnDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mActivity = MainActivity.this;
        initView();
        getToken();

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
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        textBalance = findViewById(R.id.text_balance);
        btnDrawer = findViewById(R.id.button_drawer);

        btnDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
    }

    private void bindData(){

    }

    public void getToken(){
        try {
            RestHelper.with(mActivity).getToken(new RestHelperCallback() {
                @Override
                public void onSuccess(JSONObject response) {
                    mToken = Prefs.with(mContext).getToken();
//                    bindData();
                    getBalance();
                }

                @Override
                public void onError(VolleyError error) {
                    Log.d("getToken", "onError: " + error.toString());
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void getBalance(){
        try {
            RestHelper.with(mActivity).get(Constants.CASA_URI + Constants.ACCOUNT_NO + "/balance", new RestHelperCallback() {
                @Override
                public void onSuccess(JSONObject response) {
                    try {
                        JSONObject restObj = response.getJSONObject("Response");
                        JSONObject balanceObj = restObj.getJSONObject("balance");
                        String sBalance = balanceObj.getString("availableBalance");
                        Prefs.with(mContext).setBalance(Float.parseFloat(sBalance));
                        setBalance(Float.parseFloat(sBalance));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }

                @Override
                public void onError(VolleyError error) {
                    Log.d("getBalance", "onError: " + error.toString());
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void showDialog(){
//        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
//                this);
//
//        alertDialogBuilder
//                .setTitle("Sesi Anda sudah berakhir")
//                .setMessage("Anda dapat masuk kembali menggunakan Finger ID")
//                .setIcon(R.mipmap.ic_launcher)
//                .setCancelable(false)
//                .setPositiveButton("Lanjutkan",new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog,int id) {
//                        onEndSession();
//                    }
//                })
//                .setNegativeButton("Tidak",new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog,int id) {
//                        onEndSession();
//                    }
//                });
//
//        AlertDialog alertDialog = alertDialogBuilder.create();
//        alertDialog.show();

        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View promptView = layoutInflater.inflate(R.layout.modal_session, null);

        final AlertDialog alertD = new AlertDialog.Builder(this).create();

        EditText userInput = (EditText) promptView.findViewById(R.id.userInput);

        Button btnAdd1 = (Button) promptView.findViewById(R.id.btnAdd1);

        Button btnAdd2 = (Button) promptView.findViewById(R.id.btnAdd2);

        btnAdd1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // btnAdd1 has been clicked

            }
        });

        btnAdd2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // btnAdd2 has been clicked

            }
        });

        alertD.setView(promptView);

        alertD.show();
    }

    private void onEndSession(){
        Prefs.with(mContext).setUserId(null);
        Prefs.with(mContext).setUserPass(null);
        Prefs.with(mContext).setToken(null);

        mIntent = new Intent(mContext, LoginActivity.class);
        mIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        mIntent.putExtra("hasSession", false);
        startActivity(mIntent);
        finish();
    }

    private void setBalance(Float f){
        NumberFormat currency = NumberFormat.getInstance();
        String str = currency.format(f);
        textBalance.setText(str.replaceAll(",","."));
    }


}
