package com.namimono.securitylogin.demo.config.sercurity;


import com.namimono.securitylogin.demo.config.result.MyResult;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *  认证失败处理类
 * @author GaoLiwei
 * @date 2019/4/15
 */
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    /**
     * 实现AuthenticationEntryPoint的commence方法自定义校验不通过的方法
     *
     * @param request
     * @param response
     * @param e
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException {
        // 捕获AuthenticationException中的message，并封装成自定义异常抛出
        response.setStatus(200);
        response.setCharacterEncoding("utf-8");
        //header 起作用
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        //封装要返回的信息
        MyResult result = MyResult.getUnAuthenticate();
        PrintWriter printWriter = response.getWriter();
        printWriter.write(result.toString());
        printWriter.flush();
    }
}