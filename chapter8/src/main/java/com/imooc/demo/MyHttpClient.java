package com.imooc.demo;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;

public class MyHttpClient {

    @Test
    public void firstHttpClient() throws IOException {

        String result;
        //new一个get方法
        HttpGet httpGet = new HttpGet("https://www.baidu.com/");
        //执行get方法
        HttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(httpGet);
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
    }
}
