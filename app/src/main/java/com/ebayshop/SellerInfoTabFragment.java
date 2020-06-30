package com.ebayshop;

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

        Map<String, String> sellerInfo = itemDetail.getSellerInfo();
        TextView sellerInfoTextView = view.findViewById(R.id.sellerInfo);
//        WebView webView = view.findViewById(R.id.webView);

        StringBuilder embeddedLi = new StringBuilder();
        for (Map.Entry mapElement : sellerInfo.entrySet()) {
            String entry = String.format("<li style=\"color:red;\"> <b>%1$s</b> : %2$s</li>", mapElement.getKey(), mapElement.getValue());
            embeddedLi.append(entry);
        }

        String html = String.format("<h3> %1$s </h3> <ul> %2$s </ul>", "Seller Information", embeddedLi.toString());
        Log.i("html", html);
        Spanned styledText = Html.fromHtml(html);

        sellerInfoTextView.setText(styledText);
//        webView.loadData(html, "text/html", "utf-8");
        return view;
    }

    public SellerInfoTabFragment(ItemDetail itemDetail) {
        this.itemDetail = itemDetail;
    }
}
