package com.homework.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.homework.entity.User;
import com.homework.mapper.UserMapper;
import com.homework.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.homework.shiro.AccountProfile;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lv-success
 * @since 2018-10-14
 */
@Slf4j
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {

    @Override
    public void join(Map<String, Object> map, String field) {

        if(map == null || map.get(field) == null){
            return;
        }

        Map<String, Object> joinColumns = new HashMap<>();

        //字段的值
        String linkfieldValue = map.get(field).toString();

        User user = this.getById(linkfieldValue);

        joinColumns.put("username", user.getUsername());
        joinColumns.put("email", user.getEmail());
        joinColumns.put("avatar", user.getAvatar());
        joinColumns.put("id", user.getId());
        joinColumns.put("vipLevel", user.getVipLevel());

        map.put("author", joinColumns);
    }

    @Override
    public AccountProfile login(String email, String password) {
        log.info("------------>进入用户登录判断，获取用户信息步骤");

        User user = this.getOne(new QueryWrapper<User>().eq("email", email));
        if(user == null) {
            throw new UnknownAccountException("账户不存在");
        }

        if(!user.getPassword().equals(password)) {
            throw new IncorrectCredentialsException("密码错误");
        }

        //更新最后登录时间
        user.setLasted(new Date());
        user.updateById();

        AccountProfile profile = new AccountProfile();

        BeanUtil.copyProperties(user, profile);


        //把通知和私信数量查出来



        return profile;
    }

    @Override
    @Transactional
    public R register(User user) {

        return null;
    }

    @Override
    @Transactional
    public R register(User user, String repass) {

        if(user != null && repass != null){
            if(StringUtils.isEmpty(user.getEmail()) || StringUtils.isEmpty(user.getUsername())){
                return  R.failed("必填项不能为空！");
            }

            User po = this.getOne(new QueryWrapper<User>().eq("email",user.getEmail()));
            if(po != null){
                return  R.failed("该邮箱已被注册！");
            }

            if(!repass.equalsIgnoreCase(user.getPassword())){
                return  R.failed("两次密码输入不一致！");
            }

            String passMD5 = SecureUtil.md5(user.getPassword());

            po = new User();
            po.setEmail(user.getEmail());
            po.setPassword(passMD5);
            po.setCreated(new Date());
            po.setUsername(user.getUsername());
            po.setAvatar("/images/avatar/default.png");
            po.setPoint(0);

            return this.save(po) ? R.ok("注册成功！"):R.failed("注册失败！");

        }
        return R.failed("未知错误！");
    }
}
