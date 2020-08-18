package com.imooc.testNG;

import org.testng.annotations.Test;

public class ExpectedException {

    /**
     * 这是一个会执行失败的测试异常
     */
    @Test(expectedExceptions = RuntimeException.class)
    public void expectedRuntimeException(){
        System.out.println("这是一个会执行失败的测试异常");
    }


    @Test(expectedExceptions = RuntimeException.class)
    public void expectedRuntimeException2(){
        System.out.println("这是一个会执行成功的测试异常");
        throw new RuntimeException();
    }
}
