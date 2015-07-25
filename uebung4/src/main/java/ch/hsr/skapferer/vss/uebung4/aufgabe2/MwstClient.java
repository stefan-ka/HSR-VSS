package ch.hsr.skapferer.vss.uebung4.aufgabe2;

import java.math.BigDecimal;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class MwstClient {
	public static void main(String[] args) throws Exception {
		// check security i distributed
		// -Djava.security.policy=rmi.policy
		// System.setProperty("java.security.policy", "rmi.policy");
		// if (System.getSecurityManager() == null) {
		// System.setSecurityManager(new RMISecurityManager());
		// }

		// generate some test data
		System.out.println("[MWSTClient]Generierung der Testdaten:");
		ArrayList<Article> list = new ArrayList<Article>();
		list.add(new Article("Samsung CD-R 700MB 25ER", BigDecimal.valueOf(19.95)));
		list.add(new Article("Apple iPhone", BigDecimal.valueOf(790.00)));
		list.add(new Article("Sony Ericsson X10", BigDecimal.valueOf(390.00)));

		// show items
		for (Article artikel : list) {
			System.out.println("\t" + artikel);
		}

		// get link to stub from registry
		Registry registry = LocateRegistry.getRegistry(Mwst.HOST, Mwst.PORT);
		System.out.println("[MWSTClient]lookup registry");
		Mwst stub = (Mwst) registry.lookup(Mwst.LOOKUP_NAME);

		// remote method invocation
		System.out.println("[MWSTClient]stub.calcMwst(list)) Remote Methode Invocation");
		System.out.println("[MWSTClient]Die MWST für alle Artikel: " + stub.calcMwst(list));
	}
}