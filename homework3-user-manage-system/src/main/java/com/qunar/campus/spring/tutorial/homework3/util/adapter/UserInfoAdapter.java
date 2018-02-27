package com.qunar.campus.spring.tutorial.homework3.util.adapter;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import com.qunar.campus.spring.tutorial.homework3.bean.Role;
import com.qunar.campus.spring.tutorial.homework3.bean.UserInfo;
import com.qunar.campus.spring.tutorial.homework3.bean.UserInfoVo;
import com.qunar.campus.spring.tutorial.homework3.controller.api.UserAddRequest;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * Created by liqingzhou on 17/8/2.
 */
public class UserInfoAdapter {

    public static UserInfo adaptorFrom(UserAddRequest request) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(request.getUsername());
        userInfo.setPassword(request.getPassword());
        //随机生成8位salt，用于加密
        investSalt(userInfo);
        return userInfo;
    }

    /**
     * 随机生成salt，再对明文pwd+sal 进行hash 存储
     */
    private static void investSalt(UserInfo userInfo) {
        String salt = RandomStringUtils.randomAlphanumeric(8);
        userInfo.setSalt(salt);
        userInfo.setPassword(
                Hashing.sha512().hashString(userInfo.getPassword() + salt, Charsets.UTF_8)
                        .toString());
    }

    public static UserInfoVo adaptorToVo(UserInfo userInfo, Role role) {
        UserInfoVo vo = new UserInfoVo();
        vo.setId(userInfo.getUserId());
        vo.setUsername(userInfo.getUsername());
        vo.setPassword(userInfo.getPassword());
        if (role != null) {
            vo.setRole(role.getName());
        }
        return vo;
    }

    public static UserInfoVo adaptorToVo(UserInfo userInfo) {
        return adaptorToVo(userInfo, null);
    }


    public static void main(String[] args) {
        System.out.println(Hashing.sha1().hashString("123456saltvalue", Charsets.UTF_8)
                .toString());
    }
}
