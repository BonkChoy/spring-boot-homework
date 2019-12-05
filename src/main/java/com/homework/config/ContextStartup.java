package com.homework.config;

import com.homework.entity.Category;
import com.homework.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.util.Iterator;
import java.util.List;

@Slf4j
@Order(1000)
@Component
public class ContextStartup implements ServletContextAware,ApplicationRunner{

    private ServletContext servletContext;
    @Autowired
    CategoryService categoryService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        servletContext.setAttribute("categorys",categoryService.list(null));
        log.info("ContextStartup------------>加载categorys");
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}