package com.homework.service.impl;

import com.homework.entity.Category;
import com.homework.mapper.CategoryMapper;
import com.homework.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
public class CategoryServiceImpl extends BaseServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Override
    public void join(Map<String, Object> stringObjectMap, String field) {

        String column = stringObjectMap.get(field).toString();
        Category category = this.getById(column);

        if(!StringUtils.isEmpty(category)){
            Map map = new HashMap();
            map.put("id", category.getId());
            map.put("name", category.getName());
            map.put("icon", category.getIcon());

            stringObjectMap.put("category",map);
        }
    }
}
