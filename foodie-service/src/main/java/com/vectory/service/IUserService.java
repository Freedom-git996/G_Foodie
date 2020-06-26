package com.vectory.service;

import com.vectory.bo.UserBO;
import com.vectory.pojo.Users;

public interface IUserService {

    /**
     * 判断用户名是否存在
     * @param username 用户名
     * @return boolean
     */
    boolean queryUsernameIsExist(String username);

    /**
     * 创建用户
     * @param userBo userBo
     * @return Users
     */
    Users createUser(UserBO userBo);

    /**
     * 检索用户名和密码是否匹配，用于登录
     * @param username 用户名
     * @param password 密码
     * @return Users
     */
    Users queryUserForLogin(String username, String password);
}
