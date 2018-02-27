package com.qunar.campus.spring.tutorial.generics.base;

/**
 * Description: NewRoom
 *
 * @author yushen.ma
 * @version 2015-03-11 21:05
 */
public class NewRoom<T> {

    private T t;

    public void add(T t) {
        this.t = t;
    }


    public T get() {
        return t;
    }

}

