package com.imooc.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class CookiesForGet {

    private String url;
    private ResourceBundle bundle;
    private CookieStore store;

    @BeforeTest
    public void beforeTest(){
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        url = bundle.getString("test.url");
    }

    @Test
    public void testCookies() throws IOException {
        //接受结果
        String result;

        String uri = bundle.getString("get.uri");
        //拼接get请求的url
        String testUrl = this.url+uri;
        //new一个get请求对象
        HttpGet get = new HttpGet(testUrl);
        DefaultHttpClient client = new DefaultHttpClient();
        //客户端执行get请求
        HttpResponse response = client.execute(get);
        //获取到请求结果
        result = EntityUtils.toString(response.getEntity());
        System.out.println(result);
        //获取cookies信息
        store = client.getCookieStore();
        List<Cookie> cookies = store.getCookies();
        for (Cookie cookie:cookies){
            System.out.println(cookie.getName()+";"+cookie.getValue());
        }
    }

    /**
     * 携带cookies的get请求
     */
    @Test(dependsOnMethods = {"testCookies"}) //依赖testCookies方法的执行，需要从这个方法中获取到cookies
    public void testWithCooike() throws IOException {
        //获取到uri
        String uri = bundle.getString("test.get.cookies.uri");
        String testUrl = this.url + uri;
        HttpGet get = new HttpGet(testUrl);
        DefaultHttpClient client = new DefaultHttpClient();
        //客户端在请求的时候，设置请求的cookies
        client.setCookieStore(this.store);
        HttpResponse response = client.execute(get);
        //获取到响应状态码
        int status = response.getStatusLine().getStatusCode();
        System.out.println("响应状态码"+status);
        if(status==200){
            String result = EntityUtils.toString(response.getEntity());
            System.out.println(result);
        }

    }
}
