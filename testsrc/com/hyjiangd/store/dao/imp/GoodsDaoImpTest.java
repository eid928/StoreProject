package com.hyjiangd.store.dao.imp;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.hyjiangd.store.dao.GoodsDao;
import com.hyjiangd.store.domain.Goods;

class GoodsDaoImpTest {

	GoodsDao goodsDao;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		goodsDao = new GoodsDaoImp();
	}

	@AfterEach
	void tearDown() throws Exception {
		goodsDao = null;
	}
	
	@Test
	void testFindByPk() {
		Goods goods = goodsDao.findByPk(1L);
		assertNotNull(goods);
		assertEquals(1L, goods.getId());
		assertEquals("戴爾(DELL)成就3470高性能商用辦公台式電腦整機", goods.getName());
		assertEquals(3399, goods.getPrice());
		assertEquals("戴爾(DELL)成就3470高性能商用辦公台式電腦整機(八代i3-8100 8G 1T 四年上門 有線鍵鼠 FHD寬屏)21.5英寸 ", goods.getDescription());
		assertEquals("5ae00211N25afad2c.jpg", goods.getImage());
		assertEquals("", goods.getBrand());
		assertEquals("", goods.getCpuBrand());
		assertEquals("", goods.getCardModel());
		assertEquals("", goods.getMemoryCapacity());
		assertEquals("", goods.getHdCapacity());
		assertEquals("", goods.getDisplaysize());
		
	}

	@Disabled
	@Test
	void testFindAll() {
		List<Goods> list = new ArrayList<>();
		list = goodsDao.findAll();
		assertNotNull(list);
		assertEquals(34, list.size());
		
		Goods goods = list.get(0);
		assertNotNull(goods);
		assertEquals(1L, goods.getId());
		assertEquals("戴爾(DELL)成就3470高性能商用辦公台式電腦整機", goods.getName());
		assertEquals(3399, goods.getPrice());
		assertEquals("戴爾(DELL)成就3470高性能商用辦公台式電腦整機(八代i3-8100 8G 1T 四年上門 有線鍵鼠 FHD寬屏)21.5英寸 ", goods.getDescription());
		assertEquals("5ae00211N25afad2c.jpg", goods.getImage());
		assertEquals("", goods.getBrand());
		assertEquals("", goods.getCpuBrand());
		assertEquals("", goods.getCardModel());
		assertEquals("", goods.getMemoryCapacity());
		assertEquals("", goods.getHdCapacity());
		assertEquals("", goods.getDisplaysize());
	}

	
	@Test
	void testFindStartEnd() {
		List<Goods> list = new ArrayList<>();
		list = goodsDao.findStartEnd(0, 9);
		assertNotNull(list);
		assertEquals(10, list.size());
		
		Goods goods = list.get(0);
		assertNotNull(goods);
		assertEquals(1L, goods.getId());
		assertEquals("戴爾(DELL)成就3470高性能商用辦公台式電腦整機", goods.getName());
		assertEquals(3399, goods.getPrice());
		assertEquals("戴爾(DELL)成就3470高性能商用辦公台式電腦整機(八代i3-8100 8G 1T 四年上門 有線鍵鼠 FHD寬屏)21.5英寸 ", goods.getDescription());
		assertEquals("5ae00211N25afad2c.jpg", goods.getImage());
		assertEquals("", goods.getBrand());
		assertEquals("", goods.getCpuBrand());
		assertEquals("", goods.getCardModel());
		assertEquals("", goods.getMemoryCapacity());
		assertEquals("", goods.getHdCapacity());
		assertEquals("", goods.getDisplaysize());
	}

	@Disabled
	@Test
	void testCreate() {
		Goods goods = new Goods();
		goods.setId(35);
		goods.setName("測試名稱");
		goods.setPrice(200000);
		goods.setDescription("測試描述");
		goods.setBrand("品牌");
		goods.setCpuBrand("intel");
		goods.setCpuType("i5");
		goods.setMemoryCapacity("16G");
		goods.setHdCapacity("500G");
		goods.setCardModel("GTX1060");
		goods.setDisplaysize("27");
		goods.setImage("123.jpg");
		
		goodsDao.create(goods);
		Goods goodsdb = goodsDao.findByPk(35);
		assertNotNull(goodsdb);
		assertEquals(35, goodsdb.getId());
		assertEquals("測試名稱", goodsdb.getName());
		assertEquals(200000, goodsdb.getPrice());
		assertEquals("測試描述", goodsdb.getDescription());
		assertEquals("123.jpg", goodsdb.getImage());
		assertEquals("品牌", goodsdb.getBrand());
		assertEquals("intel", goodsdb.getCpuBrand());
		assertEquals("i5", goodsdb.getCpuType());
		assertEquals("GTX1060", goodsdb.getCardModel());
		assertEquals("16G", goodsdb.getMemoryCapacity());
		assertEquals("500G", goodsdb.getHdCapacity());
		assertEquals("27", goodsdb.getDisplaysize());
		
	}

	@Disabled
	@Test
	void testModify() {
		Goods goods = new Goods();
		goods.setId(35);
		goods.setName("測試名稱2");
		goods.setPrice(200000);
		goods.setDescription("測試描述2");
		goods.setBrand("品牌2");
		goods.setCpuBrand("AMD");
		goods.setCpuType("AMD");
		goods.setMemoryCapacity("16G");
		goods.setHdCapacity("500G");
		goods.setCardModel("GTX2080");
		goods.setDisplaysize("27");
		goods.setImage("123.jpg");
		
		goodsDao.modify(goods);
		Goods goodsdb = goodsDao.findByPk(35);
		assertNotNull(goodsdb);
		assertEquals(35, goodsdb.getId());
		assertEquals("測試名稱2", goodsdb.getName());
		assertEquals(200000, goodsdb.getPrice());
		assertEquals("測試描述2", goodsdb.getDescription());
		assertEquals("123.jpg", goodsdb.getImage());
		assertEquals("品牌2", goodsdb.getBrand());
		assertEquals("AMD", goodsdb.getCpuBrand());
		assertEquals("AMD", goodsdb.getCpuType());
		assertEquals("GTX2080", goodsdb.getCardModel());
		assertEquals("16G", goodsdb.getMemoryCapacity());
		assertEquals("500G", goodsdb.getHdCapacity());
		assertEquals("27", goodsdb.getDisplaysize());
	}

	@Disabled
	@Test
	void testRemove() {
		goodsDao.remove(35);
		assertNull(goodsDao.findByPk(35));
	}

}
