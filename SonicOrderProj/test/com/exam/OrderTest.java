package com.exam;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class OrderTest extends TestCase {

	private BigDecimal taxRate = new BigDecimal(0.5);

	@Before
	protected void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void testGenerateTax() {
		OrderItem[] orderItems = new OrderItem[] {};

		Order newOrder = new Order(orderItems);

		BigDecimal totalTax = newOrder.generateTax(new BigDecimal(1), taxRate);

		System.out.println("total tax: " + totalTax);

		assertEquals(new BigDecimal(.50), totalTax);
	}

	@Test
	public void testRoundingToNearestPenny() {
		OrderItem[] orderItems = new OrderItem[] {};

		Order newOrder = new Order(orderItems);

		BigDecimal roundedTotal = newOrder.roundHalfUpToNearestPenny(new BigDecimal(5.35544));

		System.out.println("rounded total: " + roundedTotal);

		assertEquals(new BigDecimal(5.36).setScale(2, BigDecimal.ROUND_HALF_UP), roundedTotal);
	}

	@Test
	public void testTotalOrder() {

		OrderItem newMaterialItem = createMaterialItem("shampoo", new BigDecimal(2.50), 3);
		System.out.println("New material item: " + newMaterialItem.toString());
		// total price for shampoo order = 7.50
		// total tax for shampoo order = 3.75

		OrderItem newServiceItem = createServiceItem("hair cut", new BigDecimal(12.50), 2);
		System.out.println("New service item: " + newServiceItem.toString());
		// total price for hair cut order = 25

		OrderItem[] orderItems = new OrderItem[] { newMaterialItem, newServiceItem };

		Order newOrder = new Order(orderItems);

		BigDecimal orderTotal = newOrder.getOrderTotal(taxRate);
		System.out.println("order total: " + orderTotal);

		assertEquals(new BigDecimal(36.25), orderTotal);
	}

	public static MaterialItem createMaterialItem(String itemName, BigDecimal itemPrice, int quantity) {
		Item item = new Item(itemName, itemPrice);

		return new MaterialItem(item, quantity);
	}

	public static ServiceItem createServiceItem(String serviceName, BigDecimal itemPrice, int quantity) {
		Item item = new Item(serviceName, itemPrice);

		return new ServiceItem(item, quantity);
	}

}
