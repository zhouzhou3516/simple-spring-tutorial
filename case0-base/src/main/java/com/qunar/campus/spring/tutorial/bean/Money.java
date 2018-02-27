package com.qunar.campus.spring.tutorial.bean;
import java.math.BigDecimal;
/**
 * @author yushen.ma
 * @since 24/7/2017.
 **/
public class Money {

    private final BigDecimal finalZero;

    public Money(BigDecimal finalZero) {
        this.finalZero = finalZero;
    }

    public static Money of(BigDecimal balance) {
       return new Money(balance);
    }

   public BigDecimal toStoreDecimal() {
       return finalZero;
   }

   public Money minus(Money money) {
       return Money.of(finalZero.subtract(money.finalZero));
   }

    public Money plus(Money money) {
        return Money.of(finalZero.add(money.finalZero));
    }
}
