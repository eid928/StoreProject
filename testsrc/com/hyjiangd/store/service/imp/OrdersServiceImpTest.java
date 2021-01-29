package com.hyjiangd.store.service.imp;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hyjiangd.store.dao.OrderDao;
import com.hyjiangd.store.dao.OrderLineItemDao;
import com.hyjiangd.store.dao.imp.OrderDaoImp;
import com.hyjiangd.store.dao.imp.OrderLineItemDaoImp;
import com.hyjiangd.store.domain.OrderLineItem;
import com.hyjiangd.store.domain.Orders;
import com.hyjiangd.store.service.OrdersService;

class OrdersServiceImpTest {

	OrdersService orderService;
	OrderDao orderDao = new OrderDaoImp();
	OrderLineItemDao orderLineItemDao = new OrderLineItemDaoImp();
	
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		orderService = new OrdersServiceImp();
	}

	@AfterEach
	void tearDown() throws Exception {
		orderService= null;
	}

	@Test
	void testSubmitOrders() {
		List<Map<String, Object>> cart = new ArrayList<Map<String,Object>>();
		Map<String, Object> item1 = new HashMap<>();
		item1.put("goodsid", 3L);
		item1.put("quantity", 2);
		cart.add(item1);
		Map<String, Object> item2 = new HashMap<>();
		item2.put("goodsid", 8L);
		item2.put("quantity", 3);
		cart.add(item2);
		
		String ordersId = orderService.submitOrders(cart);
		assertNotNull(ordersId);
		Orders orders = orderDao.findByPk(ordersId);
		assertNotNull(orders);
		assertEquals(1, orders.getStatus());
		double total = 3099 * 2 + 1888 * 3;
		assertEquals(total, orders.getTotal());
		
		List<OrderLineItem> list = orderLineItemDao.findAll();
		List<OrderLineItem> orderLineItemList = new ArrayList<OrderLineItem>();
		
		for (OrderLineItem orderLineItem : list) {
			if(orderLineItem.getOrders().getId().equals(ordersId)) {
				orderLineItemList.add(orderLineItem);
				if(orderLineItem.getGoods().getId() == 3L) {
					assertEquals(2, orderLineItem.getQuantity());
					assertEquals(2 * 3099, orderLineItem.getSubTotal());
				}
				if(orderLineItem.getGoods().getId() == 8L) {
					assertEquals(3, orderLineItem.getQuantity());
					assertEquals(3 * 1888, orderLineItem.getSubTotal());
				}
			}
		}
		assertEquals(2, orderLineItemList.size());
	}

}
