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
 *  这是对【等叫】的测试
 *  测试点: 
 * 
 * */
public class WaitCall {
	
	private ShopChrome chrome;
	public static Logger log =  LogManager.getLogger(WaitCall.class.getName());
	
	@BeforeTest
	public void setUp() throws Exception {
		chrome = new ShopChrome(Shop.baseUrl);
		new Login().logIn(Shop.userName, Shop.passWord);
	}
	
	@Test
	public void testWaitCall() throws Exception {
		log.info("****************等叫******************");
	}
	
	@AfterClass
	public void tearDown() throws Exception {
		
		
	}

}