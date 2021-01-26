package com.hyjiangd.store.dao.imp;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.hyjiangd.store.dao.OrderLineItemDao;
import com.hyjiangd.store.domain.Goods;
import com.hyjiangd.store.domain.OrderLineItem;
import com.hyjiangd.store.domain.Orders;

class OrderLineItemDaoImpTest {

	OrderLineItemDao orderLineItemDao;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		orderLineItemDao = new OrderLineItemDaoImp();
	}

	@AfterEach
	void tearDown() throws Exception {
		orderLineItemDao = null;
	}

	@Test
	void testFindByPk() {
		OrderLineItem orderLineItem = orderLineItemDao.findByPk(1);
		assertNotNull(orderLineItem);
		assertEquals(1, orderLineItem.getId());
		assertEquals(3, orderLineItem.getQuantity());
		assertEquals(6000, orderLineItem.getSubTotal());
		assertEquals(3, orderLineItem.getGoods().getId());
		assertEquals("200", orderLineItem.getOrders().getId());
		
	}

	@Disabled
	@Test
	void testFindAll() {
		List<OrderLineItem> list = orderLineItemDao.findAll();
		assertEquals(2, list.size());
		
		OrderLineItem orderLineItem = orderLineItemDao.findByPk(2);
		assertNotNull(orderLineItem);
		assertEquals(2, orderLineItem.getId());
		assertEquals(4, orderLineItem.getQuantity());
		assertEquals(8000, orderLineItem.getSubTotal());
		assertEquals(4, orderLineItem.getGoods().getId());
		assertEquals("300", orderLineItem.getOrders().getId());
		
	}

	@Disabled
	@Test
	void testCreate() {
		OrderLineItem orderLineItem = new OrderLineItem();
		orderLineItem.setQuantity(5);
		orderLineItem.setSubTotal(2000);
		Goods goods = new Goods();
		goods.setId(5);
		Orders order = new Orders();
		order.setId("300");
		orderLineItem.setGoods(goods);
		orderLineItem.setOrders(order);
		
		orderLineItemDao.create(orderLineItem);
		
		OrderLineItem orderLineItemdb = orderLineItemDao.findByPk(7);
		assertNotNull(orderLineItemdb);
		assertEquals(7, orderLineItemdb.getId());
		assertEquals(5, orderLineItemdb.getQuantity());
		assertEquals(2000, orderLineItemdb.getSubTotal());
		assertEquals(5, orderLineItemdb.getGoods().getId());
		assertEquals("300", orderLineItemdb.getOrders().getId()); 
		
	}

	@Disabled
	@Test
	void testModify() {
		OrderLineItem orderLineItem = new OrderLineItem();
		orderLineItem.setId(7);
		orderLineItem.setQuantity(6);
		orderLineItem.setSubTotal(4000);
		Goods goods = new Goods();
		goods.setId(6);
		Orders order = new Orders();
		order.setId("200");
		orderLineItem.setGoods(goods);
		orderLineItem.setOrders(order);
		
		orderLineItemDao.modify(orderLineItem);
		
		OrderLineItem orderLineItemdb = orderLineItemDao.findByPk(7);
		assertNotNull(orderLineItemdb);
		assertEquals(7, orderLineItemdb.getId());
		assertEquals(6, orderLineItemdb.getQuantity());
		assertEquals(4000, orderLineItemdb.getSubTotal());
		assertEquals(6, orderLineItemdb.getGoods().getId());
		assertEquals("200", orderLineItemdb.getOrders().getId()); 
	}

	
	@Test
	void testRemove() {
		orderLineItemDao.remove(5);
		OrderLineItem orderLineItemdb = orderLineItemDao.findByPk(5);
		assertNull(orderLineItemdb);
		
	}

}
