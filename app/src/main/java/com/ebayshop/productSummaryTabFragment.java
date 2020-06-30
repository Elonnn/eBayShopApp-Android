package com.ebayshop;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductSummaryTabFragment extends Fragment {

    private ItemSummary itemSummary;
    private ItemDetail itemDetail;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.product_summary_tab_fragment, container, false);

        List<String> picURLs = itemDetail.getPictureURLs();
        LinearLayout imageViewContainer = view.findViewById(R.id.imageViewContainer);
        for (int i = 0; i < picURLs.size(); i++) {
            String url = picURLs.get(i);
            ImageView imageView = new ImageView(getActivity());

            imageView.setId(i);
            imageView.setPadding(20, 20, 20, 20);
            Glide.with(this).asBitmap().load(url).into(imageView);

            imageView.setScaleType(ImageView.ScaleType.FIT_START);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT));
            imageViewContainer.addView(imageView);
        }

        ((TextView)view.findViewById(R.id.title)).setText(itemSummary.getTitle());
        ((TextView)view.findViewById(R.id.price)).setText(String.format("$%s", itemSummary.getPrice()));
        ((TextView)view.findViewById(R.id.shippingPrice)).setText(String.format("Ships for $%s", itemSummary.getShippingCost()));

        displayProductFeatures(view);

        Map<String, String> specifications = new HashMap<String, String>();
        for (Map.Entry mapElement : this.itemDetail.getSpecifications().entrySet()) {
            specifications.put((String) mapElement.getKey(), String.join("/ ", (ArrayList) mapElement.getValue()));
        }
        ListInfoProcessor.displayListInfo(specifications, (WebView) view.findViewById(R.id.webView2));

        return view;
    }

    private void displayProductFeatures(@org.jetbrains.annotations.NotNull View view) {
        WebView webView = (WebView) view.findViewById(R.id.webView1);
        webView.setBackgroundColor(Color.TRANSPARENT);

        if (this.itemDetail.getSubtitle() == null && this.itemDetail.getBrand() == null) {
            ((TextView) view.findViewById(R.id.textView1)).setVisibility(View.GONE);
            webView.setVisibility(View.GONE);
            return;
        }

        StringBuilder htmlBuilder = new StringBuilder();
        if (this.itemDetail.getSubtitle() != null) {
            String line = String.format("<p> %1$s <span style=\"color: rgb(131, 131, 131);\"> %2$s </span> </p>", "Subtitle", this.itemDetail.getSubtitle());
            htmlBuilder.append(line);
        }
        if (this.itemDetail.getBrand() != null) {
            String line = String.format("<p> %1$s <span style=\"color: rgb(131, 131, 131);\"> %2$s </span> </p>", "Brand", String.join("/ ", this.itemDetail.getBrand()) );
            htmlBuilder.append(line);
        }
        webView.loadData(htmlBuilder.toString(), "text/html", "utf-8");
    }

    public ProductSummaryTabFragment(ItemSummary itemSummary, ItemDetail itemDetail) {
        this.itemSummary = itemSummary;
        this.itemDetail = itemDetail;
    }
}
