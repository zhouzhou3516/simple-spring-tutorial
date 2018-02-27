package com.qunar.campus.spring.tutorial.scope.thread.safe;

import com.qunar.campus.spring.tutorial.Difficulty;
import com.qunar.campus.spring.tutorial.Synopsis;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Description: UnThreadSafeService
 *
 * @author yushen.ma
 * @version 2015-03-20 00:12
 */
@Service
@Synopsis(name="unThread safe", difficulty = Difficulty.NORMAL)
public class UnThreadSafeService {

    /**
     * 虽然这个unThreadSafeBean被我们定义成了prototype类型。
     * 但是我们这样用的话。
     * 如果别的Service对该UnThreadSafeService有Resource的话。
     * 那么其实这个unThreadSafeService也是线程不安全的。思考为什么
     *
     * 嗯...所以呢，这个出现了一个很奇妙的场景。
     * 如果一旦某个Bean是线程不安全的，那么我们可以使用Scope=Prototype来使得
     * 每次都new一个这个对象，而不是全局共享这个对象。
     * 但是这就引发了一个问题，引用他的对象也必须是Scope=ProtoType类型的
     *
     * 通常情况下，我们是不会把一个类的Scope写成非单例的。一般来说
     * 我们会将数据（javaBean）和Bean分离。
     *
     * 但是在一些场景下面如果出现了某个类确实是线程不安全的。且new一个这样的对象
     * 很消耗资源。我们通常会使用LocalThread的方式来将其内部的“状态”控制住.
     *
     * 所以，通常情况下，是没有使用Scope的必要，至少我暂时没有遇到非用不可的场景
     * 他带来的坏处，可能远远比这样的好处多
     */
    @Resource
    UnThreadSafeBean unThreadSafeBean;


}
