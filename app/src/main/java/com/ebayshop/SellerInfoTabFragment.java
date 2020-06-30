package com.ebayshop;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;
import java.util.Map;


public class SellerInfoTabFragment extends Fragment {
    private ItemDetail itemDetail;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.seller_information_tab_fragment, container, false);

        ListInfoProcessor.displayListInfo(itemDetail.getSellerInfo(), (WebView) view.findViewById(R.id.webView1));
        ListInfoProcessor.displayListInfo(itemDetail.getReturnPolicies(), (WebView) view.findViewById(R.id.webView2));

        return view;
    }

    public SellerInfoTabFragment(ItemDetail itemDetail) {
        this.itemDetail = itemDetail;
    }
}
