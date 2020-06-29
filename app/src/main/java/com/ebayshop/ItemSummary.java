package com.ebayshop;

import java.io.Serializable;
import java.util.Map;

public class ItemSummary implements Serializable {
    private String title;
    private String imageURL;
    private String productID;
    private String price;
    private String shippingCost;
    private String condition;
    private Boolean isTopRated;
    private Map<String, String> shippingInfo;

    public String getProductID() {
        return productID;
    }

    public String getPrice() {
        return price;
    }

    public String getShippingCost() {
        return shippingCost;
    }

    public String getCondition() {
        return condition;
    }

    public Boolean getTopRated() {
        return isTopRated;
    }

    public String getTitle() {
        return title;
    }

    public String getImageURL() {
        return imageURL;
    }

    public Map<String, String> getShippingInfo() {
        return shippingInfo;
    }
}
