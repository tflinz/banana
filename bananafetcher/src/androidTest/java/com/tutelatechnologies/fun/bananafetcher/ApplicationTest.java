package com.tutelatechnologies.fun.bananafetcher;

import android.app.Application;
import android.os.Handler;
import android.test.ApplicationTestCase;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }



    /**
     * Launch a task on the main UI thread.
     */
    public void testDoLooper(){
        AtomicInteger toWatch = new AtomicInteger(0);
        Runnable testRunnable  = getTestTask(toWatch);
        boolean res = new Handler(getContext().getMainLooper()).post(testRunnable);
        assertTrue("did the test run?",res);
        while(toWatch.get()<1){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        assertEquals(1,toWatch.get());
    }

    /**
     * Test task that increments the watch variable.
     * @return
     */
    static Runnable getTestTask(final AtomicInteger toWatch) {
        return new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                toWatch.incrementAndGet();
            }
        };
    }
}
