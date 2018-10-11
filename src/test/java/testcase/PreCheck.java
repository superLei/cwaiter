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
 * 这是对【预结】的测试
 * 测试点： 全价，支付宝，微信
 * 
 * */
@Listeners({TestngRetryListener.class})
public class PreCheck {
	
	private OrderManage chrome;
	public static Logger log =  LogManager.getLogger(PreCheck.class.getName());
	private int desk = 25;
	private String peoCount = "2";
	
	@BeforeTest
	public void setUp() throws Exception {
		chrome = new OrderManage(Shop.baseUrl);
		new Login().logIn(Shop.userName, Shop.passWord);
	}
	

	@Test  (enabled = true)
	public void aPreCheck()  {
		log.info("****************点菜=>下单=>全价预结=>结账******************");
		// 开台
		chrome.selectDesks(desk, peoCount, 1);
		// 点菜
		chrome.selectSingleDish(3);
		chrome.selectSingleDish(5);
		// 下单
		chrome.submitDish(1);
		// 全价预结
		chrome.preFullPay(1);
		// 结账
		chrome.checkOut(1);
	}
	

	@Test (enabled = true)
	public void bPreCheck() {
		log.info("****************点菜->下单->订单预结->普通打折->8折******************");
		// a桌台下单
		chrome.selectDesks(7, "5", 1);
		// 双击一个菜
		chrome.doubleClickSingleDish(10);
		chrome.doubleClickSingleDish(11);
		//点击下单
		chrome.click(Shop.desktop_ChooseDish_Submit);
    	// 确定下单
		chrome.click(Shop.desktop_ChooseDish_Submit2);
		//订单预结-8折
		chrome.pre_settlement_Discount(2,1);
		log.info("***预结成功***");
		//订单结账
		chrome.checkOut(1);
		
	}
	 
	@Test (enabled = true)
	public void cPreCheck() {
		log.info("***************点菜->下单->订单预结->全价->营销活动*******************");
		// 开台
		chrome.selectDesks(desk, peoCount, 1);
		// 点菜
		chrome.doubleClickSingleDish(3);
		chrome.doubleClickSingleDish(5);
		chrome.selectSingleDish(6);
		chrome.selectSingleDish(9);
		// 下单
		chrome.submitDish(1);
		//预结按钮
		chrome.click(Shop.desktop_Order_Right_PreCheck);
		//营销活动
		chrome.click(Shop.desktop_Order_Marketing_Pre);
		//提交
		chrome.click(Shop.desktop_Order_Marketing_Pre_Sure);
		//是
		chrome.click(Shop.desktop_Order_Marketing_Pre_is);
		// 结账
		chrome.checkOut(1);
	}
	
	@Test (enabled = true)
	public void dPreCheck() {
		log.info("***************点菜->下单->订单预结->微生活会员卡-> 微生活会员卡号*******************");
		// 开台
		chrome.selectDesks(desk, peoCount, 1);
		// 点菜
		chrome.doubleClickSingleDish(3);
		chrome.doubleClickSingleDish(5);
		chrome.selectSingleDish(6);
		chrome.selectSingleDish(9);
		// 下单
		chrome.submitDish(1);
		//预结按钮
		chrome.click(Shop.desktop_Order_Right_PreCheck);
		//会员卡
		chrome.click(Shop.desktop_Order_Weishenghuo_member);
		//卡号
		chrome.type(Shop.desktop_Order_Weishenghuo_card,"13241633308");
		//服务费
		chrome.click(Shop.desktop_Order_Fee);
		//提交
		chrome.click(Shop.desktop_Order_Marketing_Pre_Sure);
		//是
		chrome.click(Shop.desktop_Order_Marketing_Pre_is);
		// 结账
		//chrome.checkOut(1);		
	}
	
	@Test (enabled = true)
	public void ePreCheck() {

		log.info("***************点菜->下单->订单预结->额外折让*******************");
		// 开台
		chrome.selectDesks(desk, peoCount, 1);
		// 点菜
		chrome.doubleClickSingleDish(3);
		chrome.doubleClickSingleDish(5);
		chrome.selectSingleDish(6);
		chrome.selectSingleDish(9);
		// 下单
		chrome.submitDish(1);
		//预结按钮
		chrome.click(Shop.desktop_Order_Right_PreCheck);
		//额外折让
		chrome.type(Shop.desktop_Order_Extra,"5");
		//提交
		chrome.click(Shop.desktop_Order_Marketing_Pre_Sure);
		//是
		chrome.click(Shop.desktop_Order_Marketing_Pre_is);
		// 结账
		chrome.checkOut(1);			
	}
	
	@AfterMethod
	public void captureAll() {
		desk++;
		chrome.open(Shop.baseUrl);
		chrome.waitTime(3000);
	}
	
	@AfterClass
	public void tearDown() {
		chrome.closeDriver();
	}

}
