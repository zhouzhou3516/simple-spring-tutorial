package com.qunar.campus.spring.tutorial.generics.base;

import com.qunar.campus.spring.tutorial.Difficulty;
import com.qunar.campus.spring.tutorial.Synopsis;
import com.qunar.campus.spring.tutorial.interfaces.model.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: SimpleExample
 *
 *
 * FAQ 1: why use Generics ?
   @see http://docs.oracle.com/javase/tutorial/java/generics/why.html

   reference:
   Java Generics FAQ:

   http://www.angelikalanger.com/GenericsFAQ/JavaGenericsFAQ.html

 * @author yushen.ma
 * @version 2015-03-10 14:50
 */
@Synopsis(name="generic", difficulty = Difficulty.EASY)
public class SimpleSynopsis {

    /**
     * 在JDK1.5之前的代码都是这样写的。
     * 因为没有泛型，集合类使用起来非常不方便
     */
    public void oldSynopsis() {
        List movies = new ArrayList();
        // warn says : unchecked call to add(E) as the member of raw list
        movies.add(new Movie("this is generic example", "yushen.ma"));

        // what's the fuck?
        Object o = movies.get(0);
        // 只有强转之后才能用
        Movie movie1 = (Movie)o;

    }

    /**
     * 甚至于，你可能在多个地方操作一个List，但是你完全不知道他是什么类型，当然IDE也不能为你提供编译检查
     * 例如出现以下代码，你也只能在运行的时候发现
     */
    public void castExceptionSynopsis() {
        List movies = new ArrayList();
        movies.add(this); // what's the fuck ?
    }

}
