package com.imooc.myTestMethod;

import com.imooc.config.TestConfig;
import com.imooc.model.InterfaceName;
import com.imooc.model.LoginCase;
import com.imooc.utils.ConfigFile;
import com.imooc.utils.DatabaseUtil;
import kotlin.random.Random;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTestCase {

    private CookieStore cookieStore;

    /**
     * 在测试之前需要做的事情，获取到所有的测试地址，和httpclient
     */
    @BeforeTest(groups = "loginTrue", description = "测试之前的准备工作，获取到httpclient以及测试地址")
    public void beforeTest(){
        TestConfig.defaultHttpClient = new DefaultHttpClient();
        TestConfig.loginUrl = ConfigFile.getTestUrl(InterfaceName.LOGIN);
        TestConfig.addUserUrl = ConfigFile.getTestUrl(InterfaceName.ADD);
        TestConfig.getUserListUrl = ConfigFile.getTestUrl(InterfaceName.GETUSERLIST);
    }

    @Test(groups = "loginTrue", description = "测试登陆成功的用例")
    public void testLoginCase() throws IOException {
        //获取到预期结果
        SqlSession session = DatabaseUtil.getSqlSession();
        LoginCase loginCase = session.selectOne("selectOne", 1);
        System.out.println(loginCase);
        //调用接口
        HttpPost post = new HttpPost(TestConfig.loginUrl);
        DefaultHttpClient client = TestConfig.defaultHttpClient;
        post.setHeader("content-type","application/json");
        JSONObject params = new JSONObject();
        params.put("username","tom");
        params.put("password","123456");
        StringEntity entity = new StringEntity(params.toString(), "utf-8");
        post.setEntity(entity);
        HttpResponse response = client.execute(post);
        String result = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(result);

        //获取到cookies，存储到全局变量中
        TestConfig.store = TestConfig.defaultHttpClient.getCookieStore();
    }


}
