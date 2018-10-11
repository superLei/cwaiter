package testcase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import page.Login;
import page.OrderManage;
import page.Shop;
import utils.TestngRetryListener;


/**
 *  [组合结账]的测试
 * 
 * */
@Listeners({TestngRetryListener.class})
public class CombinationCheckout {
	
	private OrderManage chrome;
	public static Logger log =  LogManager.getLogger(CombinationCheckout.class.getName());
//	private String deskNum = "A2";
	private int desk = 3;
	
	
	@BeforeTest
	public void setUp() throws Exception {
		chrome = new OrderManage(Shop.baseUrl);
		new Login().logIn(Shop.userName, Shop.passWord);
		chrome.selectDesks(desk, "3", 1);
	}
	
	@Test
	public void aCombinationCheckout() {
		log.info("**********点菜=>下单=>订单预结=>现金和会员卡结账***********");
	}	
	
	@AfterClass
	public void tearDown() throws Exception {
		
	}

}
