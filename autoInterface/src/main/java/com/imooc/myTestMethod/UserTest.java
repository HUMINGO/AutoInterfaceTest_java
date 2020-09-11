package com.imooc.myTestMethod;

import okhttp3.*;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class UserTest {

//    测试地址
    private String url;
    private ResourceBundle bundle;
    private CookieStore cookieStore; //将cookies设置为全局变量
    private DefaultHttpClient client;

    /**
     * 在测试之前要完成的事情，获取到url
     */

    @BeforeTest
//    @Test
    public void beforeTest(){
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        url =bundle.getString("user.test.url");
        client = new DefaultHttpClient();

//        System.out.println(url);
    }


    @Test
    public void testLogin() throws IOException {
        //获取到测试的url
        String testUrl = url + bundle.getString("user.test.login");
        //new httpPost对象
        HttpPost post = new HttpPost(testUrl);
        //传入参数
        JSONObject params = new JSONObject();
        params.put("username", "jack");
        params.put("password", "aaabbb");
        StringEntity entity = new StringEntity(params.toString(),"utf-8");
        post.setEntity(entity);
        post.setHeader("content-type","application/json");
        //执行，然后获取到执行结果
        HttpResponse response = client.execute(post);
        this.cookieStore = client.getCookieStore();
        System.out.println(this.cookieStore);

        String result = EntityUtils.toString(response.getEntity());
//        System.out.println(result);
        Assert.assertEquals("登陆成功", result);

    }

    @Test
    public void testLoginFail() throws IOException {
        //获取到测试的url
        String testUrl = url + bundle.getString("user.test.login");
        //new httpPost对象
        HttpPost post = new HttpPost(testUrl);
        //传入参数
        JSONObject params = new JSONObject();
        params.put("username", "");
        params.put("password", "aaabbb");
        StringEntity entity = new StringEntity(params.toString(),"utf-8");
        post.setEntity(entity);
        post.setHeader("content-type","application/json");
        //执行，然后获取到执行结果
        HttpResponse response = client.execute(post);
        String result = EntityUtils.toString(response.getEntity());
//        System.out.println(result);
        Assert.assertEquals("登陆失败", result);

    }

    @Test(dependsOnMethods = {"testLogin"})
    public void testAddUser() throws IOException {
        String testUrl = url + bundle.getString("user.test.add");
        HttpPost post = new HttpPost(testUrl);
        //设置头信息
        post.setHeader("content-type","application/json");
        //传入参数
        JSONObject params = new JSONObject();
        params.put("username","wangwu");
        params.put("password","111111");
        params.put("age",34);
        StringEntity entity = new StringEntity(params.toString(), "utf-8");
        post.setEntity(entity);
        HttpResponse response = client.execute(post);
        String result = EntityUtils.toString(response.getEntity());
        System.out.println(result);
    }

/*    @Test
    public void testDeleteUser() throws IOException {
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        String testUrl = url + bundle.getString("user.test.deleteUser");
        HttpPost post = new HttpPost(testUrl);
        post.setHeader("content-type","application/json");
        //传入参数
//        JSONObject params = new JSONObject();
//        params.put("uid", 5);
//        StringEntity entity = new StringEntity(params.toString(), "utf-8");
//        post.setEntity(entity);
        List<NameValuePair> nvps = new ArrayList<>();
        nvps.add(new BasicNameValuePair("uid","8"));
        post.setEntity(new UrlEncodedFormEntity(nvps));
        HttpResponse response = client.execute(post);
        String result = EntityUtils.toString(response.getEntity());
        System.out.println(result);
    }*/

    @Test
    public void testDeleteUser() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("uid", "10")
                .build();
        Request request = new Request.Builder()
                .url("http://localhost:8080/deleteUserInfo")
                .method("POST", body)
                .addHeader("Cookie", "login=true")
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.message());
    }

}
