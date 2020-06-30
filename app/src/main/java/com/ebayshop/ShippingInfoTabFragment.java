package com.ebayshop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ShippingInfoTabFragment extends Fragment {

    private ItemSummary itemSummary;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shipping_information_tab_fragment, container, false);

        ListInfoProcessor.displayListInfo(itemSummary.getShippingInfo(), (WebView) view.findViewById(R.id.webView1));

        return view;
    }

    public ShippingInfoTabFragment(ItemSummary itemSummary) {
        this.itemSummary = itemSummary;
    }
}
