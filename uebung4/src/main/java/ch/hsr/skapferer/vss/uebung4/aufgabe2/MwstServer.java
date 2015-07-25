package ch.hsr.skapferer.vss.uebung4.aufgabe2;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;

public class MwstServer implements Mwst {

	@Override
	public BigDecimal calcMwst(Collection<Article> articles) throws RemoteException {
		BigDecimal result = new BigDecimal(0);
		BigDecimal mwstSatz = BigDecimal.valueOf(0.08);
		for (Article a : articles) {
			result = result.add(a.getPrice().multiply(mwstSatz));
		}
		return result;
	}

	public static void main(String[] args) throws Exception {
		System.out.println("[MWSTServer]start");
		/**
		 * VM argument, if security manager is enabled
		 * -Djava.security.policy=rmi.policy
		 */
		// if (System.getSecurityManager() == null) {
		// System.setSecurityManager(new RMISecurityManager());
		// }

		MwstServer server = new MwstServer();
		System.out.println("[MWSTServer]export()");
		Mwst stub = (Mwst) UnicastRemoteObject.exportObject(server, Mwst.PORT);

		System.out.println("[MWSTServer]create local registry");
		Registry registry = LocateRegistry.createRegistry(Mwst.PORT);
		System.out.println("[MWSTServer]get registry");

		registry.rebind(Mwst.LOOKUP_NAME, stub);
		System.out.println("[MWSTServer]" + Mwst.LOOKUP_NAME + " bound");
	}
}