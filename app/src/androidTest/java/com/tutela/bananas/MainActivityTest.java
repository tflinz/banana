package com.tutela.bananas;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

/**
 * Created by Tony on 26/06/2015.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {
    public MainActivityTest() {
        super(MainActivity.class);
    }

    public void testActivityAccess() {
        assertEquals("I am a banana!", getActivity().getAThingy());
    }

    public void testViewInit() throws InterruptedException {
        Thread.sleep(1000);
        TextView tv = (TextView) getActivity().findViewById(R.id.loading_text_view);
        assertEquals("Banana!", tv.getText());
    }
}
