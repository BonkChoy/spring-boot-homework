package com.homework.service;

import com.homework.entity.Post;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lv-success
 * @since 2018-10-14
 */
public interface PostService extends BaseService<Post> {

    public void incrZsetValueAndUnionForLastWeekRank(Long postId);

    public void zUnionAndStoreLast7DaysForLastWeekRank();

    public void initIndexWeekRank();
}
