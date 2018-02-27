package com.qunar.campus.spring.tutorial.homework.start.container.io;

import java.net.URL;

/**
 * Created by liqingzhou on 17/7/21.
 */
public class ResourceLoader {

    public Resource getResource(String location) {
        URL resource = this.getClass().getClassLoader().getResource(location);
        return new UrlResource(resource);
    }
}
