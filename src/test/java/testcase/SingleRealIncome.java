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
 *  这是对【单项实收】的测试
 * 
 * */
@Listeners({TestngRetryListener.class})
public class SingleRealIncome {
	
	private OrderManage chrome;
	public static Logger log =  LogManager.getLogger(SingleRealIncome.class.getName());
	private int desk = 16;
	private String peoCount = "2";
	
	@BeforeTest
	public void setUp() throws Exception {
		chrome = new OrderManage(Shop.baseUrl);
		new Login().logIn(Shop.userName, Shop.passWord);
	}
	
	@Test
	public void aSingleRealIncome() {
		log.info("*********点菜->下单->多次单项实收->预结**********");
		// 开台
		chrome.selectDesks(desk, peoCount, 1);
		// 点菜
		chrome.selectSingleDish(2);
		// 下单
		chrome.submitDish(1);
		// 单项实收
		int[] index = { 0 };
		String[] money = { "10" };
		chrome.singleRealIncome(index, money, 2);
		// 全价预结
		chrome.preFullPay(1);
		// 单项实收
		int[] index2 = { 0 };
		String[] money2 = { "8" };
		chrome.singleRealIncome(index2, money2, 2);
		// 全价预结
		chrome.preFullPay(1);
		// 单项实收
		int[] index3 = { 0 };
		String[] money3 = { "12" };
		chrome.singleRealIncome(index3, money3, 2);
		// 全价预结
		chrome.preFullPay(1);
		// 结账
		chrome.checkOut(1);
	}
	
	@Test
	public void bSingleRealIncome() {
		log.info("*********点套餐->下单->单项实收[输入大于价格]->预结**********");
		// 开台
		chrome.selectDesks(desk, peoCount, 1);
		// 点菜
		chrome.selectSingleDish(4);
		// 下单
		chrome.submitDish(1);
		// 单项实收
		int[] index = { 0 };
		String[] money = { "288" };
		chrome.singleRealIncome(index, money, 2);
		// 全价预结
		chrome.preFullPay(1);
		// 结账
		chrome.checkOut(1);
	}
	
	@Test (enabled =false)
	public void cSingleRealIncome() {
		log.info("*********点一个菜多份=>下单=>单项实收=>预结结账**********");
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
