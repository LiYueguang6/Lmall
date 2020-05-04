package com.lyg.lmall.user.service.impl;

import com.lyg.lmall.user.bean.UmsMember;
import com.lyg.lmall.user.bean.UmsMemberReceiveAddress;
import com.lyg.lmall.user.mapper.UmsMemberReceiveAddressMapper;
import com.lyg.lmall.user.mapper.UserMapper;
import com.lyg.lmall.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UmsMemberReceiveAddressMapper umsMemberReceiveAddressMapper;

    @Override
    public List<UmsMember> getAllUser() {
//        List<UmsMember> umsMembers = userMapper.selectAllUser();
        List<UmsMember> umsMembers = userMapper.selectAll();
        return umsMembers;
    }

    @Override
    public List<UmsMemberReceiveAddress> getReceiveAddressByMemberId(String memberId) {
//        Example e = new Example(UmsMemberReceiveAddress.class);
//        e.createCriteria().andEqualTo("memberId",memberId);
//        umsMemberReceiveAddressMapper.selectByExample(e);
        UmsMemberReceiveAddress umsMemberReceiveAddress = new UmsMemberReceiveAddress();
        umsMemberReceiveAddress.setMemberId(memberId);
        List<UmsMemberReceiveAddress> umsMemberReceiveAddresses = umsMemberReceiveAddressMapper.select(umsMemberReceiveAddress);

        return umsMemberReceiveAddresses;
    }
}
