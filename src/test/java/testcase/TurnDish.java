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
 *  这是对【转单】的测试
 * 
 * */
@Listeners({TestngRetryListener.class})
public class TurnDish {
	private OrderManage chrome;
	public static Logger log =  LogManager.getLogger(CheckOut.class.getName());
	private int desk = 20;
	
	
	@BeforeTest
	public void setUp() throws Exception {
		chrome = new OrderManage(Shop.baseUrl);
//		new Login().logIn(Shop.userName, Shop.passWord);
	}
	
	/*
	 * 部分转单到空桌台
	 */
	@Test (enabled = true)
	public void aTurnDish() {
		// a桌台下单
		chrome.selectDesks(5, "5", 1);
		// 双击一个菜
		chrome.doubleClickSingleDish(10);
		chrome.doubleClickSingleDish(11);
		//点击下单
		chrome.click(Shop.desktop_ChooseDish_Submit);
		// 确定下单
		chrome.click(Shop.desktop_ChooseDish_Submit2);
		//点击关闭按钮
		chrome.click(Shop.desktop_Order_Close);
		//重新开台
		chrome.selectDesks(6, "3", 0);
		// 双击一个菜
		chrome.doubleClickSingleDish(10);
		chrome.doubleClickSingleDish(11);
		//点击下单
		chrome.click(Shop.desktop_ChooseDish_Submit);
		// 确定下单
		chrome.click(Shop.desktop_ChooseDish_Submit2);
		//点击转台
		chrome.waitTime(5000);
		chrome.changeDish(0, 1, 1);	
		
		log.info("************部分转单成功*******");
		//点击关闭按钮
		chrome.click(Shop.desktop_Order_Close);
		//结账清台
		chrome.selectDesk(5);
		chrome.preFullPay(1);
		chrome.checkOut(1);
		chrome.waitTime(5000);
		chrome.selectDesk(6);
		chrome.preFullPay(1);
		chrome.checkOut(1);
		//点击退出
		chrome.click(Shop.desktop_DishStyle_quit);
		chrome.waitTime(5000);
		chrome.switchToDefaultFrame();
	}
	/*
	 * 全部转单到空桌台
	 */
	@Test (enabled = false)
	public void bTurnDish() {
		// a桌台下单
		chrome.selectDesks(desk, "5", 1);
		// 双击一个菜
		chrome.doubleClickSingleDish(10);
		chrome.doubleClickSingleDish(11);
		//点击下单
		chrome.click(Shop.desktop_ChooseDish_Submit);
		// 确定下单
		chrome.click(Shop.desktop_ChooseDish_Submit2);
		//点击关闭按钮
		chrome.click(Shop.desktop_Order_Close);
		//重新开台
		chrome.selectDesks(8, "3", 0);
		// 双击一个菜
		chrome.doubleClickSingleDish(10);
		chrome.doubleClickSingleDish(11);
		//点击下单
		chrome.click(Shop.desktop_ChooseDish_Submit);
		// 确定下单
		chrome.click(Shop.desktop_ChooseDish_Submit2);
		//点击转台
		chrome.waitTime(5000);
		chrome.changeDish(0, 1, 1);	
		chrome.changeDish(0, 1, 1);
		//点击提示确定
		chrome.click(Shop.desktop_Order_Right_SwitchDish_OK);
		//关闭转台页面
		chrome.click(Shop.desktop_Order_Right_SwitchDish_close);
		//关闭订单页面
		chrome.click(Shop.desktop_Order_Close);
		
		log.info("************全部转单成功*******");
		//结账清台
		chrome.selectDesk(7);
		chrome.preFullPay(1);
		chrome.checkOut(1);
		chrome.waitTime(5000);
		chrome.selectDesk(8);
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
