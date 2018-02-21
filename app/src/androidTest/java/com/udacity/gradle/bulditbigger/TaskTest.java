package com.udacity.gradle.bulditbigger;

import android.content.Intent;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;
import android.test.AndroidTestCase;
import android.test.mock.MockContext;

import com.udacity.gradle.builditbigger.task.EndpointsAsyncTask;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by vinicius.rocha on 2/21/18.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class TaskTest{

    @Test
    public void testEndpointsTask() throws InterruptedException,
            ExecutionException, TimeoutException {
        MockContext mockContext = new MockContext() {
            @Override
            public String getPackageName() {
                return "com.udacity.gradle.builditbigger";
            }

            @Override
            public void startActivity(Intent intent) {
                /*
                * Purposely intended to be empty to avoid
                * starting an activity with an intent
                * during testing, may be redundant
                * for this particular test
                 */
            }
        };

        String result = new EndpointsAsyncTask()
                .execute(mockContext)
                .get(20, TimeUnit.SECONDS);

        //Test for not null String
        assertNotNull(result);

        //Test for not empty String
        assertFalse(result.length() == 0);
    }
}
