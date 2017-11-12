package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.widget.Button;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

import static android.support.v4.content.ContextCompat.startActivity;
import static org.junit.Assert.*;

/**
 * Created by user on 12/11/2017.
 */
@RunWith(AndroidJUnit4.class)
public class EndpointsAsyncTaskTest {

    Context context;



    // https://stackoverflow.com/questions/2321829/android-asynctask-testing-with-android-test-framework
    @Test
    public final void testAsync() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        context = InstrumentationRegistry.getContext();

        EndpointsAsyncTask testTask = new EndpointsAsyncTask() {
            @Override
            protected void onPostExecute(String result) {
                assertNotNull(result);
                if (result != null){
                    assertTrue(result.length() > 0);
                    latch.countDown();
                }
            }
        };
        testTask.execute(context);
        latch.await();


    }
}