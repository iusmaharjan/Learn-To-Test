package com.ayushmaharjan.learning.learntotest;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.concurrent.CountDownLatch;

public class HasGlobalTimeout {
    public static String log;
    private final CountDownLatch latch = new CountDownLatch(1);

    @Rule
    public Timeout globalTimeout = Timeout.seconds(10); // 10 seconds max per method tested

    @Test(timeout = 10000)
    public void testSleepForTooLong() throws Exception {
        log += "ran1";
        Thread.sleep(5_000); // sleep for 100 seconds
    }

    @Ignore
    @Test
    public void testBlockForever() throws Exception {
        log += "ran2";
        latch.await(); // will block
    }
}