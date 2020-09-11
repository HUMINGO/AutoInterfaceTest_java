package com.imooc.test;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class ExtentTest {

    @Test
    public void test1(){
        Assert.assertEquals(2,3);
    }

    @Test
    public void test2(){
        Assert.assertEquals(1,1);
    }

    @Test
    public void logDemo(){
        Reporter.log("这是我们自定义的日志信息");
        throw new RuntimeException("这是自己定的运行异常");
    }
}
