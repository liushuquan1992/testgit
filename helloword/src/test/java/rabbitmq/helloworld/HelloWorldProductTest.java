package rabbitmq.helloworld;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import rabbitmq.util.ConnectionUtil;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class HelloWorldProductTest {
	
	private final static  String  QUEUE_NAME="hello_queue";
	
	public static void main(String[] args) throws IOException, TimeoutException {
		
		Connection conn = ConnectionUtil.getConnection();
		
		Channel channel=conn.createChannel();
		
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		
		String message="hello world";
		
		channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
		
		System.out.println("send message :"+message);
		
		channel.close();
		
		conn.close();
		
		
	}

}
