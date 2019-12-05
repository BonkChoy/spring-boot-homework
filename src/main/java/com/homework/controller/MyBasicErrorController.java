package com.homework.controller;

import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MyBasicErrorController {

    /**
    * 定义500的ModelAndView
    * @param request
    * @param response
    * @return
    */

    @RequestMapping(produces = "text/html",value = "/500")
    public ModelAndView errorHtml500(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("msg","自定义错误信息");
        return new ModelAndView("error/500", model);
    }

    @RequestMapping(produces = "text/html",value = "/404")
    public String errorHtml404(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("msg","自定义错误信息");
        //return new ModelAndView("error/404", model);
        return "error/404";
    }

    /**
    * 定义500的错误JSON信息
    * @param request
    * @return
    */

    @RequestMapping(value = "/500")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> error500(HttpServletRequest request) {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("msg","自定义错误信息");
        HttpStatus status = HttpStatus.NOT_FOUND;
        return new ResponseEntity<Map<String, Object>>(model, status);
    }
}