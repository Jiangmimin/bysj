package com.doremi.shop.cart.vo;

import com.doremi.shop.product.vo.Product;

public class CartItem {
    private Product product;
    private int count;
    private double subtotal;
    
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	//计算小计
	public double getSubtotal() {
		return count*product.getShop_price();
	}
	
}
