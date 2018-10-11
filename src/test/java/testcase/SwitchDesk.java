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
 *  这是对【转台】的测试
 *  测试点： 
 *  
 * */

@Listeners({TestngRetryListener.class})
public class SwitchDesk {
	
	private OrderManage chrome;
	public static Logger log =  LogManager.getLogger(AddDish.class.getName());
	private int deskNum = 6;
	//private String[] table = {"1","云南腊肉炒香笋","38*1份","0","38","38","确认","","","管理员"};
	
	@BeforeTest
	public void setUp() throws Exception {
		
		log.info("《----------------------登录--------------------------》");
		chrome = new OrderManage(Shop.baseUrl);
		chrome.logIn(Shop.userName, Shop.passWord);
		chrome.selectDesks(deskNum,"0", 1);
	}
	
	@Test
	public void testList() throws Exception {
		log.info("《----------------------堂食取消分单：单品--------------------------》");
		for (int i = 0; i < 4; i++) {
			chrome.selectSingleDish(i);
		}
		chrome.submitDish(1);
		chrome.separateBill(0, 0);
	}
	@Test(enabled =false)
	public void testList2() throws Exception {
		log.info("《----------------------堂食分单并结账：单品------------------------》");
		chrome.separateBill(0, 1);
		chrome.preFullPay(1);
		chrome.checkOut(1);
		chrome.selectDesks(deskNum,"0", 0);
		chrome.preFullPay(1);
		chrome.checkOut(1);
		
	}
	
	@Test
	public void testSeparateBill() throws Exception {
		log.info("《----------------------堂食取消分单：套餐--------------------------》");
		
	}
	
	@Test
	public void testSeparateBill2() throws Exception {
		log.info("《----------------------堂食分单并结账：套餐-------------------------》");
		
	}
	
	@Test
	public void testSeparateBill3() throws Exception {
		log.info("《-------------------堂食取消分单：单品和套餐------------------------》");
		
	}
	
	@Test
	public void testSeparateBill4() throws Exception {
		log.info("《-------------------堂食分单并结账：单品和套餐-----------------------》");
		
	}
	
	@Test
	public void testSeparateBill5() throws Exception {
		log.info("《----------------------打包取消分单：单品--------------------------》");
		
	}
	
	@Test
	public void testSeparateBill6() throws Exception {
		log.info("《----------------------打包分单：单品--------------------------》");
		
	}
	
	@Test
	public void testSeparateBill7() throws Exception {
		log.info("《----------------------打包取消分单：套餐--------------------------》");
		
	}
	
	@Test
	public void testSeparateBill8() throws Exception {
		log.info("《----------------------打包分单：套餐--------------------------》");
		
	}
	
	@Test
	public void testSeparateBill9() throws Exception {
		log.info("《----------------------打包取消分单：单品和套餐--------------------------》");
		
	}
	
	@Test
	public void testSeparateBill10() throws Exception {
		log.info("《----------------------打包分单：单品和套餐--------------------------》");
		
	}
	@Test
	public void testSeparateBill11() throws Exception {
		log.info("《----------------------外卖取消分单：单品--------------------------》");
		
	}
	
	@Test
	public void testSeparateBill12() throws Exception {
		log.info("《----------------------外卖分单：单品--------------------------》");
		
	}
	
	@Test
	public void testSeparateBill13() throws Exception {
		log.info("《----------------------外卖取消分单：套餐--------------------------》");
		
	}
	
	@Test
	public void testSeparateBill14() throws Exception {
		log.info("《----------------------外卖分单：套餐--------------------------》");
		
	}
	
	@Test
	public void testSeparateBill15() throws Exception {
		log.info("《----------------------外卖取消分单：单品和套餐--------------------------》");
		
	}
	
	@Test
	public void testSeparateBill16() throws Exception {
		log.info("《----------------------外卖分单：单品和套餐--------------------------》");
		
	}
	
	
	
	
	
	
	
	@Test(enabled =false)
	public void testSwitchDesk() throws Exception {
		log.info("《----------------------转台测试--------------------------》");
		chrome.selectDesks(deskNum,"0", 0);
		chrome.selectSingleDish(3);
		chrome.submitDish(1);
		chrome.changeDesk(8, 0);
		chrome.changeDesk(8, 1);
		
	}
	
	@AfterClass
	public void tearDown() throws Exception {
		chrome.closeDriver();
	}

}
