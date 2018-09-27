package com.udacity.gradle.builditbigger;


import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.udacity.gradle.builditbigger.aysncTask.JokeAsyncTask;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import static com.google.android.gms.common.internal.Asserts.fail;

@RunWith(AndroidJUnit4.class)
public class AsyncTaskTest {
    private Context context;

    @Before
    public void getContext(){
        context = InstrumentationRegistry.getContext();
    }
    @Test
    public void testAsync() {

        try {

            JokeAsyncTask jokeAsyncTask = new JokeAsyncTask(new JokeAsyncTask.AsyncResponse() {
                @Override
                public void processFinish(String output) {
                }
            });
            jokeAsyncTask.execute();
            String joke = jokeAsyncTask.get(30, TimeUnit.SECONDS);
            Log.i("TEST", joke);
            Assert.assertNotNull(joke);
        } catch (Exception e){
            fail("Test Failed : " + e.getMessage());
        }
    }

}


