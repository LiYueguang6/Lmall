package com.lyg.lmall.service;

import com.lyg.lmall.bean.PmsBaseSaleAttr;
import com.lyg.lmall.bean.PmsProductInfo;

import java.util.List;

public interface SpuService {
    List<PmsProductInfo> spuList(String catalog3Id);

    List<PmsBaseSaleAttr> baseSaleAttrList();

    void saveSpuInfo(PmsProductInfo pmsProductInfo);
}
