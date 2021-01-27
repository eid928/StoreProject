package com.hyjiangd.store.service.imp;



import com.hyjiangd.store.dao.CustomerDao;
import com.hyjiangd.store.dao.imp.CustomerDaoImp;
import com.hyjiangd.store.domain.Customer;
import com.hyjiangd.store.service.CustomerService;
import com.hyjiangd.store.service.ServiceException;

public class CustomerServiceImp implements CustomerService {

	private CustomerDao customerDao = new CustomerDaoImp();
	
	@Override
	public boolean login(Customer customer) {
		Customer dbCustomer = customerDao.findByPk(customer.getId());
		
		if (dbCustomer.getPassword().equals(customer.getPassword())) {
			// 登陸密碼正確，將剩餘資訊取出待之後回傳表示層
			
			customer.setName(dbCustomer.getName());
			customer.setAddress(dbCustomer.getAddress());
			customer.setPhone(dbCustomer.getPhone());
			customer.setBirthday(dbCustomer.getBirthday());
			
			return true;
		}
		
		return false;
	}

	@Override
	public void register(Customer customer) throws ServiceException {
		Customer dbCustomer = customerDao.findByPk(customer.getId());
		
		if (dbCustomer != null) { // 表示此ID(帳號)已經被註冊過了
			throw new ServiceException("此帳號：" + customer.getId() + "已被註冊"); 
		}
		customerDao.create(customer);
	}

}
