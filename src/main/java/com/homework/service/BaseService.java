package com.homework.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * @author hua
 * @date 2019/4/14 15:31
 */
public interface BaseService<T> extends IService<T> {

    void join(Map<String,Object> stringObjectMap, String field);

    void join(List<Map<String, Object>> datas, String field);

    void join(IPage<Map<String,Object>> pageData, String field);
}