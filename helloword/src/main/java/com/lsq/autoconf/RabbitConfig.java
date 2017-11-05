package com.lsq.autoconf;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class RabbitConfig {
	
	 /**
     * 用户保存
     */
    public static final String QUEUE_NAME_USER = "user-save-queue";
    /**
     * 分用户
     */
    public static final String QUEUE_NAME_SINGLE = "user-server-queue-single";
    /**
     * 短信消息队列
     */
    public static final String MESSAGE_QUEUE_NAME = "user-queue-message";

	@Value("${spring.rabbitmq.addresses}")
    private String address;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;
    
    //用户保存队列
    @Bean(name = "userSaveConnectionFactory")
    @Primary
    public ConnectionFactory userSaveConnectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(address);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        return connectionFactory;
    }
    @Bean(name = "userSaveRabbitTemplate")
    @Primary
    public RabbitTemplate userSaveRabbitTemplate(
            @Qualifier("userSaveConnectionFactory") ConnectionFactory connectionFactory
    ) {
        RabbitTemplate userSaveRabbitTemplate = new RabbitTemplate(connectionFactory);
        return userSaveRabbitTemplate;
    }
    @Bean(name = "userSaveFactory")
    public SimpleRabbitListenerContainerFactory orderSaveFactory(
            SimpleRabbitListenerContainerFactoryConfigurer configurer,
            @Qualifier("userSaveConnectionFactory") ConnectionFactory connectionFactory
    ) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        return factory;
    }
    
    //分单
    @Bean(name = "singleConnectionFactory")
    public ConnectionFactory secondConnectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(address);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        return connectionFactory;
    }
    @Bean(name = "singleRabbitTemplate")
    public RabbitTemplate singleRabbitTemplate(
            @Qualifier("singleConnectionFactory") ConnectionFactory connectionFactory
    ) {
        RabbitTemplate singleRabbitTemplate = new RabbitTemplate(connectionFactory);
        return singleRabbitTemplate;
    }
    @Bean(name = "singleFactory")
    public SimpleRabbitListenerContainerFactory singleFactory(
            SimpleRabbitListenerContainerFactoryConfigurer configurer,
            @Qualifier("singleConnectionFactory") ConnectionFactory connectionFactory
    ) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        return factory;
    }
    //短信队列
    @Bean(name = "messageConnectionFactory")
    public ConnectionFactory threeConnectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(address);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        return connectionFactory;
    }
    @Bean(name = "messageRabbitTemplate")
    public RabbitTemplate messageRabbitTemplate(
            @Qualifier("messageConnectionFactory") ConnectionFactory connectionFactory
    ) {
        RabbitTemplate messageRabbitTemplate = new RabbitTemplate(connectionFactory);
        return messageRabbitTemplate;
    }
    
    @Bean(name = "messageFactory")
    public SimpleRabbitListenerContainerFactory messageFactory(
            SimpleRabbitListenerContainerFactoryConfigurer configurer,
            @Qualifier("messageConnectionFactory") ConnectionFactory connectionFactory
    ) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        return factory;
    }
    
    //声明队列
    @Bean
    public Queue firstQueue() {
        return new Queue(QUEUE_NAME_USER,true);
    }
    
    @Bean
    public Object twoQueue(){
    	 return new Queue(MESSAGE_QUEUE_NAME,true);
    }
    
    @Bean
    public Object threeQueue(){
    	return new Queue(QUEUE_NAME_SINGLE,true);
    }
}
