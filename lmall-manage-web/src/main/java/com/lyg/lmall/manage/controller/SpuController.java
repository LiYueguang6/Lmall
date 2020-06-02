package com.lyg.lmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lyg.lmall.bean.PmsBaseSaleAttr;
import com.lyg.lmall.bean.PmsProductInfo;
import com.lyg.lmall.manage.util.PmsUploadUtil;
import com.lyg.lmall.service.SpuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@CrossOrigin
public class SpuController {
    @Reference
    SpuService spuService;


    @RequestMapping("spuList")
    @ResponseBody
    public List<PmsProductInfo> spuList(String catalog3Id){
        List<PmsProductInfo> pmsProductInfos = spuService.spuList(catalog3Id);
        return pmsProductInfos;
    }
    @RequestMapping("baseSaleAttrList")
    @ResponseBody
    public List<PmsBaseSaleAttr> baseSaleAttrList(){
        List<PmsBaseSaleAttr> pmsBaseSaleAttrs = spuService.baseSaleAttrList();
        return pmsBaseSaleAttrs;
    }
    @RequestMapping("saveSpuInfo")
    @ResponseBody
    public String saveSpuInfo(@RequestBody PmsProductInfo pmsProductInfo){
        List<PmsProductInfo> pmsProductInfoList = spuService.saveSpuInfo(pmsProductInfo);
        return "success";
    }
    @RequestMapping("fileUpload")
    @ResponseBody
    public String fileUpload(@RequestParam("file") MultipartFile multipartFile){
        // 将图片或者音视频上传到分布式文件系统
        // 将文件的路径返回给页面。
        String imgUrl = PmsUploadUtil.uploadImage(multipartFile);
        return imgUrl;
    }
}
