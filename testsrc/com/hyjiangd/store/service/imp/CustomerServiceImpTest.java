package com.hyjiangd.store.service.imp;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.hyjiangd.store.domain.Customer;
import com.hyjiangd.store.service.CustomerService;
import com.hyjiangd.store.service.ServiceException;

class CustomerServiceImpTest {

	private CustomerService customerService;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		customerService = new CustomerServiceImp();
	}

	@AfterEach
	void tearDown() throws Exception {
		customerService = null;
	}

	@Test
	@DisplayName("登陸成功")
	void testLogin() {
		Customer customer = new Customer();
		customer.setId("hyjiangd");
		customer.setPassword("Ab27215936");
		assertTrue(customerService.login(customer));
		assertEquals("hyjiangd", customer.getId());
		assertEquals("HY", customer.getName());
		assertEquals("Ab27215936", customer.getPassword());
		assertEquals("Taipei", customer.getAddress());
		assertEquals("0903101841", customer.getPhone());
		assertEquals(1111111111, customer.getBirthday().getTime());
	}
	
	@Test
	@DisplayName("登陸失敗")
	void testLogin2() {
		Customer customer = new Customer();
		customer.setId("hyjiangd");
		customer.setPassword("27215936");
		assertFalse(customerService.login(customer));
	}
	
	@Disabled
	@DisplayName("註冊成功")
	@Test
	void testRegister() throws ServiceException {
		Customer customer = new Customer();
		customer.setId("eid928");
		customer.setName("HYJIANGD");
		customer.setPassword("27215936");
		customer.setAddress("Taiwan");
		customer.setPhone("27390821");
		customer.setBirthday(new Date(1111111111));
		
		customerService.register(customer);
		
		Customer customer2 = new Customer();
		customer2.setId("eid928");
		customer2.setPassword("27215936");
		assertTrue(customerService.login(customer2));
		assertEquals("eid928", customer.getId());
		assertEquals("HYJIANGD", customer.getName());
		assertEquals("27215936", customer.getPassword());
		assertEquals("Taiwan", customer.getAddress());
		assertEquals("27390821", customer.getPhone());
		assertEquals(1111111111, customer.getBirthday().getTime());
		
	}
	
	
	@DisplayName("註冊失敗")
	@Test
	void testRegister2() {
		Customer customer = new Customer();
		customer.setId("eid928");
		customer.setName("HYJIANGD");
		customer.setPassword("27215936");
		customer.setAddress("Taiwan");
		customer.setPhone("27390821");
		customer.setBirthday(new Date(1111111111));
		
		assertThrows(ServiceException.class, ()->customerService.register(customer));
		
	}

}
