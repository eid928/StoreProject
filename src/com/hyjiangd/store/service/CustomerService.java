package com.hyjiangd.store.service;

import com.hyjiangd.store.domain.Customer;

public interface CustomerService {
	
	
	/**
	 * 處理使用者登陸
	 * @param customer
	 * @return 登陸成功回傳true，否則回傳false
	 */
	boolean login(Customer customer);
	
	/**
	 * 處理使用者註冊
	 * @param customer
	 * @throws ServiceException 註冊失敗拋出異常
	 */
	void register(Customer customer) throws ServiceException;

}
