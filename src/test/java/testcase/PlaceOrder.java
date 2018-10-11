package testcase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import page.Shop;
import utils.ShopChrome;
import utils.TestngRetryListener;

/**
 *  这是对【下单】功能的测试
 *  测试点： 空菜品下单，单品下单， 套餐下单,单品删除，套餐删除，
 *  验证下单菜品的数目是否一致
 *  
 * */

@Listeners({TestngRetryListener.class})
public class PlaceOrder {
	
	private ShopChrome chrome;
	//private String dish1 ="云南腊肉炒香笋";
	//private String dish2 ="工作日2人套餐";
	private int deskNum = 1;
	//private int rows;
	public static Logger log =  LogManager.getLogger(PlaceOrder.class.getName());
	
	@BeforeTest
	public void setUp() throws Exception {
		chrome = new ShopChrome(Shop.baseUrl);
		chrome.logIn(Shop.userName, Shop.passWord);
		// 当前客人管理
		chrome.changeFrame(Shop.orderMagFrame);
		chrome.click(Shop.nv_OrderManager_CurPeo);
	}
	
	@Test (enabled = true)//<------------------------单品下单------------------------>
	public void atestPlaceOrder() throws Exception {
		log.info("<------------------------单品下单------------------------>");
		
		// 空菜品下单
		chrome.switchToDefaultFrame();
		chrome.changeFrame(Shop.deskStatusFrame);
		chrome.selectDesk(deskNum);//选择桌台
		chrome.click(Shop.desktop_Submit);
		chrome.click(Shop.desktop_ChooseDish_Submit);
		chrome.click(Shop.desktop_ChooseDish_Submit2);
		// 点菜下单并确认
		chrome.clickDish(2);
		chrome.waitTime(3000);
		chrome.click(Shop.desktop_ChooseDish_Save);
		for (int i = 0; i < 3; i++) {
			chrome.doubleClickSingleDish(2);
			chrome.click(Shop.desktop_ChooseDish_Submit2);
		}
		chrome.click(Shop.desktop_ChooseDish_Del);//单品删除
		chrome.click(Shop.desktop_ChooseDish_SelectAll);
		chrome.click(Shop.desktop_ChooseDish_Submit);
		chrome.click(Shop.desktop_ChooseDish_Submit2);
		/*rows = chrome.getRowCount(Shop.desktop_Order_Table);
		if(rows != 6) {
			System.out.println("------------error:rows=["+rows+"]----------");
			throw new Exception ("The row's count is not correctly!");
		}*/
		// 全价预结并确认
		chrome.waitTime(3500);
		chrome.click(Shop.desktop_ChooseDish_FullPrePay);
		chrome.click(Shop.desktop_ChooseDish_Submit2);
		// 订单结账提交并确认
		chrome.waitTime(3500);
		chrome.click(Shop.desktop_ChooseDish_Bill);
		chrome.click(Shop.desktop_Submit);
		chrome.click(Shop.desktop_ChooseDish_Submit2);
	}
	
	// <------------------------套餐下单------------------------>
	@Test
	public void btestPlaceOrder2() throws Exception {
		log.info("<------------------------套餐下单------------------------>");
		// 点菜下单并确认
		chrome.switchToDefaultFrame();
		chrome.changeFrame(Shop.deskStatusFrame);
		chrome.selectDesk(deskNum);// 选择桌台
		chrome.click(Shop.desktop_Submit);
		chrome.click(Shop.desktop_ChooseDish_Package);
		chrome.clickPackage(4);
		chrome.click(Shop.desktop_ChooseDish_Save);
		for (int i = 0; i < 3; i++) {
			chrome.clickPackage(4);
			chrome.click(Shop.desktop_ChooseDish_Save);
			chrome.click(Shop.desktop_ChooseDish_Submit2);//重复确定
		}
		chrome.click(Shop.desktop_ChooseDish_Del);//套餐删除
		chrome.click(Shop.desktop_ChooseDish_SelectAll);
		chrome.click(Shop.desktop_ChooseDish_Submit);
		chrome.click(Shop.desktop_ChooseDish_Submit2);
		/*rows = chrome.getRowCount(Shop.desktop_Order_Table);
		if(rows != 6) {
			System.out.println("------------error:rows=["+rows+"]----------");
			throw new Exception ("The row's count is not correctly!");
		} */
		// 全价预结并确认
		chrome.waitTime(3500);
		chrome.click(Shop.desktop_ChooseDish_FullPrePay);
		chrome.waitTime(1000);
		chrome.click(Shop.desktop_ChooseDish_Submit2);
		// 订单结账提交并确认
		chrome.waitTime(3500);
		chrome.click(Shop.desktop_ChooseDish_Bill);
		chrome.click(Shop.desktop_Submit);
		chrome.click(Shop.desktop_ChooseDish_Submit2);
	}
	
	
	@AfterMethod
	public void after() throws Exception {
		chrome.waitTime(3000);
	}

	@AfterClass
	public void tearDown() throws Exception {
		chrome.closeDriver();
	}

}
