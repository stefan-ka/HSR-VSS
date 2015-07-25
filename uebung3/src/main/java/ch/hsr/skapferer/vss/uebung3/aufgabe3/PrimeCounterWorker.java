package ch.hsr.skapferer.vss.uebung3.aufgabe3;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;

public class PrimeCounterWorker {
	private static final String TASK_QUEUE_NAME = "prime_queue";

	public static void main(String[] argv) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);
		System.out.println(" [*] Waiting for messages. To exit press CTRL+C.");

		// TODO faire Verteilung
		channel.basicQos(1);

		// TODO Message konsumieren
		QueueingConsumer consumer = new QueueingConsumer(channel);
		boolean durable = false;
		channel.basicConsume(TASK_QUEUE_NAME, durable, consumer);

		while (true) {
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();

			// TODO Message abarbeiten mit Hilfe von Ex03_PrimeCounterMessage
			PrimeCounterMessage message = PrimeCounterMessage.fromBytes(delivery.getBody());
			System.out.printf(" [x] Received '%s'\n", message.toString());
			long numOfPrimeNumbers = new PrimeCounter(message).countPrimes();
			System.out.printf(" [x] Done. # of prime numbers: %d\n", numOfPrimeNumbers);

			// TODO Acknowledge manuell nach Abarbeiten der Message
			channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
		}
	}

	private static class PrimeCounter {
		private final long start;
		private final long end;

		PrimeCounter(PrimeCounterMessage message) {
			this.start = message.start;
			this.end = message.end;
		}

		public long countPrimes() {
			long count = 0;
			for (long number = start; number < end; number++) {
				if (isPrime(number)) {
					count++;
				}
			}
			return count;
		}

		private boolean isPrime(long number) {
			for (long factor = 2; factor * factor <= number; factor++) {
				if (number % factor == 0) {
					return false;
				}
			}
			return true;
		}
	}
}