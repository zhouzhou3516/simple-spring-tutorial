package com.qunar.campus.spring.tutorial.mvc.parameters.web.bean;

import java.util.List;

/**
 * Description: ClassInfo
 *
 * @author yushen.ma
 * @version 2015-03-28 18:38
 */
public class ClassInfo {

    public int classNo;

    public String className;

    public List<UserInfo> users;

    public ClassInfo() { }

    public ClassInfo(int classNo, String className, List<UserInfo> users) {
        this.classNo = classNo;
        this.className = className;
        this.users = users;
    }

    public int getClassNo() {
        return classNo;
    }

    public void setClassNo(int classNo) {
        this.classNo = classNo;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<UserInfo> getUsers() {
        return users;
    }

    public void setUsers(List<UserInfo> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "ClassInfo{" +
                "classNo=" + classNo +
                ", className='" + className + '\'' +
                ", users=" + users +
                '}';
    }
}
