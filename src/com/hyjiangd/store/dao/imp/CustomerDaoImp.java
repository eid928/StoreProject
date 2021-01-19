package com.hyjiangd.store.dao.imp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hyjiangd.JDBC.db.core.JdbcTemp;
import com.hyjiangd.JDBC.db.core.PreparedStatementCreator;
import com.hyjiangd.JDBC.db.core.RowCallbackHandler;
import com.hyjiangd.store.dao.CustomerDao;
import com.hyjiangd.store.domain.Customer;

public class CustomerDaoImp implements CustomerDao{

	private JdbcTemp jdbcTemp = new JdbcTemp();
	private void populate(List<Customer> list, ResultSet rs) throws SQLException {
		Customer customer = new Customer();
		customer.setId(rs.getString("id"));
		customer.setName(rs.getString("name"));
		customer.setPassword(rs.getString("password"));
		customer.setAddress(rs.getString("address"));
		customer.setPhone(rs.getString("phone"));
		customer.setBirthday(new Date(rs.getLong("birthday")));
		list.add(customer);
	}
	
	@Override
	public Customer findByPk(String pk) {
		
		List<Customer> list = new ArrayList<>();
		String sql = "select id, name, password, address, phone, birthday from Customers where id = ?";
		
		jdbcTemp.query(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, pk);
				return pst;
			}
		}, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				populate(list, rs);
			}
		});
		if(list.size() == 1) {
			return list.get(0);
		}
		return null;
		
	}

	@Override
	public List<Customer> findAll() {
		
		List<Customer> list = new ArrayList<>();
		String sql = "select id, name, password, address, phone, birthday from Customers";
		
		jdbcTemp.query(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement pst = conn.prepareStatement(sql);
				return pst;
			}
		}, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				populate(list, rs);
			}
		});
		return list;
		
	}

	@Override
	public void create(Customer customer) {
		
		String sql = "insert into Customers (id, name, password, address, phone, birthday) values (?, ?, ?, ?, ?, ?)";
		
		jdbcTemp.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, customer.getId());
				pst.setString(2, customer.getName());
				pst.setString(3, customer.getPassword());
				pst.setString(4, customer.getAddress());
				pst.setString(5, customer.getPhone());
				pst.setLong(6, customer.getBirthday().getTime());
				return pst;
			}
		});
		
	}

	@Override
	public void modify(Customer customer) {
		
		String sql = "update Customers set name=?, password=?, address=?, phone=?, birthday=? where id=?";
		
		jdbcTemp.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, customer.getName());
				pst.setString(2, customer.getPassword());
				pst.setString(3, customer.getAddress());
				pst.setString(4, customer.getPhone());
				pst.setLong(5, customer.getBirthday().getTime());
				pst.setString(6, customer.getId());
				return pst;
			}
		});
		
	}

	@Override
	public void remove(String pk) {

		String sql = "delete from Customers where id=?";
		
		jdbcTemp.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, pk);
				return pst;
			}
		});
		
	}

}
