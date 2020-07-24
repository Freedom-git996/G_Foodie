package com.vectory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vectory.qo.UserPassportQO;
import com.vectory.vo.UserVO;
import com.vectory.enums.Sex;
import com.vectory.mapper.UsersMapper;
import com.vectory.pojo.Users;
import com.vectory.response.error.BusinessException;
import com.vectory.response.error.EmBusinessResult;
import com.vectory.service.IUserService;
import com.vectory.util.EncryptUtil;
import com.vectory.util.DateUtil;
import org.n3r.idworker.Sid;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class UserServiceImpl implements IUserService {

    private static final String USER_FACE = "http://122.152.205.72:88/group1/M00/00/05/CpoxxFw_8_qAIlFXAAAcIhVPdSg994.png";

    @Resource
    private UsersMapper usersMapper;
    @Resource
    private Sid sid;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean queryUsernameIsExist(String username) {
        QueryWrapper<Users> usersQueryWrapper = new QueryWrapper<>();
        usersQueryWrapper.eq("username", username);
        return usersMapper.selectOne(usersQueryWrapper) != null;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public UserVO createUser(UserPassportQO userPassportQO) {
        Users user = new Users();
        user.setId(sid.nextShort());
        user.setUsername(userPassportQO.getUsername());
        user.setNickname(userPassportQO.getUsername());
        user.setPassword(EncryptUtil.getInstance().MD5(userPassportQO.getPassword()));
        user.setFace(USER_FACE);
        user.setBirthday(DateUtil.stringToDate("1970-01-01"));
        user.setSex(Sex.secret.type);
        user.setCreatedTime(new Date());
        user.setUpdatedTime(new Date());
        if(usersMapper.insert(user) <= 0) throw new BusinessException(EmBusinessResult.REGISTER_ERROR);
        UserVO result = new UserVO();
        BeanUtils.copyProperties(user, result);
        return result;
    }

    @Override
    public UserVO queryUserForLogin(UserPassportQO userPassportQO) {
        QueryWrapper<Users> usersQueryWrapper = new QueryWrapper<>();
        usersQueryWrapper.eq("username", userPassportQO.getUsername());
        usersQueryWrapper.eq("password", EncryptUtil.getInstance().MD5(userPassportQO.getPassword()));
        Users record = usersMapper.selectOne(usersQueryWrapper);
        if(record == null) throw new BusinessException(EmBusinessResult.LOGIN_ERROR);
        UserVO result = new UserVO();
        BeanUtils.copyProperties(record, result);
        return result;
    }
}
