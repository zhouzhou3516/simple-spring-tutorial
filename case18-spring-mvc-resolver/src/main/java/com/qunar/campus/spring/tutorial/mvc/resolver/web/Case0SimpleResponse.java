package com.qunar.campus.spring.tutorial.mvc.resolver.web;

import com.qunar.campus.spring.tutorial.mvc.resolver.web.bean.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Description: Case0SimpleResponse
 *
 * @author yushen.ma
 * @version 2015-03-29 15:59
 */
@Controller
@RequestMapping("/simple")
public class Case0SimpleResponse {

    /**
     * 直接写返回值
     * http://localhost:8080/simple/write/response
     *
     * @param response response
     */
    @RequestMapping("/write/response")
    public void writeResponse(HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        writer.println("I'm writing response output stream directly");
    }

    /**
     * 直接写ResponseBody
     * http://localhost:8080/simple/string
     * @return String
     */
    @RequestMapping("/string")
    @ResponseBody
    public String simpleString() {
        return "I'm writing a simple string";
    }

    /**
     * 返回一个对象，利用上一个case里面的json encode
     * http://localhost:8080/simple/json
     * @return UserInfo
     */
    @RequestMapping("/json")
    @ResponseBody
    public UserInfo json() {
        return new UserInfo(1, "admin", "123");
    }

}
