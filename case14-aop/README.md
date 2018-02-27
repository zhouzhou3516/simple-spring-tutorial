 AOP 这一部分，恩。说实话，工作中用到的会非常少。

 你需要做的：
 1. 明白它的概念
 2. 明白知道有这么回事
 3. 记住以下3个事实: 1. Spring AOP只能AOP容器管理的Bean.
                   2. Spring AOP基于动态代理和CGlib实现
                   3. Spring AOP只是使用的AspectJ的AOP描述方面的功能。其他和它一毛钱关系没有
 4. 完事了

 @see http://docs.spring.io/spring/docs/4.1.5.RELEASE/spring-framework-reference/html/aop.html

 如果你对自己有要求，那就把这个包的例子看一看，

 如果你对自己还有要求, 那就把tiny-spring看一看
 如果你对自己非常变态, 那就把动态代理的实现，CGLIB的实现好好看一下
 如果你觉得自己天赋惊人。恩，我建议你好好研究JVM规范，以及ASM字节码框架,以及1.5新增的包java.lang.instrument

 不过这些，恩，都不重要啦,不要浪费太多不该浪费的时间

 @author yushen.ma
 @version 2015-03-23 20:51



