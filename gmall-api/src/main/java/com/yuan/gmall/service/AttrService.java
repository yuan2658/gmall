package com.yuan.gmall.service;

import com.yuan.gmall.bean.PmsBaseAttrInfo;
import com.yuan.gmall.bean.PmsBaseAttrValue;
import com.yuan.gmall.bean.PmsBaseSaleAttr;

import java.util.List;
import java.util.Set;


public interface AttrService {
    List<PmsBaseAttrInfo> attrInfoList(String catalog3Id);

    String saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo);

    List<PmsBaseAttrValue> getAttrValueList(String attrId);

    List<PmsBaseSaleAttr> baseSaleAttrList();

    List<PmsBaseAttrInfo> getAttrValueListByValueId(Set<String> valueIdSet);

}
