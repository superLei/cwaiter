package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class TestngRetryListener extends TestListenerAdapter {
public static Logger logger =  LogManager.getLogger(TestngRetryListener.class.getName());
	
	public static final String CONFIG = "config.properties";

	@Override
	public void onTestFailure(ITestResult tr) {
		super.onTestFailure(tr);
		logger.info(tr.getName() + " Failure");
		takeScreenShot(tr);
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		super.onTestSkipped(tr);
		logger.info(tr.getName() + " Skipped");
              
	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		super.onTestSuccess(tr);
		logger.info(tr.getName() + " Success");
	}

	@Override
	public void onTestStart(ITestResult tr) {
		super.onTestStart(tr);
		logger.info(tr.getName() + " Start");
	}

	@Override
	public void onFinish(ITestContext testContext) {
		super.onFinish(testContext);

	}
	/**
	 * 自动截图，保存图片到本地文件中
	 * 
	 * @param tr
	 */
	private void takeScreenShot(ITestResult tr) {
		File f = new File("./captures");
		if (!f.exists()) {
			f.mkdirs();
		}
		f = ShopChrome.dr.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(f, new File("./captures/"+tr.getName()+"_"+new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date())+".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


}
