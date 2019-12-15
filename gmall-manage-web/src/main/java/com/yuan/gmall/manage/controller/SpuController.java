package com.yuan.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yuan.gmall.bean.PmsProductImage;
import com.yuan.gmall.bean.PmsProductInfo;
import com.yuan.gmall.bean.PmsProductSaleAttr;
import com.yuan.gmall.manage.util.PmsUploadUtil;
import com.yuan.gmall.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@CrossOrigin
public class SpuController {

    @Reference
    SpuService spuService;

    @Autowired
    private Environment environment;


    @RequestMapping("spuImageList")
    @ResponseBody
    public List<PmsProductImage> spuImageList(String spuId) {

        List<PmsProductImage> pmsProductImages = spuService.spuImageList(spuId);
        return pmsProductImages;
    }


    @RequestMapping("spuSaleAttrList")
    @ResponseBody
    public List<PmsProductSaleAttr> spuSaleAttrList(String spuId) {

        List<PmsProductSaleAttr> pmsProductSaleAttrs = spuService.spuSaleAttrList(spuId);
        return pmsProductSaleAttrs;
    }


    @RequestMapping("fileUpload")
    @ResponseBody
    public String fileUpload(@RequestParam("file") MultipartFile multipartFile) {
        // 将图片或者音视频上传到分布式的文件存储系统
        // 将图片的存储路径返回给页面
        String imgUrl = PmsUploadUtil.uploadImage(multipartFile, environment.getProperty("tracker.ip"));
        System.out.println(imgUrl);
        return imgUrl;
    }

    @RequestMapping("saveSpuInfo")
    @ResponseBody
    public String saveSpuInfo(@RequestBody PmsProductInfo pmsProductInfo) {

        spuService.saveSpuInfo(pmsProductInfo);

        return "success";
    }

    @RequestMapping("spuList")
    @ResponseBody
    public List<PmsProductInfo> spuList(String catalog3Id) {

        List<PmsProductInfo> pmsProductInfos = spuService.spuList(catalog3Id);

        return pmsProductInfos;
    }
}
