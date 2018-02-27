package com.qunar.campus.spring.meta.data;

import java.io.Serializable;

/**
 * Description: User
 *
 * @author yushen.ma
 * @version 2015-03-23 14:42
 */
public class User implements Serializable {

    private static final long serialVersionUID = -5334408912188522583L;

    private int id;

    private String username;

    @ToStringIgnore
    private String password;

    private String firstName;

    private int age;

    private boolean male;

    public User(int id, String username, String password, String firstName, int age, boolean male) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.age = age;
        this.male = male;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isMale() {
        return male;
    }

    public void setMale(boolean male) {
        this.male = male;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", age=" + age +
                ", male=" + male +
                '}';
    }
}
