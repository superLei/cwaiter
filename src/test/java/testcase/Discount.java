package testcase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import page.Login;
import page.Shop;
import utils.ShopChrome;

/**
 *  这是对【折扣】的测试
 *  测试点： 全单、单品、套餐
 * 
 * */
public class Discount {
	
	private ShopChrome chrome;
	public static Logger log =  LogManager.getLogger(Discount.class.getName());
	
	@BeforeTest
	public void setUp() throws Exception {
		chrome = new ShopChrome(Shop.baseUrl);
		new Login().logIn(Shop.userName, Shop.passWord);
	}
	
	@Test
	public void testDish() throws Exception {
		log.info("****************单品折扣******************");
	}
	
	@Test
	public void testPackage() throws Exception {
		log.info("****************套餐折扣******************");
	}
	@Test
	public void testFull() throws Exception {
		log.info("****************全单折扣******************");
	}

	@AfterClass
	public void tearDown() throws Exception {
		
	}

}
