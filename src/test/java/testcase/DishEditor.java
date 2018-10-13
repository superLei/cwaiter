package testcase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import page.OrderManage;
import page.Shop;
import utils.TestngRetryListener;


/**
 *  这是对【菜品备注。赠菜，等叫,修改数量】功能的测试
 *  测试点： 菜品数量(最小值，随机值);单品备注，套餐备注，全单备注，取消备注,赠菜，等叫,烹饪方式
 * 
 * */

@Listeners({TestngRetryListener.class})
public class DishEditor {
	
	private OrderManage chrome;
	public static Logger log =  LogManager.getLogger(DishEditor.class.getName());
	private String deskNum = "6";
	//private String dish1 ="云南腊肉炒香笋";
	//private String dish2 = "店面牛肝菌4人套餐";
	private String count = "88";
	private String count2 = "100";
	//private String count3 = "0";//作为异常数据
   // private String money = "3800";//菜品总金额
	private String note = "喜欢你";
	//private String result = "微辣、我爱你";
	//private String[] table = {"1","云南腊肉炒香笋","38*1份","0","38","38","确认","","","管理员"};
	//private String[] table2 = {"1","店面牛肝菌4人套餐","202*1份","0","202","202","确认","","","管理员"};
	//private String[] tableEditInfo = {"云南腊肉炒香笋","1","38/份","8","0","我爱你","赠菜","等叫","编辑"};
	//private String[] tableGift = {"1","云南腊肉炒香笋","38*1份","0","38","0","确认","管理员","","管理员","","8","普通"};
	@BeforeTest
	public void setUp() throws Exception {
		chrome = new OrderManage(Shop.baseUrl);
//		chrome.logIn(Shop.userName, Shop.passWord);
		chrome.selectDesks(deskNum,"0", 1);
	}
	
	@Test
	public void atestDishCount() throws Exception {
		log.info("《-----------------------------修改菜品数量下单--------------------------------》");
	
		chrome.clickDish(2);
		chrome.getElement(Shop.desktop_ChooseDish_CountIpt).clear();
		chrome.type(Shop.desktop_ChooseDish_CountIpt, this.count);
		chrome.click(Shop.desktop_ChooseDish_Save);
		// ····跳转到了编辑界面
		chrome.click(Shop.desktop_ChooseDish_EditBtn);
		chrome.getElement(Shop.desktop_ChooseDish_Edit_Ipt).clear();
		chrome.type(Shop.desktop_ChooseDish_Edit_Ipt, this.count2);
		chrome.click(Shop.desktop_ChooseDish_Edit_Submit);
		// ····下单确认
		chrome.click(Shop.desktop_ChooseDish_Submit);
		chrome.click(Shop.desktop_ChooseDish_Submit2);
		//验证金额是否正确
		///String tmp = chrome.getCellText(Shop.desktop_Order_TableDetail, 5);
		//if(!tmp.equals(money)) {
			//log.error("-------The Actual money is "+tmp+"-------");
			//Assert.fail();
		//}
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
	
	//<------------------------------单品备注------------------------------------>
	@Test
	public void btestNote() throws Exception {
		log.info("《-----------------------------单品备注--------------------------------》");
		chrome.selectDesks(deskNum,"0", 0);
		// 单品备注
		chrome.doubleClickSingleDish(2);
		
		chrome.click(Shop.desktop_ChooseDish_Note);
		//····跳转到备注界面
		//chrome.click(Shop.desktop_ChooseDish_Note_Up1);
		chrome.type(Shop.desktop_ChooseDish_Note_Input, this.note);
		chrome.click(Shop.desktop_ChooseDish_Note_Submit);
		//String tmp = chrome.getCellText(Shop.desktop_ChooseDish_EditTable, 5);
		//ShopChrome.log.info("Assert:"+tmp);
		//assert result.equals(tmp);
		// ····下单确认
		chrome.click(Shop.desktop_ChooseDish_Submit);
		chrome.click(Shop.desktop_ChooseDish_Submit2);
		//验证金额是否正确
		//chrome.assertOrderInfo(this.table, Shop.desktop_Order_TableDetail);
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
	//<------------------------------套餐备注------------------------------------>
	@Test
	public void ctestPackageNote() throws Exception{
		log.info("《-----------------------------套餐备注--------------------------------》");
		chrome.selectDesks(deskNum,"0", 0);
		chrome.click(Shop.desktop_ChooseDish_Package);
		chrome.clickPackage(4);
		chrome.click(Shop.desktop_ChooseDish_Save);
		chrome.click(Shop.desktop_ChooseDish_Note);
		// ····跳转到备注界面
		//chrome.click(Shop.desktop_ChooseDish_Note_Up1);
		chrome.type(Shop.desktop_ChooseDish_Note_Input, this.note);
		chrome.click(Shop.desktop_ChooseDish_Note_Submit);
		//String tmp = chrome.getCellText(Shop.desktop_ChooseDish_EditTable, 5);
		//ShopChrome.log.info("Assert Text:" + tmp);
		//assert result.equals(tmp);
		// ····下单确认
		chrome.click(Shop.desktop_ChooseDish_Submit);
		chrome.click(Shop.desktop_ChooseDish_Submit2);
		// 验证金额是否正确
		//chrome.assertOrderInfo(this.table2, Shop.desktop_Order_TableDetail);
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
	//<------------------------------全单备注------------------------------------>
	@Test
	public void dtestFullNote() throws Exception {
		log.info("《-----------------------------全单备注--------------------------------》");
		chrome.selectDesks(deskNum,"0", 0);
		// 单品备注
		// 点菜下单并确认
		chrome.clickDish(2);
		chrome.click(Shop.desktop_ChooseDish_Save);
		for (int i = 0; i < 5; i++) {
			chrome.doubleClickSingleDish(2);
			chrome.click(Shop.desktop_ChooseDish_Submit2);
		}
		chrome.click(Shop.desktop_ChooseDish_SelectAll);
		chrome.click(Shop.desktop_ChooseDish_AllNote);
		// ····跳转到备注界面
		//chrome.click(Shop.desktop_ChooseDish_Note_Up1);
		chrome.type(Shop.desktop_ChooseDish_Note_Input, this.note);
		chrome.click(Shop.desktop_ChooseDish_Note_Submit);
		//String tmp = chrome.getText(Shop.desktop_ChooseDish_AllNoteText);
		//ShopChrome.log.info("Assert Text:" + tmp);
		//assert result.equals(tmp);
		// ····下单确认
		chrome.click(Shop.desktop_ChooseDish_Submit);
		chrome.click(Shop.desktop_ChooseDish_Submit2);
		// 验证金额是否正确
		//chrome.assertOrderInfo(this.table, Shop.desktop_Order_TableDetail);
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
	//<------------------------------取消备注------------------------------------>
	@Test 
	public void etestCancelNote() throws Exception {
		log.info("《-----------------------------取消备注--------------------------------》");
		chrome.selectDesks(deskNum,"0", 0);
		// 单品备注,再取消
		chrome.clickDish(2);
		chrome.click(Shop.desktop_ChooseDish_Save);
		//chrome.click(Shop.desktop_ChooseDish_Note);
		// ····跳转到备注界面
		chrome.noteDish(0, this.note, 1);
		
		//String tmp = chrome.getCellText(Shop.desktop_ChooseDish_EditTable, 5);
		//ShopChrome.log.info("Assert Text:"+tmp);
		//验证备注信息是否正确
		//assert tmp.equals("");
		// ····下单确认
		chrome.click(Shop.desktop_ChooseDish_Submit);
		chrome.click(Shop.desktop_ChooseDish_Submit2);
		// 验证金额是否正确
		//chrome.assertOrderInfo(this.table, Shop.desktop_Order_TableDetail);
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
	
    @Test
	public void ftestEditInfo() throws Exception {
		log.info("《-----------------------------数量，备注，赠菜，等叫,烹饪方式--------------------------------》");
		chrome.selectDesks(deskNum,"0", 0);
		// 点菜并修改数量下单(同时在编辑界面修改数量)
		chrome.clickDish(2);
		//chrome.click(Shop.desktop_ChooseDish_CookWay1);
		chrome.click(Shop.desktop_ChooseDish_Save);
		this.three(Shop.desktop_ChooseDish_Wait);
		this.three(Shop.desktop_ChooseDish_Gift);
		chrome.click(Shop.desktop_ChooseDish_Note);
		// ····跳转到备注界面
		chrome.type(Shop.desktop_ChooseDish_Note_Input, this.note);
		chrome.click(Shop.desktop_ChooseDish_Note_Submit);
		this.three(Shop.desktop_ChooseDish_SelectAll);
		//chrome.assertOrderInfo(this.tableEditInfo, Shop.desktop_ChooseDish_EditTable);
		// ····下单确认
		chrome.click(Shop.desktop_ChooseDish_Submit);
		chrome.click(Shop.desktop_ChooseDish_Submit2);
		// 验证金额是否正确
		//chrome.waitTime(1500);
		//chrome.assertOrderInfo(this.tableGift, Shop.desktop_Order_TableDetailHasGift);
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
    @Test(enabled=false)
    public void testCookWay() throws Exception {
    	log.info("《-----------------------------烹饪方式--------------------------------》");
    }
	@AfterClass
	public void tearDown() throws Exception {
		chrome.closeDriver();
	}
	
	public void three(String str) throws Exception {
		for (int i = 0; i < 3; i++) {
			chrome.click(str);
			chrome.waitTime(1000);
		}
	}

}
