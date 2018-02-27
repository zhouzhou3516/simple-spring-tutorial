package com.qunar.campus.spring.tutorial.reflection;

import com.qunar.campus.spring.tutorial.interfaces.model.Movie;

/**
 * Description: Titanic
 *
 * 惊悚电影
 *
 * @author yushen.ma
 * @version 2015-03-10 16:19
 */
public class ThrillerMovie extends Movie {

    /** 例如：西方，日本，中国的呀什么的。总之就是个类型 */
    private String thrillerType;

    public ThrillerMovie(String thrillerType) {
        this.thrillerType = thrillerType;
    }

    public ThrillerMovie(String name, String directorName, String thrillerType) {
        super(name, directorName);
        this.thrillerType = thrillerType;
    }

    public String getThrillerType() {
        return thrillerType;
    }

    public void setThrillerType(String thrillerType) {
        this.thrillerType = thrillerType;
    }

    @Override
    public String toString() {
        return "Thriller{" +
                "thrillerType='" + thrillerType + '\'' +
                '}';
    }
}
