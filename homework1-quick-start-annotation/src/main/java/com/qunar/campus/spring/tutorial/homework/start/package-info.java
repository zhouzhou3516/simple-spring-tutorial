/**
 * 作业题目：
 *
 * 该作业一共分为3个部分。
 * 1. 基于spring ioc，自行实现所有已经定义的接口的所有方法。这一部分中，只能使用xml配置的办法，
 * 并且在test中自行测试自己的所有方法。
 *
 * 2. 在项目中新建一个模块，不论你是copy还是怎么样，将1中的实现，改写成使用注解的配置。
 * 并且在test中自行测试自己的所有方法。
 * 注：除了必要的dataSource以及数据库的配置以外。其他都只使用注解的方式注入
 *
 * 3. 将以上项目修改为不使用spring的方式(不论你自己实现容器,还是new对象)，并且在test中自行测试所有方法
 *
 * Tips
 * 1. 针对不同的表实现不同的dao对象(使用JDBCTemplate)，具体参考case11
 * 2. 数据库请使用类似于case11中的本地数据库,
 * 3. 请使用spring-test
 * 4. 每一个方法上还有具体的要求
 * 5. bean请自行实现setter,getter,Serializable
 * 6. 工具类请自行封装成util(静态方法或者单例类)
 *
 * 评判标准:
 * 1. 请不要使用面向巧合编程的技巧。搞清楚你每一个配置的标签，引入的pom依赖的含义,不要有一些奇奇怪怪的东西
 * 2. 请注意适当拆分service和component, 每个个函数请不要超过30行。理解Service和Component标签
 * 3. 请不要使用C或C++编程风格,自行揣摩什么叫C编程风格
 * 4. 请不要让你的代码你含有不必要的黄色的warn
 * 5. 请尽可能地“分层”、“设计”、“抽象”、“bala”, 在没学会设计之前，不要妄谈“过度设计”
 *
 *
 * Description: package-info

 * @author yushen.ma
 * @version 2015-03-30 10:34
 */
package com.qunar.campus.spring.tutorial.homework.start;