package com.vectory.service;

import com.vectory.qo.UserPassportQO;
import com.vectory.vo.UserVO;

public interface IUserService {

    /**
     * 判断用户名是否存在
     * @param username 用户名
     * @return boolean
     */
    boolean queryUsernameIsExist(String username);

    /**
     * 创建用户
     * @param userPassportQO userBo
     * @return Users
     */
    UserVO createUser(UserPassportQO userPassportQO);

    /**
     * 检索用户名和密码是否匹配，用于登录
     * @param userPassportQO userBo
     * @return Users
     */
    UserVO queryUserForLogin(UserPassportQO userPassportQO);
}
