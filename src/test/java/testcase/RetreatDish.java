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
 *  这是对【退菜】的测试
 * 
 * */
@Listeners({TestngRetryListener.class})
public class RetreatDish {
	
	private OrderManage chrome;
	public static Logger log =  LogManager.getLogger(RetreatDish.class.getName());
	private int desk = 12;
	private String peoCount = "5";
	private String reason = "沽清";//退菜理由
	private String giftNote = "我喜欢";
	
	@BeforeTest
	public void setUp() throws Exception {
		chrome = new OrderManage(Shop.baseUrl);
		new Login().logIn(Shop.userName, Shop.passWord);
	}
	
	@Test
	public void aRetreatDish() {
		log.info("*********点菜=>下单=>退其中几个菜=>下单结账**********");
		// 开台
		chrome.selectDesks(desk, peoCount, 1);
		// 点菜
		chrome.selectSingleDish(0);
		chrome.doubleClickSingleDish(1);
		chrome.doubleClickSingleDish(2);
		// 下单
		chrome.submitDish(1);
		// 退菜
		int[] index = { 0, 1 };
		chrome.foodBack(index, reason, 1);
		// 全价预结
		chrome.preFullPay(1);
		// 结账
		chrome.checkOut(1);

	}
	
	@Test 
	public void bRetreatDish() {
		log.info("*********点菜->下单->全选退菜**********");
		// 开台
		chrome.selectDesks(desk, peoCount, 1);
		// 点菜
		chrome.selectSingleDish(0);
		chrome.doubleClickSingleDish(3);
		chrome.doubleClickSingleDish(4);
		// 下单
		chrome.submitDish(1);
		// 退菜
		int[] index = { 0, 1,2 };
		chrome.foodBack(index, 2, 1);
	}
	
	@Test (enabled = false)
	public void cRetreatDish() {
		log.info("*********点菜=>下单=>退其中几个菜=>加菜=>结账**********");
		// 开台
		chrome.selectDesks(desk, peoCount, 1);
		// 点菜
		chrome.selectSingleDish(0);
		chrome.doubleClickSingleDish(3);
		chrome.doubleClickSingleDish(4);
		// 下单
		chrome.submitDish(1);
		// 退菜
		int[] index = { 0, 1 };
		chrome.foodBack(index, 2, 1);
		// 加菜
		chrome.addDish(1, 1);
		// 全价预结
		chrome.preFullPay(1);
		// 结账
		chrome.checkOut(1);
		
	}
	
	@Test (enabled = false)
	public void dRetreatDish() {
		log.info("*********点一种菜=>下单=>退其中几份菜=>预结结账**********");
		// 开台
		chrome.selectDesks(desk, peoCount, 1);
		// 点菜
		chrome.selectSingleDish(0,"6");
		// 下单
		chrome.submitDish(1);
		// 退菜
		int[] index = {0};
		String[] count = {"5"};
		chrome.foodBack(index, count,2, 1);
		// 全价预结
		chrome.preFullPay(1);
		// 结账
		chrome.checkOut(1);
		
	}
	
	@Test (enabled = false)
	public void eRetreatDish() {
		log.info("*********点菜=>下单=>加菜=>退其中几个菜=>下单结账**********");
		// 开台
		chrome.selectDesks(desk, peoCount, 1);
		// 点菜
		chrome.selectSingleDish(2);
		// 下单
		chrome.submitDish(1);
		// 加菜单品
		chrome.addDish(0, 1);
		// 加单品
		chrome.selectSingleDish(3);
		// 下单
		chrome.submitDish(1);
		// 退菜
		int[] index = { 0, 1 };
		chrome.foodBack(index, reason, 1);
		// 全价预结
		chrome.preFullPay(1);
		// 结账
		chrome.checkOut(1);

	}
	
	@Test (enabled = false)
	public void fRetreatDish() {
		log.info("*********点一种菜=>下单=>退其中几份菜=>加菜=>赠菜=>预结结账**********");
		// 开台
		chrome.selectDesks(desk, peoCount, 1);
		// 点菜
		chrome.selectSingleDish(2,"6");
		// 下单
		chrome.submitDish(1);
		// 加菜单品
		chrome.addDish(0, 1);
		// 加单品
		chrome.selectSingleDish(2);
		// 下单
		chrome.submitDish(1);
		// 退菜
		int[] index = { 0, 1 };
		String[] count = {"5"};
		chrome.foodBack(index, count,2, 1);
		// 赠菜
		int[] index2 = { 0 };
		chrome.giftDish(index2, 0, giftNote, 1);
		// 全价预结
		chrome.preFullPay(1);
		// 结账
		chrome.checkOut(1);
		
	}
	
	@Test (enabled = false)
	public void gRetreatDish() {
		log.info("*********点一种菜=>下单=>退其中几份菜=>赠菜=>预结结账**********");
		// 开台
		chrome.selectDesks(desk, peoCount, 1);
		// 点菜
		chrome.selectSingleDish(2, "5");
		// 下单
		chrome.submitDish(1);
		// 赠菜
		int[] index2 = { 0 };
		chrome.giftDish(index2, 0, giftNote, 1);
		// 全价预结
		chrome.preFullPay(1);
		// 结账
		chrome.checkOut(1);
		
		
	}
	
	@Test (enabled = false)
	public void hRetreatDish() {
		log.info("*********点菜=>下单=>退其中几个菜=>快速加菜=>下单结账**********");
		// 开台
		chrome.selectDesks(desk, peoCount, 1);
		// 点菜
		chrome.selectSingleDish(2);
		chrome.doubleClickSingleDish(3);
		// 下单
		chrome.submitDish(1);
		// 快速加菜
		chrome.quickAddDish("2", 1);
		// 下单
		chrome.submitDish(1);
		// 全价预结
		chrome.preFullPay(1);
		// 结账
		chrome.checkOut(1);
		
	}
	
	@Test (enabled = false)
	public void iRetreatDish() {
		log.info("*********点一种菜=>下单=>退其中几份菜=>单项实收=>预结结账**********");
		// 开台
		chrome.selectDesks(desk, peoCount, 1);
		// 点菜
		chrome.selectSingleDish(6, "5");
		// 下单
		chrome.submitDish(1);
		// 单项实收
		int[] index = { 0 };
		String[] money = {"6"};
		chrome.singleRealIncome(index, money, 2);
		// 全价预结
		chrome.preFullPay(1);
		// 结账
		chrome.checkOut(1);

	}
	
	@Test (enabled = false)
	public void jRetreatDish() {
		log.info("*********点菜=>下单=>退其中几个菜=>加菜套餐=>结账**********");
		// 开台
		chrome.selectDesks(desk, peoCount, 1);
		// 点菜
		chrome.selectSingleDish(0);
		chrome.doubleClickSingleDish(3);
		chrome.doubleClickSingleDish(4);
		// 下单
		chrome.submitDish(1);
		// 退菜
		int[] index = { 0, 1 };
		chrome.foodBack(index, 2, 1);
		// 加菜套餐
		chrome.addDish(1, 2);
		// 全价预结
		chrome.preFullPay(1);
		// 结账
		chrome.checkOut(1);
		
	}
	
	@Test
	public void kRetreatDish() {
		log.info("*********点套餐->下单->全单退菜**********");
		// 开台
		chrome.selectDesks(desk, peoCount, 1);
		// 点菜
		chrome.selectSingleDish(0);
		chrome.doubleClickSingleDish(1);
		chrome.doubleClickSingleDish(2);
		// 下单
		chrome.submitDish(1);
		// 全单退菜
		chrome.allFoodBack(reason, 1);	
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
