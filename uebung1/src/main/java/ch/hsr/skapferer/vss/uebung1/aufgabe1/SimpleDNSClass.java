package ch.hsr.skapferer.vss.uebung1.aufgabe1;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class SimpleDNSClass {

	public void printLocalAddresses() throws UnknownHostException {
		InetAddress localHost = InetAddress.getLocalHost();
		for (InetAddress inetAddress : InetAddress.getAllByName(localHost.getCanonicalHostName())) {
			printInetAddress(inetAddress);
		}
	}

	public void printIPAdressByName(String name) throws UnknownHostException {
		printInetAddress(InetAddress.getByName(name));
	}

	public void printHostnameByIPAddress(byte[] ipAddress) throws UnknownHostException {
		printInetAddress(InetAddress.getByAddress(ipAddress));
	}

	public void printAllAddressesForName(String name) throws UnknownHostException {
		for (InetAddress inetAddress : InetAddress.getAllByName(name)) {
			printInetAddress(inetAddress);
		}
	}

	private void printInetAddress(InetAddress inetAddress) {
		System.out.printf("IP: %s Name: %s \n", inetAddress.getHostAddress(), inetAddress.getHostName());
	}

	public static void main(String[] args) throws Exception {
		SimpleDNSClass simpleDNS = new SimpleDNSClass();

		simpleDNS.printLocalAddresses();
		simpleDNS.printIPAdressByName("sidv0012.hsr.ch");
		simpleDNS.printHostnameByIPAddress(new byte[] { (byte) 152, 96, 36, 121 });
		simpleDNS.printAllAddressesForName("www.google.com");
	}

}
