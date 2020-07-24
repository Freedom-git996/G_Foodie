package com.vectory.controller.center;

import com.vectory.response.CommonReturnType;
import com.vectory.service.ICenterUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(value = "USER_CENTER")
@RestController
@RequestMapping("center")
public class CenterController {

    @Resource
    private ICenterUserService centerUserService;

    @ApiOperation(value = "USER_INFO", httpMethod = "GET")
    @GetMapping("userInfo")
    public CommonReturnType userInfo(@ApiParam(name = "userId", value = "用户ID", required = true)
                                   @RequestParam String userId) {
        return CommonReturnType.success(centerUserService.queryUserInfo(userId));
    }
}
