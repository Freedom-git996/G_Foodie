package com.vectory.service;

import com.vectory.bo.AddressBO;
import com.vectory.pojo.UserAddress;

import java.util.List;

public interface IAddressService {

    /**
     * 根据用户id查询用户的收货地址列表
     * @param userId userId
     * @return List
     */
    List<UserAddress> queryAll(String userId);

    /**
     * 用户新增地址
     * @param addressBO addressBO
     */
    void addNewUserAddress(AddressBO addressBO);

    /**
     * 用户修改地址
     * @param addressBO addressBO
     */
    void updateUserAddress(AddressBO addressBO);

    /**
     * 根据用户id和地址id，删除对应的用户地址信息
     * @param userId userId
     * @param addressId addressId
     */
    void deleteUserAddress(String userId, String addressId);

    /**
     * 修改默认地址
     * @param userId userId
     * @param addressId addressId
     */
    void updateUserAddressToBeDefault(String userId, String addressId);

    /**
     * 根据用户id和地址id，查询具体的用户地址对象信息
     * @param userId userId
     * @param addressId addressId
     * @return UserAddress
     */
    UserAddress queryUserAddres(String userId, String addressId);
}
