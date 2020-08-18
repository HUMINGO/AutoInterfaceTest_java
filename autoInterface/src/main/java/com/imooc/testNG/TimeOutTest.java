package com.imooc.testNG;

import org.testng.annotations.Test;

public class TimeOutTest {

    @Test(timeOut = 3000)
    public void testTimeOutSuccess() throws InterruptedException {
        Thread.sleep(2000);
    }
}
