package com.lsq.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.QueueingConsumer.Delivery;
import com.rabbitmq.client.ShutdownSignalException;

public class MqConsumerTest {
	public static void main(String[] args) throws IOException, TimeoutException, ShutdownSignalException, ConsumerCancelledException, InterruptedException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("127.0.0.1");
		factory.setPort(5672);
		factory.setUsername("lsq");
		factory.setPassword("123456");
		Connection conn = factory.newConnection();
		Channel channel=conn.createChannel();
		String queueName ="queue";
		channel.queueDeclare(queueName, false, false, false, null);
		QueueingConsumer consumer = new QueueingConsumer(channel) ;
		channel.basicConsume(queueName, true, consumer) ;  
		int i=0;
		    //循环获取消息  
		    while(true){  
		    	
		        //循环获取信息  
		        //指向下一个消息，如果没有会一直阻塞
		        Delivery delivery = consumer.nextDelivery() ;  

		        String msg = new String(delivery.getBody()) ;    

		        System.out.println("接收 message[" + msg+i + "] from " + queueName+i); 
		        i++;
		    }  
		
	}
	
	
}
