package com.yuan.gmall.service;

import com.yuan.gmall.bean.PaymentInfo;

import java.util.Map;

public interface PaymentService {

    String getPaymentFrom(String outTradeNo);

    void savePaymentInfo(PaymentInfo paymentInfo);

    void updatePayment(PaymentInfo paymentInfo);

    void sendDelayPaymentResultCheckQueue(String outTradeNo,int count);

    Map<String,Object> checkAlipayPayment(String outTradeNo);
}
