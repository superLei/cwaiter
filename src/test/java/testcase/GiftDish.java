package testcase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import page.Login;
import page.OrderManage;
import page.Shop;


/**
 *  这是对【赠菜】的测试
 * 
 * */
public class GiftDish {
	
	private OrderManage chrome;
	public static Logger log =  LogManager.getLogger(GiftDish.class.getName());
	private int desk = 6;
	
	@BeforeTest
	public void setUp() throws Exception {
		chrome = new OrderManage(Shop.baseUrl);
		new Login().logIn(Shop.userName, Shop.passWord);
	}
	
	@Test
	public void aGiftDish() {
		log.info("*********点菜=>下单=>赠菜=>下单结账***********");
		// 开台
		chrome.selectDesks(desk, "2", 1);
		// 点菜
		chrome.selectSingleDish(0);
		chrome.doubleClickSingleDish(1);
		chrome.doubleClickSingleDish(2);
		// 下单
		chrome.submitDish(1);
		// 赠菜
		int[] index = { 0, 1 };
		chrome.giftDish(index, 0, "123", 1);
		// 全价预结
		chrome.preFullPay(1);
		// 结账
		chrome.checkOut(1);
	}
	
	@Test (enabled = false)
	public void bGiftDish() {
		log.info("*********点菜=>下单=>赠菜=>加菜=>下单结账***********");
	}
	
	@Test (enabled = false)
	public void cGiftDish() {
		log.info("*********点菜=>下单=>赠菜=>快速加菜=>下单结账***********");
	}
	
	@Test (enabled = false)
	public void dGiftDish() {
		log.info("*********点菜=>下单=>赠菜=>退菜=>下单结账***********");
	}
	
	@Test (enabled = false)
	public void eGiftDish() {
		log.info("*********点菜=>下单=>赠菜=>快速加菜=>加菜=>下单结账***********");
	}
	
	@Test (enabled = false)
	public void fGiftDish() {
		log.info("*********点菜=>下单=>赠菜=>快速加菜=>退菜=>下单结账***********");
	}
	
	@Test (enabled = false)
	public void gGiftDish() {
		log.info("*********点菜=>下单=>赠菜=>退菜=>快速加菜=>下单结账***********");
	}
	
	@Test (enabled = false)
	public void hGiftDish() {
		log.info("*********点菜=>下单=>赠菜=>退菜=>加菜=>下单结账***********");
	}
	
	@Test (enabled = false)
	public void iGiftDish() {
		log.info("*********点菜=>下单=>赠菜=>加菜=>退菜=>预结结账***********");
	}
	
	@Test (enabled = false)
	public void jGiftDish() {
		log.info("*********点菜=>下单=>赠菜=>加菜=>赠菜=>预结结账***********");
	}
	
	@Test (enabled = false)
	public void kGiftDish() {
		log.info("*********点菜=>下单=>赠菜=>加菜=>快速加菜=>预结结账***********");
	}
	
	@AfterMethod (enabled = false)
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
