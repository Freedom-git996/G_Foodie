package com.vectory.controller.center;

import com.vectory.pojo.UserAddress;
import com.vectory.qo.OperateAddressQO;
import com.vectory.response.CommonReturnType;
import com.vectory.service.IAddressService;
import com.vectory.util.validator.ValidatorGroup;
import com.vectory.util.validator.ValidatorUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(value = "ADDRESS")
@RequestMapping("address")
@RestController
public class MyAddressController {

    @Resource
    private IAddressService addressService;

    @ApiOperation(value = "QUERY_ADDRESS", httpMethod = "GET")
    @GetMapping("list")
    public CommonReturnType list(@ApiParam(name = "userId", value = "用户ID", required = true)
                                 @RequestParam String userId) {
        List<UserAddress> list = addressService.queryAll(userId);
        return CommonReturnType.success(list);
    }

    @ApiOperation(value = "ADD_ADDRESS", httpMethod = "POST")
    @PostMapping("add")
    public CommonReturnType add(@RequestBody OperateAddressQO operateAddressQO) {
        ValidatorUtil.validate(operateAddressQO);
        addressService.addNewUserAddress(operateAddressQO);
        return CommonReturnType.success();
    }

    @ApiOperation(value = "UPDATE_ADDRESS", httpMethod = "POST")
    @PostMapping("update")
    public CommonReturnType update(@RequestBody OperateAddressQO operateAddressQO) {
        ValidatorUtil.validate(operateAddressQO, ValidatorGroup.AddressUpdateBean.class);
        addressService.updateUserAddress(operateAddressQO);
        return CommonReturnType.success();
    }

    @ApiOperation(value = "DELETE_ADDRESS", httpMethod = "POST")
    @PostMapping("delete")
    public CommonReturnType delete(@RequestBody OperateAddressQO operateAddressQO) {
        addressService.deleteUserAddress(operateAddressQO.getUserId(), operateAddressQO.getAddressId());
        return CommonReturnType.success();
    }

    @ApiOperation(value = "SET_DEFAULT_ADDRESS", httpMethod = "POST")
    @PostMapping("setDefalut")
    public CommonReturnType setDefalut(@RequestBody OperateAddressQO operateAddressQO) {
        addressService.updateUserAddressToBeDefault(operateAddressQO.getUserId(), operateAddressQO.getAddressId());
        return CommonReturnType.success();
    }
}
