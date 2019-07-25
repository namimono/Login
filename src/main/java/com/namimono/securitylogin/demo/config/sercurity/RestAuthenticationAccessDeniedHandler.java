package com.namimono.securitylogin.demo.config.sercurity;

import com.namimono.securitylogin.demo.config.result.MyResult;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *  权限不足处理类
 * @author GaoLiwei
 * @date 2019/4/16
 */
public class RestAuthenticationAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        //登陆状态下，权限不足执行该方法
        response.setStatus(200);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter printWriter = response.getWriter();
        //封装要返回的信息
        MyResult result = MyResult.getUnAuthenticate();
        printWriter.write(result.toString());
        printWriter.flush();
    }
}
