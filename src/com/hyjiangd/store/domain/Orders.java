package com.hyjiangd.store.domain;

import java.util.Date;

public class Orders {
	
	private String id;
	private Date orderDate;
	// 訂單狀態 = 1, 代表未確認。訂單狀態 = 0, 代表已確認。
	private int status = 1;
	private double total;
//	private List<OrderLineItem> orderLineItems;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
	

}
