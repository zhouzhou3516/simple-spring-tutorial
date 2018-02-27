package com.qunar.campus.spring.tiny.aspect;

/**
 * Description: PriceService
 *
 * @author yushen.ma
 * @version 2015-03-21 20:00
 */
public class PriceService {

    public void notMatch() { }

    public void processPrice() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.interrupted();
        }
    }

}
