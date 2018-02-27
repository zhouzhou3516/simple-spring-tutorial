package com.qunar.campus.spring.tutorial.application.case2.auto.annotation;

import javax.annotation.Resource;

/**
 * Description: UserService
 *
 * @author yushen.ma
 * @version 2015-03-19 21:13
 */
public class UserService {

    /**
     * FAQ : 与@Resource标签类似的标签还有
     * 1. @Required
     * 2. @Autowired
     * 3. @Qualifier
     * 4. @Inject
     * 5. @Named
     *
     * 这些是基于不同的规范实现的标签
     * 他们有什么区别呢？ :P
     *
     * 提示：我建议，只使用Resource标签即可。
     * Resource标签，默认是按照名称来装配注入的，只有当找不到与名称匹配的bean才会按照类型来装配注入
     */
    @Resource
    private UserDao userDao;

    public String getUser() {
        return userDao.getUserName();
    }

}
