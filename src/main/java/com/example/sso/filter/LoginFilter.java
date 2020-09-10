package com.example.sso.filter;

import com.example.sso.Do.User;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: fany
 * Date: 2020/9/9 18:44
 * @Description:登录拦截器
 */
@Component
public class LoginFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        //登录页面放行
        if ("/login".equals(httpServletRequest.getRequestURI())){
            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }


        //其他接口需要调用拦截器
        User userDo = (User) httpServletRequest.getSession().getAttribute("admin");
        //已登录
        if (userDo!=null){
            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }

        //未登录
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter writer = httpServletResponse.getWriter();
        writer.write("请先登录");
        writer.flush();
        writer.close();

    }
}
