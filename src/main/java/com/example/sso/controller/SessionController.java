package com.example.sso.controller;

import com.example.sso.Do.User;
import com.example.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
/**
 * @Author: fany
 * Date: 2020/9/9 16:28
 * @Description:用途
 */
@RestController
public class SessionController {



    @PostMapping("login")
    public  String login(@RequestBody User user, HttpSession session){
        if ("admin".equals(user.getUsername())&&"admin".equals(user.getPassword())){
            session.setAttribute(user.getUsername(),user);
            return "登录成功";
        }
    return "登录失败";
    };


    @PostMapping("verify")
    public  String verify(@RequestBody User user, HttpSession httpSession){
        User attribute = (User) httpSession.getAttribute(user.getUsername());
        if (attribute==null){
            return  "用户未登录";
        }
        return "用户已登录";
    }

    @PostMapping("escLogin")
    public String escLogin(@RequestBody User user,HttpSession httpSession){
        String username = user.getUsername();
        if (username!=null){
            httpSession.removeAttribute(username);
            return "清除成功";
        }
        return "无此用户,请先登录";
    }

    @PostMapping("api1")
    public String api1(HttpSession session){
        return "获取到api1返回数据";
    }

    @PostMapping("api2")
    public String api2(HttpSession session){
        return "获取到api2返回数据";
    }


    @Autowired
    UserService userService;

    @GetMapping("noSessionServiceMethod")
    public String  noSessionServiceMethod(){
        String s = userService.doCall();
        if (StringUtils.isEmpty(s)){
            return "接口无返回信息";
        }
        return s;
    }
}
