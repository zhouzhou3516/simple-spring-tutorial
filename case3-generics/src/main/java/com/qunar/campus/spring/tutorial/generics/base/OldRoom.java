package com.qunar.campus.spring.tutorial.generics.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description: OldRoom
 *
 * 这是一个简单的Room类，他里面有一个元素，其实他只是List的一个缩影
 *
 * @author yushen.ma
 * @version 2015-03-11 20:58
 */
public class OldRoom {

    private Object object;

    private void set(Object o) {
        this.object = o;
    }

    public Object get() {
        return this.object;
    }

    /**
     * 静态内部类有哪些使用场景?
     * 非静态内部类有哪些使用场景？
     */
    public static class Main {

        final static private Logger logger = LoggerFactory.getLogger(Main.class);

        public static void main(String args[]) {
            OldRoom room = new OldRoom();
            room.set(69);
            //为什么Collection框架下面的集合对象，对于primitive类型的变量，都只能使用包装器呢，例如 int => integer ?
            Integer i = (Integer)room.get();
            logger.info("{}", i);
        }

    }

}
