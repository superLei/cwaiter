package testcase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import page.Login;
import page.OrderManage;
import page.Shop;
import utils.TestngRetryListener;

/**
 *  这是对【转台】的测试
 * 
 * */
@Listeners({TestngRetryListener.class})
public class TureOrder {
	
	private OrderManage chrome;
	public static Logger log =  LogManager.getLogger(CheckOut.class.getName());
	private int desk = 24;
	
	
	@BeforeTest
	public void setUp() throws Exception {
		chrome = new OrderManage(Shop.baseUrl);
//		new Login().logIn(Shop.userName, Shop.passWord);
	}
	/*
	 * 转台
	 */
	@Test (enabled = true)
	public void aTureOrder() {
		// a桌台下单
		chrome.selectDesks(desk, "5", 1);
		// 双击一个菜
		chrome.doubleClickSingleDish(10);
		
		//点击下单
		chrome.click(Shop.desktop_ChooseDish_Submit);
		// 确定下单
		chrome.click(Shop.desktop_ChooseDish_Submit2);
		//点击转台
		chrome.waitTime(3000);
		chrome.changeDesk(10, 1);		
		log.info("************转台成功*******");
		chrome.waitTime(5000);
		chrome.selectDesk(10);
		chrome.preFullPay(1);
		chrome.checkOut(1);
		
		
		
	}
	
	@AfterMethod
	public void captureAll() {
		desk++;
		chrome.open(Shop.baseUrl);
		chrome.waitTime(3000);
	}
	
	@AfterClass
	public void tearDown() throws Exception {
		chrome.closeDriver();
	}
		

}
