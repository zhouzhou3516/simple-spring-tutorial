package com.qunar.campus.spring.tutorial.homework.start.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.qunar.campus.spring.tutorial.homework.start.bean.Role;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by liqingzhou on 17/8/1.
 */
public class CachedViewServiceImpl implements CachedViewService {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private UserManageService userManageService;
    private LoadingCache<Integer, Integer> viewCache;

    public CachedViewServiceImpl() {
    }

    @Override
    public int userCount(Role role) {
        try {
            return viewCache.get(role.getId());
        } catch (ExecutionException e) {
            logger.error("error when get userCount from cache.", e.getCause());
            return 0;
        }
    }

    public void init() {
        viewCache = CacheBuilder.newBuilder()
                .expireAfterWrite(60, TimeUnit.SECONDS).maximumSize(20)
                .build(new CacheLoader<Integer, Integer>() {
                    @Override
                    public Integer load(Integer roleId) throws Exception {
                        Role role = new Role();
                        role.setId(roleId);
                        return userManageService.userCount(role);
                    }
                });
    }

    public void destroy() {
        //  destroy
    }

    public UserManageService getUserManageService() {
        return userManageService;
    }

    public void setUserManageService(
            UserManageService userManageService) {
        this.userManageService = userManageService;
    }
}
