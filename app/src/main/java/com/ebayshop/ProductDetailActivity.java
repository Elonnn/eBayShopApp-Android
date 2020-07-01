package com.ebayshop;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
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

    private ProgressBar spinner;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        spinner = (ProgressBar)findViewById(R.id.progressBar1);

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

                        spinner.setVisibility(View.GONE);
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

        Drawable seller = getResources().getDrawable(R.drawable.ic_seller);
        seller.setColorFilter(new
                PorterDuffColorFilter(ContextCompat.getColor(this, R.color.ic_launcher_background), PorterDuff.Mode.MULTIPLY));

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.information_variant_selected);
        tabLayout.getTabAt(1).setIcon(seller);
        tabLayout.getTabAt(2).setIcon(R.drawable.truck_delivery_selected);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            case R.id.action_redirect:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(this.itemSummary.getItemURL()));
                startActivity(browserIntent);
                return true;

            default:
                return false;

        }
    }
}