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

import com.hyjiangd.store.dao.CustomerDao;
import com.hyjiangd.store.domain.Customer;

class CustomerDaoImpTest {

	CustomerDao customerDao = null;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		customerDao = new CustomerDaoImp();
	}

	@AfterEach
	void tearDown() throws Exception {
		customerDao = null;
	}

	@Test
	void testFindByPk() {
		Customer customer = customerDao.findByPk("hyjiangd");
		assertNotNull(customer);
		assertEquals("hyjiangd", customer.getId());
		assertEquals("HY", customer.getName());
		assertEquals("Ab27215936", customer.getPassword());
		assertEquals("Taipei", customer.getAddress());
		assertEquals("0903101841", customer.getPhone());
		assertEquals(1111111111L, customer.getBirthday().getTime());
	}
	@Disabled
	@Test
	void testFindAll() {
		List<Customer> list = customerDao.findAll();
		assertEquals(list.size(), 1);
	}
	
	@Disabled
	@Test
	void testCreate() {
		Customer customer = new Customer();
		customer.setId("eid928");
		customer.setName("eddie");
		customer.setPassword("Abcd27215936");
		customer.setAddress("Tainan");
		customer.setPhone("27215936");
		customer.setBirthday(new Date(1111111112L));
		
		customerDao.create(customer);
		Customer customerdb = customerDao.findByPk("eid928");
		
		assertNotNull(customerdb);
		assertEquals("eid928", customerdb.getId());
		assertEquals("eddie", customerdb.getName());
		assertEquals("Abcd27215936", customerdb.getPassword());
		assertEquals("Tainan", customerdb.getAddress());
		assertEquals("27215936", customerdb.getPhone());
		assertEquals(1111111112L, customerdb.getBirthday().getTime());
		
	}
	
	@Disabled
	@Test
	void testModify() {
		Customer customer = new Customer();
		customer.setId("eid928");
		customer.setName("eddieJiangd");
		customer.setPassword("Abcd27215936hy");
		customer.setAddress("Tainan");
		customer.setPhone("27390821");
		customer.setBirthday(new Date(1111111113L));
		
		customerDao.modify(customer);
		Customer customerdb = customerDao.findByPk("eid928");
		
		assertNotNull(customerdb);
		assertEquals("eid928", customerdb.getId());
		assertEquals("eddieJiangd", customerdb.getName());
		assertEquals("Abcd27215936hy", customerdb.getPassword());
		assertEquals("Tainan", customerdb.getAddress());
		assertEquals("27390821", customerdb.getPhone());
		assertEquals(1111111113L, customerdb.getBirthday().getTime());
	}
	
	@Test
	void testRemove() {
		customerDao.remove("eid928");
		Customer customerdb = customerDao.findByPk("eid928");
		assertNull(customerdb);
		
	}

}
