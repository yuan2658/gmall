package com.yuan.gmall.order.mq;

import com.yuan.gmall.bean.OmsOrder;
import com.yuan.gmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.MapMessage;

/**
 * @author yy
 */
@Component
public class OrderServiceMqListener {

    @Autowired
    OrderService orderService;

    @JmsListener(destination = "PAYHMENT_SUCCESS_QUEUE",containerFactory = "jmsQueueListener")
    public void consumePaymentResult(MapMessage mapMessage) throws JMSException {

        String outTradeNo = mapMessage.getString("out_trade_no");

        // 更新订单状态业务
        System.out.println(outTradeNo);

        OmsOrder omsOrder = new OmsOrder();
        omsOrder.setOrderSn(outTradeNo);
        orderService.updateOrder(omsOrder);

        System.out.println("11111111111111");


    }
}
