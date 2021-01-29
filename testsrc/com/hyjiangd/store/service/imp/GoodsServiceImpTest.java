package com.hyjiangd.store.service.imp;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hyjiangd.store.domain.Goods;
import com.hyjiangd.store.service.GoodsService;

class GoodsServiceImpTest {

	GoodsService goodsService;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		goodsService = new GoodsServiceImp();
	}

	@AfterEach
	void tearDown() throws Exception {
		goodsService = null;
	}

	@Test
	void testQueryAll() {
		List<Goods> list = goodsService.queryAll();
		assertEquals(34, list.size());
		
		Goods goods = list.get(2);
		assertEquals(3L, goods.getId());
		assertEquals("聯想天逸510S", goods.getName());
		assertEquals(3099D, goods.getPrice());
		assertEquals("聯想（Lenovo）天逸510S商用台式?公電腦整機（i3-7100 4G 1T 集顯 WiFi 藍牙 三年上門 win10）19.5英寸", goods.getDescription());
		assertEquals("聯想（Lenovo）", goods.getBrand());
		assertEquals("Intel ", goods.getCpuBrand());
		assertEquals("Intel i3", goods.getCpuType());
		assertEquals("4G", goods.getMemoryCapacity());
		assertEquals("1T", goods.getHdCapacity());
		assertEquals("集成顯?", goods.getCardModel());
		assertEquals("", goods.getDisplaysize());
		assertEquals("5a6e946eNd622e938.jpg", goods.getImage());
	}
	
	@Test
	void testQueryByStartEnd() {
		List<Goods> list = goodsService.queryByStartEnd(0, 9);
		assertEquals(10, list.size());
		
		Goods goods = list.get(2);
		assertEquals(3L, goods.getId());
		assertEquals("聯想天逸510S", goods.getName());
		assertEquals(3099D, goods.getPrice());
		assertEquals("聯想（Lenovo）天逸510S商用台式?公電腦整機（i3-7100 4G 1T 集顯 WiFi 藍牙 三年上門 win10）19.5英寸", goods.getDescription());
		assertEquals("聯想（Lenovo）", goods.getBrand());
		assertEquals("Intel ", goods.getCpuBrand());
		assertEquals("Intel i3", goods.getCpuType());
		assertEquals("4G", goods.getMemoryCapacity());
		assertEquals("1T", goods.getHdCapacity());
		assertEquals("集成顯?", goods.getCardModel());
		assertEquals("", goods.getDisplaysize());
		assertEquals("5a6e946eNd622e938.jpg", goods.getImage());
	}

	@Test
	void testQueryDetail() {
		Goods goods = goodsService.queryDetail(3);
		assertNotNull(goods);
		
		assertEquals(3L, goods.getId());
		assertEquals("聯想天逸510S", goods.getName());
		assertEquals(3099D, goods.getPrice());
		assertEquals("聯想（Lenovo）天逸510S商用台式?公電腦整機（i3-7100 4G 1T 集顯 WiFi 藍牙 三年上門 win10）19.5英寸", goods.getDescription());
		assertEquals("聯想（Lenovo）", goods.getBrand());
		assertEquals("Intel ", goods.getCpuBrand());
		assertEquals("Intel i3", goods.getCpuType());
		assertEquals("4G", goods.getMemoryCapacity());
		assertEquals("1T", goods.getHdCapacity());
		assertEquals("集成顯?", goods.getCardModel());
		assertEquals("", goods.getDisplaysize());
		assertEquals("5a6e946eNd622e938.jpg", goods.getImage());
	}

}
