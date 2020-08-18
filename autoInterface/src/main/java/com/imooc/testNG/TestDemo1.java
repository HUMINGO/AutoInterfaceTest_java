package com.imooc.testNG;

import org.testng.annotations.*;

public class TestDemo1 {

    @Test
    public void test1(){
        System.out.println("Test:this is a test");
    }

    @BeforeMethod
    public void beforeMethod(){
        System.out.println("beforeMethod在测试方法之前运行");
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("afterMethod在测试方法之后运行");
    }

    @BeforeClass
    public void beforeClass(){
        System.out.println("beforeClass:在类运行之前执行");
    }

    @AfterClass
    public void afterClass(){
        System.out.println("afterClass:在类运行之后执行");
    }

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("beforeSuite:在测试套件之前运行");
    }

    @AfterSuite
    public void afterSuite(){
        System.out.println("afterSuite:在测试套件之后运行");
    }

}
