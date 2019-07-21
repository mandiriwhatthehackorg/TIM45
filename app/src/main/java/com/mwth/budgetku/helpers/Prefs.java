package com.mwth.budgetku.helpers;


import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {

    private static final String PREF_NAME = "COM.KORA.POSKORA.PREF";

    private static final String PREF_AUTH_TOKEN = PREF_NAME + ".AUTH_TOKEN";
    private static final String PREF_USER_ID = PREF_NAME + ".USER_ID";
    private static final String PREF_USER_PASS = PREF_NAME + ".USER_PASS";
    private static final String PREF_USER_NAME = PREF_NAME + ".USER_NAME";
    private static final String PREF_CIFF_NO = PREF_NAME + ".CIFF_NO";
    private static final String PREF_ACCOUNT_NO = PREF_NAME + ".ACCOUNT_NO";
    private static final String PREF_BALANCE = PREF_NAME + ".PREF_BALANCE";

    private static Prefs instance;
    private final SharedPreferences sharedPreferences;

    public Prefs(Context context) {
        sharedPreferences = context.getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static Prefs with(Context context) {

        if (instance == null) {
            instance = new Prefs(context);
        }
        return instance;
    }

    public String getToken(){
        return sharedPreferences.getString(PREF_AUTH_TOKEN, null);
    }

    public void setToken(String s){
        sharedPreferences.edit().putString(PREF_AUTH_TOKEN, s).apply();
    }

    public String getUserId(){
        return sharedPreferences.getString(PREF_USER_ID, null);
    }

    public void setUserId(String s){
        sharedPreferences.edit().putString(PREF_USER_ID, s).apply();
    }

    public String getUserPass(){
        return sharedPreferences.getString(PREF_USER_PASS, null);
    }

    public void setUserPass(String s){
        sharedPreferences.edit().putString(PREF_USER_PASS, s).apply();
    }

    public String getUserName(){
        return sharedPreferences.getString(PREF_USER_NAME, null);
    }

    public void setUserName(String s){
        sharedPreferences.edit().putString(PREF_USER_NAME, s).apply();
    }

    public String getCiffNo(){
        return sharedPreferences.getString(PREF_CIFF_NO, null);
    }

    public void setCiffNo(String s){
        sharedPreferences.edit().putString(PREF_CIFF_NO, s).apply();
    }

    public String getAccountNo(){
        return sharedPreferences.getString(PREF_ACCOUNT_NO, null);
    }

    public void setAccountNo(String s){
        sharedPreferences.edit().putString(PREF_ACCOUNT_NO, s).apply();
    }

    public float getBalance(){
        return sharedPreferences.getFloat(PREF_BALANCE, 0);
    }

    public void setBalance(float f){
        sharedPreferences.edit().putFloat(PREF_BALANCE, f).apply();
    }
}
