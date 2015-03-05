package ch.hsr.skapferer.vss.uebung3;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

public class Ex03_TwitterMessageTopicConsumer {
	private static final String EXCHANGE_NAME = "twitter_topics";

	public static void main(String[] argv) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		// TODO Deklariere exchange

		// TODO Erstelle nameless queue

		for (String topic : getTopics(argv)) {
			// TODO Binde queue und exchange
		}

		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
		QueueingConsumer consumer = new QueueingConsumer(channel);

		// TODO basicConsume

		while (true) {
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			String message = new String(delivery.getBody());

			// TODO Finde Topic von delivery heraus
		}
	}

	private static String[] getTopics(String[] argv) {
		if (argv.length < 1)
			return new String[] { "boris.*" };
		return argv;
	}
}