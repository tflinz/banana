package com.tutela.bananafetcher;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.webkit.WebView;
import android.widget.ImageView;

import com.example.bananafinder.BananaFinder;
import com.example.bananafinder.BananaFinderConcrete;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutionException;

/**
 * Created by Tony on 26/06/2015.
 * <p/>
 * This banana fetcher only fetches a single static banana.
 */
public class BananaFetcherConcrete implements BananaFetcher {
    /**
     * Logging tag.
     */
    private static final String TAG = "Concrete Banana Fetcher";

    /**
     * Where to find bananas.
     */
    private final BananaFinder bFinder;

    /**
     * Create a new concrete banana fetcher.
     */
    public BananaFetcherConcrete() {
        bFinder = new BananaFinderConcrete();
    }

    @Override
    public void loadImage(ImageView imgView) {
        try {
            Log.i(TAG, "Loading Image...");
            Bitmap gotBMP =
                    new AsyncTask<Void, Void, Bitmap>() {
                        @Override
                        protected Bitmap doInBackground(Void... params) {
                            InputStream inputStream = null;
                            try {
                                inputStream = loadImageStream();
                                return readStream(inputStream);
                            } catch (IOException e) {
                                e.printStackTrace();
                            } finally {
                                if (inputStream != null) {
                                    try {
                                        inputStream.close();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                            return null;
                        }
                    }.execute().get();
            if (gotBMP != null) {
                Log.i(TAG, "Decoding image...");
                imgView.setImageBitmap(gotBMP);
                Log.i(TAG, "Loaded Image.");
            } else {
                Log.w(TAG, "gotStream is null.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadImage(WebView webView) {
        webView.loadUrl(bFinder.getABanana());
    }

    private Bitmap readStream(InputStream inputStream) {
        Bitmap bmp = BitmapFactory.decodeStream(inputStream);
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmp;
    }

    private InputStream loadImageStream() throws IOException {
        URL url = new URL(bFinder.getABanana());
        URLConnection urlConnection = url.openConnection();
        InputStream in = new BufferedInputStream(urlConnection.getInputStream());
        return in;
    }
}
