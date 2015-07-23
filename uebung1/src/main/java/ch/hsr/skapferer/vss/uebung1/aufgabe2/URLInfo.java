package ch.hsr.skapferer.vss.uebung1.aufgabe2;

import java.net.URL;

public class URLInfo {

	public static void printUrlInfo(String urlAsString) throws Exception {
		URL url = new URL(urlAsString);
		System.out.println("------------------------------------------------------------------");
		System.out.println("Protokoll: " + url.getProtocol());
		System.out.println("Host: " + url.getHost());
		System.out.println("Port: " + url.getPort());
		System.out.println("Pfad: " + url.getPath());
		System.out.println("Datei: " + url.getFile());
		System.out.println("Referenz: " + url.getRef());
		System.out.println("------------------------------------------------------------------");
	}

	public static void main(String[] args) throws Exception {
		printUrlInfo("http://www.hsr.ch");
		printUrlInfo("http://de.selfhtml.org/html/tabellen/aufbau.htm#definieren");
		printUrlInfo("https://unterricht.hsr.ch:443");
		printUrlInfo("http://www.google.ch/#aq=1&aqi=g5&aql=&hl=en&q=hsr+rapperswil");
	}

}
