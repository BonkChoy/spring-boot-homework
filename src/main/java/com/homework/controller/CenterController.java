package com.homework.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.homework.entity.Post;
import com.homework.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Request;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/user")
public class CenterController extends BaseController{

    @GetMapping("/center")
    public String center(@RequestParam(defaultValue = "1") Integer current,@RequestParam(defaultValue = "10") Integer PageSize) {

        //定义分页对象，初始化分页查询相关参数
        Page<Post> page = new Page<>();
        page.setCurrent(current);
        page.setPages(PageSize );

        //定义查询组装器
        QueryWrapper<Post> wrapper = new QueryWrapper<Post>().eq("user_id",getProfileId()).orderByDesc("created");

        IPage<Map<String,Object>> pageData = postService.pageMaps(page,wrapper);
        req.setAttribute("pageData",pageData);
        log.info("-------------->进入个人中心");

        return "user/center";
    }

    @GetMapping("/setting")
    public String setting() {
        User user = userService.getById(getProfileId());
        user.setPassword(null);

        req.setAttribute("user", user);
        return "user/setting";
    }

    @ResponseBody
    @PostMapping("/message/nums")
    public Object getMessNums() {
        Map<String, Object> result = new HashMap<>();
        result.put("status", 0);
        result.put("count", 2);

        return result;
    }

}
