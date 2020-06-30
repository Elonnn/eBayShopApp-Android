package com.ebayshop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import java.util.List;

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
        return view;
    }

    public ProductSummaryTabFragment(ItemSummary itemSummary, ItemDetail itemDetail) {
        this.itemSummary = itemSummary;
        this.itemDetail = itemDetail;
    }
}
