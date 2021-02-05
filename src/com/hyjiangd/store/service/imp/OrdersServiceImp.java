package com.hyjiangd.store.service.imp;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.hyjiangd.store.dao.GoodsDao;
import com.hyjiangd.store.dao.OrderDao;
import com.hyjiangd.store.dao.OrderLineItemDao;
import com.hyjiangd.store.dao.imp.GoodsDaoImp;
import com.hyjiangd.store.dao.imp.OrderDaoImp;
import com.hyjiangd.store.dao.imp.OrderLineItemDaoImp;
import com.hyjiangd.store.domain.Goods;
import com.hyjiangd.store.domain.OrderLineItem;
import com.hyjiangd.store.domain.Orders;
import com.hyjiangd.store.service.OrdersService;

public class OrdersServiceImp implements OrdersService{

	private GoodsDao goodsDao = new GoodsDaoImp();
	private OrderDao orderDao = new OrderDaoImp();
	private OrderLineItemDao orderLineItemDao = new OrderLineItemDaoImp();
	
	@Override
	public String submitOrders(List<Map<String, Object>> cart) {
		Orders orders = new Orders();
		Date date = new Date();
		orders.setOrderDate(date);
		// 訂單ID為現在時間再加上隨機數
		String ordersid = String.valueOf(date.getTime()) + String.valueOf((int)Math.random() * 100);
		orders.setId(ordersid);
		
		orders.setStatus(1);
		orders.setTotal(0.0f);
		// 將訂單插入到數據庫
		orderDao.create(orders);
		
		double total = 0.0;
		
		for (Map<String, Object> item : cart) {
			// item = [商品ID, 數量]
			Long goodsId = (Long) item.get("goodsid");
			Integer quantity = (Integer)item.get("quantity");
			
			Goods goods = goodsDao.findByPk(goodsId);
			Double subtotal = goods.getPrice() * quantity;
			total += subtotal;
			
			OrderLineItem orderLineItem = new OrderLineItem();
			orderLineItem.setQuantity(quantity);
			orderLineItem.setGoods(goods);
			orderLineItem.setOrders(orders);
			orderLineItem.setSubTotal(subtotal);
			
			orderLineItemDao.create(orderLineItem);
		}
		
		orders.setTotal(total);
		orderDao.modify(orders);
		
		return ordersid;
	}

}
