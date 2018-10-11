package testcase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import page.OrderManage;
import page.Shop;
import utils.TestngRetryListener;

import org.testng.annotations.BeforeTest;
import org.testng.AssertJUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Listeners;




/**
 * 这是对【加菜】的测试 
 *
 * */

@Listeners({TestngRetryListener.class})
public class AddDish {
	private OrderManage chrome;
	public static Logger log =  LogManager.getLogger(AddDish.class.getName());
	private int desk = 8;
	private String peoCount = "20";
	private int dish = 1;
	private String note = "我爱你";
//	private String[] table = {"1","云南腊肉炒香笋","38*1份","0","38","38","确认","","","管理员"};
	private String reason = "沽清";//全单退菜理由
	
	
	@BeforeTest
	public void setUp() throws Exception {
		
		log.info("《----------------------登录--------------------------》");
		chrome = new OrderManage(Shop.baseUrl);
		chrome.logIn(Shop.userName, Shop.passWord);
		log.info("------进入当前客人管理------");
		chrome.changeFrame(Shop.orderMagFrame);
		chrome.click(Shop.nv_OrderManager_CurPeo);
	}
	
	@Test 
	public void aAddDish() throws Exception {
		log.info("《----------------------点菜=>下单=>加单个菜=>备注=>下单结账--------------------------》");
		log.info("-----点菜下单并确认----");
		chrome.switchToDefaultFrame();
		chrome.changeFrame(Shop.deskStatusFrame);
		chrome.selectDesk(desk);
		chrome.click(Shop.desktop_Submit);
		chrome.doubleClickSingleDish(dish);
		log.info("-------确定下单-------");
		chrome.click(Shop.desktop_ChooseDish_Submit);
		chrome.click(Shop.desktop_ChooseDish_Submit2);
		//String windows1 = chrome.getWindowHandle();
		log.info("-----加菜下单备注并确认-----");
		chrome.click(Shop.desktop_ChooseDish_AddDish);
		chrome.click(Shop.desktop_ChooseDish_AddDishSubmit);
		chrome.doubleClickSingleDish(1);
		log.info("-----跳转到备注界面-----");
		chrome.click(Shop.desktop_ChooseDish_Note);
		chrome.type(Shop.desktop_ChooseDish_Note_Input, this.note);
		chrome.click(Shop.desktop_ChooseDish_Note_Submit);
		log.info("------验证备注信息是否正确------");
		chrome.waitTime(4000);//必须要有这个延时
		String tmp = chrome.getCellText(Shop.desktop_ChooseDish_EditTable, 5);
		log.info("Assert:"+tmp);
		AssertJUnit.assertEquals(tmp, note);
		chrome.click(Shop.desktop_ChooseDish_Submit);
		chrome.click(Shop.desktop_ChooseDish_Submit2);
		log.info("-----全价预结并确认-----");
	    chrome.waitTime(5000);
		chrome.click(Shop.desktop_ChooseDish_FullPrePay);
		chrome.click(Shop.desktop_ChooseDish_Submit2);
		log.info("-----验证金额是否正确----");
//		chrome.assertOrderInfo(this.table, Shop.desktop_Order_TableDetail);
		log.info("-----订单结账提交并确认-----");
		chrome.waitTime(4000);
		chrome.click(Shop.desktop_ChooseDish_Bill);
		chrome.click(Shop.desktop_Submit);
		chrome.click(Shop.desktop_ChooseDish_Submit2);
		
	}
	
	
	@Test (enabled = false)
	public void dtestReturnDish3() throws Exception {
		log.info("<----------------------------单个菜品退菜------------------------------>");
		log.info("------进入当前客人管理------");
		chrome.changeFrame(Shop.orderMagFrame);
		chrome.click(Shop.nv_OrderManager_CurPeo);
		log.info("-----点菜下单并确认----");
		chrome.switchToDefaultFrame();
		chrome.changeFrame(Shop.deskStatusFrame);
		chrome.selectDesk(desk);
		chrome.click(Shop.desktop_Submit);
		chrome.doubleClickSingleDish(1);	
		log.info("-------确定下单-------");
		chrome.click(Shop.desktop_ChooseDish_Submit);
		chrome.click(Shop.desktop_ChooseDish_Submit2);
		log.info("-------跳转到退菜--------");
		chrome.waitTime(5000);
		chrome.clickAndWait(Shop.desktop_Order_Right_Return);
		chrome.checkDish(0);
		chrome.click(Shop.desktop_Order_ReturnSubmit);
		chrome.click(Shop.desktop_Order_Retrun_OKSubmit);
	}
	@Test (enabled = false)
	public void etestReturnDish4() throws Exception {	
		log.info("《----------------------------全单退菜-----------------------------------》");
		log.info("------进入当前客人管理------");
		chrome.changeFrame(Shop.orderMagFrame);
		chrome.click(Shop.nv_OrderManager_CurPeo);
		log.info("-----点菜下单并确认----");
		chrome.switchToDefaultFrame();
		chrome.changeFrame(Shop.deskStatusFrame);
		chrome.selectDesk( desk);
		chrome.click(Shop.desktop_Submit);
		chrome.doubleClickSingleDish(1);
		chrome.doubleClickSingleDish(1);
		chrome.click(Shop.chooseDish_RepeatDish_Submit);
		log.info("-------确定下单-------");
		chrome.click(Shop.desktop_ChooseDish_Submit);
		chrome.click(Shop.desktop_ChooseDish_Submit2);
		log.info("-------跳转到退菜--------");
		chrome.waitTime(8000);
		chrome.clickAndWait(Shop.desktop_Order_Right_AllReturn);
		chrome.select(Shop.desktop_Order_TableAllReturn, this.reason);
		chrome.click(Shop.desktop_Order_ReturnSubmit);
		chrome.click(Shop.desktop_Order_Retrun_OKSubmit);
		
	}
	
	@Test 
	public void fAddDish() throws Exception {
		log.info("《----------------------点菜=>下单=>加套餐=>备注=>下单结账--------------------------》");
		// 开台
		chrome.selectDesks(desk, peoCount, 1);
		// 点菜
		chrome.selectPackage(0);
		// 下单
		chrome.submitDish(1);	
		// 加菜套餐
		chrome.addDish(dish, 2);
		// 备注
		chrome.noteDish(0, note, 1);
		// 下单
		chrome.submitDish(1);
		// 全价预结
		chrome.preFullPay(1);
		// 结账
		chrome.checkOut(1);
		
	}
	
	@Test 
	public void gAddDish() throws Exception {
		log.info("《----------------------点菜=>下单=>先加套餐,再加单品=>备注=>下单结账--------------------------》");
		// 开台
		chrome.selectDesks(desk, peoCount, 1);
		// 点菜
		chrome.selectPackage(0);
		// 下单
		chrome.submitDish(1);
		// 加菜套餐
		chrome.addDish(0, 2);
		// 加单品
		chrome.selectSingleDish(dish, "1");
		// 备注
		chrome.noteDish(0, note, 1);
		// 下单
		chrome.submitDish(1);
		// 全价预结
		chrome.preFullPay(1);
		// 结账
		chrome.checkOut(1);
	}
	
	@Test
	public void hAddDish() throws Exception {
		log.info("《----------------------点菜=>下单=>先加单品,再加套餐=>备注=>下单结账--------------------------》");
	
		// 开台
		chrome.selectDesks(desk, peoCount, 1);
		// 点菜
		chrome.selectPackage(0);
		// 下单
		chrome.submitDish(1);
		// 加菜单品
		chrome.addDish(dish, 1);
		// 加套餐
		chrome.selectPackage(0);
		// 备注
		chrome.noteDish(0, note, 1);
		// 下单
		chrome.submitDish(1);
		// 全价预结
		chrome.preFullPay(1);
		// 结账
		chrome.checkOut(1);
	}
	
	@Test (enabled = false)
	public void iAddDish() throws Exception {
		log.info("《----------------------点菜=>下单=>先加单品,再加套餐=>全单备注=>下单结账--------------------------》");
		// 开台
		chrome.selectDesks(desk, peoCount, 1);
		// 点菜
		chrome.selectPackage(0);
		// 下单
		chrome.submitDish(1);
		// 加菜单品
		chrome.addDish(0, 1);
		// 加套餐
		chrome.selectPackage(0);
		// 全单备注
		chrome.noteDish(0, note, 3);
		// 下单
		chrome.submitDish(1);
		// 全价预结
		chrome.preFullPay(1);
		// 结账
		chrome.checkOut(1);
	}
	
	@Test (enabled = false)
	public void jAddDish() throws Exception {
		log.info("《----------------------点菜=>下单=>先加单品,再加套餐=>删除一个菜品=>下单结账--------------------------》");
		// 开台
		chrome.selectDesks(desk, peoCount, 1);
		// 点菜
		chrome.selectPackage(0);
		// 下单
		chrome.submitDish(1);
		// 加菜单品
		chrome.addDish(0, 1);
		// 加套餐
		chrome.selectPackage(0);
		// 删除菜品
		chrome.click(Shop.desktop_ChooseDish_Del);
		// 下单
		chrome.submitDish(1);
		// 全价预结
		chrome.preFullPay(1);
		// 结账
		chrome.checkOut(1);
		
	}
	
	@Test (enabled = false)
	public void kAddDish() throws Exception {
		log.info("《----------------------点菜=>下单=>先加单品,再加套餐=>删除一个菜品,再赠菜=>下单结账--------------------------》");
		// 开台
		chrome.selectDesks(desk, peoCount, 1);
		// 点菜
		chrome.selectPackage(0);
		// 下单
		chrome.submitDish(1);
		// 加菜单品
		chrome.addDish(0, 1);
		// 加套餐
		chrome.selectPackage(0);
		// 删除菜品
		chrome.click(Shop.desktop_ChooseDish_Del);
		// 全选
		chrome.click(Shop.desktop_ChooseDish_SelectAll);
		// 赠菜
		chrome.click(Shop.desktop_ChooseDish_Gift);
		// 下单
		chrome.submitDish(1);
		// 全价预结
		chrome.preFullPay(1);
		// 结账
		chrome.checkOut(1);
	}
	
	@Test (enabled = false)
	public void lAddDish() throws Exception {
		log.info("《----------------------点菜=>下单=>加多个菜=>全单备注=>下单结账--------------------------》");
		// 开台
		chrome.selectDesks(desk, peoCount, 1);
		// 点菜
		chrome.selectPackage(0);
		// 下单
		chrome.submitDish(1);
		// 加菜单品
		chrome.addDish(0, 1);
		// 加单品
		chrome.selectSingleDish(2);
		chrome.selectSingleDish(3);
		// 全单备注
		chrome.noteDish(0, note, 3);
		// 下单
		chrome.submitDish(1);
		// 全价预结
		chrome.preFullPay(1);
		// 结账
		chrome.checkOut(1);
		
	}
	
	@Test (enabled = false)
	public void mAddDish() throws Exception {
		log.info("《----------------------点菜=>下单=>加菜=>修改菜品数量=>下单结账--------------------------》");
		// 开台
		chrome.selectDesks(desk, peoCount, 1);
		// 点菜
		chrome.selectPackage(0);
		// 下单
		chrome.submitDish(1);
		// 加菜单品
		chrome.addDish(dish, 1);
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
	public void nAddDish() throws Exception {
		log.info("《----------------------点菜=>下单=>加菜=>修改菜品数量=>下单结账--------------------------》");
		// 开台
		chrome.selectDesks(desk, peoCount, 1);
		// 点菜
		chrome.selectPackage(0);
		// 下单
		chrome.submitDish(1);
		// 加菜单品
		chrome.addDish(0, 1);
		// 点菜修改数量
		chrome.selectSingleDish(2, "6");
		// 下单
		chrome.submitDish(1);
		// 全价预结
		chrome.preFullPay(1);
		// 结账
		chrome.checkOut(1);
	}
	
	@Test (enabled = false)
	public void oAddDish() throws Exception {
		log.info("《----------------------点菜=>下单=>加菜=>全选删除=>下单结账--------------------------》");
		// 开台
		chrome.selectDesks(desk, peoCount, 1);
		// 点菜
		chrome.selectPackage(0);
		// 下单
		chrome.submitDish(1);
		// 加菜单品
		chrome.addDish(0, 1);
		// 全选
		chrome.click(Shop.desktop_ChooseDish_SelectAll);
		// 删除菜品
		chrome.click(Shop.desktop_ChooseDish_Del);
		// 空菜品下单
		chrome.submitDish(1);
		// 加单品
		chrome.selectSingleDish(2);
		// 下单
		chrome.submitDish(1);
		// 全价预结
		chrome.preFullPay(1);
		// 结账
		chrome.checkOut(1);
		
	}
	
	@Test (enabled = false)
	public void pAddDish() throws Exception {
		log.info("《----------------------点菜=>下单=>加菜=>退其中几个菜=>下单结账--------------------------》");
		// 开台
		chrome.selectDesks(desk, peoCount, 1);
		// 点菜
		chrome.selectPackage(0);
		// 下单
		chrome.submitDish(1);
		// 加菜单品
		chrome.addDish(0, 1);
		// 加单品
		chrome.selectSingleDish(2);
		chrome.selectSingleDish(4);
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
	public void qAddDish() throws Exception {
		log.info("《----------------------点菜=>下单=>加菜=>退其中几个菜=>赠菜=>下单结账--------------------------》");
		// 开台
		chrome.selectDesks(desk, peoCount, 1);
		// 点菜
		chrome.selectSingleDish(2);
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
		chrome.foodBack(index, reason, 1);
		// 赠菜
		int[] index2 = { 0 };
		chrome.giftDish(index2, 0, note, 1);
		// 全价预结
		chrome.preFullPay(1);
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
	public void tearDown() throws Exception {
		chrome.closeDriver();
	}

	
	

}

