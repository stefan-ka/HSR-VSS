package ch.hsr.skapferer.vss.uebung3.aufgabe3;

import java.io.*;

public class PrimeCounterMessage implements Serializable {
	public static final long serialVersionUID = 1L;
	public final long start;
	public final long end;

	public PrimeCounterMessage(long start, long end) {
		this.start = start;
		this.end = end;
	}

	public String toString() {
		return String.format("Prime numbers from %d to %d", start, end);
	}

	public byte[] toBytes() throws IOException {
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream(); ObjectOutputStream oos = new ObjectOutputStream(baos)) {
			oos.writeObject(this);
			oos.flush();
			return baos.toByteArray();
		}
	}

	public static PrimeCounterMessage fromBytes(byte[] body) throws IOException, ClassNotFoundException {
		try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(body))) {
			Object msg = ois.readObject();
			if (!(msg instanceof PrimeCounterMessage)) {
				throw new IllegalArgumentException("Invalid message type");
			}
			return (PrimeCounterMessage) msg;
		}
	}
}