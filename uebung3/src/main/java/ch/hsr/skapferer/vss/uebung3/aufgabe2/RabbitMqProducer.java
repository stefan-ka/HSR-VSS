package ch.hsr.skapferer.vss.uebung3.aufgabe2;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.Map;

public class RabbitMqProducer {
	private final static String QUEUE_NAME = "hello_queue";

	public static void main(String[] argv) throws IOException {
		Connection connection = createConnection();
		Channel channel = createChannel(connection);
		publishMessage(channel, argv);
		channel.close();
		connection.close();
	}

	private static Connection createConnection() throws IOException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		return factory.newConnection();
	}

	private static Channel createChannel(Connection connection) throws IOException {
		Channel channel = connection.createChannel();
		boolean durable = false;
		boolean exclusive = false;
		boolean autoDelete = false;
		Map<String, Object> queueProperties = null;
		channel.queueDeclare(QUEUE_NAME, durable, exclusive, autoDelete, queueProperties);
		return channel;
	}

	private static void publishMessage(Channel channel, String[] argv) throws IOException {
		String message = getMessage(argv);
		channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
		System.out.printf(" [x] Sent '%s\n", message);
	}

	private static String getMessage(String[] argv) {
		if (argv.length < 1)
			return "Hello World";
		return argv[0];
	}
}
