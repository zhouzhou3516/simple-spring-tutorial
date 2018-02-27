package com.qunar.campus.spring.tutorial.factory;

import com.qunar.campus.spring.tutorial.interfaces.FileMovieFinder;
import com.qunar.campus.spring.tutorial.interfaces.MovieFinder;
import com.qunar.campus.spring.tutorial.interfaces.MovieService;

/**
 * Description: MovieRelativeFactory
 *
 * FAQ : 什么是工厂模式 ?

   工厂模式是我们最常用的实例化对象模式了，是用工厂方法代替new操作的一种模式。 -- 摘自百度百科（不可信）

   definition:
   @see http://en.wikipedia.org/wiki/Factory_method_pattern

 *
 * FAQ : 什么是单例模式(Singleton pattern) ？

   definition:
   @see http://en.wikipedia.org/wiki/Singleton_pattern

   why do i need the singleton design pattern:
   @see http://stackoverflow.com/questions/482594/why-do-i-need-the-singleton-design-pattern

   When should you use the singleton pattern instead of a static class?
   http://stackoverflow.com/questions/46541/when-should-you-use-the-singleton-pattern-instead-of-a-static-class

   FAQ : Java 里面有哪些办法实现单例模式、

   在jdk1.5之前，只有2种办法，而这两种办法都要保持构造器私有，并且导出共有静态成员

   function 1:
   {code}
   public class Single {

      public static final Elvis INSTANCE = new Single();
      private Single() {}
      //... other code
   }
   {code}

   function 2:
   {code}
   public class Single {

     private static final Elvis INSTANCE = new Single();
     private Single() {}
     public static Single getInstatnce() { return INSTANCE; }
     //.. other code
   }
   {code}

   function 3:
   {code}
   public enum Single {

     INSTANCE;
     //...other code

   }
   {code}

   注意：前两种方法都有一定的缺陷，如果你想知道是什么缺陷 @see Effetive Java Item3 Singleton


 * FAQ : 有什么值得注意的？

   通常情况下，与单例模式常常配合使用的还有lazy-loading,不过你需要慎用这个功能。
   Effective Java Item 71 Use lazy initialization judiciously
   BTW 看了这么多Effective Java你真的不打算买一本读一读？

 *
 * @author yushen.ma
 * @version 2015-03-10 14:07
 */
public enum MovieRelativeFactory {

    INSTANCE;

    public MovieFinder buildMovieFinder() {
        return new FileMovieFinder();
    }

    public MovieService buildMovieService() {
        return new BetterMovieService();
    }

}
