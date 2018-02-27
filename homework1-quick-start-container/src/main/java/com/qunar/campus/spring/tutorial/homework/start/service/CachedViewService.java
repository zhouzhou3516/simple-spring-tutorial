package com.qunar.campus.spring.tutorial.homework.start.service;

import com.qunar.campus.spring.tutorial.homework.start.bean.Role;

/**
 * Description: CachedViewService
 *
 * 附加题: 缓存视图
 *
 * Tips: 实现该类可能会用到spring init callbacks
 * InitializingBean or PostConstruct
 *
 * @author yushen.ma
 * @version 2015-03-30 11:37
 */
public interface CachedViewService {

    /**
     * 利用UserManageService中的查询某种类型的用户的数量
     * 请使用guava cache进行结果缓存，或者，自行实现缓存
     *
     * Tips 缓存时间60s
     *
     * @param role role
     * @return int
     */
    int userCount(Role role);

}
