package ch.hsr.skapferer.vss.uebung2.aufgabe2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class HttpClient {

	public static void main(String[] args) throws IOException {
		if (args.length != 2) {
			System.err.println("Usage: java HttpClient host path");
			System.exit(1);
		}

		try (Socket s = new Socket(args[0], 80);
				OutputStreamWriter out = new OutputStreamWriter(s.getOutputStream());
				BufferedReader is = new BufferedReader(new InputStreamReader(s.getInputStream()))) {

			out.write("GET /" + args[1] + " HTTP/1.\r\n");
			out.write("Host: " + args[0] + "\r\n");
			out.write("\r\n");
			out.flush();

			int c;
			while ((c = is.read()) != -1) {
				System.out.print((char) c);
			}
		}
	}
}