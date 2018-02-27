package com.qunar.campus.spring.tiny.aspect;

import org.aopalliance.aop.Advice;

/**
 *
 * AOP术语：
 *
 * JoinPoint : 连接点
 * 程序执行的某个特定位置：如类开始初始化前、类初始化后、类某个方法调用前、调用后、调用抛出异常后。
 * 一个类或一段代码拥有一些具有边界性质的特定点。这些代码中的特定点就称为"连接点"
 * 注意：Spring只支持方法的连接点，即仅能在方法调用前、方法调用后、方法抛出异常时以及方法调用前后这些程序执行点
 * “织入”增强。为什么呢？
 *
 * PointCut: 切点
 * 每个程序类都拥有多个连接点（JoinPoints），如一个拥有两个方法的类，这两个方法都是连接点，即连接点
 * 是程序中客观存在的事物。但在这为数众多的连接点中，如何定位到某个感兴趣的连接点上呢？AOP通过切点定位特定
 * 的连接点。
 *
 * Advice:增强
 * 增强是织入到目标类连接点上的一段程序代码，
 * 注意：Spring中增强除了表述一段程序代码以外，还拥有另一个和链接蒂娜相关的信息，这便是
 * 执行点的方位。结合执行点的方位信息和切点信息，我们就可以找到特定的连接点。
 *
 * Introduction: 引介
 * 引介是一种特殊的增强，即便某个类没有一些方法，我们通过引介可以为其动态添加接口
 *
 * Weaving: 织入
 * 织入是将增强添加到目标类具体连接点上的过程。
 * AOP通常有3种织入方式：
 * 1. 编译期织入，这要求使用特殊的编译期。AspectJ就是这样的东西
 *
 * Aspect = JoinPoint + Advice
 *
 * AOP的核心在于：1. 如何通过切点和增强定位到目标对象的连接点上（即如何描述怎么切，以及如何读取这些描述，并且定位到对应
 * 的类）。2. 如何织入
 *
 * 关于2. 我们已经在tiny-7中有所了解。我们使用JDK动态代理可以针对这些接口实现代理,动态生成一个类
 * 让他看起来像是那么回事, 但是这个有一点点缺陷就是，JDK动态代理只能针对接口。
 * 其实有一种特殊的技术叫做“动态字节码”,其原来就是按照Java虚拟机指令规范，在程序运行期的时候
 * 生成一些Class文件，然后通过ClassLoader将其载入。但是如果我们直接写代码来生成字节码，
 * 或许我们需要通读白皮书规范，还要设计一套实用的接口用来操作字节码。
 *
 * 其实有一个现成的字节码框架叫做ASM
 * @see http://asm.ow2.org/
 *
 * CGlib对其是一层包装,提供类似于JDK代理一样的接口，不过没有任何的限制条件。
 * 另外CGlib在生成对象的时候相对于JDK代理要稍微耗时， 但是运行相对高效(这句话我不保证一定正确
 * @see http://github.com/cglib/cglib
 *
 * 接下来就是看如何定义和定位这些切点。其实Spring对AspectJ的运用也就仅此而已。
 * Spring仅仅利用了AspectJ的描述功能。(为什么Spring不用使用特殊的编译器就可以实现AOP?)
 *
 * 另外一方面就是如何定义“增强”，如同我们tiny-7中的methodInterceptor就是一个方法级别的增强
 *
 * @AspectJ refers to a style of declaring aspects as regular
 * Java classes annotated with annotations. The @AspectJ style
 * was introduced by the AspectJ project as part of the AspectJ 5
 * release. Spring interprets the same annotations as AspectJ 5,
 * using a library supplied by AspectJ <important>for pointcut parsing and matching.</important>
 * The AOP runtime is still pure Spring AOP though,
 * and there is no dependency on the AspectJ compiler or weaver. -- by spring document
 *
 * 此接口，只是表示一个增强
 *
 * @author yushen.ma
 * @version 2015-03-20 13:55
 */
public interface Advisor {

    Advice getAdvice();

}
