package com.tutelatechnologies.fun.bananafinder;

import android.os.AsyncTask;
import android.util.Log;

import com.amazonaws.mobileconnectors.apigateway.ApiClientFactory;
import com.tutelatechnologies.fun.banana.BananaClient;
import com.tutelatechnologies.fun.banana.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

/**
 * Created by Tony on 13/07/2015.
 */
public class BananaFinderAWS implements BananaFinder {

    private static final String TAG = "BananaFinderAWS";

    private final List<BananaModelItemsItem> items = new ArrayList<BananaModelItemsItem>();

    private final Random r = new Random();

    public BananaFinderAWS() {
        try {
            items.clear();
            items.addAll(new AsyncTask<Void, Void, List<BananaModelItemsItem>>() {
                @Override
                protected List<BananaModelItemsItem> doInBackground(Void... params) {
                    ApiClientFactory factory = new ApiClientFactory();
                    final BananaClient bc = factory.build(BananaClient.class);
                    BananaModel result = bc.rootGet("hello", "1");
                    Log.w(TAG, "Bananas listed on site: " + result.getItems().size());
                    Log.w(TAG, "Bananas listed in count:" + result.getCount() + ", scannedCount:" + result.getScannedCount());
                    return result.getItems();
                }
            }.execute().get());
        } catch (InterruptedException | ExecutionException e) {
            items.clear();
        } finally {
            if (items.isEmpty()) {
                BananaModelItemsItem fauxNana = new BananaModelItemsItem();
                fauxNana.setUrl(BananaFinderConcrete.DEFAULT_BANANA);
                items.add(fauxNana);
            }
        }
    }

    @Override
    public String getABanana() {
        return items.get(r.nextInt(items.size())).getUrl();
    }
}
