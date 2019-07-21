package com.mwth.budgetku.helpers;

import com.android.volley.VolleyError;
import org.json.JSONObject;

public interface RestHelperCallback {
    void onSuccess(JSONObject response);
    void onError(VolleyError error);
}