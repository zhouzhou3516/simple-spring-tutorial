/**
 * Description: package-info

 * @author yushen.ma
 * @version 2015-04-04 21:16
 *
 * 作业题目：
 *
 * 1. 将上次的作业应用成一个web程序。
 * 实现我所定义的UserController的所有方法。
 * 注意，在Controller层你需要检测前端传给你的参数是否合法。如果不合法的话应该怎么办呢
 *
 * 2. 使用上一节课实现的UserManageService作为Service层
 *
 * 3. 数据库你可以选择仍然使用上一节课中用到的H2内存数据库,或者使用你本地的mysql
 * 如果你使用mysql的话，请将建表语句放在resource目录下，方便我查看
 *
 * 4. 另外，这次需要保证事务，你需要配置事务管理器，并且使用Transactional标签，会修改一点点之前的UserManagement
 *
 * Tips:
 * 1. 分离Controller层的组件，和Service层的组件。
 * 2. 使用默认的Dispatcher来加载Controller,使用ContextLoaderListener加载Service和Component
 * 3. 好好想清楚Controller里面应该做什么？Service里面应该做什么
 *    这样你大概就知道各个层应该处理什么东西
 * 4. 不要把Transactional标签打在Controller的任何方法上，在父子容器的时候，他是不会生效的
 * 5. 你自己可以测试一下Transactional标签是否生效（在方法内部跑出异常）
 *
 * 你可以通过postman或者DHC来测试你的接口
 * 有时间你可以写个页面出来
 *
 * 评判标准：
 * 除了包含上次的标准以外,
 * 1. 分离Controller和Service层的逻辑
 *
 */
