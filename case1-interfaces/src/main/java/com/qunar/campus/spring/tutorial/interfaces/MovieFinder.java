package com.qunar.campus.spring.tutorial.interfaces;

import com.qunar.campus.spring.tutorial.interfaces.model.Movie;

import java.util.List;

/**
 * Description: MovieFinder
 *
 * 这是一个简单的Movie Finder接口
 *
 * FAQ 1: 什么是接口？ 接口的作用是什么?
 *
   definition:
   @see http://docs.oracle.com/javase/tutorial/java/concepts/interface.html

   How are java interfaces actually used:
   @see http://stackoverflow.com/questions/504904/how-are-java-interfaces-actually-used

   Why to use Interfaces, Multiple Inheritance vs Interfaces, Benefits of Interfaces:
   @see http://stackoverflow.com/questions/8531292/why-to-use-interfaces-multiple-inheritance-vs-interfaces-benefits-of-interface

 * @author yushen.ma
 * @version 2015-03-08 00:16
 */
public interface MovieFinder {

    /**
     * 查看所有的电影
     * @return a list of movies
     */
    List<Movie> find();

}
