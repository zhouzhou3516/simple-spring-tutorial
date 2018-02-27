package com.qunar.campus.spring.tiny.property.support;

/**
 * Description: this is bean factory
 *
 * @author yushen.ma
 * @version 2015-03-15 18:31
 */
public class HelloWorldService {

    private String text;

    public String helloWorld() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
