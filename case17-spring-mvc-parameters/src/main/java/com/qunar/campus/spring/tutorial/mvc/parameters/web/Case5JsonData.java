package com.qunar.campus.spring.tutorial.mvc.parameters.web;

import com.qunar.campus.spring.tutorial.mvc.parameters.web.bean.ClassInfo;
import com.qunar.campus.spring.tutorial.mvc.parameters.web.bean.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

/**
 * Description: Case5JsonData
 *
 * 重要程度：5颗星~~~~
 *
 * @author yushen.ma
 * @version 2015-03-28 18:39
 */
@Controller
@RequestMapping("/json")
public class Case5JsonData {

    final private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 如果你细心的话，会发现get请求的参数是连接在url后面的
     * 而post请求的参数是在http body中
     * 虽然看起他们都是这样：  username=admin&password=111
     *
     * 获取http body中的内容
     *
     * 1. http://localhost:8080/json/request/body?username=admin&password=123
     *    这样默认请求过去是GET方法，页面返回415 提示：UNSUPPORTED_MEDIA_TYPE
     *    控制台提示：Cannot extract parameter (String body): no Content-Type found
     *    难道是因为我们没传Content-Type?
     *
     * 2. 使用Chrome浏览器下载一个插件，比如...呃... postman, dhc 或者你问问前端他们用的啥
     *    传一个Content-Type:text/plain过去 ---> ok成功
     *    但是打印出来的东西为空。
     *    因为GET请求默认不包含body
     *
     * 3. 设置为POST请求
     *    打印出来是：username=admin&password=123
     *
     * 4. 日志里面答应出来一行
     *
     * 18:54:32.644 [869594804@qtp-726969946-2]
     * DEBUG o.s.w.s.m.a.AnnotationMethodHandlerAdapter
     * - Written [I've got it] as "text/plain;charset=ISO-8859-1"
     * using [org.springframework.http.converter.StringHttpMessageConverter@45609812]
     *
     * 点开这个StringHttpMessageConverter
     * 嗯..这个类所在的包，
     * 恩就是这些parameters转换的核心所在
     *
     * 5. 看一看这个接口： HttpMessageConverter
     *   支持的类型，支持的Charset,
     *   再看看StringHttpMessageConverter的实现
     *
     *   BTW 了解字符编码同样重要，为什么读取文件的时候必须要设置字符编码，考虑：难道就不能推导出来吗？
     *
     **/
    @RequestMapping("/request/body")
    @ResponseBody
    public String requestBody(@RequestBody String body) {
        logger.info("{}", body);
        return "I've got it";
    }

    /**
     * 在dataBind的时候, 提到了如果对象层级很深，那我们就有点没有办法了
     * 当然如果你愿意这样 ===>  http://host?class.classId=1&class.className=2&class.users.0.userId=1&....
     * 这样的话，既不易读，也不易写。
     *
     * 后来在数据交换格式分别出现了xml, json（注 ：当然xml的主要作用并不是用来做数据交换）
     * 公司内部一般使用json, 但不排除特定的场景下用到xml
     * 下面以json为例
     *
     * 利用body里面可以塞任意格式的数据，前端把一个对象利用json序列化之后塞进去，然后后端从Body中取出，然后反序列化.
     * 这样就得到了交换数据的功能啦
     *
     * 1. http://localhost:8080/json/request/json
     *    application/json
     *
     * 2. 报错，说406.嗯这个时候需要用到另外一个标签：<mvc:annotation-driven/>
     *    之前介绍过DispatcherServlet里面默认了一些Convert.
     *    他们根据Content-Type和Charset以及Class<?>来判断是否生效
     *    比如之前的StringHttpMessageConverter
     *
     *    BTW: spring MVC的架构其实分为了几个部分。每个部分都有对应的默认实现
     *    具体的默认实现参考spring-webmvc包下面的org.springframework.web.servlet.DispatcherServlet.properties
     *    其中HandlerAdapter持有所有Mapping和对应的处理器（Controller-RequestMapping）
     *    我们所使用的是AnnotationMethodHandlerAdapter。基于注解的映射
     *
     *    点开这个类看其构造方法里面有一行
     *    <code>
     *    		this.messageConverters = new HttpMessageConverter[]{new ByteArrayHttpMessageConverter(), stringHttpMessageConverter,
     new SourceHttpMessageConverter(), new XmlAwareFormHttpMessageConverter()};
     *    </code>
     *    其中默认初始化了3个Converter。
     *
     *    BTW. Converter的含义大概类似于将request -> 我们内部表示的对象，
     *         与其对应的有Formatter，意思是将我们内部表示的对象 -> response,
     *         但是Formatter现在Spring也没有太好地定义，只是简单地复用了Converter API。
     *
     *    如果我们需要使用到JSON encode和decode，我们就需要为其配置相应的Converter
     *    当然，spring以及默认写好了很多很多converter，只是默认并不加载
     *    参见：spring-web包下面的org.springframework.http.converter
     *
     *    我们需要使用的就是MappingJackson2HttpMessageConverter
     *
     *    Tips. 这个里面还有一个MappingJacksonHttpMessageConverter, 他们分别使用了
     *    fastJson和jackson。由于fastJson在处理的时候会将字符串放在perm区，在1.7之后
     *    perm区才挪到堆中，所以公司以前处理一个小故障，因为使用fastJson。
     *
     * 3. 如何配置这些converter呢？    这里先不讲
     *
     *    我们需要先学习一个偷懒的标签。
     *    <mvc:annotation-driven/>
     *
     *    在dispatcherServlet里面已经为我们默认使用了一些组件
     *
     *    mvc:annotation-driven的含义是加上更多更常用的默认组件
     *
     *    Tips(hard): 想直到该标签的具体实现，需要了解spring 解析xml的基本原理
     *    参考： spring-webmvc包下面的META-INF.spring.handlers中定义的：MvcNamespaceHandler
     *    其对应的处理器是：
     *    registerBeanDefinitionParser("annotation-driven", new AnnotationDrivenBeanDefinitionParser());
     *    在AnnotationDrivenBeanDefinitionParser里面，444-446行
     *    <code>
             if (jackson2Present) {
             messageConverters.add(createConverterBeanDefinition(MappingJackson2HttpMessageConverter.class, source));
             }
         </code>
         这句话的意思是，当jackson2Present的时候（其实是，当lib中含有jackson2的类包时），加载MappingJackson2HttpMessage

         于是我们在dispatcher-servlet.xml里面配置上<mvc:annotation-driven/>
     *
     * 4. 在pom 文件中加入fastJson的依赖

         <code>
             <dependency>
             <groupId>com.fasterxml.jackson.core</groupId>
             <artifactId>jackson-databind</artifactId>
             </dependency>

             <dependency>
             <groupId>com.fasterxml.jackson.core</groupId>
             <artifactId>jackson-core</artifactId>
             </dependency>
         </code>
     *
     *
     * 总结：为了使用json, 我们在配置文件中加入了<mvc:annotation-driven/>
     * 在pom中加入了fastJson的依赖。嗯。就是这么简单，但是理解其原理还是略蛋疼呀~~
     *
     * important：另外，不要去看那些“中文博客”上面各种坑爹的配置json的办法。那些几乎已经落伍了。
     * 因为在3.2之前AnnotationDriven的处理类里面很多东西都没有默认
     *
     */
    @RequestMapping("/request/json")
    @ResponseBody
    public String requestJsonBody(@RequestBody ClassInfo classInfo) {
        logger.info("{}", classInfo);
        return "I've got it";
    }

    /**
     * 这个方法用来测试
     * 在没有配置任何东西的时候，直接把一个类返回出去是啥？
     *
     * @return ClassInfo
     */
    @RequestMapping("/request/decode")
    @ResponseBody
    public ClassInfo json() {
        ClassInfo classInfo = new ClassInfo();
        classInfo.setClassName("3年纪7班");
        classInfo.setClassNo(100033);
        classInfo.setUsers(new ArrayList<UserInfo>(){
            private static final long serialVersionUID = 8121082396052000488L;
            { this.add(new UserInfo(1, "admin","123"));}});
        return classInfo;
    }

    // ...  终于写完了！
}
