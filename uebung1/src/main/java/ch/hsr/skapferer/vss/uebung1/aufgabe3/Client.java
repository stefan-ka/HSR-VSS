package ch.hsr.skapferer.vss.uebung1.aufgabe3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

public class Client {
	public static void main(String[] args) throws Exception {
		System.out.println("[URLConnectionClient]Connect");
		URL url = new URL("http://localhost:" + Server.PORT);
		URLConnection connection = url.openConnection();
		connection.setDoOutput(true);

		try (PrintWriter toServer = new PrintWriter(connection.getOutputStream())) {
			System.out.println("[URLConnectionClient]Send");
			toServer.println("String=HelloServer");
		}

		try (BufferedReader fromServer = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
			System.out.println("[URLConnectionClient]Read");
			String reversed = fromServer.readLine();
			System.out.println("[URLConnectionClient]String reversed: " + reversed);
		}
	}
}
