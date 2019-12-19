package com.homework.service.impl;

import com.homework.entity.Comment;
import com.homework.mapper.CommentMapper;
import com.homework.service.CommentService;
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
public class CommentServiceImpl extends BaseServiceImpl<CommentMapper, Comment> implements CommentService {

    @Override
    public void join(Map<String, Object> stringObjectMap, String field) {

        if(stringObjectMap.get(field) == null){
            return;
        }

        String column = stringObjectMap.get(field).toString();
        Comment comment = this.getById(column);

        HashMap<String,Object> map = new HashMap<>();
        map.put("id",comment.getId());
        map.put("content",comment.getContent());
        map.put("created",comment.getCreated());

        stringObjectMap.put("comment",map);
    }
}
