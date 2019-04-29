package com.homework.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.homework.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.homework.shiro.AccountProfile;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lv-success
 * @since 2018-10-14
 */
public interface UserService extends BaseService<User> {

    /**
     * 给关联用户的分页结果添加用户信息
     * @param pageData
     * @param linkfield
     */
    //void join(IPage<Map<String,Object>> pageData, String linkfield);

    /**
     * 用于用户登录
     * AccountProfile是有用户基本信息的类，包括私信、通知数量，头像等
     * @param email
     * @param password
     * @return
     */
    AccountProfile login(String email, String password);

    /**
     * 注册
     * @param user
     * @return
     */
    R register(User user);

    R register(User user, String repass);
}
