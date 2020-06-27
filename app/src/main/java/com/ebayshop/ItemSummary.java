package com.ebayshop;

public class ItemSummary {
    private String title;
    private String imageURL;

    public String getTitle() {
        return title;
    }

    public String getImageURL() {
        return imageURL;
    }

    public ItemSummary(String title, String imageURL) {
        this.title = title;
        this.imageURL = imageURL;
    }
}
