package rabbitmq.work;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import rabbitmq.util.ConnectionUtil;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Produce {
	
	private final static  String  QUEUE_NAME="hello_queue_work";
	
	public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
		
		Connection conn = ConnectionUtil.getConnection();
		
		Channel channel=conn.createChannel();
		
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		
		
		for (int i = 0; i < 50; i++) {
			String message="hello world"+i;
			channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
			System.out.println("send message :"+message);
			Thread.sleep(i*10);
		}
		
		channel.close();
		
		conn.close();
		
		
	}

}
