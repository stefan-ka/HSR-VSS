package ch.hsr.skapferer.vss.uebung4.aufgabe2;

import java.math.BigDecimal;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

public interface Mwst extends Remote {
	public static final int PORT = 1100;
	public static final String HOST = "localhost";
	public static final String LOOKUP_NAME = "Mwst";

	BigDecimal calcMwst(Collection<Article> articles) throws RemoteException;
}
