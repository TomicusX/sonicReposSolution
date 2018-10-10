package com.exam;

import java.io.Serializable;
import java.math.BigDecimal;

public class Item implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;
	private BigDecimal price;

	public Item(String name, BigDecimal price) {
		this.name = name;
		this.price = price;
	}

	@Override
	public int hashCode() {
		return name.hashCode() + price.hashCode();
	}

	public boolean equals(Object anObject) {
		if (this == anObject) {
			return true;
		}

		if (anObject instanceof Item) {
			Item anotherItem = (Item) anObject;

			return (this.name.equals(anotherItem.getName()) && this.price.equals(anotherItem.getPrice()));
		}

		return false;

	}

	public String getName() {
		return name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return "Item [name=" + name + ", price=" + price + "]";
	}

}
