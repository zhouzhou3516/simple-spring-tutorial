package com.qunar.campus.spring.tiny.context;

/**
 * Description: InitializingBean
 *
 * 另外一个生命周期接口
 *
 * @author yushen.ma
 * @version 2015-03-22 16:59
 */
public interface InitializingBean {

    void afterPropertiesSet() throws Exception;

}