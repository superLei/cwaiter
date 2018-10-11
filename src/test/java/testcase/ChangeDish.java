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
 *  这是对【换菜】的测试
 *  测试点： 单品，套餐
 * 
 * */
public class ChangeDish {
	
	private ShopChrome chrome;
	public static Logger log =  LogManager.getLogger(ChangeDish.class.getName());
	
	@BeforeTest
	public void setUp() throws Exception {
		chrome = new ShopChrome(Shop.baseUrl);
		new Login().logIn(Shop.userName, Shop.passWord);
	}
	
	@Test
	public void testDish() throws Exception {
		log.info("****************单品换菜******************");
	}
	
	@Test
	public void testPackage() throws Exception {
		log.info("****************套餐换菜******************");
	}
	
	@AfterClass
	public void tearDown() throws Exception {
		
	}
}

