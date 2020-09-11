package com.imooc.myTestMethod;

import com.imooc.utils.DatabaseUtil;
import org.testng.annotations.Test;

import java.io.IOException;

public class DeleteUserTestCase {

    @Test
    public void deleteUser() throws IOException {
        int num = DatabaseUtil.getSqlSession().delete("deleteOne");
        if(num > 0){
            System.out.println("success");
        }
    }
}
