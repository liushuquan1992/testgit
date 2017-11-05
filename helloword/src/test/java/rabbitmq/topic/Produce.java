package rabbitmq.topic;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import rabbitmq.util.ConnectionUtil;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Produce {
	
	private final static  String  EXCHANGE_NAME="hello_exchange_topic";
	
	public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
		
		Connection conn = ConnectionUtil.getConnection();
		
		Channel channel=conn.createChannel();
		
		channel.exchangeDeclare(EXCHANGE_NAME,"topic");
		
		String message=" id=1001商品删除了 ";
		channel.basicPublish(EXCHANGE_NAME,"item.delete", null, message.getBytes());
		System.out.println("send message :"+message);
		
		channel.close();
		conn.close();
	}

}
