package com.qunar.campus.spring.tutorial.homework.start.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.qunar.campus.spring.tutorial.homework.start.bean.Role;
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
    @Resource
    private UserManageService userManageService;
    private LoadingCache<Integer, Integer> viewCache;

    @Override
    public int userCount(Role role) {
        try {
            return viewCache.get(role.getId());
        } catch (ExecutionException e) {
            logger.error("error when get userCount from cache.", e.getCause());
            return 0;
        }
    }

    @PostConstruct
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

    @PreDestroy
    public void destroy() {
        //  destroy
    }

    //什么时候refresh？
    public void refresh(Integer roleId) {
        viewCache.refresh(roleId);
    }
}
