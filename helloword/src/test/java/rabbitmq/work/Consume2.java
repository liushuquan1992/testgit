package rabbitmq.work;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import rabbitmq.util.ConnectionUtil;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;

public class Consume2 {
	
	private static final String QUEUE_NAME="hello_queue_work";
	
	public static void main(String[] args) throws IOException, TimeoutException, ShutdownSignalException, ConsumerCancelledException, InterruptedException {
		Connection conn = ConnectionUtil.getConnection();
		Channel channel = conn.createChannel();
		
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		channel.basicQos(1);
		
		QueueingConsumer consumer=new QueueingConsumer(channel);
		channel.basicConsume(QUEUE_NAME, false,consumer);
		while(true){
			QueueingConsumer.Delivery delivery =consumer.nextDelivery();
			String message= new String(delivery.getBody());
			System.out.println("receive2 message :"+message);
			Thread.sleep(1000);
			channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
		}
		
	}

}
