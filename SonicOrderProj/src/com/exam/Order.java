package com.exam;

import java.io.Serializable;
import java.math.BigDecimal;

public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	private final OrderItem[] orderItems;

	public Order(OrderItem[] orderItems) {
		this.orderItems = orderItems;
	}

	// Returns the total order cost after the tax has been applied
	public BigDecimal getOrderTotal(BigDecimal taxRate) {

		BigDecimal taxableTotal = new BigDecimal(0);
		BigDecimal nonTaxableTotal = new BigDecimal(0);

		for (int i = 0; i < orderItems.length; i++) {
			OrderItem orderItem = orderItems[i];
			if (orderItem instanceof Taxable) {
				taxableTotal = taxableTotal.add(orderItem.getTotalPrice());
			} else {
				nonTaxableTotal = nonTaxableTotal.add(orderItem.getTotalPrice());
			}
		}

		BigDecimal orderTotal = taxableTotal.add(nonTaxableTotal).add(generateTax(taxableTotal, taxRate));
		return roundHalfUpToNearestPenny(orderTotal);
	}

	protected BigDecimal roundHalfUpToNearestPenny(BigDecimal orderTotal) {
		return orderTotal.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	protected BigDecimal generateTax(BigDecimal price, BigDecimal taxRate) {
		return price.multiply(taxRate);
	}

	public OrderItem[] getItems() {
		return orderItems;
	}

}
