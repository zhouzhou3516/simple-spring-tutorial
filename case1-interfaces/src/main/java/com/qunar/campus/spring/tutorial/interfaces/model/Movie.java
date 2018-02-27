package com.qunar.campus.spring.tutorial.interfaces.model;

import com.google.common.base.Objects;

import java.io.Serializable;

/**
 * Description: Movie
 *
 * 这是一个基本的JavaBean对象
 *
 * FAQ 1: 什么是JavaBean ？

   A JavaBean is just a standard
      1. All properties private (use getters/setters)
      2. A public no-argument constructor
      3. Implements Serializable.

   reference:
   @see http://stackoverflow.com/questions/3295496/what-is-a-javabean-exactly

   definition :
   @see http://www.oracle.com/technetwork/java/javase/documentation/spec-136004.html


 * FAQ 2: 什么是Serialization? 什么是Seriablizable接口? 什么是serialVersionUID ?

   Serialization is the conversion of an object to a series of bytes,
   so that the object can be easily saved to persistent storage or streamed across a communication link.
   The byte stream can then be deserialized - converted into a replica of the original object.

   Reference:
   what is object serialization :
   @see http://stackoverflow.com/questions/447898/what-is-object-serialization

   why java need serializable interface:
   @see http://stackoverflow.com/questions/441196/why-java-needs-serializable-interface

   what is a serial version uid and why should i use it?
   @see http://stackoverflow.com/questions/285793/what-is-a-serialversionuid-and-why-should-i-use-it

   Serialization:
   Effective Java 11th Section. Serialization

   definition:
   @see http://www.tutorialspoint.com/java/java_serialization.htm

   BTW:
   如果你使用dubbo服务，凡是需要远程调用返回的bean都需要实现这个接口。

 * FAQ 3: 如何配置IDEA自动生成serialVersionUID

   @see http://stackoverflow.com/questions/12912287/intellij-idea-generating-serialversionuid

   FAQ 4: 如何自动生成getter 和 setter方法
   @see http://www.jetbrains.com/idea/help/generating-getters-and-setters.html

   BTW: 有空的话可以把IDEA的使用手册读一读,可能会有意外收获

 * @author yushen.ma
 * @version 2015-03-10 12:39
 */
public class Movie implements Serializable {

    private static final long serialVersionUID = -4158064337268925488L;

    /** the name of movie */
    private String name;

    /** the name of director */
    private String directorName;

    public Movie() { }

    public Movie(String name, String directorName) {
        this.name = name;
        this.directorName = directorName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", directorName='" + directorName + '\'' +
                '}';
    }
}
