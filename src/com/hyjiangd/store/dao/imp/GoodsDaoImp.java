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
import com.hyjiangd.store.dao.GoodsDao;
import com.hyjiangd.store.domain.Goods;

public class GoodsDaoImp implements GoodsDao{

	private JdbcTemp jdbcTemp = new JdbcTemp();
	private void populate(List<Goods> list, ResultSet rs) throws SQLException {
		Goods goods = new Goods();
		
		goods.setId(rs.getLong("id"));
		goods.setName(rs.getString("name"));
		goods.setPrice(rs.getDouble("price"));
		goods.setDescription(rs.getString("description"));
		goods.setBrand(rs.getString("brand"));
		goods.setCpuBrand(rs.getString("cpu_brand"));
		goods.setCpuType(rs.getString("cpu_type"));
		goods.setMemoryCapacity(rs.getString("memory_capacity"));
		goods.setHdCapacity(rs.getString("hd_capacity"));
		goods.setCardModel(rs.getString("card_model"));
		goods.setDisplaysize(rs.getString("displaysize"));
		goods.setImage(rs.getString("image"));
		
		list.add(goods);
	}

	public static void main(String[] args) {
		GoodsDao goodsDao = new GoodsDaoImp();
		for(int i = 5; i < 36; i ++) {
			goodsDao.remove(i);
		}
	}
	
	@Override
	public Goods findByPk(long pk) {
		
		List<Goods> list = new ArrayList<>();
		
		String sql = "select * from goods where id = ?";
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
	public List<Goods> findAll() {
		List<Goods> list = new ArrayList<>();
		String sql = "select * from goods";
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
	public List<Goods> findStartEnd(int start, int end) {
		List<Goods> list = new ArrayList<>();
		int limit = end - start + 1;
		int offset = start;
		String sql = "select * from goods limit ? offset ?";
		
		jdbcTemp.query(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setInt(1, limit);
				pst.setInt(2, offset);
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
	public void create(Goods goods) {
		String sql = "insert into goods (id, name, price, description, brand, cpu_brand, cpu_type, memory_capacity, hd_capacity, card_model, displaysize, image)"
				+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		jdbcTemp.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement pst = conn.prepareStatement(sql);
				
				pst.setLong(1, goods.getId());
				pst.setString(2, goods.getName());
				pst.setDouble(3, goods.getPrice());
				pst.setString(4, goods.getDescription());
				pst.setString(5, goods.getBrand());
				pst.setString(6, goods.getCpuBrand());
				pst.setString(7, goods.getCpuType());
				pst.setString(8, goods.getMemoryCapacity());
				pst.setString(9, goods.getHdCapacity());
				pst.setString(10, goods.getCardModel());
				pst.setString(11, goods.getDisplaysize());
				pst.setString(12, goods.getImage());
				
				return pst;
			}
		});
	}

	@Override
	public void modify(Goods goods) {
		String sql = "update goods set name=?, price=?, description=?, brand=?, cpu_brand=?, cpu_type=?, memory_capacity=?, hd_capacity=?, card_model=?, displaysize=?, image=? where id = ?";
		
		jdbcTemp.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement pst = conn.prepareStatement(sql);
				
				
				pst.setString(1, goods.getName());
				pst.setDouble(2, goods.getPrice());
				pst.setString(3, goods.getDescription());
				pst.setString(4, goods.getBrand());
				pst.setString(5, goods.getCpuBrand());
				pst.setString(6, goods.getCpuType());
				pst.setString(7, goods.getMemoryCapacity());
				pst.setString(8, goods.getHdCapacity());
				pst.setString(9, goods.getCardModel());
				pst.setString(10, goods.getDisplaysize());
				pst.setString(11, goods.getImage());
				pst.setLong(12, goods.getId());
				
				return pst;
			}
		});
	}

	@Override
	public void remove(long pk) {
		String sql = "delete from goods where id=?";
		
		jdbcTemp.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setLong(1, pk);
				
				return pst;
			}
		});
		
	}

	

}
