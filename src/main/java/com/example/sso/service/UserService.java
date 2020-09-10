package com.example.sso.service;

import com.example.sso.Do.User;
import com.example.sso.action.GetSessionAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: fany
 * Date: 2020/9/10 9:49
 * @Description:用途
 */
@Service
public class UserService {

    @Autowired
    GetSessionAction getSessionAction;

    public String doCall(){
        User admin = getSessionAction.getCurrentUser("admin");
        return "service层当前登录的用户信息是"+admin.toString();

    }

}
