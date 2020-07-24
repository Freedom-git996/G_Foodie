package com.vectory.service;

import com.vectory.qo.UserUpdateQO;
import com.vectory.vo.UserVO;

public interface ICenterUserService {

    /**
     * 根据用户id查询用户信息
     * @param userId userId
     * @return UserVO
     */
    UserVO queryUserInfo(String userId);

    /**
     * 修改用户信息
     * @param userUpdateQO userUpdateQO
     * @return UserVO
     */
    UserVO updateUserInfo(UserUpdateQO userUpdateQO);

    /**
     * 用户头像更新
     * @param userId userId
     * @param faceUrl faceUrl
     * @return UserVO
     */
    UserVO updateUserFace(String userId, String faceUrl);
}
