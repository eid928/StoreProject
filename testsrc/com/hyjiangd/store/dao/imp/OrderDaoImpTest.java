package com.hyjiangd.store.dao.imp;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.hyjiangd.store.dao.OrderDao;
import com.hyjiangd.store.domain.Orders;

class OrderDaoImpTest {

	private OrderDao orderdao;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		orderdao = new OrderDaoImp();
	}

	@AfterEach
	void tearDown() throws Exception {
		orderdao = null;
	}

	@Test
	void testFindByPk() {
		Orders orders = orderdao.findByPk("100");
		assertNotNull(orders);
		assertEquals("100", orders.getId());
		assertEquals(111111111, orders.getOrderDate().getTime());
		assertEquals(1, orders.getStatus());
		assertEquals(9000, orders.getTotal());
		
	}

	@Disabled
	@Test
	void testFindAll() {
		List<Orders> list = orderdao.findAll();
		assertEquals(2, list.size());
		
		
		Orders orders = list.get(1);
		assertNotNull(orders);
		assertEquals("200", orders.getId());
		assertEquals(222222222, orders.getOrderDate().getTime());
		assertEquals(0, orders.getStatus());
		assertEquals(2500, orders.getTotal());
	}

	@Disabled
	@Test
	void testCreate() {
		Orders orders = new Orders();
		orders.setId("300");
		orders.setOrderDate(new Date(333333333L));
		orders.setStatus(0);
		orders.setTotal(3000.0);
		orderdao.create(orders);
		
		Orders ordersdb = orderdao.findByPk("300");
		assertEquals("300", ordersdb.getId());
		assertEquals(333333333L, ordersdb.getOrderDate().getTime());
		assertEquals(0, ordersdb.getStatus());
		assertEquals(3000.0, ordersdb.getTotal());
	}
	
	@Disabled
	@Test
	void testModify() {
		Orders orders = new Orders();
		orders.setId("300");
		orders.setOrderDate(new Date(333333333L));
		orders.setStatus(1);
		orders.setTotal(4500.0);
		orderdao.modify(orders);
		
		Orders ordersdb = orderdao.findByPk("300");
		assertEquals("300", ordersdb.getId());
		assertEquals(333333333L, ordersdb.getOrderDate().getTime());
		assertEquals(1, ordersdb.getStatus());
		assertEquals(4500.0, ordersdb.getTotal());
	}

	
	@Test
	void testRemove() {
		orderdao.remove("300");
		assertNull(orderdao.findByPk("300"));
	}

}
