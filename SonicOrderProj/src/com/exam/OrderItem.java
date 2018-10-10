package com.exam;

import java.io.Serializable;
import java.math.BigDecimal;

public abstract class OrderItem implements Serializable {
	private static final long serialVersionUID = 1L;

	private final Item item;
	private final int quantity;
	private final BigDecimal totalPrice;

	protected OrderItem(Item item, int quantity) {
		this.item = item;
		this.quantity = quantity;
		this.totalPrice = item.getPrice().multiply(new BigDecimal(quantity));
	}

	protected Item getItem() {
		return item;
	}

	protected int getQuantity() {
		return quantity;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	@Override
	public String toString() {
		return "OrderItem [item=" + item + ", quantity=" + quantity + ", totalPrice=" + totalPrice + ", type="
				+ this.getClass().toString() + "]";
	}

}
