package rabbitmq.ps;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import rabbitmq.util.ConnectionUtil;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;

public class Consume1 {
	
	private static final String QUEUE_NAME="hello_queue_work1";
	
	private final static  String  EXCHANGE_NAME="hello_exchange_fanout";
	
	public static void main(String[] args) throws IOException, TimeoutException, ShutdownSignalException, ConsumerCancelledException, InterruptedException {
		Connection conn = ConnectionUtil.getConnection();
		Channel channel = conn.createChannel();
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		
		channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "");
		//同一时刻服务器只会发一条消息给消费者  能者多得
		channel.basicQos(1);
		//定义队列的消费者
		QueueingConsumer consumer=new QueueingConsumer(channel);
		//监听队列； false： 手动    true： 自动
		channel.basicConsume(QUEUE_NAME, false,consumer);
		while(true){
			QueueingConsumer.Delivery delivery =consumer.nextDelivery();
			String message= new String(delivery.getBody());
			System.out.println("receive1 message :"+message);
			Thread.sleep(10);
			//返回确认状态  手动确认  反馈消息的确认状态
			channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
		}
		
	}

}
