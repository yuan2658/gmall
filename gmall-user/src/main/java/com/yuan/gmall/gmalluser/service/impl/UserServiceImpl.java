package com.yuan.gmall.gmalluser.service.impl;



import com.yuan.gmall.bean.UserInfo;
import com.yuan.gmall.gmalluser.mapper.UserInfoMapper;

import com.yuan.gmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    public List<UserInfo> userInfoList() {
        return userInfoMapper.selectAll();
    }
}
