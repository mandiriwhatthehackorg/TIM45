package com.mwth.budgetku.helpers;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Base64;
import android.util.Log;

import com.android.volley.*;
import com.android.volley.toolbox.Volley;
import com.mwth.budgetku.common.Constants;
import com.mwth.budgetku.common.utils.CustomJsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class RestHelper {

    @SuppressLint("StaticFieldLeak")
    private static RestHelper mInstance;

    private Activity mActivity;

    private Context mContext;

    public static RestHelper getInstance() {
        return mInstance;
    }

    private RestHelper(Application application, Activity activity) {
        mContext = application.getApplicationContext();
        mActivity = activity;
    }

    private RestHelper(Application application) {
        mContext = application.getApplicationContext();
    }

    public static RestHelper with(Activity activity) {
        if (mInstance == null) {
            mInstance = new RestHelper(activity.getApplication(), activity);
        }
        return mInstance;
    }

    public static RestHelper with(Context context) {
        if (mInstance == null) {
            mInstance = new RestHelper((Application) context);
        }
        return mInstance;
    }


    public void getToken(final RestHelperCallback callback) throws JSONException {
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);

        final HashMap<String, String> paramHeader = new HashMap<String, String>();
        Map<String, String> params = new HashMap();

        String credentials = Constants.CID + ":" + Constants.CIS;
        String auth = "Basic "
                + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);

        paramHeader.put("Accept", "application/json");
        paramHeader.put("Authorization", auth);

        String mUri = Constants.TOKEN_URI;

        CustomJsonObjectRequest jsonObjRequest = new CustomJsonObjectRequest
                (Request.Method.GET, mUri, params, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject c = response;
                            Prefs.with(mContext).setToken(c.getString("jwt"));
                            callback.onSuccess(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.onError(error);
                        error.printStackTrace();
                    }
                })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                if(paramHeader != null) {
                    return paramHeader;
                } else {
                    return super.getHeaders();
                }
            }
        };

        jsonObjRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 5000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 5;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {
                callback.onError(error);
                error.printStackTrace();
            }
        });

        requestQueue.add(jsonObjRequest);
    }

    public void get(final String uri, final RestHelperCallback callback) throws JSONException {
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);

        final HashMap<String, String> paramHeader = new HashMap<String, String>();
        Map<String, String> params = new HashMap();

        paramHeader.put("Accept", "application/json");
        paramHeader.put("Authorization", "Bearer " + Prefs.with(mContext).getToken());

        CustomJsonObjectRequest jsonObjRequest = new CustomJsonObjectRequest
                (Request.Method.GET, uri, params, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        callback.onSuccess(response);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.onError(error);
                        error.printStackTrace();
                    }
                })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                if(paramHeader != null) {
                    return paramHeader;
                } else {
                    return super.getHeaders();
                }
            }
        };

        jsonObjRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 5000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 5;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {
                callback.onError(error);
                error.printStackTrace();
            }
        });

        requestQueue.add(jsonObjRequest);
    }


    public void get(final String uri, String querySearch, final RestHelperCallback callback) throws JSONException {
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);

        final HashMap<String, String> paramHeader = new HashMap<String, String>();
        Map<String, String> params = new HashMap();

        paramHeader.put("Accept", "application/json");
        paramHeader.put("Authorization", "Bearer " + Prefs.with(mContext).getToken());

        String mUri = uri + querySearch;

        CustomJsonObjectRequest jsonObjRequest = new CustomJsonObjectRequest
                (Request.Method.GET, mUri, params, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        callback.onSuccess(response);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.onError(error);
                        error.printStackTrace();
                    }
                })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                if(paramHeader != null) {
                    return paramHeader;
                } else {
                    return super.getHeaders();
                }
            }
        };

        jsonObjRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 5000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 5;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {
                callback.onError(error);
                error.printStackTrace();
            }
        });

        requestQueue.add(jsonObjRequest);
    }

    public void post(final String uri, final JSONObject jsonBody, final RestHelperCallback callback) throws JSONException {
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);

        final HashMap<String, String> paramHeader = new HashMap<String, String>();
        Map<String, String> headerParams = new HashMap();

        paramHeader.put("Accept", "application/json");
        paramHeader.put("Authorization", "Bearer " + Prefs.with(mContext).getToken());

        final String mRequestBody = jsonBody.toString();

        CustomJsonObjectRequest jsonObjRequest = new CustomJsonObjectRequest
                (Request.Method.POST, uri, headerParams, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        callback.onSuccess(response);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.onError(error);
                        error.printStackTrace();
                    }
                })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                if(paramHeader != null) {
                    return paramHeader;
                } else {
                    return super.getHeaders();
                }
            }

            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return mRequestBody == null ? null : mRequestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", mRequestBody, "utf-8");
                    return null;
                }
            }
        };

        jsonObjRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 5000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 5;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {
                callback.onError(error);
                error.printStackTrace();
            }
        });

        requestQueue.add(jsonObjRequest);
    }

}
