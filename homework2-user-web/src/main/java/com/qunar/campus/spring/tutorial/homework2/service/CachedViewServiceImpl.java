package com.qunar.campus.spring.tutorial.homework2.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.qunar.campus.spring.tutorial.homework2.bean.Role;
import com.qunar.campus.spring.tutorial.homework2.dao.RoleDao;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by liqingzhou on 17/8/1.
 */
@Service
public class CachedViewServiceImpl implements CachedViewService {


    private Logger logger = LoggerFactory.getLogger(getClass());

    private LoadingCache<Integer, Integer> userCountCache;
    private LoadingCache<Integer, Role> roleCache;
    @Resource
    private UserManagerService userManagerService;
    @Resource
    private RoleDao roleDao;

    @Override
    public int userCount(Role role) {
        try {
            return userCountCache.get(role.getId());
        } catch (ExecutionException e) {
            logger.error("error when get userCount from cache.", e.getCause());
            return 0;
        }
    }

    @PostConstruct
    public void init() {
        userCountCache = CacheBuilder.newBuilder()
                .expireAfterWrite(60, TimeUnit.SECONDS).maximumSize(20)
                .build(new CacheLoader<Integer, Integer>() {
                    @Override
                    public Integer load(Integer roleId) throws Exception {
                        Role role = new Role();
                        role.setId(roleId);
                        return userManagerService.userCount(role);
                    }
                });

        roleCache = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.HOURS).maximumSize(20)
                .build(
                        new CacheLoader<Integer, Role>() {
                            @Override
                            public Role load(Integer id) throws Exception {
                                return roleDao.selectRole(id);
                            }
                        });
    }

    @Override
    public Role getRole(int id) {
        try {
            return roleCache.get(id);
        } catch (ExecutionException e) {
            logger.error("error when get role from cache.", e.getCause());
            return null;
        }
    }

    @PreDestroy
    public void destroy() {
        //  destroy
    }

}
