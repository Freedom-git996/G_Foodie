package com.vectory.controller;

import com.vectory.qo.UserPassportQO;
import com.vectory.util.validator.ValidatorGroup;
import com.vectory.util.validator.ValidatorUtil;
import com.vectory.vo.UserVO;
import com.vectory.response.CommonReturnType;
import com.vectory.response.error.EmBusinessResult;
import com.vectory.service.IUserService;
import com.vectory.util.JsonUtil;
import com.vectory.util.CookieUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Api(value = "USER_PASSPORT")
@RestController
@RequestMapping("passport")
public class PassportController {

    @Resource
    private IUserService userService;

    @ApiOperation(value = "USERNAME_ISEXIST", httpMethod = "GET")
    @GetMapping("usernameIsExist")
    public CommonReturnType usernameIsExist(@ApiParam(name = "username", value = "用户名", required = true)
                                                @RequestParam(value = "username") String username) {
        return userService.queryUsernameIsExist(username) ?
                CommonReturnType.fail(EmBusinessResult.USERNAME_REPEAT) : CommonReturnType.success();
    }

    @ApiOperation(value = "USER_REGIST", httpMethod = "POST")
    @PostMapping("regist")
    public CommonReturnType regist(@RequestBody UserPassportQO userPassportQO,
                                   HttpServletRequest request,
                                   HttpServletResponse response) {
        ValidatorUtil.validate(userPassportQO, ValidatorGroup.RegisterBean.class);
        if (!StringUtils.equals(userPassportQO.getPassword(), userPassportQO.getConfirmPassword()))
            return CommonReturnType.fail(EmBusinessResult.REPEAT_PASSWORD_ERROR);
        if (userService.queryUsernameIsExist(userPassportQO.getUsername()))
            return CommonReturnType.fail(EmBusinessResult.USERNAME_REPEAT);
        UserVO userVO = userService.createUser(userPassportQO);
        CookieUtil.setCookie(request, response, "user", JsonUtil.obj2String(userVO));
        // TODO 二期 弃用cookie，生成用户token，存入redis会话 同步购物车数据
        return CommonReturnType.success();
    }

    @ApiOperation(value = "USER_LOGIN", httpMethod = "POST")
    @PostMapping("login")
    public CommonReturnType login(@RequestBody UserPassportQO userPassportQO,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
        ValidatorUtil.validate(userPassportQO, ValidatorGroup.LoginBean.class);
        UserVO userVO = userService.queryUserForLogin(userPassportQO);
        CookieUtil.setCookie(request, response, "user", JsonUtil.obj2String(userVO), true);
        // TODO 二期 弃用cookie，生成用户token，存入redis会话 同步购物车数据
        return CommonReturnType.success(userVO);
    }


    @ApiOperation(value = "USER_LOGOUT", httpMethod = "POST")
    @PostMapping("logout")
    public CommonReturnType logout(HttpServletRequest request,
                                   HttpServletResponse response) {
        CookieUtil.deleteCookie(request, response, "user");
        // TODO 用户退出登录，需要清空购物车
        // TODO 二期 分布式会话中需要清除用户数据
        return CommonReturnType.success();
    }
}
