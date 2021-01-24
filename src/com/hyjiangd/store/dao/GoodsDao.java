package com.hyjiangd.store.dao;

import java.util.List;

import com.hyjiangd.store.domain.Goods;

public interface GoodsDao {
	
	Goods findByPk(long pk);
	List<Goods> findAll();
	
	/**
	 * 提供分頁查詢
	 * @param start 開始的位置，從0開始
	 * @param end 結束的位置，從0開始
	 * @return
	 */
	List<Goods> findStartEnd(int start, int end);
	void create(Goods goods);
	void modify(Goods goods);
	void remove(long pk);

}
