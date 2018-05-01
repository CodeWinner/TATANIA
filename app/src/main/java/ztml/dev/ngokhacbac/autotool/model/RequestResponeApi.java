package ztml.dev.ngokhacbac.autotool.model;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class RequestResponeApi {
    private static RequestResponeApi INSTANCE;
    private Context context;
    private RequestResponeListenner requestResponeListenner;

    public RequestResponeApi(Context context) {
        this.context = context;

    }

    public void setOnRequestResponeListenner(RequestResponeListenner requestResponeListenner) {
        this.requestResponeListenner = requestResponeListenner;
    }

    public static RequestResponeApi getInstance(Context context) {
        if (INSTANCE == null) INSTANCE = new RequestResponeApi(context);
        return INSTANCE;
    }

    public void sendRequest(String url) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                requestResponeListenner.RequestSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                requestResponeListenner.RequestError();
            }
        });
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(stringRequest);
    }
}
