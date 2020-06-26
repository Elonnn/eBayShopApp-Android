package com.ebayshop;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

final class SearchParams {
    public String keywords;
}


public class ItemCatalogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_catalog);
        setTitle("Search Results");

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        String searchParams = getIntent().getStringExtra("SEARCH_PARAMS");
        assert searchParams != null;
        Log.i("params", searchParams);

        TextView textView = (TextView) findViewById(R.id.textView);

        Spannable itemNumSpannable = new SpannableString("50");
        itemNumSpannable.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.ic_launcher_background)), 0, itemNumSpannable.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.append(itemNumSpannable);

        Spannable constSpannable = new SpannableString(" results for ");
        textView.append(constSpannable);

        Gson gson = new Gson();
        Spannable itemKeywordsSpannable = new SpannableString(gson.fromJson(searchParams, SearchParams.class).keywords);
        itemKeywordsSpannable.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.ic_launcher_background)), 0, itemKeywordsSpannable.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.append(itemKeywordsSpannable);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                "https://ebay-shopping-2.wl.r.appspot.com/api/search?params=" + searchParams,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("Response", response.substring(0, 500));
//                        TODO: gson parse data, and do scroll view
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Response", "That didn't work!");
                    }
                });
        requestQueue.add(stringRequest);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return false;
    }
}