package com.qunar.campus.spring.tutorial.scope.thread.safe;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Description: UnThreadSafeBean
 *
 * 该类不是一个线程安全的类。
 * 所以我们使用prototype的模式,每一次有人获取该对象的时候就会生成一个新的对象
 *
 * @author yushen.ma
 * @version 2015-03-20 00:09
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UnThreadSafeBean {

    int count = 1;

    public void increase() {
        this.count++;
    }
}
