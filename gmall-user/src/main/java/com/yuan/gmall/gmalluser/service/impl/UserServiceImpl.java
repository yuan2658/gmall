package com.yuan.gmall.gmalluser.service.impl;



import com.alibaba.dubbo.config.annotation.Service;
import com.yuan.gmall.bean.UserAddress;
import com.yuan.gmall.bean.UserInfo;
import com.yuan.gmall.gmalluser.mapper.UserAddressMapper;
import com.yuan.gmall.gmalluser.mapper.UserInfoMapper;

import com.yuan.gmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    UserAddressMapper userAddressMapper;

    @Override
    public List<UserInfo> userInfoList() {
        return userInfoMapper.selectAll();
    }

    @Override
    public List<UserAddress> getUserAddressList(String userId) {
        UserAddress userAddress = new UserAddress();
        userAddress.setUserId(userId);
        List<UserAddress> select = userAddressMapper.select(userAddress);
        return select;
    }

    @Override
    public UserAddress getUserAddressByAddressId(String deliveryAddress) {
        UserAddress userAddress = new UserAddress();
        userAddress.setId(deliveryAddress);
        return userAddressMapper.selectOne(userAddress);
    }
}
