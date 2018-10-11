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
	public void logIn(String user, String pwd) {
		try {
			type(Shop.logIn_User, Shop.userName);
			type(Shop.logIn_Pwd, Shop.passWord);
			click(Shop.logIn_Submit);
			log.info("login successfully!");
		} catch (Exception e) {
			log.info("login failed: " + "/r/n" + e);
		}
	}

}
