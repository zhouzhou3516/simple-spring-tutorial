package com.qunar.campus.spring.tutorial.controller;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Description: ExceptionController
 *
 * @author yushen.ma
 * @version 2015-04-04 19:24
 */
@RequestMapping("/exception")
@Controller
public class ExceptionController {

    @RequestMapping("/exception")
    public void exception(String username) {
        Preconditions.checkArgument(StringUtils.isBlank(username), "username should not be null");
    }

}
