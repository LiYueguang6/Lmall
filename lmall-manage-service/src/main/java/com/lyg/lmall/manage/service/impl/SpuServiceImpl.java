package com.lyg.lmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lyg.lmall.bean.*;
import com.lyg.lmall.manage.mapper.*;
import com.lyg.lmall.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class SpuServiceImpl implements SpuService {
    @Autowired
    PmsProductInfoMapper pmsProductInfoMapper;
    @Autowired
    PmsBaseSaleAttrMapper pmsBaseSaleAttrMapper;
    @Autowired
    PmsProductImageMapper pmsProductImageMapper;
    @Autowired
    PmsProductSaleAttrMapper pmsProductSaleAttrMapper;
    @Autowired
    PmsProductSaleAttrValueMapper pmsProductSaleAttrValueMapper;
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
    public void saveSpuInfo(PmsProductInfo pmsProductInfo) {
        // 保存商品信息
        pmsProductInfoMapper.insertSelective(pmsProductInfo);
        // 获取商品ID
        String productId = pmsProductInfo.getId();
        // 保存商品图片信息
        List<PmsProductImage> pmsProductImages = pmsProductInfo.getSpuImageList();
        for (PmsProductImage pmsProductImage : pmsProductImages) {
            pmsProductImage.setProductId(productId);
            pmsProductImageMapper.insertSelective(pmsProductImage);
        }
        // 增加销售属性（是否包括颜色、大小之类的），内部再实现保存销售属性下的销售属性值
        List<PmsProductSaleAttr> pmsProductSaleAttrs = pmsProductInfo.getSpuSaleAttrList();
        for (PmsProductSaleAttr pmsProductSaleAttr : pmsProductSaleAttrs) {
            pmsProductSaleAttr.setProductId(productId);
            pmsProductSaleAttrMapper.insertSelective(pmsProductSaleAttr);

            List<PmsProductSaleAttrValue> pmsProductSaleAttrValues = pmsProductSaleAttr.getSpuSaleAttrValueList();
            for (PmsProductSaleAttrValue pmsProductSaleAttrValue : pmsProductSaleAttrValues) {
                pmsProductSaleAttrValue.setProductId(productId);
                pmsProductSaleAttrValueMapper.insertSelective(pmsProductSaleAttrValue);
            }

        }
    }
}
