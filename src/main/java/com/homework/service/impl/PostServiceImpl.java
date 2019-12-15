package com.homework.service.impl;

import com.homework.entity.Post;
import com.homework.mapper.PostMapper;
import com.homework.service.PostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lv-success
 * @since 2018-10-14
 */
@Service
public class PostServiceImpl extends BaseServiceImpl<PostMapper, Post> implements PostService {

    @Override
    public void join(Map<String, Object> stringObjectMap, String field) {

        if(stringObjectMap.get(field) == null){
            return;
        }

        String column = stringObjectMap.get(field).toString();
        Post post = this.getById(column);

        HashMap<String,Object> map = new HashMap<>();
        map.put("id",post.getId());
        map.put("title",post.getTitle());
        map.put("created",post.getCreated());

        stringObjectMap.put("post",map);
    }
}
