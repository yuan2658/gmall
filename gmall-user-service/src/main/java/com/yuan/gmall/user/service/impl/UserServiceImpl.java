package com.yuan.gmall.user.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.yuan.gmall.bean.UmsMember;
import com.yuan.gmall.bean.UmsMemberReceiveAddress;
import com.yuan.gmall.service.UserService;
import com.yuan.gmall.user.mapper.UmsMemberReceiveAddressMapper;
import com.yuan.gmall.user.mapper.UserMapper;
import com.yuan.gmall.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UmsMemberReceiveAddressMapper umsMemberReceiveAddressMapper;

    @Autowired
    RedisUtil redisUtil;

    @Override
    public List<UmsMember> getAllUser() {

        List<UmsMember> umsMembers = userMapper.selectAll();//userMapper.selectAllUser();

        return umsMembers;
    }

    @Override
    public List<UmsMemberReceiveAddress> getReceiveAddressByMemberId(String memberId) {

        // 封装的参数对象
        UmsMemberReceiveAddress umsMemberReceiveAddress = new UmsMemberReceiveAddress();
        umsMemberReceiveAddress.setMemberId(memberId);
        List<UmsMemberReceiveAddress> umsMemberReceiveAddresses = umsMemberReceiveAddressMapper.select(umsMemberReceiveAddress);


//       Example example = new Example(UmsMemberReceiveAddress.class);
//       example.createCriteria().andEqualTo("memberId",memberId);
//       List<UmsMemberReceiveAddress> umsMemberReceiveAddresses = umsMemberReceiveAddressMapper.selectByExample(example);

        return umsMemberReceiveAddresses;
    }

    @Override
    public UmsMember login(UmsMember umsMember) {
        Jedis jedis = null;
        try {
            jedis = redisUtil.getJedis();

            if(jedis!=null){
                String umsMemberStr = jedis.get("user:" + umsMember.getPassword() + ":info");
                if (StringUtils.isNotBlank(umsMemberStr)) {
                    // 密码正确
                    return JSON.parseObject(umsMemberStr, UmsMember.class);
                }
            }
            // 链接redis失败，开启数据库
            UmsMember umsMemberFromDb =loginFromDb(umsMember);
            if(umsMemberFromDb!=null){
                jedis.setex("user:" + umsMember.getPassword() + ":info",60*60*24, JSON.toJSONString(umsMemberFromDb));
            }
            return umsMemberFromDb;
        }finally {
            jedis.close();
        }
    }

    @Override
    public void addUserToken(String token, String memberId) {
        Jedis jedis = redisUtil.getJedis();
        jedis.setex("user:"+memberId+":token",60*60*2,token);
        jedis.close();
    }

    @Override
    public UmsMember checkOauthUser(UmsMember umsCheck) {
        return null;
    }

    @Override
    public UmsMember getOauthUser(UmsMember umsMemberCheck) {
        return null;
    }

    private UmsMember loginFromDb(UmsMember umsMember) {
        List<UmsMember> umsMembers = userMapper.select(umsMember);
        if(umsMembers.size()>0){
            return umsMembers.get(0);
        }
        return null;
    }

    @Override
    public UmsMemberReceiveAddress getReceiveAddressById(String receiveAddressId) {
        UmsMemberReceiveAddress umsMemberReceiveAddress = new UmsMemberReceiveAddress();
        umsMemberReceiveAddress.setId(receiveAddressId);
        UmsMemberReceiveAddress umsMemberReceiveAddress1 = umsMemberReceiveAddressMapper.selectOne(umsMemberReceiveAddress);
        return umsMemberReceiveAddress1;
    }
}
