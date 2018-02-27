#### Simple Spring Application WEB MVC Other

1. Filter

   1.1 关于Filter和Spring的使用，第一个想到的就是如何在filter中拿到spring的对象
       显然filter不属于spring的管理范畴，人家可是servlet的人。
       参考：com.qunar.campus.spring.tutorial.controller.filter.OriginFilter

   1.2 Spring同样也提供了一个代理类，来帮助filter与spring容器的交互
       参考：com.qunar.campus.spring.tutorial.component.filter.DelegatingFilter

   1.3 同样spring针对web开发，提供各式各样的预定义的filter
       参考：org.springframework.web.filter包下面的filter
       举个比较重要的例例子就是CharacterEncodingFilter
       之前在设置编码的时候，通常我们自行写一个filter来设置编码。但是现在spring已经搞了一个啦。

2. Interceptor
   顾名思义为拦截器，其实在Java中很多地方用到这个概念，包括动态代理，AOP。
   他们实现的方式各有不同，并且针对的事情也各有不同。

   同样spring提供了web interceptor来拦截。
   但是拦截什么呢？ 和filter之间又有什么不一样的呢
   参考：com.qunar.campus.spring.tutorial.controller.interceptor


3. ExceptionResolver
   异常视图解析器。一般的情况下，在Controller内部会出现这样的代码
   {code}

       @RequestMapping("/user/save")
       public String saveUser(String username, String password) {
           if (StringUtils.isBlank(username)) {
                 Model model = new Model();
                 model.addAttribute("errorMsg", "用户名不能为空")
                 return "error/common" //跳转到错误提示页面
           }
           if (StringUtils.isBlank(password)) {
                 Model model = new Model();
                 model.addAttribute("errorMsg", "密码不能为空")
                return "error/common" //错误提示页面
           }
           // do some other things
       }

   {code}
   不蛋疼么？
   spring WEB MVC 提供了exceptionResolver来缓解这样的症状
   参考:
   1. dispatch-servlet.xml

   {code}
       @RequestMapping("/user/save")
       public String saveUser(String username, String password) {
           Assert(StringUtils.isBlank(username), "用户名不能为空")
           Assert(StringUtils.isBlank(password), "密码不能为空")
       }
   {code}

   BTW, 在这种情况下，我们就可以更容易地使用“验证器”，参考JSR303，以及Spring Validation


   问题来了，如果是json的请求你却返回了一个html页面，恐怕不太好吧..
   所以如何处理呢。

   2. 我们可以继承SimpleMappingExceptionResolver。通过覆盖doResolveException()
   方法来特别针对标注了ResponseBody的方法进行特别的处理

   参考com.qunar.campus.spring.tutorial.controller.resolver.SimpleExceptionResolver

   3. 在Qunar.common.web这个包下面已经实现了这样的exceptionResolver建议各位使用
   同时支持Qunar前后端返回规范。
   参考：http://wiki.corp.qunar.com/display/devwiki/common-web







