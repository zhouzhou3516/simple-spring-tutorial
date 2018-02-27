package com.qunar.campus.spring.tiny.xml.io;

import java.net.URL;

/**
 * 小工厂
 *
 * @author yushen.ma
 * @version 2015-03-16 21:31
 */
public class ResourceLoader {

    public Resource getResource(String location){
        URL resource = this.getClass().getClassLoader().getResource(location);
        return new UrlResource(resource);
    }

}
