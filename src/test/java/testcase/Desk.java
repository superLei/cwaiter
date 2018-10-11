package testcase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import page.Shop;
import utils.ShopChrome;
import utils.TestngRetryListener;

/**
 *  对【桌台】的测试
 *  测试点： 无效桌台、有效桌台
 *  要求： 桌台B4 必须是无效桌台
 * 
 * */

@Listeners({TestngRetryListener.class})
public class Desk {
	
	private ShopChrome chrome;
	//private String[] deskNum = {"A20","A21","A22","A23"};
	private String deskNotExsit = "B4";
//	private String dish1 ="云南腊肉炒香笋";
	//private String invaildDeskNum = "X8";
	public static Logger log =  LogManager.getLogger(Desk.class.getName());
	@BeforeTest
	public void setUp() throws Exception {
		
		log.info("《---------------------------------------------------》");
		chrome = new ShopChrome(Shop.baseUrl);
		chrome.logIn(Shop.userName, Shop.passWord);
		// 进入当前客人管理
		chrome.changeFrame(Shop.orderMagFrame);
		chrome.click(Shop.nv_OrderManager_CurPeo);
	}
	
	@Test(enabled = false)
	public void testSelectDesk() throws Exception {
		log.info("《--------------选择桌台：无效桌台------------------》");
		
		// 选择有效桌台
		chrome.switchToDefaultFrame();
		chrome.changeFrame(Shop.deskStatusFrame);
		for (int i = 10; i < 13; i++) {
			chrome.selectDesk(i);
			chrome.click(Shop.desktop_Cancel);
		}
		// 选择无效桌台
		Assert.assertTrue(!chrome.selectDesk(deskNotExsit));
		
	}
	@Test
	public void testMulitDesk() throws Exception {
		log.info("《--------------------开多个桌台=>下单=>结账------------------------》");
		chrome.switchToDefaultFrame();
		chrome.changeFrame(Shop.deskStatusFrame);
		for (int i = 12; i < 15; i++) {
			chrome.selectDesk(i);
			chrome.click(Shop.desktop_Submit);
			// 点菜下单并确认
			chrome.clickDish(1);
			chrome.click(Shop.desktop_ChooseDish_Save);
			chrome.click(Shop.desktop_ChooseDish_Submit);
			chrome.click(Shop.desktop_ChooseDish_Submit2);
			chrome.click(Shop.desktop_Order_Close);
			chrome.waitTime(3000);
		}
		//桌台结账
		for (int i = 12; i < 15; i++) {
			chrome.selectDesk(i);
			// 全价预结并确认
			chrome.click(Shop.desktop_ChooseDish_FullPrePay);
			
			chrome.click(Shop.desktop_ChooseDish_Submit2);
			// 订单结账提交并确认
			chrome.waitTime(3500);
			chrome.click(Shop.desktop_ChooseDish_Bill);
			chrome.click(Shop.desktop_Submit);
			chrome.click(Shop.desktop_ChooseDish_Submit2);
			chrome.waitTime(3000);
		}
		
		
	}
	

	
	@AfterClass
	public void tearDown() throws Exception {
		chrome.closeDriver();
	}


}

