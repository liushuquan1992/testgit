package com.lsq.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class MqPublicTest {
	
	public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("127.0.0.1");
		factory.setPort(5672);
		factory.setUsername("lsq");
		factory.setPassword("123456");
		Connection conn = factory.newConnection();
		Channel channel=conn.createChannel();
		String msg="hello mq";
		String queueName ="queue";
		for(int i=0;i<80;i++){
			channel.queueDeclare(queueName, false, false, false, null);
			channel.basicPublish("", queueName, null, msg.getBytes());
			System.out.println("发送  message[" + msg+i + "] to "+ queueName+i +" success!"); 
			Thread.sleep(10000);
		}
	    //关闭管道
	    channel.close();  
	    //关闭连接
	    conn.close();
	}
}
