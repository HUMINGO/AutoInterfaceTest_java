package com.imooc.testNG.groups;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

public class GroupOnMethod {

    @Test(groups = "server")
    public void test1(){
        System.out.println("server组的测试方法1");
    }

    @Test(groups = "server")
    public void test2(){
        System.out.println("server组的测试方法2");
    }

    @BeforeGroups(groups = "server")
    public void beforeGroup(){
        System.out.println("server组运行前执行的方法");
    }

    @AfterGroups(groups = "server")
    public void afterGroup(){
        System.out.println("server组运行后执行的方法");
    }


}
