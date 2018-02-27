package com.qunar.campus.spring.tutorial.homework.start.service.handler;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author liqingzhou on 17/9/14
 */
@Order(1)
@Component
public class AHandler implements Handler {

    @Override
    public void handler() {
        System.out.println("HandlerA");
    }
}
