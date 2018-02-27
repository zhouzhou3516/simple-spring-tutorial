package com.qunar.campus.spring.tutorial.reflection;

import com.qunar.campus.spring.tutorial.Difficulty;
import com.qunar.campus.spring.tutorial.Synopsis;
import com.qunar.campus.spring.tutorial.factory.MovieRelativeFactory;
import com.qunar.campus.spring.tutorial.interfaces.MovieService;
import com.qunar.campus.spring.tutorial.interfaces.model.Movie;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Description: ReflectionSynopsis
 *
 * FAQ : What is reflection

   Definition :
   http://docs.oracle.com/javase/tutorial/reflect/

   Reference:
   What is reflection and why is it useful ?
   http://stackoverflow.com/questions/37628/what-is-reflection-and-why-is-it-useful
 *
 * FAQ : 为什么JDBC 要使用Class.forName ? 这个时候它到底干了什么
 *
   {code}
   // 加载MySql的驱动类  注意：并且要完成了类的初始化工作就行
   Class.forName("com.mysql.jdbc.Driver") ;
   Connection con = DriverManager.getConnection(url , username , password);
   // do other things
   {code}

   需要一点点class load 相关的知识

   @see http://stackoverflow.com/questions/8053095/what-is-the-actual-use-of-class-fornameoracle-jdbc-driver-oracledriver-while
 *
 * @author yushen.ma
 * @version 2015-03-10 15:55
 */
@Synopsis(name="reflection", difficulty = Difficulty.EASY)
public class ReflectionSynopsis {

    public static void main(String[] args) {  }

    /**
     *  通过反射获取一些类的基本信息
     *  包括类名, 包名
     */
    public static void demo1() {
        MovieService movieService = MovieRelativeFactory.INSTANCE.buildMovieService();
        // get the package name
        String packageName = movieService.getClass().getPackage().getName();
        // get the class name
        String className = movieService.getClass().getName();
    }

    /**
     * 通过反射加载并实例化一个类(通过默认构造函数)
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static void demo2() throws
            ClassNotFoundException, IllegalAccessException, InstantiationException {
        // 通过一个类全名加载一个Class，你猜它是怎么实现的？ ClassLoader又是什么
        // 还记得JDBC装载驱动类的时候吗？ 你知道他为什么这么做么？ 为什么不直接new出来呢？--浪费一个Driver对象
        /**
         * see com.mysql.jdbc.Driver这个类的源代码,静态代码自动new一个Driver对象，注册到Manager中，所以就不需要外面new了
         static {
         try {
         java.sql.DriverManager.registerDriver(new Driver());
         } catch (SQLException E) {
         throw new RuntimeException(“Can’t register driver!”);
         }
         }
         */
        Class<?> class1 = Class.forName("com.qunar.campus.spring.tutorial.interfaces.model.Movie");

        Movie movie = (Movie)class1.newInstance();
        movie.setName("qunar simple spring tutorial");
        movie.setDirectorName("yushen.ma");
    }

    /**
     * 认识Constructor,使用非空构造函数实例化
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    public static Movie demo3() throws
            ClassNotFoundException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException,
            InstantiationException {
        Class<?> class1 = Class.forName("com.qunar.campus.spring.tutorial.interfaces.model.Movie");
        Constructor<?> constructor = class1.getConstructor(String.class, String.class);
        return (Movie) constructor.newInstance("qunar simple spring tutorial", "yushen.ma");
    }

    /**
     * 通过reflection修改对象属性
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    public static void demo4() throws
            ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, InstantiationException,
            IllegalAccessException, NoSuchFieldException {
        Class<?> class1 = Class.forName("com.qunar.campus.spring.tutorial.interfaces.model.Movie");
        Constructor<?> constructor = class1.getConstructor(String.class, String.class);
        Movie movie = (Movie) constructor.newInstance("qunar simple spring tutorial", "yushen.ma");

        Field nameField = class1.getDeclaredField("name");
        nameField.setAccessible(true); //即使是private对象，设置了该值之后就可以访问
        nameField.set(movie, "yaya.zhang");
    }

    /**
     * 调用方法
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    public static void demo5() throws ClassNotFoundException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException,
            InstantiationException {
        Class<?> class1 = Class.forName("com.qunar.campus.spring.tutorial.interfaces.model.Movie");
        Constructor<?> constructor = class1.getConstructor(String.class, String.class);
        Movie movie = (Movie) constructor.newInstance("qunar simple spring tutorial", "yushen.ma");

        // hack to getName method
        Method getNameMethod = class1.getDeclaredMethod("getName");
        getNameMethod.invoke(movie);

        // hack to setName method
        Method setNameMethod = class1.getDeclaredMethod("setName", String.class);
        setNameMethod.invoke(movie, "a reflection name");
    }

    /**
     * 获取父类，或者获取接口
     * @throws ClassNotFoundException
     */
    public static void demo6() throws ClassNotFoundException {

        Class<?> thrillerMovieClass = Class.forName("com.qunar.campus.spring.tutorial.reflection");
        // get super class
        Class<?> superclass = thrillerMovieClass.getSuperclass();

        // get interfaces
        Class<?>[] interfaces = thrillerMovieClass.getInterfaces();

        // 判断是否是子类
        thrillerMovieClass.isAssignableFrom(superclass);
    }
}
