package rabbitmq.util;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ConnectionUtil {
	public static Connection getConnection() throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		factory.setUsername("lsq");
		factory.setPassword("123456");
		factory.setPort(5672);
		factory.setVirtualHost("/lsq");
		Connection conn = factory.newConnection();
		return conn;
	}
	
	

}
