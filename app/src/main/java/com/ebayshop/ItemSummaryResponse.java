package com.ebayshop;

import java.util.ArrayList;

public class ItemSummaryResponse {
    ArrayList<ItemSummary> items;

    public ItemSummaryResponse(ArrayList<ItemSummary> items) {
        this.items = items;
    }
}
