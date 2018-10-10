package com.exam;

public class MaterialItem extends OrderItem implements Taxable {

	public MaterialItem(Item item, int quantity) {
		super(item, quantity);
	}

}
