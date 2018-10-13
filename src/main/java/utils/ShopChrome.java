 package utils;

import java.io.File;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.beust.jcommander.internal.Nullable;

import page.Shop;




public class ShopChrome {
	

	public static ChromeDriver dr = null;
	public static Logger log =  LogManager.getLogger(ShopChrome.class.getName());
	private final String path = "D:/Chrome/Application/Chrome.exe";
	Field[] methods = Shop.class.getFields();
	
	public ShopChrome(String baseUrl) {
//		setProperty();
		browser();
		this.open(baseUrl);
	}
	public ShopChrome() {
		System.setProperty("webdriver.chrome.driver", "./libs/chromedriver.exe");
	}
	/**
	 * 设置谷歌浏览器的环境属性
	 */ 	
	public static void setProperty() {
		System.setProperty("webdriver.chrome.driver", "./libs/chromedriver.exe");
	}
	
	/**
	 *  打开指定的Url地址
	 *  @param url 网址
	 * */
	public void open(String url) {
		try {
			log.info("open url: "+url);
//			ChromeOptions options = new ChromeOptions();
//			options.setBinary(path);
//			dr = new ChromeDriver(options);
			dr.get(url);
		} catch (Exception e) {
			log.error("open error"+e);
			e.printStackTrace();
		}
	}
	
	/**
	 *  初始化浏览器
	 * 
	 * */
	public void browser() {
		System.setProperty("webdriver.chrome.driver", "./libs/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.setBinary(path);
		dr = new ChromeDriver(options);
		dr.manage().window().maximize();
		dr.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	/**
	 *  勾选checkBox
	 *  @param eleName Xpath 元素 
	 * 
	 * */
	public void check(String eleName) {
		log.info("**Check**"+eleName);
		WebElement ele = null;
		try {
			ele = dr.findElementByXPath(eleName);
		} catch (Exception e) {
			log.error("The element is not find!" +"\r\n" + e);
		}
		if (!ele.isSelected()) {
			ele.click();
		} else {
			log.error(eleName + "had been selected!");
            return;
		}
	}
	
	/**
	 *  关闭浏览器当前的tab
	 * 
	 * 
	 * */
	public void closeTab() {
		new Actions(dr).keyDown(Keys.CONTROL).sendKeys("w").perform();
	}

	
	/**
	 * 截图并保存到默认路径
	 */
	public void capture()  {
		File f = new File("d://logs");
		if (!f.exists()) {
			try {
				f.mkdirs();
			} catch (Exception e) {
				log.error("D盘不存在"+"\r\n"+e);
			}
		}
		f = dr.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(f, new File(
					"d:\\logs\\screenshot-" + new SimpleDateFormat("hh-mm-ss").format(new Date()) + ".png"));
		} catch (Exception e) {
			log.error("capture failed!!!!"+e);
		}
	}
	
	/**
	 * 切换到默认的Frame
	 */
	public void switchToDefaultFrame() {
		try {
			log.debug("切换到默认的Frame");
			dr.switchTo().defaultContent();
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
	}
	
	/**
	 * 设置页面等待的时间
	 */
	public void waitTime(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			log.error("Wait time:" +"\r\n"+ e);
			e.printStackTrace();
		}
	}

	/**
	 * 点击页面上的元素
	 * @param xpath元素
	 * */
	public void click(String elementName) { 
		if(elementName == null) {
			log.debug("elementName shouldn't be null!");
			return;
		}
		for (Field f : methods) {
			try {
				if(f.get(methods).equals(elementName)) {log.info("    " + f.getName());break;}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				log.error("不能找到元素对应的变量"+e);
			}	
		}
		waitTime(1500);
		try {
			dr.findElementByXPath(elementName).click();
		} catch (Exception e) {
			log.error(elementName + "not find!!" + "\r\n" + e);
			Assert.fail();
		}
	}
	
	public void click2(String elementName) {
		if(elementName == null) {
			log.error("elementName shouldn't be null!");
			return;
		}
		waitTime(800);
		try {
			new Actions(dr).moveToElement(dr.findElementByXPath(elementName)).click().perform();
		} catch (Exception e) {
			log.error(elementName + "not find!!" + "\r\n" + e);
			Assert.fail();
		}
	}
	
	/**
	 *  默认最大等待15s点击页面上的元素
	 * 
	 * */
	public void clickAndWait(String element) {
		if(element == null) {
			log.error("element shouldn't be null!");
			return;
		}
		if (waitForElementClick(element,15)) {
			try {
				dr.findElementByXPath(element).click();
			} catch (Exception e) {
				log.error(element + "not find!!" + "\r\n" + e);
				Assert.fail();
			}
		} else {
			log.error("The element is not visable or not load!");
			Assert.fail();
		}
	}
	
	/** 
	  * 判断元素在指定时间是否可以点击
	  * @param by 元素 
	  * @param seconds 指定秒数 
	  * @return 出现返回true 否则返回false 
	  */  
	 public boolean waitForElementClick(String element, int seconds) {  
	     try {  
	         new WebDriverWait(dr, seconds).until(ExpectedConditions.elementToBeClickable(By.xpath(element)));  
	         //new WebDriverWait(dr, seconds).until(ExpectedConditions.elementToBeClickable(By.xpath(element)));
	         return true;  
	     } catch (Exception e) { 
	    	 log.error("we cannot locat the element in specified seconds! " + "\r\n" +e);
	         return false;  
	     }  
	 }  
	
	/**
	 * 输入数据到页面上的元素
	 * @param elementName xpath元素
	 * @param text 要输入的内容
	 * */
	public void type(String elementName,String text) {
		if(text == null ||elementName ==null ) {
			log.debug("element shouldn't be null!");
			return;
		}
		try {
			 for (Field f : methods) {
					if(f.get(methods).equals(elementName)) {log.info("    " + f.getName()+ "  =  " + text);break;}
				}
			 dr.findElementByXPath(elementName).clear();
			 dr.findElementByXPath(elementName).sendKeys(text);
		} catch (Exception e) {
			log.error("type:"+text+" "+e);
		}
	}
	
	/**
	 * 清除输入框里面的内容
	 * @param xpath元素
	 * */
	public void clearText(String[] elementName) {
		for (String str : elementName) {
			try {
				for (Field f : methods) {
					if(f.get(methods).equals(elementName)) {log.info("    " + f.getName());break;}
				}
				dr.findElementByXPath(str).clear();
			} catch (Exception e) {
				log.error("Error:-->Clear: "+e);
			}
		}
	}
	
	/**
	 * 在页面上打开一个新的tab
	 * @param url 网址
	 * */
	public void openTab(String url) {
		log.debug("Open a Tab with a Url:" + url );
		try {
			Actions action = new Actions(dr);
			action.sendKeys(Keys.CONTROL+"t").perform();
			dr.get(url);
			
		} catch (Exception e) {
			log.error("打开新的tab错误："+e);
			e.printStackTrace();
		}
	}
	
	/**
	 *  根据xpath元素改变页面Frame
	 *  @param xpath元素
	 * */
	public void changeFrame(String elementName) {
		try {
			for (Field f : methods) {
				if (f.get(methods).equals(elementName)) {log.info("    " + f.getName());break;}
			}
			dr.switchTo().frame(dr.findElementByXPath(elementName));
		} catch (Exception e) {
			log.error("Not find!!!  " +elementName+ "\r\n" + e);
			Assert.fail();
		}
	}
	
	/**
	 * 选取原来的句柄，回到原来页面 
	 * @param window 窗口ID
	 * */
	public void changeWindow(String window) {
		try {
			log.info("Change Window:"+window);
			this.waitTime(500);
			dr.switchTo().window(window);
		} catch (Exception e) {
			log.error("Change Window:"+e);
		}
	
	}
	
	/**
	 * 点击弹出框的确定按钮 
	 */
	public void alertSubmit() {
		log.info("Alert Submit");
		this.waitTime(2000);
		try {
			dr.switchTo().alert().accept();
		} catch (Exception e) {
			log.info("Alert not Find: "+e);
		}
	}
	
	/**
	 * 获取xpath的元素对象
	 * @param xpath元素
	 * */
	public WebElement getElement(String eleName) throws Exception {
		return dr.findElementByXPath(eleName);
	}
	
	/**
	 * 获取当前页面句柄
	 * */
	public String getWindowHandle() {
		return dr.getWindowHandle();
	}
	
	/**
	 * 切换到默认的Frame
	 */
	public void DefaultFrame() {
		try {
			log.debug("切换到默认的Frame");
			dr.switchTo().defaultContent();
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
	}
	/**
	 * 转到对应的页面
	 * */
	public void navigateToPage(String url) {
		log.debug("转到页面："+url);
		try {
			dr.navigate().to(url);
		} catch (Exception e) {
			log.error("转到页面失败："+e);
		}
	}
	
	/**
	 * 获取页面元素的文本
	 * 
	 */
	public String getText(String element) {
		String tmp = "";
		 try {
			tmp = dr.findElementByXPath(element).getText();
			log.debug("get element's text: "+tmp);
		} catch (Exception e) {
			log.error("failed to get element's text"+"\r\n"+e);
		}
		return tmp;
	}
	
	/**
	 * 点击页面上对应文本的下拉选择框
	 * @param eleName 页面上下拉框的xpath元素
	 * @param text 下拉框对应的文本
	 * 
	 * */
	
	public void select(String eleName,String text) {
		log.info("Select :" + text);
		try {
			Select s = new Select(dr.findElement(By.xpath(eleName)));
			s.selectByVisibleText(text);
		} catch (Exception e) {
			log.error("Select :" + text+"\r\n"+e);
		}
		
	}
	
	/**
	 * 点击页面上对应文本的下拉选择框
	 * @param eleName 页面上下拉框的xpath元素
	 * @param index 下拉框对应的文本的序号
	 * 
	 * */
	
	public void select(String eleName,Integer index) {
		if(eleName == null || index == null) {
			log.debug("element shouldn't be null!");
			return;
		}
		log.info("Select :" + index);
		try {
			Select s = new Select(dr.findElement(By.xpath(eleName)));
			s.selectByIndex(index);
		} catch (Exception e) {
			log.error("Select :" + index+"\r\n"+e);
		}
		
	}
	 /** 
	  * 判断元素在指定时间是否显示 
	  * 元素是否在指定时间内显示（存在dom结构且属性为显示）马上返回true 
	  * 如果到指定时间仍未显示（不存在与dom结构 或者存在于dom结构但属性为‘隐藏’）则返回false 
	  * 适用于ajax 
	  *  
	  * @param seconds 指定秒数 
	  * @return 出现返回true 否则返回false 
	  */  
	 public boolean waitForElementVisible(WebElement element, int seconds) {  
		try {
			// 判断该元素是否被加载在DOM中，并不代表该元素一定可见
//			new WebDriverWait(dr, seconds).until(ExpectedConditions
//					.presenceOfElementLocated(By.xpath(element)));
			// 判断元素(定位后)是否可见
//			new WebDriverWait(dr, seconds).until(ExpectedConditions
//					.visibilityOf(element));
			new WebDriverWait(dr, seconds).until(ExpectedConditions.elementToBeClickable(element));
			return true;
		} catch (Exception e) {
			log.error("we cannot locat the element in specified seconds! "
					+ "\r\n" + e);
			return false;
		}
	 } 
	
	/**
	 * 
	 *  关闭浏览器
	 */
	public void closeDriver() {
		log.info(" Closing Driver");
		try {
			dr.quit();
		} catch (Exception e) {
			log.error("Close:"+e);
		}
	}
	
	/**
	 *  获取指定行的元素对象
	 *  @param Xpath
	 *  @param 行序号
	 *  @return 元素对象
	 * */ 
	 public WebElement getEle(String trXpath,int rowIdx) {
		 WebElement row = null;
		try {
			//this.waitTime(1000);
			List<WebElement> rows = dr.findElements(By.xpath(trXpath));
			row = rows.get(rowIdx);
		} catch (Exception e) {
			log.error("Cannot get WebElment"+"\r\n"+e);
		}
		 return row;
	 }
	
	 

	 /**
	  * 点击指定文本的元素
	  * @param xPath
	  * @param 元素文本
	  * @return 执行次数
	  * */
	public void clickByText(String xPath, String eleText) {
		log.info("Page's WebElement: " + eleText);
		if(eleText == null) {
			log.error("xPath shouldn't be null!");
			return;
		}
		int count = this.getRowCount(xPath);
		try {
			for (int i = 0; i < count; i++) {
				WebElement wb = getEle(xPath, i);
				if (waitForElementVisible(wb,15) && wb.getText().equals(eleText)) {
					wb.click();
					break;
				}
			}
		} catch (Exception e) {
			log.error("\r\n" + e);
		}
		
	}
	
	/**
	 *  点击指定序号的元素
	 *  @param xPath
	 *  @param  index 元素序号
	 * 
	 * */
	public void clickByIndex(String xPath, Integer index) {
		log.info("WebElement: " + xPath);
		if(xPath == null || index == null) {
			log.debug("index is null!");
			return;
		}
		 int count = this.getRowCount(xPath);
		 if(index < 0 || index > count) {
			log.error("The index is error"); 
		 }
		 else {
			 try {
				 WebElement wb = getEle(xPath, index);
				 if (waitForElementVisible(wb,15)) {
					wb.click();
				}
			} catch (Exception e) {
				log.error("\r\n" + e);
			}
		 }
	}
	
	 /**
	  *  获取表的行数
	  *  @return 行数
	  * */
	 public int getRowCount(String rowXpath) {
		    List<WebElement> list = null;
			try {
				//waitTime(1000);
				list = dr.findElements(By.xpath(rowXpath));
				//log.info(rowXpath + " rows: " + list.size());
				return list.size();
			} catch (NoSuchElementException e) {
				log.error(rowXpath+"\r\n"+e);
				throw new NoSuchElementException("Fail to get rows!");
			}
		}
	
	/**
	 *  获取页面上指定行的元素
	 *  @param rowIdx 是元素的序号，从1开始计数 
	 * */
	 /*
	public WebElement getCellEle(String trXpath,int rowIdx) {
		 
		 WebElement td = null;
		try {
			List<WebElement> rows = dr.findElements(By.xpath(trXpath));
			WebElement row = rows.get(rowIdx);
			List<WebElement> col = row.findElements(By.tagName("div"));
			td = col.get(0);
			log.info(trXpath+"'s WebElement is "+td.toString());
		} catch (Exception e) {
			log.error(trXpath+" : "+rowIdx+e);
		}
		 return td;
	 }*/
	 
	 /**
	  * 选择单个菜品
	  * @param eleText 单个菜品的名称
	  * 
	  * */
	public void clickDish(String eleText) {
		log.info("Row at: " + eleText);
		try {
			this.waitTime(1000);
			int count = this.getRowCount(Shop.desktop_ChooseDish_TableLeft12);
			for (int i = 0; i < count; i++) {
				if (getCellEle2(Shop.desktop_ChooseDish_TableLeft12, i).getText().equals(eleText)) {
					getCellEle2(Shop.desktop_ChooseDish_TableLeft12, i).click();
					break;
				} else {
					log.error("Can't find:"+eleText);
				}
			}
		} catch (Exception e) {
			log.error("Click: "+eleText+": "+e);
		}
	}
	
	 /**
	  * 选择单个菜品
	  * @param textIndex 单个菜品的序号，从0 开始
	  * 
	  * 
	  * */
	public void clickDish(int textIndex) {
		log.info("Click: " + textIndex);
		try {
			this.waitTime(1000);
			int count = this.getRowCount(Shop.desktop_ChooseDish_TableLeft12);
				if (textIndex <= count || textIndex >= 0) {
					getCellEle2(Shop.desktop_ChooseDish_TableLeft12, textIndex).click();
				} else {
					log.error("Out of range:"+textIndex);
				}	
		} catch (Exception e) {
			log.error("Row at: "+textIndex+" is error: "+e);
		}
	}
	
	
	/**
	 * 桌台所在的xpath
	 * */
	public WebElement getCellEle3(String trXpath,int rowIdx) {
		 WebElement td2 = null;
		try {
			//this.waitTime(1000);
			List<WebElement> rows = dr.findElements(By.xpath(trXpath));
			WebElement row = rows.get(rowIdx);
			List<WebElement> col = row.findElements(By.tagName("div"));
			td2 = col.get(0);
			//WebElement row2= col.get(0);
			//List<WebElement> col2 = row2.findElements(By.tagName("div"));
			//td2 = col2.get(0);
			
		} catch (Exception e) {
			log.error("Unable to get the element: "+trXpath+"\r\n"+e);
		}
		 return td2;
	 }
	/**
	 *  根据桌台名称选择桌台
	 *  @param eleText 桌台名称
	 * */
	public boolean selectDesk(String eleText) {
		boolean isExsit = false;
		log.info("桌台："+eleText);
		int count = this.getRowCount(Shop.desktops);
		WebElement wb = null;  
		for (int i = 0; i < count; i++) {
			wb = this.getCellEle3(Shop.desktops, i);
			if (wb.getText().equals(eleText)) {
				wb.click();
				isExsit = true;
				break;
			}
		}
		return isExsit;
		
	}
	
	/**
	 *  根据桌台序号选择桌台
	 *  @param index 桌台序号
	 * */
	public boolean selectDesk(int index) {
		boolean isExsit = false;
		log.info("桌台序号："+index);
		int count = this.getRowCount(Shop.desktops);
		log.debug("The desk's count is "+count);
		if(index <0 || index > count) {
			log.error("Out of index for Dish");
		}
		else {
			WebElement wb = this.getCellEle3(Shop.desktops, index);
			wb.click();
			isExsit = true;
		}
		return isExsit;
		
	}
	

	
	/**
	 *  
	 * 
	 * */
	public String getCellText(String trXpath,int rowIdx) throws Exception {
		 //this.waitTime(500);
		 List<WebElement> rows = dr.findElements(By.xpath(trXpath));
		 WebElement row = rows.get(rowIdx);
		 List<WebElement> col = row.findElements(By.tagName("div"));
		 WebElement td = col.get(0);
		 HashMap<Integer, WebElement> hMap = new HashMap<Integer,WebElement>();
		 hMap.put(rowIdx, td); 
		 return hMap.get(rowIdx).getText();
	 }
	
	 
	 /**
	  *  获取门店套餐对应的页面元素
	  * */
	 public WebElement getCell(String trXpath,int rowIdx) {
		 WebElement td = null;
		try {
			//this.waitTime(1000);
			List<WebElement> rows = dr.findElements(By.xpath(trXpath));
			td = rows.get(rowIdx);
		} catch (Exception e) {
			log.error("Cannot find the element or your's is error"+"\r\n"+e);
		}
		 return td;
	 }
	
	/**
	 * 点击门店点菜界面中对应的套餐菜项
	 * @param eleText 套餐名称
	 * 
	 * */
	public String clickPackage(String eleText) {
		String text = "";
		log.info("Call:"+eleText);
		int count = this.getRowCount(Shop.desktop_ChooseDish_TableLeft2);
		for (int i = 0; i < count; i++) {
			WebElement wb = getCell(Shop.desktop_ChooseDish_TableLeft2, i);
			text = wb.getText();
			if (text.equals(eleText)) {
				wb.click();
				break;
			}
			else {
				log.error("Call:"+eleText+" error:");
			}
		}
		this.waitTime(1000);
		return text;
	}
	
	/**
	 * 点击门店点菜界面中对应的套餐菜项
	 * @param index 按序号来点套餐 从0开始
	 * 
	 * */
	public int clickPackage(int index) {
	    int runCount = 1;
		int count = this.getRowCount(Shop.desktop_ChooseDish_TableLeft2);
		if(index <0 || index > count) {
			log.error("Out of index for Dish");
		}
		else {
			WebElement wb = getCell(Shop.desktop_ChooseDish_TableLeft2, index);
			wb.click();
		}
		this.waitTime(1000);
		return runCount;
	}
	
	/**
	 *  获取菜品List中的单个菜品项
	 * 
	 * */
	 public WebElement getCellEle2(String trXpath,int rowIdx) {
		 WebElement td = null;
		try {
			this.waitTime(1000);
			List<WebElement> rows = dr.findElements(By.xpath(trXpath));
			//log.info("*********"+rows.size());
			td = rows.get(rowIdx);
		} catch (Exception e) {
			log.error("Get webElement error! "+"\r\n"+e);
		}
		 return td;
	 }
	 
	 /**
	  *  选择单个菜品
	  *  @param index  菜品的序号，由上到下，从0 开始
	  *  @param count 菜品数量
	  * 
	  * */
	 public void clickSingleDish(int index, String count) {
		 int dcount = this.getRowCount(Shop.desktop_ChooseDish_TableLeft12);
		 if(index <0 || index > dcount) {
				log.error("Out of index for Dish");
			}
			else {
				WebElement wb = getCell(Shop.desktop_ChooseDish_TableLeft12, index);
				wb.click();
				this.type(Shop.desktop_ChooseDish_CountIpt, count);
				this.click(Shop.desktop_ChooseDish_Save);
			}
	 }
	
	/**
	 * 双击门店点菜界面中对应名称的单品菜项
	 * 
	 * */
	public void doubleClickSingleDish(String eleText) {
		log.info("------- "+eleText);
		this.waitTime(1000);
		Actions action = new Actions(dr);
		int count = this.getRowCount(Shop.desktop_ChooseDish_TableLeft12);
		for (int i = 0; i < count; i++) {
			WebElement ele = getCellEle2(Shop.desktop_ChooseDish_TableLeft12, i);
			if (ele.getText().equals(eleText)) {
				action.doubleClick(ele).perform();
				break;
			}
			else {
				//log.error("没有找到这个菜品："+eleText);
			}
		}
		
	}
	
	/**
	 * 双击门店点菜界面中对应序号的单品菜项
	 * @param index 菜品的序号，由上到下，从0 开始
	 * @throws Exception 
	 * 
	 * */
	public void doubleClickSingleDish(int index) {
		//log.info("------- " + eleText);
		//this.waitTime(500);
		Actions action = new Actions(dr);
		int count = this.getRowCount(Shop.desktop_ChooseDish_TableLeft12);
		
		if (index < 0 ||index >= count) {
			log.error("Out of index!");
		}
		else {
			WebElement ele = getCellEle2(Shop.desktop_ChooseDish_TableLeft12, index);
			try {
				action.doubleClick(ele).perform();
			} catch (Exception e) {
				log.error("元素没有文本" + e);
			} 
		}
		
	}
	
	/**
	 * 退菜所在的xpath
	 * */
	public WebElement getReturnDish(String trXpath,int rowIdx) {
		 WebElement td = null;
		try {
			List<WebElement> rows = dr.findElements(By.xpath(trXpath));
			td = rows.get(rowIdx);
		} catch (Exception e) {
			log.error(e);
		}
		 return td;
	 }
	
	/**
	 *  在退菜界面，选择退菜品项
	 *  @param xPath 表格路径
	 *  @param ordinal 退菜的菜品的序号，由上向下，从0开始
	 * */
	public void checkDish(int ordinal) {
		log.info("Check the dish: "+ ordinal);
		int count = this.getRowCount(Shop.desktop_Order_TableReturn);
		for (int i = 0; i < count; i++) {
			if (i == ordinal) {
				getReturnDish(Shop.desktop_Order_TableReturn, i).click();
				break;
			}
			else {
			      log.error("选择退菜或分单菜品失败!!!");
			}
		}
	}
	
	/**
	 *  在退菜界面，选择退菜品项
	 *  @param xPath 表格路径
	 *  @param ordinal 退菜的菜品的序号，由上向下，从0开始
	 *  @param count 菜品数量
	 * */
	public void checkDish(int ordinal,String count) {
		log.info("Check the dish: "+ ordinal);
		int row = this.getRowCount(Shop.desktop_Order_TableReturn);
		for (int i = 0; i < row; i++) {
			if (i == ordinal) {
				getReturnDish(Shop.desktop_Order_TableReturn, i).click();
				getReturnDish(Shop.returnDish_Count, i).sendKeys(count);;
				break;
			}
			else {
			      log.error("选择退菜菜品或菜品数量失败!!!");
			}
		}
	}
	
	/**
	 *  选择退菜价格
	 *  @param index 菜品价格序号，从0 开始
	 *  @param money 菜品的价格
	 * */
	public void changeMoney(int index, String money) {
		log.info("The dish: "+ index + "change money to: "+ money);
		int count = this.getRowCount(Shop.singleRealIncome_ipt);
		for (int i = 0; i < count; i++) {
			WebElement wb = getReturnDish(Shop.singleRealIncome_ipt, i);
			if (i == index) {
				wb.clear();
				wb.sendKeys(money);
				break;
			}
			else {
			      log.error("选择菜品价格失败!!!");
			}
		}
	}
	
	//验证订单信息
	public void assertOrderInfo(String[] strs,String tableXpath) {
		this.waitTime(5000);
		// int rows = getRowCount(Shop.desktop_Order_TableDetail);
		int limit = strs.length;
		for (int i = 0; i < limit; i++) {
			try {
				String tmp = getCellText(tableXpath, i);
				log.debug(strs[i]+"=="+tmp);
				if (i == 10) {
					continue;
				}
			    assert strs[i].equals(tmp);
			    
			} catch (Exception e) {
				// TODO Auto-generated catch block
				log.error(e);
				e.printStackTrace();
			}
		}

	}
	
	/**
	 *  选择单个菜品类型
	 *  @param typeName 类型名称
	 * */
	public void clickDishType(String typeName) {
		log.info("*****"+typeName);
		String xpath = "//span[.='+"+typeName+"']";
		this.click(xpath);
		
	}
	
	/**
	 *  随机选择桌台
	 * 
	 * */
	public void randomDesk(String xPath) {
		int count = this.getRowCount(xPath);
		this.getCellEle3(xPath, new Random().nextInt(count)).click();
				
	}
	
	/**
	 * 备注所在页面的元素
	 * */
	public WebElement getNoteEle(int rowIdx) {
		WebElement td = null;
		 try {
			//this.waitTime(1000);
			List<WebElement> rows = dr.findElements(By.xpath(Shop.desktop_ChooseDish_NoteText));
			WebElement row = rows.get(rowIdx);
			List<WebElement> col = row.findElements(By.tagName("a"));
			td = col.get(0);
			
		} catch (Exception e) {
			log.error("Get error:"+"\r\n"+e);
		}
		return td;
	 }
	
	/**
	 * 备注所在页面的元素
	 * */
	public WebElement getNoteEle2(int rowIdx) {
		WebElement td = null;
		 try {
			//this.waitTime(1000);
			List<WebElement> rows = dr.findElements(By.xpath(Shop.desktop_ChooseDish_NoteText));
			WebElement row = rows.get(rowIdx);
			List<WebElement> col = row.findElements(By.tagName("span"));
			td = col.get(0);
			
		} catch (Exception e) {
			log.error("Get error:"+"\r\n"+e);
		}
		return td;
	 }
	
	
	/**
	 *  选择备注的checkbox
	 *  @param index 备注界面的备注单选框，从0开始
	 * 
	 * */
	public String selectNote(int index) {
	
		WebElement wb = getNoteEle(index);
		String text = getNoteEle2(index).getText();
		log.info("选择备注："+ text);
		try {
			wb.click();
		} catch (Exception e) {
			log.error(e);
		}
		return text;
	}
	
	/**
	 * 预结后，选择支付方式界面的元素
	 * */
	public WebElement getPayStyle1Ele(int rowIdx) {
		WebElement td = null;
		 try {
			List<WebElement> rows = dr.findElements(By.xpath(Shop.desktop_FullPrePay_PayStyle1));
			td = rows.get(rowIdx);
			
		} catch (Exception e) {
			log.error("Get error:"+"\r\n"+e);
		}
		return td;
	 }
	
	/**
	 * 预结后，选择支付方式界面
	 * 
	 * */
	public void typePayStyle(int index,String str) {
		WebElement wb = getPayStyle1Ele(index);
		log.info("选择支付方式的序号："+index);
		try {
			wb.clear();
			wb.sendKeys(str);
		} catch (Exception e) {
			log.error(e);
		}
	}
	
	/**
	 * 预结后，选择支付方式界面
	 * 
	 * */
	public void clickPayStyle(int index,String str) {
		WebElement wb = getPayStyle1Ele(index);
		log.info("选择支付方式的序号："+index);
		try {
			new Actions(dr).moveToElement(wb).sendKeys(str).perform();
			//wb.click();
		} catch (Exception e) {
			log.error(e);
		}
	}
	
	
	

	

	
	

}
