package com.vectory.controller.center;

import com.vectory.qo.AddShopCatQO;
import com.vectory.qo.DelShopCatQO;
import com.vectory.response.CommonReturnType;
import com.vectory.util.validator.ValidatorUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api(value = "SHOPCAT")
@RequestMapping("shopcart")
@RestController
public class MyShopcatController {

    @ApiOperation(value = "ADD_SHOPCAT", httpMethod = "POST")
    @PostMapping("/add")
    public CommonReturnType add(@RequestBody AddShopCatQO addShopCatQO) {
        ValidatorUtil.validate(addShopCatQO);
        // TODO 前端用户在登录的情况下，添加商品到购物车，会同时在后端同步购物车到redis缓存
        return CommonReturnType.success();
    }

    @ApiOperation(value = "从购物车中删除商品", notes = "从购物车中删除商品", httpMethod = "POST")
    @PostMapping("/del")
    public CommonReturnType del(@RequestBody DelShopCatQO delShopCatQO) {
        ValidatorUtil.validate(delShopCatQO);
        // TODO 用户在页面删除购物车中的商品数据，如果此时用户已经登录，则需要同步删除后端购物车中的商品
        return CommonReturnType.success();
    }
}
