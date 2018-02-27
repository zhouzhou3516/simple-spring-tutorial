package com.qunar.campus.spring.tutorial.mvc.parameters.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description: HelloWorldController
 *
 * @author yushen.ma
 * @version 2015-03-28 02:03
 */
@Controller
public class Case0HelloWorldController {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "it works!";
    }
}
