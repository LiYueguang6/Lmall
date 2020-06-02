package com.lyg.lmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lyg.lmall.bean.PmsBaseSaleAttr;
import com.lyg.lmall.bean.PmsProductInfo;
import com.lyg.lmall.manage.mapper.PmsBaseSaleAttrMapper;
import com.lyg.lmall.manage.mapper.PmsProductInfoMapper;
import com.lyg.lmall.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class SpuServiceImpl implements SpuService {
    @Autowired
    PmsProductInfoMapper pmsProductInfoMapper;
    @Autowired
    PmsBaseSaleAttrMapper pmsBaseSaleAttrMapper;
    @Override
    public List<PmsProductInfo> spuList(String catalog3Id) {
        PmsProductInfo pmsProductInfo = new PmsProductInfo();
        pmsProductInfo.setCatalog3Id(catalog3Id);
        return pmsProductInfoMapper.select(pmsProductInfo);
    }

    @Override
    public List<PmsBaseSaleAttr> baseSaleAttrList() {
        return pmsBaseSaleAttrMapper.selectAll();
    }

    @Override
    public List<PmsProductInfo> saveSpuInfo(PmsProductInfo pmsProductInfo) {

        return null;
    }
}
