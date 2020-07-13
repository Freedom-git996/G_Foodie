package com.vectory.service;

import com.vectory.bo.CenterUserBO;
import com.vectory.pojo.Users;

public interface ICenterUserService {

    /**
     * 根据用户id查询用户信息
     * @param userId userId
     * @return Users
     */
    Users queryUserInfo(String userId);

    /**
     * 修改用户信息
     * @param userId userId
     * @param centerUserBO centerUserBO
     */
    Users updateUserInfo(String userId, CenterUserBO centerUserBO);

    /**
     * 用户头像更新
     * @param userId userId
     * @param faceUrl faceUrl
     * @return Users
     */
    Users updateUserFace(String userId, String faceUrl);
}
