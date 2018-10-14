package testcase;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Cookie;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import page.Login;
import page.OrderManage;
import page.Shop;
import utils.GetCookie;
import utils.ShopChrome;
import utils.TestngRetryListener;
import utils.doOCR;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 *  [组合结账]的测试
 * 
 * */
@Listeners({TestngRetryListener.class})
public class CombinationCheckout {
	
	private ShopChrome chrome;
	public static Logger log =  LogManager.getLogger(CombinationCheckout.class.getName());
	
	@BeforeTest
	public void setUp() throws Exception {

		Cookie ck = new Cookie("Cookie", GetCookie.loginAndGetCookie());
//		Cookie ck2 = new Cookie("access_token", "adb66cda-0e2d-498b-b1bc-7591ae4b4c8c");
		Cookie ck3 = new Cookie("Cookie", "access_token=4e89ad51-aeca-4719-adc5-d1539820b41a;");
		Cookie ck4 = new Cookie("Cookie", "dynamic_code_session=faaba619-a0aa-4adb-a982-9b06d59288bd");
		List<Cookie> cks = new ArrayList<>();
		cks.add(ck);
//		cks.add(ck4);

		chrome = new ShopChrome("http://pre.shop.hualala.com",cks);

	}
	
	@Test
	public void aCombinationCheckout() throws Exception {
        log.info("**********点菜=>下单=>订单预结=>现金和会员卡结账***********");
//        chrome.waitTime(1000);
        chrome.click(Shop.loginBtn);

//        chrome.waitForElementClick(Shop.loginBtn, 10);
        String srcUrl = chrome.getElement(Shop.dynamicCode).getAttribute("src");
        System.out.println(srcUrl);
        String code = doOCR.seeOCR(srcUrl);
        System.out.println("code is: " + code);
        new Login().logIn(Shop.groupID,Shop.userName,Shop.passWord,code);
    }
    @Test
	public void bTest() {
//		chrome.open("http://pre.shop.hualala.com");
		System.out.println("kwkwk");
	}

	@AfterClass
	public void tearDown() throws Exception {
//		chrome.waitTime(5000);
//		chrome.closeDriver();
	}



}
