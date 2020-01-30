package com.yuan.gmall.config;

import com.yuan.gmall.utils.ActiveMqUtil;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

import javax.jms.Session;

/**
 * @author yy
 */
@Configuration
public class ActiveMqConfig {

    @Value("${spring.activemq.broker-url:disabled}")
    String brokerUrl;

    @Value("${spring.activemq.pool.enabled:disabled}")
    String listenerEnable;

    @Bean
    public ActiveMqUtil getActiveMqUtil() {
        if ("disabled".equals(brokerUrl)) {
            return null;
        }
        ActiveMqUtil activeMqUtil = new ActiveMqUtil();
        activeMqUtil.init(brokerUrl);
        return activeMqUtil;
    }

    /**
     * 定义一个消息监听器连接工厂，这里定义的是点对点模式的监听器连接工厂
     *
     * @param activeMqConnectionFactory
     * @return
     */
    @Bean(name = "jmsQueueListener")
    public DefaultJmsListenerContainerFactory jmsQueueListenerContainerFactory(ActiveMQConnectionFactory activeMqConnectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        if ("disabled".equals(listenerEnable)) {
            return null;
        }
        factory.setConnectionFactory(activeMqConnectionFactory);
        //设置并发数
        factory.setConcurrency("5");
        //重连间隔时间
        factory.setRecoveryInterval(5000L);
        factory.setSessionTransacted(false);
        factory.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);

        return factory;
    }


    @Bean
    public ActiveMQConnectionFactory activeMqConnectionFactory() {
        return new ActiveMQConnectionFactory(brokerUrl);
    }

}
