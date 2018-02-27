package com.qunar.campus.spring.tutorial.mvc.parameters.web;

import com.qunar.campus.spring.tutorial.mvc.parameters.web.bean.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description: Case4BindingData
 *
 * 数据绑定
 *
 * @author yushen.ma
 * @version 2015-03-28 18:13
 */
@Controller
@RequestMapping("/bind/data")
public class Case4BindingData {

    final private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 这种浏览器应该怎么传过来呢
     *
     * http://localhost:8080/bind/data/ordinary?userId=1&username=admin&password=123
     *
     * 参数居然自动绑定到了对象里面.. magic
     * @param userInfo userInfo
     * @return String
     */
    @RequestMapping("/ordinary")
    @ResponseBody
    public String ordinary(UserInfo userInfo) {
        logger.info("userInfo : {}", userInfo);
        return "I've got it";
    }

    /**
     * 感觉这个请求在乱传参数，可以spring还是拿到的返回结果
     * http://localhost:8080/bind/data/ordinary2?userId=1&username=admin&password=123&otherThing=asdasd
     *
     * 简单的对象还好.嗯，如果对象层级很深，而且还有重名的对象，那可怎么办呢..
     * 例如ClassInfo对象
     *
     * @param userInfo userInfo
     * @param username username
     * @param otherThing other thing
     * @return String
     */
    @RequestMapping("/ordinary2")
    @ResponseBody
    public String ordinary2(UserInfo userInfo, String username, String otherThing) {
        logger.info("userInfo : {}", userInfo);
        logger.info("username : {}", username);
        logger.info("otherThing: {}", otherThing);
        return "I've got them";
    }

    // ... 在其他的文档或者“中文博客中”会提到ModelAttribute，SessionAttribute这样的东西。
    // ... 其旨在自动的将一些“领域对象”同步到“视图”的上下文中
    // ... 当然，Data Validation也是依赖于 ModelAttribute。@see JSR 303
    // ... 另外，默认情况下，对象属性（如上面的UserInfo）,会自动开启ModelAttribute,其他的基本类型不会
    // ... 这一部分内容重要程度为负数...所以，几乎可以忽略 ---> BTW 只有自动验证有点点作用

}
