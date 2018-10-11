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
 *  这是对【沽清】的测试
 *  测试点： 当天，当餐，长期，取消，相互转换
 * 
 * */
public class SellOut {
	
	

	private ShopChrome chrome;
	public static Logger log =  LogManager.getLogger(SellOut.class.getName());
	
	@BeforeTest
	public void setUp() throws Exception {
		chrome = new ShopChrome(Shop.baseUrl);
		new Login().logIn(Shop.userName, Shop.passWord);
	}
	
	@Test
	public void testToday() throws Exception {
		log.info("****************当天沽清******************");
	}
	@Test
	public void testThisTime() throws Exception {
		log.info("****************当餐沽清******************");
	}
	@Test
	public void testLong() throws Exception {
		log.info("****************长期沽清******************");
	}
	@Test
	public void testCancel() throws Exception {
		log.info("****************取消沽清******************");
	}
	@Test
	public void testMulit() throws Exception {
		log.info("****************当天转换成当餐******************");
	}
	@Test
	public void testMulit2() throws Exception {
		log.info("****************当天转换成长期******************");
	}
	
	@AfterClass
	public void tearDown() throws Exception {
		
	}

}