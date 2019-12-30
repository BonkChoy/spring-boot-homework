package com.homework.shiro;

/**
 * Created by vzengwenhua951014 on 2019-12-30.
 */

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.api.R;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.UserFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 异步请求登录过滤器
 */
public class AuthFilter extends UserFilter {

    @Override
    protected void redirectToLogin(ServletRequest servletRequest, ServletResponse response) throws IOException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String header = request.getHeader("X-Requested-With");

        if (header != null && "XMLHttpRequest".equals(header)) {
            Subject subject = SecurityUtils.getSubject();
            if (!subject.isAuthenticated()){
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().write(JSONUtil.toJsonStr(R.failed("请先登录！")));
            }else{
                super.redirectToLogin(servletRequest,response);            }
        }
    }
}
