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
 *  这是对门店【点菜】的测试
 *  测试点： 点套餐下单； 点单个菜品下单；先点单个菜品，再点套餐；先点套餐，再点单个菜品；
 *  @date 2016-6-22
 *  @author SuLei
 * */
@Listeners({TestngRetryListener.class})
public class OrderDish {
	
	private OrderManage chrome;
	public static Logger log =  LogManager.getLogger(OrderDish.class.getName());
	private int desk = 1;
	private String peoCount = "20";
	private int dish2 = 4;
	
	@BeforeTest
	public void setUp() throws Exception {
		chrome = new OrderManage(Shop.baseUrl);
		new Login().logIn(Shop.userName, Shop.passWord);
	}
	
	@Test 
	public void aOrderDish() throws Exception {
		log.info("**************点套餐=>下单=>结账***************");
		chrome.selectDesks(desk,peoCount,1);
		chrome.selectPackage(dish2);
		chrome.submitDish(1);
		chrome.preFullPay(1);
		chrome.checkOut(1);	
	}
	
	@Test
	public void bOrderDish() throws Exception {
		log.info("***********点单个菜品=>下单=>结账*****************");
		chrome.selectDesks(desk,peoCount,1);
		chrome.doubleClickSingleDish(dish2);
//		chrome.submitDish(0);
		chrome.submitDish(1);
		chrome.preFullPay(1);
//		chrome.checkOut(0);	
		chrome.checkOut(1);	
	}
	
	@Test 
	public void cOrderDish() throws Exception {
		log.info("***********先点单个菜品=>再点套餐=>下单结账*****************");
		chrome.selectDesks(desk,peoCount,1);
		chrome.doubleClickSingleDish(dish2);
		chrome.selectPackage(dish2);
		chrome.submitDish(1);
		chrome.preFullPay(1);
		chrome.checkOut(1);	
	}
	
	@Test (enabled = false)
	public void dOrderDish() throws Exception {
		log.info("***********先点套餐=>再点单个菜品=>下单结账*****************");
		chrome.selectDesks(desk,peoCount,1);
		chrome.selectPackage(dish2);
		chrome.selectSingleDish(1);
		chrome.submitDish(1);
		chrome.preFullPay(1);
		chrome.checkOut(1);	
	}
	
	@Test
	public void eOrderDish() throws Exception {
		log.info("***********点两个菜品=>删除一个菜品=>下单结账*****************");
		// 开台
		chrome.selectDesks(desk, peoCount, 1);
		// 点菜
		chrome.selectPackage(0);
		chrome.selectSingleDish(dish2);
		// 删除菜品
		chrome.click(Shop.desktop_ChooseDish_Del);
		// 下单
		chrome.submitDish(1);
		// 全价预结
		chrome.preFullPay(1);
		// 结账
		chrome.checkOut(1);
	}
	
	@Test
	public void fOrderDish() {
		log.info("***********点两个菜品=>备注一个菜品=>下单结账*****************");
		// 开台
		chrome.selectDesks(desk,peoCount,1);
		// 点菜
		chrome.selectSingleDish(dish2);
		chrome.waitTime(1500);
		chrome.selectPackage(0);
		// 备注
		chrome.noteDish(0, "好吃", 1);
		// 下单
		chrome.submitDish(1);
		// 全价预结
		chrome.preFullPay(1);
		// 结账
		chrome.checkOut(1);
		
	}
	
	@Test
	public void gOrderDish() {
		log.info("***********点两个菜品=>全单备注=>下单结账*****************");
		// 开台
		chrome.selectDesks(desk, peoCount, 1);
		// 点菜
		chrome.selectSingleDish(dish2);
		chrome.waitTime(1500);
		chrome.selectPackage(0);
		// 全单备注
		chrome.noteDish(0, "好吃", 3);
		// 下单
		chrome.submitDish(1);
		// 全价预结
		chrome.preFullPay(1);
		// 结账
		chrome.checkOut(1);
	}
	
	@Test(enabled = false)
	public void hOrderDish() {
		log.info("***********点一个菜品=>修改赠菜=>下单结账*****************");
		// 开台
		chrome.selectDesks(desk, peoCount, 1);
		// 点菜
		chrome.selectSingleDish(dish2);
		// 修改赠菜
		chrome.click(Shop.desktop_ChooseDish_Gift);
		// 下单
		chrome.submitDish(1);
		// 全价预结
		chrome.preFullPay(1);
		// 结账
		chrome.checkOut(1);
		
	}
	
	@Test (enabled = false)
	public void iOrderDish() {
		log.info("***********点两个菜品=>全选删除=>点一个菜品=>修改赠菜=>下单结账*****************");
		// 开台
		chrome.selectDesks(desk, peoCount, 1);
		// 点菜
		chrome.selectSingleDish(dish2);
		chrome.waitTime(1500);
		chrome.selectPackage(2);
		// 全选删除
		chrome.click(Shop.desktop_ChooseDish_SelectAll);
		chrome.click(Shop.desktop_ChooseDish_Del);
		// 点菜
		chrome.selectPackage(1);
		// 修改赠菜
		chrome.click(Shop.desktop_ChooseDish_Gift);
		// 下单
		chrome.submitDish(1);
		// 全价预结
		chrome.preFullPay(1);
		// 结账
		chrome.checkOut(1);
		
	}
	
	@Test (enabled = false)
	public void lOrderDish() {
		log.info("***********点一个菜品=>编辑=>修改数量=>下单结账*****************");
		// 开台
		chrome.selectDesks(desk, peoCount, 1);
		// 点菜
		chrome.selectSingleDish(dish2);
		// 编辑
		chrome.click(Shop.desktop_ChooseDish_EditBtn);
		// 修改数量
		chrome.type(Shop.desktop_ChooseDish_Edit_Ipt, "6");
		chrome.click(Shop.desktop_ChooseDish_Edit_Submit);
		// 下单
		chrome.submitDish(1);
		// 全价预结
		chrome.preFullPay(1);
		// 结账
		chrome.checkOut(1);
				

	}
	
	@Test (enabled = false)
	public void mOrderDish() {
		log.info("***********点一个菜品=>修改数量=>保存=>下单结账*****************");
		// 开台
		chrome.selectDesks(desk, peoCount, 1);
		// 点菜修改数量
		chrome.selectSingleDish(dish2, "6");
		// 下单
		chrome.submitDish(1);
		// 全价预结
		chrome.preFullPay(1);
		// 结账
		chrome.checkOut(1);	
	}
	
	@Test
	public void nOrderDish() {
		log.info("***********点一个菜品->修改等叫(等叫，取消等叫)->下单*****************");
		// 开台
		chrome.selectDesks(desk, peoCount, 1);
		// 点菜
		chrome.selectSingleDish(dish2);
		// 修改等叫
		chrome.click(Shop.desktop_ChooseDish_Wait);
		// 下单
		chrome.submitDish(1);
		// 全价预结
		chrome.preFullPay(1);
		// 结账
		chrome.checkOut(1);
	}
	@AfterMethod (alwaysRun = true)
	public void captureAll() {
		//chrome.capture();
		desk++;
		dish2++;
		chrome.open(Shop.baseUrl);
		chrome.waitTime(3000);
	}
	
	@AfterClass
	public void tearDown() throws Exception {
		chrome.closeDriver();
	}

}
