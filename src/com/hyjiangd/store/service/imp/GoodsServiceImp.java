package com.hyjiangd.store.service.imp;

import java.util.List;

import com.hyjiangd.store.dao.GoodsDao;
import com.hyjiangd.store.dao.imp.GoodsDaoImp;
import com.hyjiangd.store.domain.Goods;
import com.hyjiangd.store.service.GoodsService;

public class GoodsServiceImp implements GoodsService{

	GoodsDao goodsDao = new GoodsDaoImp();
	
	@Override
	public List<Goods> queryAll() {
		return goodsDao.findAll();
	}

	@Override
	public List<Goods> queryByStartEnd(int start, int end) {
		return goodsDao.findStartEnd(start, end);
	}

	@Override
	public Goods queryDetail(long goodsid) {
		return goodsDao.findByPk(goodsid);
	}

}
