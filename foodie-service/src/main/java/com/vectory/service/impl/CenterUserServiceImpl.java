package com.vectory.service.impl;

import com.vectory.qo.UserUpdateQO;
import com.vectory.vo.UserVO;
import com.vectory.mapper.UsersMapper;
import com.vectory.pojo.Users;
import com.vectory.response.error.BusinessException;
import com.vectory.response.error.EmBusinessResult;
import com.vectory.service.ICenterUserService;
import org.n3r.idworker.Sid;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class CenterUserServiceImpl implements ICenterUserService {

    @Resource
    public UsersMapper usersMapper;
    @Resource
    private Sid sid;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public UserVO queryUserInfo(String userId) {
        Users record = usersMapper.selectById(userId);
        if(record == null) throw new BusinessException(EmBusinessResult.NO_USER_LOGIN);
        UserVO result = new UserVO();
        BeanUtils.copyProperties(record, result);
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public UserVO updateUserInfo(UserUpdateQO userUpdateQO) {
        Users updateUser = new Users();
        BeanUtils.copyProperties(userUpdateQO, updateUser);
        updateUser.setUpdatedTime(new Date());
        if(usersMapper.updateById(updateUser) <= 0)
            throw new BusinessException(EmBusinessResult.USERINFO_UPDATE_FAIL);
        return queryUserInfo(userUpdateQO.getId());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public UserVO updateUserFace(String userId, String faceUrl) {
        Users updateUser = new Users();
        updateUser.setId(userId);
        updateUser.setFace(faceUrl);
        updateUser.setUpdatedTime(new Date());
        usersMapper.updateById(updateUser);
        return queryUserInfo(userId);
    }
}
