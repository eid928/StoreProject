package com.hyjiangd.store.dao;

import java.util.List;

import com.hyjiangd.store.domain.Goods;

public interface GoodsDao {
	
	Goods findByPk(long pk);
	List<Goods> findAll();
	void create(Goods goods);
	void modify(Goods goods);
	void remove(Goods goods);

}
