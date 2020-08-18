package com.imooc.testNG.groups;


import org.testng.annotations.Test;

@Test(groups = "student")
public class GroupOnClass2 {

    public void stu3(){
        System.out.println("stu3开始执行了");
    }

    public void stu4(){
        System.out.println("stu4开始执行了");
    }
}
