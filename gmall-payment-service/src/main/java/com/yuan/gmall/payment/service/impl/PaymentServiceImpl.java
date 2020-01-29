package com.yuan.gmall.payment.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.yuan.gmall.bean.PaymentInfo;
import com.yuan.gmall.payment.mapper.PaymentInfoMapper;
import com.yuan.gmall.service.PaymentService;
import com.yuan.gmall.utils.ActiveMqUtil;
import org.apache.activemq.command.ActiveMQMapMessage;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import javax.jms.*;


@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    PaymentInfoMapper paymentInfoMapper;

    @Autowired
    ActiveMqUtil activeMqUtil;

    @Override
    public void savePaymentInfo(PaymentInfo paymentInfo) {
        paymentInfoMapper.insertSelective(paymentInfo);
    }


    @Override
    public void updatePayment(PaymentInfo paymentInfo) {
        String orderSn = paymentInfo.getOrderSn();
        Example e = new Example(PaymentInfo.class);
        e.createCriteria().andEqualTo("orderSn",orderSn);

        Connection connection = null;
        Session session = null;
        try {
            connection = activeMqUtil.getConnectionFactory().createConnection();
            session = connection.createSession(true, Session.SESSION_TRANSACTED);

            paymentInfoMapper.updateByExampleSelective(paymentInfo,e);
            // 支付成功后，引起的系统服务-》订单服务的更新-》库存服务-》物流服务
            // 调用mq发送支付成功的消息
            Queue payhmentSuccessQueue = session.createQueue("PAYHMENT_SUCCESS_QUEUE");
            MessageProducer producer = session.createProducer(payhmentSuccessQueue);

            //TextMessage textMessage=new ActiveMQTextMessage(); // 字符串文本
            // hash结构
            MapMessage mapMessage = new ActiveMQMapMessage();
            mapMessage.setString("out_trade_no",paymentInfo.getOrderSn());
            producer.send(mapMessage);
            session.commit();

        }catch (Exception ex){
            // 消息回滚
            try {
                session.rollback();
            } catch (JMSException exc) {
                exc.printStackTrace();
            }
        }finally {
            try {
                connection.close();
                session.close();
            } catch (JMSException ex) {
                ex.printStackTrace();
            }
        }


    }
}
