package ch.hsr.skapferer.vss.uebung1.aufgabe2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class URLReader {

	public static void readURL(String url) throws Exception {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(url).openStream()))) {
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		readURL("http://www.kapferer.ch");
	}

}
