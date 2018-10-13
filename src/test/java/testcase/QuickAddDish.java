package testcase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import page.Login;
import page.OrderManage;
import page.Shop;
import utils.TestngRetryListener;


/**
 *  这是对【快速加菜】的测试
 *
 * 
 * */
@Listeners({TestngRetryListener.class})
public class QuickAddDish {
	
	private OrderManage chrome;
	public static Logger log =  LogManager.getLogger(QuickAddDish.class.getName());
	private int desk = 6;
	private String peoCount = "6";
	
	@BeforeTest
	public void setUp() throws Exception {
		chrome = new OrderManage(Shop.baseUrl);
//		new Login().logIn(Shop.userName, Shop.passWord);
	}
	
	@Test
	public void aQuickAddDish() {
		log.info("*********点菜=>下单=>快速加菜=>下单结账**********");
		// 开台
		log.info("*****开台******");
		chrome.selectDesks(desk, peoCount, 1);
		// 点菜
		log.info("*****点菜******");
		chrome.selectPackage(0);
		// 下单
		log.info("*****下单******");
		chrome.submitDish(1);
		// 快速加菜
		log.info("*****快速加菜******");
		chrome.quickAddDish("3", 1);
		// 全价预结
		log.info("*****全价预结******");
		chrome.preFullPay(1);
		// 结账
		log.info("*****结账******");
		chrome.checkOut(1);
	}
}
