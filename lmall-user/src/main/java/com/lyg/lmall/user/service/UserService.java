package com.lyg.lmall.user.service;

import com.lyg.lmall.user.bean.UmsMember;
import com.lyg.lmall.user.bean.UmsMemberReceiveAddress;

import java.util.List;

public interface UserService {
    List<UmsMember> getAllUser();

    List<UmsMemberReceiveAddress> getReceiveAddressByMemberId(String memberId);
}
