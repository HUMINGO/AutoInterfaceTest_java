package com.imooc.testNG;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class DataProviderTest {

    @Test(dataProvider = "data")
    public void Test1(String name,int age){
        System.out.println("name:"+name+";age"+age);
    }

    @DataProvider(name = "data")
    public Object[][] dataProvider() {

        Object[][] o = new Object[][]{
                {"jack",23},
                {"Tom",233}
        };

        return o;
    }

    @Test(dataProvider = "dataProvider")
    public void test2(String name,int age){
        System.out.println("name:"+name+";age:"+age);
    }

    @Test(dataProvider = "dataProvider")
    public void test3(String name,int age){
        System.out.println("name:"+name+";age:"+age);
    }

    @DataProvider(name = "dataProvider")
    public Object[][] paramProvider(Method method){
        Object[][] result = null;

        if(method.getName().equals("test2")){
            result = new Object[][]{
                    {"张三",23},
                    {"李四",21}
            };
        }else if(method.getName().equals("test3")){
            result = new Object[][]{
                    {"kkoo",23},
                    {"bbbb",21}
            };
        }
        return result;
    }
}
