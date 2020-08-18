package com.imooc.testNG.groups;


import org.testng.annotations.Test;

@Test(groups = "teacher")
public class GroupOnClass3 {

    public void teacher1(){
        System.out.println("teacher1开始运行了");
    }

    public void teacher2(){
        System.out.println("teacher2开始运行了");
    }
}
