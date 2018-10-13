package testcase;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import page.Login;
import page.OrderManage;
import page.Shop;
import utils.ShopChrome;
import utils.TestngRetryListener;
import utils.doOCR;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 *  [组合结账]的测试
 * 
 * */
@Listeners({TestngRetryListener.class})
public class CombinationCheckout {
	
	private ShopChrome chrome;
	public static Logger log =  LogManager.getLogger(CombinationCheckout.class.getName());
//	private String deskNum = "A2";
	private int desk = 3;
	
	
	@BeforeTest
	public void setUp() throws Exception {
		chrome = new ShopChrome(Shop.baseUrl);
//		new Login().logIn(Shop.userName, Shop.passWord);
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
	@AfterClass
	public void tearDown() throws Exception {
		chrome.waitTime(5000);
		chrome.closeDriver();
	}



}
