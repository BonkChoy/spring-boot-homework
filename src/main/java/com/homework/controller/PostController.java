package com.homework.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.homework.entity.Category;
import com.homework.entity.Post;
import com.homework.service.CategoryService;
import com.homework.utils.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
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

    @GetMapping("/user/post")
    public String getPost(){

        String id = req.getParameter("id");
        Post post = new Post();
        if(!StringUtils.isEmpty(id)) {
            post = postService.getById(Long.valueOf(id));
        }

        List<Category> categories = categoryService.list(new QueryWrapper<Category>().orderByDesc("order_num"));

        req.setAttribute("pid",id);
        req.setAttribute("post",post);
        req.setAttribute("categories",categories);

        return "post/add";
    }

    @ResponseBody
    @PostMapping("/user/post")
    public R posttPost(@Valid Post post, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return R.failed(bindingResult.getFieldError().getDefaultMessage());
        }

        if(post.getId() == null) {
            post.setUserId(getProfileId());

            post.setModified(new Date());
            post.setCreated(new Date());
            post.setCommentCount(0);
            post.setEditMode(Constant.EDIT_HTML_MODEL);
            post.setLevel(0);
            post.setRecommend(false);
            post.setViewCount(0);
            post.setVoteDown(0);
            post.setVoteUp(0);
            post.setStatus(Constant.NORMAL_STATUS);
        } else {

            Post tempPost = postService.getById(post.getId());
            if(tempPost.getUserId() != getProfileId()) {
                return R.failed("不是自己的帖子");
            }
        }

        postService.saveOrUpdate(post);

        //TODO 给所有订阅的人发送消息

        return R.ok(post.getId());
    }


}