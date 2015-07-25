package ch.hsr.skapferer.vss.uebung4.aufgabe2;

import java.io.Serializable;
import java.math.BigDecimal;

public class Article implements Serializable {
	private static final long serialVersionUID = 1L;
	private final String name;
	private final BigDecimal price;

	public Article(String name, BigDecimal price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {

		return name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public String toString() {
		return "[Artikel] " + name + ": " + price;
	}
}