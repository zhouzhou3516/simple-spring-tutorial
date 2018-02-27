/**
 * Description: package-info

 * @author yushen.ma
 * @version 2015-04-04 21:16
 *
 * 作业题目：
 *
 * 在copy一个作业2出来，并且修改它
 *
 * 1. 增加配置common-web并且使用@JsonBody而不是@ResponseBody来返回
 * 2. 按照case19 - README.txt里面提到的wiki来配置异常处理器
 * 3. 增加用户登录，退出，权限验证（不论你使用filter或者是interceptor,甚至于是AOP。你也可以异想天开）
 * 注：默认数据库中有2个角色，1->管理员 2->用户。只有管理员可以访问作业2中的所有接口。
 * 如果是用户的话则返回"Qunar欢迎你", 这样的页面
 *
 * 不用实现用户注册，只有管理员添加过的用户才能访问那些页面
 *
 */
