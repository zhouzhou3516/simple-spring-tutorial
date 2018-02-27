package com.qunar.campus.spring.tutorial.homework.start.service.handler;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author liqingzhou on 17/9/14
 */
@Service
public class MyServiceImpl {
    @Resource
    private List<Handler > handlerList;


}
