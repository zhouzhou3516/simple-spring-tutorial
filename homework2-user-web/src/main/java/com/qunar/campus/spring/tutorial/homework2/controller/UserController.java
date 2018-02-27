package com.qunar.campus.spring.tutorial.homework2.controller;

import com.google.common.base.Preconditions;
import com.qunar.campus.spring.tutorial.homework2.bean.APIResponse;
import com.qunar.campus.spring.tutorial.homework2.bean.Role;
import com.qunar.campus.spring.tutorial.homework2.bean.UserInfo;
import com.qunar.campus.spring.tutorial.homework2.bean.UserInfoVo;
import com.qunar.campus.spring.tutorial.homework2.controller.api.UserAddRequest;
import com.qunar.campus.spring.tutorial.homework2.service.CachedViewService;
import com.qunar.campus.spring.tutorial.homework2.service.UserManagerService;
import com.qunar.campus.spring.tutorial.homework2.util.adapter.UserInfoAdapter;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

;

/**
 * Description: UserController
 *
 * @author yushen.ma
 * @version 2015-04-04 21:19
 */
@Controller
@RequestMapping
public class UserController {

    Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private UserManagerService userManagerService;
    @Resource
    private CachedViewService cachedViewService;

    /**
     * 访问该接口返回首页.Jsp
     *
     * 你需要在model里面传入1个值
     * siteName --> 站点名称
     *
     * 并且在页面中渲染出来
     */
    @RequestMapping
    public ModelAndView homePage(Model mode) {
        return new ModelAndView("../homePage").addObject("siteName", "Spring homework");
    }

    // -------------------------------- 以下均为json接口 ---------------------------------------

    /**
     * 添加一个用户
     * 返回添加的用户。但是需要把插入数据库的id带上哦
     *
     * @param userAddRequest 添加用户请求
     * @return APIResponse<UserInfo>
     */
    @RequestMapping("user/add")
    @ResponseBody
    public APIResponse<UserInfoVo> addUser(@RequestBody UserAddRequest userAddRequest) {
        Preconditions.checkArgument(StringUtils.isNotBlank(userAddRequest.getUsername()),
                "username 不能为空");
        Preconditions.checkArgument(StringUtils.isNotBlank(userAddRequest.getPassword()),
                "password 不能为空");
        Preconditions.checkArgument(userAddRequest.getRole() != null, "role 不能为空");
        Preconditions.checkArgument(userAddRequest.getRole().getId() > 0, "role.Id 不合法");

        UserInfo userInfo = UserInfoAdapter.adaptorFrom(userAddRequest);
        Role role = userAddRequest.getRole();
        userManagerService.addUser(userInfo, role);
        Role realRole = cachedViewService.getRole(role.getId());
        return APIResponse.success(UserInfoAdapter.adaptorToVo(userInfo, realRole));
    }

    /**
     * 按照前端传入的pageNum, pageSize返回用户列表
     *
     * @return APIResponse<List<UserInfo>>
     */
    @RequestMapping("user/page")
    @ResponseBody
    public APIResponse listUser(@RequestParam(required = false) Integer pageNum,
            @RequestParam(required = false) Integer pageSize) {

        if (pageNum == null || pageNum <= 0) {
            pageNum = 1;
            logger.info("pageNum not correct {}, use default 1", pageNum);
        }
        if (pageSize == null || pageSize <= 0) {
            logger.info("pageSize  not correct {}, use default 10", pageSize);
            pageSize = 10;
        }

        return APIResponse.success(userManagerService.listUsers(pageNum, pageSize));
    }

    /**
     * 删除前端传入的userId所对应的用户
     *
     * @param userId 用户Id
     * @return APIResponse 操作结果
     */
    @ResponseBody
    @RequestMapping("user/delete")
    public APIResponse deleteUser(long userId) {
        userManagerService.deleteUser(userId);
        return APIResponse.success("删除成功");
    }

    /**
     * 前端会传入一个带有userId的用户对象,
     * 更新这个用户的其他信息
     *
     * @param userInfo 用户信息
     * @return APIResponse<UserInfo> 操作成功后将用户返回
     */
    @ResponseBody
    @RequestMapping("user/update")
    public APIResponse updateUser(UserInfo userInfo) {
        Preconditions.checkArgument(StringUtils.isNotBlank(userInfo.getUsername()),
                "username 不能为空");
        Preconditions.checkArgument(StringUtils.isNotBlank(userInfo.getPassword()),
                "password 不能为空");
        userManagerService.updateUser(userInfo);
        return APIResponse.success(userInfo);
    }

    /**
     * 列出全部用户
     *
     * @return APIResponse<Role>
     */
    @RequestMapping("roles")
    @ResponseBody
    public APIResponse listRole() {
        return APIResponse.success(userManagerService.listRoles());
    }

    /**
     * 添加一个角色
     *
     * @param role 显然前端是不会传id给你的
     * @return APIResponse<Role> 将存好的role返回给前端
     */
    @RequestMapping("role/add")
    @ResponseBody
    public APIResponse addRole(@RequestBody Role role) {
        Preconditions.checkArgument(role != null, "缺少role对象");
        Preconditions.checkArgument(StringUtils.isNotBlank(role.getName()), "缺少role name参数");
        userManagerService.addRole(role);
        return APIResponse.success(role);
    }

}
