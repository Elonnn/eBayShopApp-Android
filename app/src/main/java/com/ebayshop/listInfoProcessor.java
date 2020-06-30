package com.ebayshop;

import android.graphics.Color;
import android.util.Log;
import android.webkit.WebView;

import java.util.Map;

public class ListInfoProcessor {

    static String splitCamelCase(String s) {
        s = s.substring(0, 1).toUpperCase() + s.substring(1);
        return s.replaceAll(
                String.format("%s|%s|%s",
                        "(?<=[A-Z])(?=[A-Z][a-z])",
                        "(?<=[^A-Z])(?=[A-Z])",
                        "(?<=[A-Za-z])(?=[^A-Za-z])"
                ),
                " "
        );
    }

    static void displayListInfo(Map<String, String> entries, WebView webView){
        webView.setBackgroundColor(Color.TRANSPARENT);

        StringBuilder embeddedLi = new StringBuilder();
        for (Map.Entry mapElement : entries.entrySet()) {
            String entry = String.format("<li style=\"margin: 20px 0;\"> <b>%1$s</b> : %2$s</li>", splitCamelCase((String) mapElement.getKey()), splitCamelCase((String) mapElement.getValue()));
            embeddedLi.append(entry);
        }

        String html = String.format("<ul style=\"color: rgb(131, 131, 131);\"> %1$s </ul>", embeddedLi.toString());
        Log.i("html", html);

        webView.loadData(html, "text/html", "utf-8");
    }
}
