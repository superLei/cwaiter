package page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import utils.ShopChrome;

public class Login extends ShopChrome{

	public Login() {
		System.setProperty("webdriver.chrome.driver", "./libs/chromedriver.exe");
	}

	public static Logger log = LogManager.getLogger(Login.class.getName());

	// 实现登录功能
	public void logIn(String groupID, String userName, String passWord,String code) {
		try {
			type(Shop.EgroupID,groupID);
			type(Shop.Eusername, userName);
			type(Shop.Epassword, passWord);
			type(Shop.Ecode, code);
			click(Shop.Esubmit);
			log.info("login successfully!");
		} catch (Exception e) {
			log.info("login failed: " + "/r/n" + e);
		}
	}

}
