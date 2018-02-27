package com.qunar.campus.spring.tiny.aspect;

import com.qunar.campus.spring.tiny.aspect.impl.AspectJExpressionPointcut;
import com.qunar.campus.spring.tutorial.Difficulty;
import com.qunar.campus.spring.tutorial.Synopsis;
import org.junit.Assert;
import org.junit.Test;


/**
 * @author yushen.ma
 * @version 2015-03-21 20:00
 */
@Synopsis(name="AspectJ Wave", difficulty = Difficulty.HARD)
public class AspectJExpressionPointcutTest {

    @Test
    public void testClassFilter() throws Exception {
        String expression = "execution(* com.qunar.campus.spring.tiny.aspect.*.*(..))";
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression(expression);
        boolean matches = aspectJExpressionPointcut.getClassFilter().matches(PriceService.class);
        Assert.assertTrue(matches);
    }

    @Test
    public void testMethodInterceptor() throws Exception {
        String expression = "execution(* com.qunar.campus.spring.tiny.aspect.PriceService.processPrice(..))";
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression(expression);
        boolean matches =
                aspectJExpressionPointcut.getMethodMatcher()
                        .matches(PriceService.class.getDeclaredMethod("processPrice"), PriceService.class);
        Assert.assertTrue(matches);
    }

    //匹配的方法是processPrice,我们传入的方法是notMatch,当然应该不匹配啦
    @Test
    public void testNotMatch() throws Exception {
        String expression = "execution(* com.qunar.campus.spring.tiny.aspect.PriceService.processPrice(..))";
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression(expression);
        boolean matches =
                aspectJExpressionPointcut.getMethodMatcher()
                        .matches(PriceService.class.getDeclaredMethod("notMatch"),
                                 PriceService.class);
        //assert not match
        Assert.assertTrue(!matches);
    }
}
