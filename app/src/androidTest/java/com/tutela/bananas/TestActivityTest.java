package com.tutela.bananas;

import android.test.ActivityInstrumentationTestCase2;


/**
 * Created by Tony on 26/06/2015.
 */
public class TestActivityTest extends ActivityInstrumentationTestCase2<TestActivity> {
    public TestActivityTest() {
        super(TestActivity.class);
    }

    public void testAccessibility() {
        assertEquals("That banana thing?!", getActivity().getAThing());
    }

    public void testViewInit() throws InterruptedException {
        Thread.sleep(1000);
//        TextView tv = (TextView) getActivity().findViewById(R.id.test_text_view);
//        assertEquals("Banana!", tv.getText());
    }
}
