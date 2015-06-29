package com.tutela.bananas;

import android.app.Activity;
import android.os.Bundle;


public class TestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }

    /**
     * For testing.
     *
     * @return
     */
    String getAThing() {
        return "That banana thing?!";
    }
}
