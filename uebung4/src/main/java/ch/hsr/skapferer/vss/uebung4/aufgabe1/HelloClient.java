package ch.hsr.skapferer.vss.uebung4.aufgabe1;

import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class HelloClient {

	public static void main(String[] args) {
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		}
		try {
			Registry registry = LocateRegistry.getRegistry(Hello.HOST, Hello.PORT);
			Hello hello = (Hello) registry.lookup(Hello.NAME);
			System.out.println(hello.getHello());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
