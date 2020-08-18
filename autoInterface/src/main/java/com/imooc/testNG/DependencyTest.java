package com.imooc.testNG;

import org.testng.annotations.Test;

public class DependencyTest {

    /**
     * 写两个测试用例，test2的运行依赖test1
     */
    @Test
    public void test1(){
        System.out.println("登陆成功");
        int a = 1 / 0;
    }

    @Test(dependsOnMethods = {"test1"})
    public void test2(){
        System.out.println("支付成功");
    }
}
