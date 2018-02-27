package com.qunar.campus.spring.tutorial.transaction.aop.bean;

import com.qunar.campus.spring.tutorial.bean.Money;

import java.io.Serializable;

/**
 * Description: Account
 *
 * @author yushen.ma
 * @version 2015-03-25 22:05
 */
public class Account implements Serializable {

    private static final long serialVersionUID = -16170820717053890L;

    public Account() { }

    public Account(int userId, Money balance) {
        this.userId = userId;
        this.balance = balance;
    }

    public Account(int id, int userId, Money balance) {
        this.id = id;
        this.userId = userId;
        this.balance = balance;
    }

    private int id;

    private int userId;

    private Money balance;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Money getBalance() {
        return balance;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", userId=" + userId +
                ", balance=" + balance +
                '}';
    }
}

