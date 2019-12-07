package com.yuan.gmall.service;


import com.yuan.gmall.bean.UserAddress;
import com.yuan.gmall.bean.UserInfo;

import java.util.List;

public interface UserService {

    List<UserInfo> userInfoList();

    List<UserAddress> getUserAddressList(String userId);

    UserAddress getUserAddressByAddressId(String deliveryAddress);
}
