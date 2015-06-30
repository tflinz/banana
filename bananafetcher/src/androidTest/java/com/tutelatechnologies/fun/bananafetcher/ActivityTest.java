package com.tutelatechnologies.fun.bananafetcher;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Tony on 26/06/2015.
 */
public class ActivityTest extends ActivityInstrumentationTestCase2<TestActivity> {

    public ActivityTest() {
        super(TestActivity.class);
    }

    /**
     * Launch a task on the main UI thread.
     */
    public void testRunningOnMainThread() throws Throwable {
        AtomicInteger toWatch = new AtomicInteger(0);
        Runnable testRunnable = ApplicationTest.getTestTask(toWatch);
        runTestOnUiThread(testRunnable);
        while (toWatch.get() < 1) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        assertEquals(1, toWatch.get());
    }

    /**
     * Do some UI work on the ui thread.
     *
     * @throws Throwable
     */
    public void testTextViews() throws Throwable {
        AtomicInteger toWatch = new AtomicInteger(0);
        runTestOnUiThread(getTextChangerRunnable(toWatch));
        while (toWatch.get() < 1) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        assertEquals(1, toWatch.get());
    }

    private Runnable getTextChangerRunnable(final AtomicInteger toWatch) {
        final TextView tv1 = (TextView) getActivity().findViewById(R.id.testtext);
        final TextView tv2 = (TextView) getActivity().findViewById(R.id.testtext2);
        return new Runnable() {
            @Override
            public void run() {
                tv1.setText("This is some new text.");
                tv2.setText("Some different other text.");
                assertEquals("This is some new text.", tv1.getText());
                assertEquals("Some different other text.", tv2.getText());
                ApplicationTest.getTestTask(toWatch).run();
            }
        };
    }
}
