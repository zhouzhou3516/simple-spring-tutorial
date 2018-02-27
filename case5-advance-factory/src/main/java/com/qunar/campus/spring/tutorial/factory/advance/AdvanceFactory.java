package com.qunar.campus.spring.tutorial.factory.advance;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.qunar.campus.spring.tutorial.Difficulty;
import com.qunar.campus.spring.tutorial.Synopsis;
import org.apache.commons.collections.CollectionUtils;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Set;

/**
 * Description: com.qunar.campus.spring.tutorial.factory.advance.AdvanceFactory
 *
 * @author yushen.ma
 * @version 2015-03-10 17:29
 */
@Synopsis(name="advance factory", difficulty = Difficulty.NORMAL)
public enum AdvanceFactory {

    INSTANCE;

    /**
     * 一个简单版本的build
     *
     * 问题 接口如何实例化 ? :
     *
     * {code}
     *
     * public class MovieFinderImpl {
     *     // some code
     * }
     *
     * public MovieServiceImpl {
     *     private final MovieFinder finder;
     *     public MovieServiceImpl() {
     *          this.finder = AdvanceFactory.build(MovieFinder.class); //你妹呀，这是个接口啊, 你让Factory情何以堪
     *     }
     * }
     *
     * {code}
     *
     * @param clazz 目标class
     * @param <T> 类型
     * @return 生成的对象啦
     */
    public <T> T build1(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (Throwable e) {
            throw new RuntimeException();
        }
    }

    /**
     *
     * 看起来完美多了,
     *
     * 问题: 如何避免bean对factory的依赖呢?
     *
     * @param clazz class
     * @param <T> 类型
     * @return target object
     */
    public <T> T build2(final Class<T> clazz) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        return this.findTheImplements(clazz).newInstance();
    }

    /**
     *
     * 问题 如果一个接口在指定的path里面有两个实现类怎么办？ 实例化哪一个？
     * 为了避免object 对factory的依赖
     *
     * 问题 如果我们拿到的实现类的有多个构造方法
     *
     * 问题 如果底层对象还需要一些其他值怎么配置？
     *
     * {code}
     *
     * public class MovieBlackFilter {
     *     private final String blackName
     *     public FileMovieFinderImpl(String blackName) {
     *          this.blackName = blackName;
     *     }
     * }
     *
     * public class MovieServiceImpl {
     *     private final MovieBlackFilter filter;
     *     public MovieServiceImpl() {
     *          //这个类需要一些基础数据怎么传啊 ?
     *          this.filter = AdvanceFactory.build(MovieBlackFilter.class);
     *     }
     * }
     *
     * {code}
     *
     *
     * @param <T> 类型
     * @param tClass 目标类型
     * @return 实例
     */
    public <T> Object build3(final Class<T> tClass) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
        // 1. find the implement class
        Class<T> implement = this.findTheImplements(tClass);

        // 2. find the constructor
        Constructor<?>[] constructors = implement.getConstructors();//我们应该用哪一个 Constructor来实例化呢;
        if (CollectionUtils.isEmpty(Lists.newArrayList(constructors))) {
            throw new RuntimeException("class cannot be init");
        }//End Of If
        Constructor<?> constructor = constructors[0];

        // 3. init all the params
        // 3.1 if no parameters
        if (CollectionUtils.isEmpty(Lists.newArrayList(constructor.getParameterTypes()))) {
            return constructor.newInstance();
        }
        // 3.2 if need parameters
        List<Object> params = Lists.newArrayList();
        for (Class<?> param : constructor.getParameterTypes()) {
            params.add(this.build3(param));
        }
        return constructor.newInstance(params.toArray());
    }


    /**
     * find the implements of the giving interface
     *
     * @param tClass 目标类
     * @param <T> 类型
     * @return target object
     * @throws ClassNotFoundException
     */
    @SuppressWarnings("unchecked")
    private <T> Class<T> findTheImplements(final Class<T> tClass) throws ClassNotFoundException {
        Reflections reflections = new Reflections("com.qunar.hotel");
        Set<Class<? extends Object>> allClasses = reflections.getSubTypesOf(Object.class);
        if (CollectionUtils.isEmpty(allClasses)) {
            throw new ClassNotFoundException(allClasses.toString() + "is not found");
        }
        Iterables.removeIf(allClasses, new Predicate<Class<?>>() {
            @Override
            public boolean apply(Class<?> input) {
                return input.isAssignableFrom(tClass) && input.equals(tClass);
            }
        });
        //如果有两个类，那我们实例化哪个类呢？
        //好吧，这个问题留给下一个方法解决。我们只实例化第一个
        if (CollectionUtils.isEmpty(allClasses)) {
            throw new ClassNotFoundException(allClasses.toString() + "is not found");
        }
        return (Class<T>) allClasses.iterator().next();
    }

}
