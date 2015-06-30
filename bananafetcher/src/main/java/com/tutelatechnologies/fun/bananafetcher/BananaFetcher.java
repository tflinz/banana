package com.tutelatechnologies.fun.bananafetcher;

import android.webkit.WebView;
import android.widget.ImageView;

/**
 * Created by Tony on 26/06/2015.
 */
public interface BananaFetcher {
    /**
     * Inject a banana into the provided image view.
     * @param imgView
     */
    void loadImage(ImageView imgView);

    /**
     * Inject a banana into the provided web view.
     * @param webView
     */
    void loadImage(WebView webView);
}
