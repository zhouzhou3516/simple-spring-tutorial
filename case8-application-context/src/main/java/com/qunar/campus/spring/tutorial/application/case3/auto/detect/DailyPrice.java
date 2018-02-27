package com.qunar.campus.spring.tutorial.application.case3.auto.detect;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Description: DailyPrice
 *
 * 每日报价
 *
 * @author yushen.ma
 * @version 2015-03-19 21:35
 */
public class DailyPrice implements Serializable {

    private int hotelId;

    private int roomId;

    private String roomName;

    /**
     * 特别注意：为了不多依赖更多的JAR包,这里暂时使用BigDecimal对象来表示金额
     * 应该使用Money对象来表示金额
     *
     * @see http://wiki.corp.qunar.com/x/FwPFAw
     */
    private BigDecimal money;

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public DailyPrice(int hotelId, int roomId, String roomName, BigDecimal money) {
        this.hotelId = hotelId;
        this.roomId = roomId;
        this.roomName = roomName;
        this.money = money;
    }

    @Override
    public String toString() {
        return "DailyPrice{" +
                "hotelId=" + hotelId +
                ", roomId=" + roomId +
                ", roomName='" + roomName + '\'' +
                ", money=" + money +
                '}';
    }
}
