package com.ebayshop;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import java.util.ArrayList;

public class ProductDetailActivity extends AppCompatActivity {

    private ItemSummary itemSummary;
    private ItemDetail itemDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        ItemSummary itemSummary = (ItemSummary) getIntent().getSerializableExtra("ITEM_SUMMARY");
        assert itemSummary != null;
        this.itemSummary = itemSummary;
        setTitle(itemSummary.getTitle());

//        String url = "http://localhost:3000/api/item?id=" + itemSummary.getProductID();
//        android device localhost:3000 does not hit the port on this computer.
        String url = "https://ebay-shopping-2.wl.r.appspot.com/api/item?id=" + itemSummary.getProductID();
        Log.i("request", url);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("Response", response);
                        Gson gson = new Gson();
                        ItemDetail itemDetail = gson.fromJson(response, ItemDetail.class );
                        showDetailProductScreen(itemDetail);
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

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private void showDetailProductScreen(ItemDetail itemDetail) {
        this.itemDetail = itemDetail;

        viewPager = (ViewPager) findViewById(R.id.pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ProductSummaryTabFragment(this.itemSummary, this.itemDetail), "PRODUCT");
        adapter.addFragment(new SellerInfoTabFragment(this.itemDetail), "SELLER INFO");
        adapter.addFragment(new ShippingInfoTabFragment(this.itemSummary), "SHIPPING");
        viewPager.setAdapter(adapter);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
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