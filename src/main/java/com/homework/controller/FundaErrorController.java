package com.homework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class FundaErrorController implements ErrorController {
    private static final String PATH = "/error";

    @Autowired
    private ErrorAttributes errorAttributes;

    @Override
    public String getErrorPath() {
        return PATH;
    }


    @RequestMapping
    public String doHandleError(HttpServletRequest request) {
        WebRequest requestAttributes = new ServletWebRequest(request);
        Map<String,Object> errorAttributesData = errorAttributes.getErrorAttributes(requestAttributes,true);

        Integer status=(Integer)errorAttributesData.get("status");  //状态码
        String path=(String)errorAttributesData.get("path");        //请求路径
        String messageFound=(String)errorAttributesData.get("message");   //异常信息

        return "redirect:/" + status;
    }
}