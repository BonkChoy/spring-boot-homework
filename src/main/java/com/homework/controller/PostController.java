package com.homework.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.homework.entity.Post;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class PostController extends  BaseController{

    @GetMapping("/post/{id}")
    public  String index(@PathVariable Long id) {

        Map<String,Object> post = postService.getMap(new QueryWrapper<Post>().eq("id",id));
        Assert.notNull(post,"该文章已被删除！");
        userService.join(post,"user_id");
        req.setAttribute("post",post);

        return "post/index";
    }

    @GetMapping("/category/{id}")
    public String category(@PathVariable Long id,
                           @RequestParam(defaultValue = "1")Integer current,
                           @RequestParam(defaultValue = "10")Integer PageSize){

        Page<Post> page = new Page<>();
        page.setCurrent(current);
        page.setSize(PageSize);

        IPage<Map<String, Object>> pageData = postService.pageMaps(page, new QueryWrapper<Post>().eq("category_id",id).orderByDesc("created"));
        //添加关联的用户信息
        userService.join(pageData, "user_id");

        //添加关联的分类信息
        categoryService.join(pageData,"category_id");

        req.setAttribute("pageData", pageData);
        req.setAttribute("currentCategoryId", id);
        return "post/category";
    }
}