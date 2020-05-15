package com.lyg.lmall.service;

import com.lyg.lmall.bean.UmsMember;
import com.lyg.lmall.bean.UmsMemberReceiveAddress;

import java.util.List;

public interface UserService {
    List<UmsMember> getAllUser();

    List<UmsMemberReceiveAddress> getReceiveAddressByMemberId(String memberId);

}
