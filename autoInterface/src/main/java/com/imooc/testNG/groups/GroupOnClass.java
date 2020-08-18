package com.imooc.testNG.groups;

import org.testng.annotations.Test;

@Test(groups = "student")
public class GroupOnClass {

    public void stu1(){
        System.out.println("stu1开始执行了");
    }

    public void stu2(){
        System.out.println("stu2开始执行了");
    }
}
