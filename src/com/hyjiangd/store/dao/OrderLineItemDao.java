package com.hyjiangd.store.dao;

import java.util.List;
import com.hyjiangd.store.domain.OrderLineItem;

public interface OrderLineItemDao {
	
	OrderLineItem findByPk(long pk);
	List<OrderLineItem> findAll();
	void create(OrderLineItem orderLineItem);
	void modify(OrderLineItem orderLineItem);
	void remove(String pk);

}
