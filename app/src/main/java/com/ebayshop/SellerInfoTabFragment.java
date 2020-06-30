package com.ebayshop;

import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;
import java.util.Map;

public class SellerInfoTabFragment extends Fragment {
    private ItemDetail itemDetail;

    /**
     * Called to have the fragment instantiate its user interface view.
     * This is optional, and non-graphical fragments can return null. This will be called between
     * {@link #onCreate(Bundle)} and {@link #onActivityCreated(Bundle)}.
     * <p>A default View can be returned by calling {@link #Fragment(int)} in your
     * constructor. Otherwise, this method returns null.
     *
     * <p>It is recommended to <strong>only</strong> inflate the layout in this method and move
     * logic that operates on the returned View to {@link #onViewCreated(View, Bundle)}.
     *
     * <p>If you return a View from here, you will later be called in
     * {@link #onDestroyView} when the view is being released.
     *
     * @param inflater           The LayoutInflater object that can be used to inflate
     *                           any views in the fragment,
     * @param container          If non-null, this is the parent view that the fragment's
     *                           UI should be attached to.  The fragment should not add the view itself,
     *                           but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     *                           from a previous saved state as given here.
     * @return Return the View for the fragment's UI, or null.
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.seller_information_tab_fragment, container, false);

        Map<String, String> sellerInfo = itemDetail.getSellerInfo();
        TextView sellerInfoTextView = view.findViewById(R.id.seller_info);

        for (Map.Entry mapElement : sellerInfo.entrySet()) {
            String key = (String)mapElement.getKey();

        }
//
//        String html = String.format(getResources().getString(R.string.ul), embedded);
//        Log.i("html", html);
//        Spanned styledText = Html.fromHtml(html);
//
//        textView.setText(styledText);

        return view;
    }

    public SellerInfoTabFragment(ItemDetail itemDetail) {
        this.itemDetail = itemDetail;
    }
}
