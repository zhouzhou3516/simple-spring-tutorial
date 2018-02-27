package com.qunar.campus.spring.tiny.jdk.proxy;

/**
 * Description: TrackingMethod
 *
 * 场景：
 * 这是我们的用户登录接口，突然有一天，根据系统监控显示，我们登录的时常居然点击后需要处理10秒钟。
 *
 * 于是，我们需要紧急监控该接口，但是该接口的实现类有150000000000个。其中包括，数据库UserService,
 * 缓存UserService, 以及各种第三方的Service.我们需要知道每一个实现的具体操作时间
 *
 * 怎么办呢？根据tiny6里面的postProcessor来处理？
 *
 * @author yushen.ma
 * @version 2015-03-20 13:42
 */
public interface UserService {

    void login();

}
