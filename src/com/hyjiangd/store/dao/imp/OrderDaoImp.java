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
import com.hyjiangd.store.dao.OrderDao;
import com.hyjiangd.store.domain.Orders;

public class OrderDaoImp implements OrderDao {

	private JdbcTemp jdbctemp = new JdbcTemp();
	private void populate(List<Orders> list, ResultSet rs) throws SQLException {
		Orders orders = new Orders();
		orders.setId(rs.getString("id"));
		orders.setOrderDate(new Date(rs.getLong("order_date")));
		orders.setStatus(rs.getInt("status"));
		orders.setTotal(rs.getDouble("total"));
		
		list.add(orders);
	}
	
	@Override
	public Orders findByPk(String pk) {
		
		String sql = "select * from orders where id = ?";
		List<Orders> list = new ArrayList<>();
		
		jdbctemp.query(new PreparedStatementCreator() {
			
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
		
		if (list.size() == 1) {
			return list.get(0);
		}
		
		return null;
	}

	@Override
	public List<Orders> findAll() {
		
		String sql = "select * from orders";
		List<Orders> list = new ArrayList<>();
		
		jdbctemp.query(new PreparedStatementCreator() {
			
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
	public void create(Orders orders) {
		
		String sql = "insert into orders (id, order_date, status, total) values (?, ?, ?, ?)";
		jdbctemp.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, orders.getId());
				pst.setLong(2, orders.getOrderDate().getTime());
				pst.setInt(3, orders.getStatus());
				pst.setDouble(4, orders.getTotal());
				return pst;
			}
		});
		
	}

	@Override
	public void modify(Orders orders) {
		
		String sql = "update orders set order_date = ?, status = ?, total = ? where id = ?";
		jdbctemp.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(4, orders.getId());
				pst.setLong(1, orders.getOrderDate().getTime());
				pst.setInt(2, orders.getStatus());
				pst.setDouble(3, orders.getTotal());
				return pst;
			}
		});
		
	}

	@Override
	public void remove(String pk) {
		
		String sql = "delete from orders where id = ?";
		jdbctemp.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, pk);
				return pst;
			}
		});

	}
}
