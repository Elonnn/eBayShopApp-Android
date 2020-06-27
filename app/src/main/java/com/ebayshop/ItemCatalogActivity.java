package com.ebayshop;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import java.util.ArrayList;

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
                        Log.i("Response", response);
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

        ArrayList<ItemSummary> items = new ArrayList<ItemSummary>();
        items.add(new ItemSummary("TAG Heuer Men's WAV511A.BA0900 Grand Carrera Automatic Calibre 6 RS Watch", "https://thumbs4.ebaystatic.com/m/m0qxRb6jS2Hr8mpeGHm4ZOA/140.jpg"));
//      {"items":[{"productID":"303512932859","title":"TAG Heuer Men's WAV511A.BA0900 Grand Carrera Automatic Calibre 6 RS Watch","imageURL":"https://thumbs4.ebaystatic.com/m/m0qxRb6jS2Hr8mpeGHm4ZOA/140.jpg","itemURL":"https://www.ebay.com/itm/TAG-Heuer-Mens-WAV511A-BA0900-Grand-Carrera-Automatic-Calibre-6-RS-Watch-/303512932859","price":"3415.97","location":"Brooklyn,NY,USA","isTopRated":true,"category":"Wristwatches","condition":"New with tags","shippingType":"Free","shippingCost":"0.0","shipToLocations":"Worldwide","isExpedited":true,"oneDayShippingAvailable":true,"bestOfferEnabled":true,"buyItNowAvailable":false,"listingType":"FixedPrice","gift":false,"watchCount":"1"},{"productID":"303552349954","title":"Tag Heuer Carrera","imageURL":"https://thumbs3.ebaystatic.com/m/mY-x7tUdId5DsZ5awR9gjdQ/140.jpg","itemURL":"https://www.ebay.com/itm/Tag-Heuer-Carrera-/303552349954","price":"3415.95","location":"Brooklyn,NY,USA","isTopRated":true,"category":"Wristwatches","condition":"New with tags","shippingType":"Free","shippingCost":"0.0","shipToLocations":"Worldwide","isExpedited":true,"oneDayShippingAvailable":true,"bestOfferEnabled":true,"buyItNowAvailable":false,"listingType":"FixedPrice","gift":false,"watchCount":"3"},{"productID":"303530583623","title":"Tag Heuer Formula 1 Calibre 16 Men's Chrono Cau2011.ba0873","imageURL":"https://thumbs4.ebaystatic.com/m/mbQjRqiRGDnNhN-VkCirFfw/140.jpg","itemURL":"https://www.ebay.com/itm/Tag-Heuer-Formula-1-Calibre-16-Mens-Chrono-Cau2011-ba0873-/303530583623","price":"3415.95","location":"Brooklyn,NY,USA","isTopRated":true,"category":"Wristwatches","condition":"New with tags","shippingType":"Free","shippingCost":"0.0","shipToLocations":"Worldwide","isExpedited":true,"oneDayShippingAvailable":true,"bestOfferEnabled":true,"buyItNowAvailable":false,"listingType":"FixedPrice","gift":false,"watchCount":"1"},{"productID":"303560868881","title":"TAG Heuer Connected Modular 45 Men's Watch SBF8A8012.11FT6077","imageURL":"https://thumbs2.ebaystatic.com/m/mvPETcqml2yHj97llp2eIQw/140.jpg","itemURL":"https://www.ebay.com/itm/TAG-Heuer-Connected-Modular-45-Mens-Watch-SBF8A8012-11FT6077-/303560868881","price":"3415.95","location":"Brooklyn,NY,USA","isTopRated":true,"category":"Wristwatches","condition":"New with tags","shippingType":"Free","shippingCost":"0.0","shipToLocations":"Worldwide","isExpedited":true,"oneDayShippingAvailable":true,"bestOfferEnabled":true,"buyItNowAvailable":false,"listingType":"FixedPrice","gift":false,"watchCount":"1"},{"productID":"303560869374","title":"TAG Heuer CONNECTED Luxury Smart Watch (Compatible with Android/iPhone) (Brow...","imageURL":"https://thumbs3.ebaystatic.com/m/m2fulrarE-rlmV5m3iL6Gag/140.jpg","itemURL":"https://www.ebay.com/itm/TAG-Heuer-CONNECTED-Luxury-Smart-Watch-Compatible-Android-iPhone-Brow-/303560869374","price":"3415.95","location":"Brooklyn,NY,USA","isTopRated":true,"category":"Wristwatches","condition":"New with tags","shippingType":"Free","shippingCost":"0.0","shipToLocations":"Worldwide","isExpedited":true,"oneDayShippingAvailable":true,"bestOfferEnabled":true,"buyItNowAvailable":false,"listingType":"FixedPrice","gift":false,"watchCount":"1"}]}
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, items);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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