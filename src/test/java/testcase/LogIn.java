package testcase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import page.Shop;
import utils.ShopChrome;
import utils.TestngRetryListener;

/**
 * 这是对【登录】的测试
 * 
 * */
@Listeners({TestngRetryListener.class})
public class LogIn {
	
	private ShopChrome chrome;
	private String wrongPwd = "78*&^08";
	private String wrongUser = "8989';89";
	private String[] clearEle = {Shop.logIn_User,Shop.logIn_Pwd};
	@BeforeTest
	public void setUp() throws Exception {
		chrome = new ShopChrome(Shop.baseUrl);
	}
	
	@Test
	public void testLogIn() throws Exception {
		//异常登录-->用户名和密码都为空
		chrome.click(Shop.logIn_Submit);
		chrome.alertSubmit();
		//异常登录-->空密码
		chrome.type(Shop.logIn_User,Shop.userName);
		chrome.click(Shop.logIn_Submit);
		chrome.alertSubmit();
		chrome.clearText(clearEle);
		//异常登录-->空用户名
		chrome.type(Shop.logIn_Pwd,Shop.passWord);
		chrome.click(Shop.logIn_Submit);
		chrome.alertSubmit();
		chrome.clearText(clearEle);
		//异常登录-->错误用户名
		chrome.type(Shop.logIn_User,this.wrongUser);
		chrome.type(Shop.logIn_Pwd,Shop.passWord);
		chrome.click(Shop.logIn_Submit);
		chrome.alertSubmit();
		chrome.clearText(clearEle);
		// 异常登录-->错误密码
		chrome.type(Shop.logIn_User, Shop.userName);
		chrome.type(Shop.logIn_Pwd, this.wrongPwd);
		chrome.click(Shop.logIn_Submit);
		chrome.alertSubmit();
		chrome.clearText(clearEle);
		// 正常登录
		chrome.type(Shop.logIn_User,Shop.userName);
		chrome.type(Shop.logIn_Pwd,Shop.passWord);
		chrome.click(Shop.logIn_Submit);
	}
	
	@AfterClass
	public void tearDown() throws Exception {
		chrome.closeDriver();
	}
	
	
	

}
