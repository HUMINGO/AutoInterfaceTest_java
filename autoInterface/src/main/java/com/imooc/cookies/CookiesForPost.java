package com.imooc.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.HttpCookie;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import org.json.JSONObject;

public class CookiesForPost {

    private String url;
    private ResourceBundle bundle;
    private CookieStore cookieStore;

    @BeforeTest
    public void beforeTest(){
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        url = bundle.getString("test.url");
    }

    @Test
    public void testGetCookies() throws IOException {
        //从配置文件中获取到url
        String uri = bundle.getString("get.uri");
        String testUrl = this.url + uri;
        //获取到httpGet请求对象
        HttpGet httpGet = new HttpGet(testUrl);
        //获取到执行get请求的client对象
        DefaultHttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(httpGet);
        //从response中获取到cookies
        this.cookieStore = client.getCookieStore();

    }

    @Test(dependsOnMethods = {"testGetCookies"})
    public void postWithCookies() throws IOException {
        String result;
        String uri = bundle.getString("test.post.cookies.uri");
        String url = this.url + uri;
        HttpPost post = new HttpPost(url);

        DefaultHttpClient client = new DefaultHttpClient();
        //设置请求头信息
        post.setHeader("content-type","application/json");
        //设置cookies

        //传入参数
        JSONObject params = new JSONObject();
        params.put("username","jack");
        params.put("age","23");
        StringEntity entity = new StringEntity(params.toString(),"utf-8");
        post.setEntity(entity);

        //设置cookie
        client.setCookieStore(this.cookieStore);
        //获取响应结果
        HttpResponse response = client.execute(post);
        //处理结果
        result = EntityUtils.toString(response.getEntity());
        System.out.println(result);
        //将返回的结果转化为json对象
        JSONObject resultJson = new JSONObject(result);
        //获取到结果值
        String success = (String) resultJson.get("jack");
        String status = (String) resultJson.get("status");
        Assert.assertEquals("success",success);
        Assert.assertEquals("1",status);
    }
}
