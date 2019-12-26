package com.homework.controller;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.homework.entity.Post;
import com.homework.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@Controller
public class UserController extends BaseController{

    @RequestMapping("/u/{id}")
    public String home(@PathVariable Long id) {

        User user = userService.getById(id);
        user.setPassword(null);

        //30天内容的文章
        Date date30Before = DateUtil.offsetDay(new Date(), -30).toJdkDate();
        List<Post> posts = postService.list(new QueryWrapper<Post>()
                .eq("user_id", id)
                .ge("created", date30Before)
                .orderByDesc("created"));

        //TODO 动作记录

        req.setAttribute("user", user);
        req.setAttribute("posts", posts);

        return "user/home";
    }
}
