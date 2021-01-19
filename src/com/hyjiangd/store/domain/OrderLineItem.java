package com.hyjiangd.store.domain;

public class OrderLineItem {
	// 訂單詳細物件，會對應到一個實體的訂單物件以及一個實體的商品物件
	
	private long id;
	private int quantity;
	// 小計
	private double subTotal;
	//對應一個實體訂單，但一個訂單可能對應多個訂單詳細
	private Orders orders;
	//對應一個實體商品，但一個商品可能對應多個訂單詳細
	private Goods goods;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}
	public Orders getOrders() {
		return orders;
	}
	public void setOrders(Orders orders) {
		this.orders = orders;
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	
}
