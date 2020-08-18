package com.imooc.testNG.suite;

import org.testng.annotations.Test;

public class PayTest {

    @Test
    public void pay(){
        System.out.println("支付宝支付成功");
    }

    @Test(enabled = false)
    public void ignore(){
        System.out.println("忽略测试执行");
    }
}
