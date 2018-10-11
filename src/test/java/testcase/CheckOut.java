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
 *  这是对【结账】的测试
 *  测试点： 现金，会员卡，组合
 * 
 * */
@Listeners({TestngRetryListener.class})
public class CheckOut {
	
	private OrderManage chrome;
	public static Logger log =  LogManager.getLogger(CheckOut.class.getName());
	private String VipCard = "17090403700";
	private String verificationCode = "666666";
	private int desk = 26;
	private int dish = 1;
	private int cash = 1;
	
	@BeforeTest
	public void setUp() throws Exception {
		chrome = new OrderManage(Shop.baseUrl);
		new Login().logIn(Shop.userName, Shop.passWord);
		
	}
	
	@Test  
	public void aCheckOut() {
		log.info("**************点菜=>下单=>订单预结=>现金结账*****************");
		chrome.selectDesks(desk, "3", 1);
		chrome.selectSingleDish(dish);
		chrome.submitDish(1);
		chrome.preFullPay(1);
		chrome.checkOut(1);
	}
	
	@Test
	public void bCheckOut() {
		log.info("**************点菜->下单->订单预结->微生活*****************");
		// 开台
		chrome.selectDesks(desk, "3", 1);
		// 点菜
		chrome.selectSingleDish(dish);
		// 下单
		chrome.submitDish(1);
		// 选择结账方式
		chrome.prePay(3, VipCard, null, 0, null, 2, null, null);
		chrome.prePay_Ok(1);
		// 会员卡订单结账
		chrome.checkOut_Vip(0, null, 1);
		chrome.checkOut_Ok(1,1);
	}
	
	@Test
	public void cCheckOut() {
		log.info("**************点菜->下单->订单预结->微生活积分*****************");
		// 开台
		chrome.selectDesks(desk, "3", 1);
		// 点菜
		chrome.selectSingleDish(dish);
		// 下单
		chrome.submitDish(1);
		// 选择结账方式
		chrome.prePay(3, VipCard, null, 0, null, 2, null, null);
		chrome.prePay_Ok(1);
		// 会员卡订单结账
		chrome.checkOut_Vip(0, null, 1);
		chrome.clickByText(Shop.checkOut_StyleTable, "微生活积分");
		chrome.checkOut_Ok(1,1);
		
	}
	
	@Test 
	public void dCheckOut() {

		log.info("**************点菜->下单->订单预结->普通&微生活会员卡*****************");
		//开台
		chrome.selectDesks(desk, "3", 1);
		// 点菜
		chrome.selectSingleDish(dish);
		// 下单
		chrome.submitDish(1);
		// 选择结账方式
		chrome.prePay(3,VipCard,null,0,null,2,null,null);
		chrome.prePay_Ok(1);
		// 会员卡订单结账
		chrome.click(Shop.desktop_Order_CombainCheckout);
		chrome.type(Shop.combainCheckout_Cash, cash+"");
		// 输入储值卡金额
		chrome.type(Shop.combainCheckout_ValueCard, (getMoney()-cash)+"");
		// 获取验证码
		chrome.click(Shop.combainCheckout_GetVerificationCode);
		chrome.click(Shop.combainCheckout_GetVerificationCode_Ok);
		// 输入验证码
		chrome.type(Shop.combainCheckout_VerificationCode, verificationCode);
		// 
		chrome.checkOut_Ok(2,1);
		
	}
	
	@Test
	public void eCheckOut() {
		log.info("**************点菜->下单->订单预结->普通&微生活积分*****************");
		//开台
		chrome.selectDesks(desk, "3", 1);
		// 点菜
		chrome.selectSingleDish(dish);
		// 下单
		chrome.submitDish(1);
		// 选择结账方式
		chrome.prePay(3,VipCard,null,0,null,2,null,null);
		chrome.prePay_Ok(1);
		// 会员卡订单结账
		chrome.click(Shop.desktop_Order_CombainCheckout);
		chrome.type(Shop.combainCheckout_Cash, cash+"");
		// 输入微生活积分金额
		chrome.type(Shop.combainCheckout_WeiSHIntegral, (getMoney()-cash)+"");
		// 获取验证码
		chrome.click(Shop.combainCheckout_GetVerificationCode);
		chrome.click(Shop.combainCheckout_GetVerificationCode_Ok);
		// 输入验证码
		chrome.type(Shop.combainCheckout_VerificationCode, verificationCode);
		// 
		chrome.checkOut_Ok(2,1);
	}
	
	@Test
	public void fCheckOut() {
		log.info("**************点菜->下单->订单预结->微生活券->组合结账->现金&券*****************");
		//开台
		chrome.selectDesks(desk, "3", 1);
		// 点菜
		chrome.selectSingleDish(4);
		// 下单
		chrome.submitDish(1);
		// 选择结账方式
		chrome.prePay(3,VipCard,null,0,null,2,null,null);
		chrome.prePay_Ok(1);
		// 券订单结账
		chrome.checkOut_Vip(null, 0, 1);
		chrome.click(Shop.order_TurntocombainCheckout_Ok);
		// 输入现金
		chrome.type(Shop.combainCheckout_Cash, chrome.getMoneyByDouble(Shop.combainCheckout_ShengYuYinfu)+"");
		// 确定
		chrome.checkOut_Ok(2,1);
		
	}
	
	@Test
	public void gCheckOut() {
		log.info("**************点菜->下单->订单预结->抹零*****************");
		//开台
		chrome.selectDesks(desk, "3", 1);
		// 点菜
		chrome.selectSingleDish(dish);
		// 下单
		chrome.submitDish(1);
		// 选择结账方式
		chrome.prePay(0,null,null,null,null,null,null,null);
		chrome.prePay_Ok(1);
		// 抹零
		chrome.checkOut(null,0,verificationCode,"0.1");
		// 
		chrome.checkOut_Ok(1,1);
	}
	
	public int getMoney() {
		int money = 0;
		String[] str = chrome.getText(Shop.order_Shishou).split("：");
		if (str!= null) {
			money = Integer.valueOf(str[1]);
		} else {
			log.error("Don't get the shishou money!");
		}
		return money;
		
	}
	@AfterMethod (alwaysRun = true)
	public void captureAll() {
		desk++;
		dish++;
		chrome.open(Shop.baseUrl);
		chrome.waitTime(3000);
	}
	
	@AfterClass
	public void tearDown() {
		chrome.closeDriver();
	}
	

}
