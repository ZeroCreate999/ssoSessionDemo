package com.example.sso.action;

import com.example.sso.Do.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.AbstractRequestAttributes;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

/**
 * @Author: fany
 * Date: 2020/9/10 9:55
 * @Description:用途
 */
@Component
public class GetSessionAction {


    public static  HttpServletRequest getCurrentRequest(){

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        HttpServletRequest request = requestAttributes.getRequest();
        return request;
    }


    public  User getCurrentUser(String username){

        User userDo = (User) getCurrentRequest().getSession().getAttribute(username);

        return userDo;
    }



}
