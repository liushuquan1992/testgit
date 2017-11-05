package rabbitmq.helloworld;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;

import rabbitmq.util.ConnectionUtil;

public class HelloWorldConsumeTest {
	
	private static final String QUEUE_NAME="hello_queue";
	
	public static void main(String[] args) throws IOException, TimeoutException, ShutdownSignalException, ConsumerCancelledException, InterruptedException {
		Connection conn = ConnectionUtil.getConnection();
		Channel channel = conn.createChannel();
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		QueueingConsumer consumer=new QueueingConsumer(channel);
		channel.basicConsume(QUEUE_NAME, true,consumer);
		while(true){
			QueueingConsumer.Delivery delivery =consumer.nextDelivery();
			String message= new String(delivery.getBody());
			System.out.println("receive message :"+message);
			
			
		}
		
	}

}
