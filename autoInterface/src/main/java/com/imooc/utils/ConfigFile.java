package com.imooc.utils;

import com.imooc.model.InterfaceName;

import java.util.Locale;
import java.util.ResourceBundle;

public class ConfigFile {

    private static ResourceBundle bundle = ResourceBundle.getBundle("application", Locale.CHINA);

    /**
     * 写一个获取测试地址的方法
     * @param name
     */
    public static String getTestUrl(InterfaceName name){
        String url = bundle.getString("test.url");
        String uri = "";
        if(name == InterfaceName.ADD){
            uri = bundle.getString("user.test.add");
        }else if(name == InterfaceName.LOGIN){
            uri = bundle.getString("user.test.login");
        }else if(name == InterfaceName.GETUSERLIST){
            uri = bundle.getString("user.test.getUserList");
        }
        String testUrl = url + uri;
        return testUrl;
    }
}
