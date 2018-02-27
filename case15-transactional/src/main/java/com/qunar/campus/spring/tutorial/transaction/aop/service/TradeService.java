package com.qunar.campus.spring.tutorial.transaction.aop.service;

import com.qunar.campus.spring.tutorial.bean.Money;
import com.qunar.campus.spring.tutorial.transaction.aop.bean.Account;
import com.qunar.campus.spring.tutorial.transaction.aop.dao.AccountDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Description: TradeService
 *
 * Spring Transactional文档
 * @see http://docs.spring.io/spring/docs/4.1.5.RELEASE/spring-framework-reference/html/transaction.html#transaction-declarative
 *
 * FAQ 1: Transactional标签只能用于public方法, 主要是受限于CGLib

   When using proxies, you should apply the @Transactional annotation only to methods with public visibility.
   If you do annotate protected, private or package-visible methods with the @Transactional annotation,
   no error is raised, but the annotated method does not exhibit the configured transactional settings.
   Consider the use of AspectJ (see below) if you need to annotate non-public methods.

 * FAQ 2: 使用该标签对生命周期有要求 --->  我认为通常情况下标注在service的方法上即可

   In proxy mode (which is the default), only external method calls coming in through the proxy are intercepted.
   This means that self-invocation, in effect, a method within the target object calling another method of the target object,
   will not lead to an actual transaction at runtime even if the invoked method is marked with @Transactional.
   Also, the proxy must be fully initialized to provide the expected behaviour so you should not
   rely on this feature in your initialization code, i.e. @PostConstruct.

 * FAQ3 : Transactional 标签是 Inherited， 也就是子类方法也会默认继承

 * FAQ4: 这个注解只会在当前application-context中生效
   @EnableTransactionManagement and <tx:annotation-driven/> only looks for
   @Transactional on beans in the same application context they are defined in.
   This means that, if you put annotation driven configuration in a WebApplicationContext
   for a DispatcherServlet, it only checks for @Transactional beans in your controllers,
    and not your services

 * FAQ5: 默认情况下Transactional标签表现的特性
   1. Propagation setting is PROPAGATION_REQUIRED.
   2. Isolation level is ISOLATION_DEFAULT.
   3. Transaction is read/write.
   4. Transaction timeout defaults to the default timeout of the underlying transaction system, or to none if timeouts are not supported.
   5. important -----> Any RuntimeException triggers rollback, and any checked Exception does not. ---> why?

   FAQ 6: 虽然在Controller上使用Transactional是可以的, 但不要这样做

   In some cases a controller may need to be decorated with an AOP proxy at runtime.
   One example is if you choose to have @Transactional annotations directly on the controller.
   When this is the case, for controllers specifically, we recommend using class-based proxying.
   This is typically the default choice with controllers. However if a controller must implement
   an interface that is not a Spring Context callback (e.g. InitializingBean, *Aware, etc),
   you may need to explicitly configure class-based proxying. For example with <tx:annotation-driven />,
   change to <tx:annotation-driven proxy-target-class="true" />.

   this is because only the service layer in the application has the logic
   needed to identify the scope of a database/business transaction.
   The controller and persistence layer by design can't/shouldn't know the scope of a transaction.

 *
 *
 * @author yushen.ma
 * @version 2015-03-25 22:10
 */
@Service
public class TradeService {

    @Resource
    AccountDao accountDao;

    @Transactional
    public void createAccount(Account account) {
        accountDao.create(account);
    }

    @Transactional
    public void transfers(Account from, Account to, Money money, boolean throwable) {
        from.setBalance(from.getBalance().minus(money));
        to.setBalance(to.getBalance().plus(money));
        accountDao.update(from);
        if (throwable) {
            throw new RuntimeException("a simple test");
        }
        accountDao.update(to);
    }

    public Account find(int i) {
        return accountDao.find(i);
    }

}
