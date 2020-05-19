package com.lyg.lmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lyg.lmall.bean.PmsBaseAttrInfo;
import com.lyg.lmall.bean.PmsBaseAttrValue;
import com.lyg.lmall.manage.mapper.PmsBaseAttrInfoMapper;
import com.lyg.lmall.manage.mapper.PmsBaseAttrValueMapper;
import com.lyg.lmall.service.AttrService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class AttrServiceImpl implements AttrService {
    @Autowired
    PmsBaseAttrInfoMapper pmsBaseAttrInfoMapper;
    @Autowired
    PmsBaseAttrValueMapper pmsBaseAttrValueMapper;
    @Override
    public List<PmsBaseAttrInfo> attrInfoList(String catalog3Id) {
        PmsBaseAttrInfo pmsBaseAttrInfo = new PmsBaseAttrInfo();
        pmsBaseAttrInfo.setCatalog3Id(catalog3Id);
        return pmsBaseAttrInfoMapper.select(pmsBaseAttrInfo);
    }
}
