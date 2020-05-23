package com.lyg.lmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lyg.lmall.bean.PmsBaseAttrInfo;
import com.lyg.lmall.bean.PmsBaseAttrValue;
import com.lyg.lmall.manage.mapper.PmsBaseAttrInfoMapper;
import com.lyg.lmall.manage.mapper.PmsBaseAttrValueMapper;
import com.lyg.lmall.service.AttrService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

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

    @Override
    public String savAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo) {
        String id = pmsBaseAttrInfo.getId();
        if (StringUtils.isBlank(id)) {
            // 如果为空则插入新平台属性
            pmsBaseAttrInfoMapper.insertSelective(pmsBaseAttrInfo);  //insert将null插入数据库 insertselective不将NULL值插入数据库

            List<PmsBaseAttrValue> attrValueList = pmsBaseAttrInfo.getAttrValueList();
            for (PmsBaseAttrValue pmsBaseAttrValue : attrValueList) {
                pmsBaseAttrValue.setAttrId(pmsBaseAttrInfo.getId());
                pmsBaseAttrValueMapper.insertSelective(pmsBaseAttrValue);

            }
        } else {
            // id不为空则执行修改
            // 先更新PmsBaseAttrInfo的列表
            Example example = new Example(PmsBaseAttrInfo.class);
            example.createCriteria().andEqualTo("id",pmsBaseAttrInfo.getCatalog3Id());
            pmsBaseAttrInfoMapper.updateByExampleSelective(pmsBaseAttrInfo, example);

            // 再删除该属性id下的所有分类
            PmsBaseAttrValue pmsBaseAttrValueDe = new PmsBaseAttrValue();
            pmsBaseAttrValueDe.setAttrId(id);
            pmsBaseAttrValueMapper.delete(pmsBaseAttrValueDe);

            // 删除后再插入新的值
            List<PmsBaseAttrValue> pmsBaseAttrValueList = pmsBaseAttrInfo.getAttrValueList();
            for (PmsBaseAttrValue pmsBaseAttrValue : pmsBaseAttrValueList) {
                pmsBaseAttrValueMapper.insertSelective(pmsBaseAttrValue);
            }
        }

        return "success";
    }

    @Override
    public List<PmsBaseAttrValue> getAttrValueList(String attrId) {
        PmsBaseAttrValue pmsBaseAttrValue = new PmsBaseAttrValue();
        pmsBaseAttrValue.setAttrId(attrId);
        return pmsBaseAttrValueMapper.select(pmsBaseAttrValue);
    }
}
