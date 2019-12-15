package com.yuan.gmall.item.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.yuan.gmall.bean.PmsProductSaleAttr;
import com.yuan.gmall.bean.PmsSkuInfo;
import com.yuan.gmall.bean.PmsSkuSaleAttrValue;
import com.yuan.gmall.service.SkuService;
import com.yuan.gmall.service.SpuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ItemController {


    @Reference
    SkuService skuService;

    @Reference
    SpuService spuService;

    @RequestMapping("{skuId}.html")
    public String item(@PathVariable String skuId, ModelMap map){

        PmsSkuInfo pmsSkuInfo = skuService.getSkuById(skuId);

        //sku对象
        map.put("skuInfo",pmsSkuInfo);
        //销售属性列表
        List<PmsProductSaleAttr> pmsProductSaleAttrs = spuService.spuSaleAttrListCheckBySku(pmsSkuInfo.getProductId(),pmsSkuInfo.getId());
        map.put("spuSaleAttrListCheckBySku",pmsProductSaleAttrs);

        return "item";
    }


    @RequestMapping("index")
    public String index(){

        return "item";

    }

}
