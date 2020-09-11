package com.imooc.myTestMethod;

import com.imooc.config.TestConfig;
import com.imooc.model.User2;
import com.imooc.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class GetUserListTest {

    @Test(dependsOnGroups = {"loginTrue"})
    public void getUserList() throws IOException {
        //获取到预期结果
        List<User2> user2List = DatabaseUtil.getSqlSession().selectList("getUserList", "tom");
        JSONArray expectResult = new JSONArray(user2List);

        //调用接口火球到实际结果
        JSONArray resultJson = getJsonResult("tom");
        Assert.assertEquals(expectResult.length(), resultJson.length());
    }

    private JSONArray getJsonResult(String tom) throws IOException {
        HttpPost post = new HttpPost(TestConfig.getUserListUrl);
        post.setHeader("content-type","application/json");
        //存入cookie
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);
        //传入参数
        JSONObject params = new JSONObject();
        params.put("username", "tom");
        StringEntity entity = new StringEntity(params.toString(),"utf-8");
        post.setEntity(entity);
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        String result = EntityUtils.toString(response.getEntity(), "utf-8");
        JSONArray resultJson = new JSONArray(result);
        return resultJson;
    }
}
