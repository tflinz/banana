package com.tutela.bananas;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.tutela.bananafetcher.BananaFetcher;
import com.tutela.bananafetcher.BananaFetcherConcrete;


/**
 * Test
 */
public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";

    private final BananaFetcher bf = new BananaFetcherConcrete();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        WebView webView = ((WebView) findViewById(R.id.where_the_banana_goes));
        bf.loadImage(webView);
        clearLoading();
    }

    private void clearLoading() {
        TextView loadingView = ((TextView) findViewById(R.id.loading_text_view));
        loadingView.setText("Banana!");
    }

    /**
     * For testing.
     *
     * @return
     */
    String getAThingy() {
        return "I am a banana!";
    }
}
