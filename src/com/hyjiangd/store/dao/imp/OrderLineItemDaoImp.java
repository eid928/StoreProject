package com.hyjiangd.store.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hyjiangd.JDBC.db.core.JdbcTemp;
import com.hyjiangd.JDBC.db.core.PreparedStatementCreator;
import com.hyjiangd.JDBC.db.core.RowCallbackHandler;
import com.hyjiangd.store.dao.OrderLineItemDao;
import com.hyjiangd.store.domain.Goods;
import com.hyjiangd.store.domain.OrderLineItem;
import com.hyjiangd.store.domain.Orders;

public class OrderLineItemDaoImp implements OrderLineItemDao {

	JdbcTemp jdbcTemp = new JdbcTemp();
	private void populate(List<OrderLineItem> list, ResultSet rs) throws SQLException {
		OrderLineItem orderLineItem = new OrderLineItem();
		orderLineItem.setId(rs.getLong("id"));
		orderLineItem.setQuantity(rs.getInt("quantity"));
		orderLineItem.setSubTotal(rs.getFloat("sub_total"));
		
		Orders orders = new Orders();
		orders.setId(rs.getString("orderid"));
		orderLineItem.setOrders(orders);
		
		Goods goods = new Goods();
		goods.setId(rs.getLong("goodsid"));
		orderLineItem.setGoods(goods);
		
		list.add(orderLineItem);
	}
	
	@Override
	public OrderLineItem findByPk(long pk) {
		List<OrderLineItem> list = new ArrayList<>();
		String sql = "select * from orderlineitems where id = ?";
		
		jdbcTemp.query(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setLong(1, pk);
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
	public List<OrderLineItem> findAll() {
		List<OrderLineItem> list = new ArrayList<>();
		String sql = "select * from orderlineitems";
		
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
	public void create(OrderLineItem orderLineItem) {
		String sql = "insert into orderlineitems (id, goodsid, orderid, quantity, sub_total) values (?, ?, ?, ?, ?)";
		jdbcTemp.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setLong(1, orderLineItem.getId());
				pst.setLong(2, orderLineItem.getGoods().getId());
				pst.setString(3, orderLineItem.getOrders().getId());
				pst.setInt(4, orderLineItem.getQuantity());
				pst.setDouble(5, orderLineItem.getSubTotal());
				return pst;
			}
		});
	}

	@Override
	public void modify(OrderLineItem orderLineItem) {
		String sql = "update orderlineitems set goodsid=?, orderid=?, quantity=?, sub_total=? where id=?";
		jdbcTemp.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setLong(5, orderLineItem.getId());
				pst.setLong(1, orderLineItem.getGoods().getId());
				pst.setString(2, orderLineItem.getOrders().getId());
				pst.setInt(3, orderLineItem.getQuantity());
				pst.setDouble(4, orderLineItem.getSubTotal());
				return pst;
			}
		});

	}

	@Override
	public void remove(String pk) {
		String sql = "delete from orderlineitems where id=?";
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
