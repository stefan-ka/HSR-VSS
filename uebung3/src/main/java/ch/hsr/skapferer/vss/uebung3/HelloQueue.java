package ch.hsr.skapferer.vss.uebung3;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class HelloQueue {

	private static final String MESSAGE = "Hello World";

	public static void main(String[] args) throws IOException {
		HelloQueue queue = new HelloQueue();
		queue.runHelloQueue();
	}

	public void runHelloQueue() throws IOException {
		Connection connection = createConnection();
		Channel channel = connection.createChannel();
		channel.queueBind(Ex03_RabbitMqConsumer.QUEUE_NAME, "", "");
	}

	private Connection createConnection() throws IOException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		return factory.newConnection();
	}

}
