package com.vectory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vectory.qo.OperateAddressQO;
import com.vectory.enums.YesOrNo;
import com.vectory.mapper.UserAddressMapper;
import com.vectory.pojo.UserAddress;
import com.vectory.service.IAddressService;
import org.n3r.idworker.Sid;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class AddressServiceImpl implements IAddressService {

    @Resource
    private UserAddressMapper userAddressMapper;
    @Resource
    private Sid sid;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<UserAddress> queryAll(String userId) {
        QueryWrapper<UserAddress> userAddressQueryWrapper = new QueryWrapper<>();
        userAddressQueryWrapper.eq("user_id", userId);
        return userAddressMapper.selectList(userAddressQueryWrapper);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void addNewUserAddress(OperateAddressQO operateAddressQO) {
        int isDefault = 0;
        List<UserAddress> addressList = this.queryAll(operateAddressQO.getUserId());
        if (addressList == null || addressList.isEmpty())
            isDefault = 1;
        String addressId = sid.nextShort();
        UserAddress newAddress = new UserAddress();
        BeanUtils.copyProperties(operateAddressQO, newAddress);
        newAddress.setId(addressId);
        newAddress.setIsDefault(isDefault);
        newAddress.setCreatedTime(new Date());
        newAddress.setUpdatedTime(new Date());
        userAddressMapper.insert(newAddress);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateUserAddress(OperateAddressQO operateAddressQO) {
        String addressId = operateAddressQO.getAddressId();
        UserAddress pendingAddress = new UserAddress();
        BeanUtils.copyProperties(operateAddressQO, pendingAddress);
        pendingAddress.setId(addressId);
        pendingAddress.setUpdatedTime(new Date());
        userAddressMapper.updateById(pendingAddress);
    }

    @Override
    public void deleteUserAddress(String userId, String addressId) {
        QueryWrapper<UserAddress> userAddressQueryWrapper = new QueryWrapper<>();
        userAddressQueryWrapper.eq("id", addressId);
        userAddressQueryWrapper.eq("user_id", userId);
        userAddressMapper.delete(userAddressQueryWrapper);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateUserAddressToBeDefault(String userId, String addressId) {
        QueryWrapper<UserAddress> userAddressQueryWrapper = new QueryWrapper<>();
        userAddressQueryWrapper.eq("user_id", userId);
        userAddressQueryWrapper.eq("is_default", YesOrNo.YES.type);
        List<UserAddress> list  = userAddressMapper.selectList(userAddressQueryWrapper);
        for (UserAddress ua : list) {
            ua.setIsDefault(YesOrNo.NO.type);
            userAddressMapper.updateById(ua);
        }

        UserAddress defaultAddress = new UserAddress();
        defaultAddress.setId(addressId);
        defaultAddress.setUserId(userId);
        defaultAddress.setIsDefault(YesOrNo.YES.type);
        userAddressMapper.updateById(defaultAddress);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public UserAddress queryUserAddres(String userId, String addressId) {
        QueryWrapper<UserAddress> userAddressQueryWrapper = new QueryWrapper<>();
        userAddressQueryWrapper.eq("id", addressId);
        userAddressQueryWrapper.eq("user_id", userId);
        return userAddressMapper.selectOne(userAddressQueryWrapper);
    }
}
