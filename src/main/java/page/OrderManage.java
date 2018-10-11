package page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.beust.jcommander.internal.Nullable;

import utils.ShopChrome;

public class OrderManage extends ShopChrome {
	
	public static Logger log = LogManager.getLogger(OrderManage.class.getName());
	
	
	public OrderManage(String baseUrl) {
		super(baseUrl);
	}
	
	
	/**
	 *  获取页面上的金额，格式以 '：'分隔的
	 *  @param xpath
	 *  @return int
	 * 
	 * */
	public int getMoneyByInt(String xpath) {
		int money = 0;
		String[] str = getText(xpath).split("：");
		if (str!= null) {
			money = Integer.valueOf(str[1]);
		} else {
			log.error("Don't get the money!");
		}
		return money;
		
	}
	
	/**
	 *  获取页面上的金额，格式以 '：'分隔的
	 *  @param xpath
	 *  @return double
	 * 
	 * */
	public double getMoneyByDouble(String xpath) {
		double money = 0.01;
		String[] str = getText(xpath).split("：");
		if (str!= null) {
			money = Double.parseDouble(str[1]);
		} else {
			log.error("Don't get the money!");
		}
		return money;
		
	}
	
	
	/**
	 *  随机选择桌台并确认
	 *  
	 *  @param flag 0:直接选桌台； 1：先点当前客人管理
	 *  @param people 人数
	 * 
	 * */
	public void selectDesks(String people, int flag) {
		switch (flag) {
		case 0:
			waitTime(3000);
			log.info("******点菜下单并确认********");
			switchToDefaultFrame();
			changeFrame(Shop.deskStatusFrame);
			randomDesk(Shop.desktops);
			//selectDesk(Shop.desktops, desk);
			waitTime(2000);
			String[] tmp = {Shop.desktop_People};
			clearText(tmp);
			type(Shop.desktop_People, people);
			click(Shop.desktop_Submit);
			break;
		case 1:
			log.info("******进入当前客人管理******");
			changeFrame(Shop.orderMagFrame);
			click(Shop.nv_OrderManager_CurPeo);
			log.info("******点菜下单并确认********");
			switchToDefaultFrame();
			changeFrame(Shop.deskStatusFrame);
			randomDesk(Shop.desktops);
			//selectDesk(Shop.desktops, desk);
			waitTime(2000);
			type(Shop.desktop_People, people);
			click(Shop.desktop_Submit);
			break;

		default:
			log.error("The operation: "+flag+" is not find!");
			break;
		}
		
	}
	
	/**
	 *  选择指定桌台并确认
	 *  @param desk 桌台名称
	 *  @param flag 0:直接选桌台； 1：先点当前客人管理
	 *  @param people 人数
	 * 
	 * */
	public void selectDesks(String desk, String people, int flag) {
		switch (flag) {
		case 0:
			waitTime(3000);
			log.info("******点菜下单并确认********");
			switchToDefaultFrame();
			changeFrame(Shop.deskStatusFrame);
			
			selectDesk(desk);
			waitTime(2000);
			type(Shop.desktop_People, people);
			click(Shop.desktop_Submit);
			break;
		case 1:
			waitTime(3000);
			log.info("******进入当前客人管理******");
			changeFrame(Shop.orderMagFrame);
			click(Shop.nv_OrderManager_CurPeo);
			log.info("******点菜下单并确认********");
			switchToDefaultFrame();
			changeFrame(Shop.deskStatusFrame);
			selectDesk(desk);
			waitTime(2000);
			type(Shop.desktop_People, people);
			click(Shop.desktop_Submit);
			break;

		default:
			log.error("The operation: "+flag+" is not find!");
			break;
		}
		
	}
	
	/**
	 *  选择指定桌台并确认
	 *  @param deskIndex 桌台序号
	 *  @param flag 0:直接选桌台； 1：先点当前客人管理
	 *  @param people 人数
	 * 
	 * */
	public void selectDesks(int deskIndex, String people, int flag) {
		waitTime(3000);
		switch (flag) {
		case 0:
			break;
		case 1:
			waitTime(3000);
			log.info("******进入当前客人管理******");
			changeFrame(Shop.orderMagFrame);
			click(Shop.nv_OrderManager_CurPeo);
			waitTime(3500);
			break;
		default:
			log.error("The operation: "+flag+" is not find!");
			break;
		}
		log.info("******点菜下单并确认********");
		switchToDefaultFrame();
		changeFrame(Shop.deskStatusFrame);
		selectDesk(deskIndex);
		waitTime(2000);
		type(Shop.desktop_People, people);
		click(Shop.desktop_Submit);
		
	}
	/**
	 *  开台
	 * @param desk 桌台；
	 * @param peopleMount 人数
	 * @param eatStyle 就餐方式
	 * @param flag 是否先点击当前客人管理   0：是； 1：否
	 * 
	 * */
	public void selectDesk(String desk, String peopleMount, String eatStyle, int flag) {
		switch (flag) {
		case 0:
			waitTime(3000);
			log.info("******点菜下单并确认********");
			switchToDefaultFrame();
			changeFrame(Shop.deskStatusFrame);
			selectDesk(desk);
			type(Shop.desktop_People, peopleMount);
			check(eatStyle);
			click(Shop.desktop_Submit);
			break;
		case 1:
			log.info("******进入当前客人管理******");
			changeFrame(Shop.orderMagFrame);
			click(Shop.nv_OrderManager_CurPeo);
			log.info("******点菜下单并确认********");
			switchToDefaultFrame();
			changeFrame(Shop.deskStatusFrame);
			selectDesk(desk);
			type(Shop.desktop_People, peopleMount);
			check(eatStyle);
			click(Shop.desktop_Submit);
			break;

		default:
			log.error("The operation: "+flag+" is not find!");
			break;
		}
		
	}
	
	/**
	 *  选择单个菜品
	 *  @param dishIndex 单个菜品的序号从0开始
	 * @throws Exception 
	 * 
	 * */
	public void selectSingleDish(int dishIndex) {
		click(Shop.desktop_ChooseDish_Single);
		try {
			doubleClickSingleDish(dishIndex);
		} catch (Exception e) {
			log.error(e);
		}
	}
	
	/**
	 *  选择单个菜品
	 *  @param dishIndex 单个菜品的序号从0开始
	 *  @param count 菜品数量
	 * @throws Exception 
	 * 
	 * */
	public void selectSingleDish(int dishIndex, String count) {
		click(Shop.desktop_ChooseDish_Single);
		try {
			clickSingleDish(dishIndex, count);
		} catch (Exception e) {
			log.error(e);
		}
	}
	
	/**
	 *  选择套餐
	 *  @param pgeName 套餐的名称
	 * 
	 * */
	public void selectPackage(String pgeName) {
		log.info("********选择套餐**********");
		click(Shop.desktop_ChooseDish_Package);
		clickPackage(pgeName);
		waitTime(5000);
		click(Shop.desktop_ChooseDish_PackageSave);	
	}
	
	/**
	 *  选择套餐
	 *  @param pgeName 套餐的名称
	 * 
	 * */
	public void selectPackage(int pgeName) {
		log.info("********选择套餐**********");
		click(Shop.desktop_ChooseDish_Package);
		clickPackage(pgeName);
		waitTime(5000);
		click(Shop.desktop_ChooseDish_PackageSave);	
	}
	
	/**
	 *  菜品备注
	 *  @param index 备注界面的备注单选框，从0开始
	 *  @param freeNote 自由备注的文本
	 *  @param flag 0: 取消备注. 1: 确定备注 2:取消全单备注 3： 确定全单备注
	 *  
	 * */
	public void noteDish(int index,String freeNote,int flag) {
		switch (flag) {
		case 0:
			click(Shop.desktop_ChooseDish_Note);
			selectNote(index);
			type(Shop.desktop_ChooseDish_Note_Input, freeNote);
			click(Shop.desktop_ChooseDish_Note_Cancel);
			break;
		case 1:
			click(Shop.desktop_ChooseDish_Note);
			selectNote(index);
			type(Shop.desktop_ChooseDish_Note_Input, freeNote);
			click(Shop.desktop_ChooseDish_Note_Submit);
			break;
		case 2:
			click(Shop.desktop_ChooseDish_AllNote);
			selectNote(index);
			type(Shop.desktop_ChooseDish_Note_Input, freeNote);
			click(Shop.desktop_ChooseDish_Note_Cancel);
			break;
			
		case 3:
			click(Shop.desktop_ChooseDish_AllNote);
			selectNote(index);
			type(Shop.desktop_ChooseDish_Note_Input, freeNote);
			click(Shop.desktop_ChooseDish_Note_Submit);
			break;

		default:
			log.error("The operation: "+flag+" is not find!");
			break;
		}
	}
	
	/**
	 *  提交菜品
	 *  @param flag 0: 确定取消下单. 1: 确定下单
	 * */
	public void submitDish(int flag) {
		click(Shop.desktop_ChooseDish_Submit);
		switch (flag) {
		case 0:
			log.info("*******确定取消下单*******");
			
			click(Shop.desktop_ChooseDish_Submit_Cancel);
			break;
		case 1:
			log.info("*******确定下单*******");
			click(Shop.desktop_ChooseDish_Submit2);
			break;

		default:
			log.error("The operation: "+flag+" is not find!");
			break;
		}
		waitTime(2000);
		
	}
	
	/**
	 *  全价预结并确认
	 * 
	 * */
	public void preFullPay(int flag) {
		log.info("<--------------全价预结--------------->");
		waitTime(4000);
		click(Shop.desktop_ChooseDish_FullPrePay);
		switch (flag) {
		case 0:
			log.info("*****取消全价预结");	
			click(Shop.desktop_ChooseDish_Submit_Cancel);
			
			break;
		case 1:
			log.info("*****全价预结并确认");
			click(Shop.desktop_ChooseDish_Submit2);
			break;

		default:
			log.error("The operation: "+flag+" is not find!");
			break;
		}
	}
	
	/**
	 * 预结确认
	 * @param flag 1: 确定  0: 取消
	 * 
	 * */
	public void prePay_Ok(int flag) {
		if(flag == 1) {
			log.info("------预结并确认");
			click(Shop.prePay_Commit);
			click(Shop.desktop_ChooseDish_Submit2);
		} else {
			log.info("------取消预结");	
			click(Shop.desktop_ChooseDish_Submit_Cancel);
		}
	}
	
	/**
	 *  预结
	 *  @param  prePayIndex 预结方式序号
	 *  @param  vipcard 会员卡号
	 *  @param  discountRate 折扣率序号，0-3，可为空
	 *  @param  serviceMoney 服务费序号，0-4
	 *  @param  otherDiscount 额外折让
	 *  @param  reason 折让理由序号，0-10，具体看页面
	 *  @param  print 是否打印序号，0:是  1:否
	 *  @param  secret 授权密码
	 * */
	public void prePay(Integer prePayIndex,String vipcard,Integer discountRate, Integer serviceMoney, String otherDiscount, Integer reason, Integer print, String  secret) {
		log.info("<--------------预结--------------->");
		click(Shop.desktop_Order_Right_PrePay);
		clickByIndex(Shop.prePayTable, prePayIndex);
		type(Shop.prePay_Cardcode,vipcard);
		clickByIndex(Shop.prePay_DiscoutTable, discountRate);
		clickByIndex(Shop.prePay_ServiceMoney, serviceMoney);
//		type(Shop.prePay_OtherDiscount,otherDiscount);
		select(Shop.prePay_DiscountReson, reason);
		clickByIndex(Shop.prePay_PrintTable, print);
		type(Shop.prePay_secret, secret);
	}
	
	/**
	 *  订单结账提交并确认
	 *  @param flag 1: 确定  0: 取消
	 * 
	 * */
	public void checkOut(int flag) {
		waitTime(4000);//页面跳转的太慢，等待开发修改
		click(Shop.desktop_ChooseDish_Bill);
		
		switch (flag) {
		case 0:
			log.info("*****取消订单结账提交*****");
			click(Shop.desktop_ChooseDish_Submit_Cancel);
			click(Shop.desktop_ChooseDish_Bill_Close);
			break;
		case 1:
			log.info("*****订单结账提交并确认*****");
			click(Shop.desktop_Submit);
			click(Shop.desktop_ChooseDish_Submit2);
			break;
		default:
			log.error("The operation: "+flag+" is not find!");
			break;
		}
	}
	
	/**
	 *  订单结账
	 *  @param payStyle 支付方式 
	 *  @param flag 1: 确定  0: 取消
	 *  @param verificationCode 验证码
	 *  @param smallChange 抹零金额
	 * 
	 * */
	public void checkOut(String payStyle,int flag1,String verificationCode,String smallChange) {
		waitTime(4000);//页面跳转的太慢，等待开发修改
		click(Shop.desktop_ChooseDish_Bill);
		clickByText(Shop.checkOut_StyleTable, payStyle);
		if (flag1 == 1) {
			click(Shop.checkOut_GetVerificationCode);
		} 
		type(Shop.checkOut_VerificationCode,verificationCode);
		type(Shop.checkOut_GiveSmallChange, smallChange);	
	}
	
	/**
	 *  会员卡结账
	 *  @param vipTicket1 活动券序号，从0开始
	 *  @param vipTicket2 代金券序号
	 *  @param vipFlag  1: 确定会员券信息  0: 取消
	 * 
	 * */
	public void checkOut_Vip(Integer vipTicket1, Integer vipTicket2, int vipFlag) {
		waitTime(4000);//页面跳转的太慢，等待开发修改
		click(Shop.desktop_ChooseDish_Bill);
		// 活动与券信息填写
		clickByIndex(Shop.checkOut_VipTicketTable1, vipTicket1);
		clickByIndex(Shop.checkOut_VipTicketTable2, vipTicket2);
		if (vipFlag == 1) {
			click(Shop.checkOut_VipTicket_Ok);
			
		} else {
			click(Shop.checkOut_VipTicket_Close);
		}
	}
	
	/**
	 *  结账确认
	 *  @param payFlag 1: 订单结账 2：组合结账
	 *  @param flag 1: 确定结账 0: 取消
	 * 
	 * */
	public void checkOut_Ok(int payFlag,int flag) {
	
		if (flag == 1) {
			log.info("------订单结账提交并确认");
			click(Shop.desktop_Submit);
			if (payFlag == 1) {
				click(Shop.desktop_ChooseDish_Submit2);
			}
		} else {
			log.info("------取消订单结账提交");
			click(Shop.desktop_ChooseDish_Submit_Cancel);
			click(Shop.desktop_ChooseDish_Bill_Close);
		}
	}
	
	
	/**
	 *  退菜
	 *  @param index 退菜的菜品的序号集合，由上向下，从0开始
	 *  @param reason 退菜理由，必须和界面上一致
	 *  @param flag 0: 跳转到退菜并关闭; 1: 跳转到退菜.
	 * */
	public void foodBack(int[] index , String reason, int flag) {
		clickAndWait(Shop.desktop_Order_Right_Return);
		for (int i = 0; i < index.length; i++) {
			checkDish(index[i]);
		}
		select(Shop.desktop_Order_TableAllReturn, reason);
		switch (flag) {
		case 0:
			log.info("*******跳转到退菜并关闭********");
			click(Shop.desktop_Order_ReturnClose);
			break;
		case 1:
			log.info("*******跳转到退菜********");
			click(Shop.desktop_Order_ReturnSubmit);
//			click(Shop.desktop_Order_Retrun_OKSubmit);
			break;

		default:
			log.error("The operation: "+flag+" is not find!");
			break;
		}
	}
	
	/**
	 *  退菜
	 *  @param index 退菜的菜品的序号集合，由上向下，从0开始
	 *  @param reason 退菜理由序号，从0开始
	 *  @param flag 0: 跳转到退菜并关闭; 1: 跳转到退菜.
	 * */
	public void foodBack(int[] index , int reason, int flag) {
		clickAndWait(Shop.desktop_Order_Right_Return);
		for (int i = 0; i < index.length; i++) {
			checkDish(index[i]);
		}
		select(Shop.desktop_Order_TableAllReturn, reason);
		switch (flag) {
		case 0:
			log.info("*******跳转到退菜并关闭********");
			click(Shop.desktop_Order_ReturnClose);
			break;
		case 1:
			log.info("*******跳转到退菜********");
			click(Shop.desktop_Order_ReturnSubmit);
//			click(Shop.desktop_Order_Retrun_OKSubmit);
			break;

		default:
			log.error("The operation: "+flag+" is not find!");
			break;
		}
	}
	
	/**
	 *  退菜
	 *  @param index 退菜的菜品的序号集合，由上向下，从0开始
	 *  @param count 菜品数量
	 *  @param reason 退菜理由序号，从0开始
	 *  @param flag 0: 跳转到退菜并关闭; 1: 跳转到退菜.
	 * */
	public void foodBack(int[] index ,String[] count, int reason, int flag) {
		clickAndWait(Shop.desktop_Order_Right_Return);
		for (int i = 0; i < index.length; i++) {
			checkDish(index[i],count[i]);
		}
		select(Shop.desktop_Order_TableAllReturn, reason);
		switch (flag) {
		case 0:
			log.info("*******跳转到退菜并关闭********");
			click(Shop.desktop_Order_ReturnClose);
			break;
		case 1:
			log.info("*******跳转到退菜********");
			click(Shop.desktop_Order_ReturnSubmit);
			break;

		default:
			log.error("The operation: "+flag+" is not find!");
			break;
		}
	}
	
	/**
	 *  全单退菜
	 *  @param reason 退菜理由
	 *  @param flag 0: 跳转到退菜并关闭; 1: 跳转到全单退菜. 
	 * 
	 * */
	public void allFoodBack(String reason,int flag) {
		waitTime(5000);//页面跳转的太慢，等待开发修改
		clickAndWait(Shop.desktop_Order_Right_AllReturn);
		select(Shop.desktop_Order_TableAllReturn,reason);
		switch (flag) {
		case 0:
			log.info("*********跳转到全单退菜并关闭*********");
			click(Shop.desktop_Order_ReturnClose);
			break;
		case 1:
			log.info("*********跳转到全单退菜*********");
			click(Shop.desktop_Order_ReturnSubmit);
			click(Shop.desktop_Order_Retrun_OKSubmit);
			break;

		default:
			log.error("The operation: "+flag+" is not find!");
			break;
		}
	}
	
	/**
	 *  分单
	 *  @param index 分单的菜品从0开始
	 *  @param flag 0: 跳转到分单界面并关闭; 1: 跳转到分单并确定. 
	 * */
	public void separateBill(int index,int flag) {
		waitTime(5000);
		click(Shop.desktop_Order_Right_SwitchDesk);
		checkDish(index);
		switch (flag) {
		case 0:
			log.info("*********跳转到分单并关闭*********");
			click(Shop.desktop_Order_ReturnClose);
			break;
			
		case 1:
			log.info("*********跳转到分单*********");
			click(Shop.desktop_Order_ReturnSubmit);
			break;

		default:
			log.error("The operation: "+flag+" is not find!");
			break;
		}
		
	}
	
	/**
	 *  转台
	 *  @param index 它是转台界面选择桌台的序号从0开始
	 *  @param flag 0: 跳转到转台界面并关闭; 1: 跳转到转台并确定. 
	 * */
	public void changeDesk(int index,int flag) {
		click(Shop.desktop_Order_Right_SwitchDesk);
		select(Shop.desktop_Order_TableAllReturn,index);
		switch (flag) {
		case 0:
			log.info("*********跳转到转台并关闭*********");
			click(Shop.desktop_Order_ReturnClose);
			break;
			
		case 1:
			log.info("*********跳转到转台*********");
			click(Shop.desktop_Order_ReturnSubmit);
			break;

		default:
			log.error("The operation: "+flag+" is not find!");
			break;
		}
	}

	/**
	 *  加菜流程
	 *  @param index 菜品的序号，由上到下，从0 开始
	 *  @param flag 0: 加菜下单并取消; 1: 加菜单品; 2: 加菜套餐
	 * @throws Exception 
	 * */
	public void addDish(int index,int flag) {
		click(Shop.desktop_ChooseDish_AddDish);
		switch (flag) {
		case 0:
			log.info("-----加菜下单并取消-----");
			click(Shop.desktop_Cancel);
			break;
			
		case 1:
			log.info("-----加菜单品下单并确认-----");
			click(Shop.addDish_Submit);
			waitTime(1000);
			selectSingleDish(index);
			break;
		case 2:
			log.info("-----加菜套餐下单并确认-----");
			click(Shop.addDish_Submit);
			waitTime(1000);
			selectPackage(index);
			break;

		default:
			log.error("The operation: "+flag+" is not find!");
			break;
		}
	}
	
	/**
	 *  快速加菜
	 *  @param count 菜品数量
	 *  @param flag 0: 关闭快速加菜界面  1：提交
	 * 
	 * */
	public void quickAddDish(String count, int flag) {
		log.info("-----快速加菜-----");
		click(Shop.quickAddish);
		switch (flag) {
		case 0:
			log.info("-----取消快速加菜-----");
			click(Shop.quickAddish_Close);
			break;

		case 1:
			log.info("-----确定快速加菜-----");
			type(Shop.quickAddish_DishIpt,count);
			click(Shop.quickAddish_Submit);
			break;

		default:
			log.error("The operation: "+flag+" is not find!");
			break;
		}
	}
	
	/**
	 *  赠菜
	 *  @param index 赠菜界面菜品的序号集合，由上到下，从0 开始，可以多选
	 *  @param giftNum 赠菜理由，从0开始
	 *  @param note 备注信息
	 *  @param flag 0: 关闭赠菜界面  1：提交赠菜
	 * 
	 * */
	public void giftDish(int[] index,int giftNum,String note,int flag) {
		click(Shop.desktop_Order_Right_Gift);
		for (int i = 0; i < index.length; i++) {
			checkDish(index[i]);
		}
		switch (flag) {
		case 0:
			log.info("*******跳转到赠菜并关闭********");
			click(Shop.giftDish_close);
			
			break;
		case 1:
			log.info("*******跳转到赠菜并提交********");
			select(Shop.desktop_Order_TableAllReturn,giftNum);
			type(Shop.desktop_Order_Gift_Note,note);
			click(Shop.giftDish_Submit);
			break;

		default:
			log.error("The operation: "+flag+" is not find!");
			break;
		}
	}
	
	/**
	 * 预结后，选择支付方式界面
	 * 
	 * */
	
	public void doSelectPayStyle() {
		
		
	}
	
	
	/**
	 *  单项实收
	 *  @param index 菜品序号集合
	 *  @param money 菜品价格集合
	 *  @param flag 0: 关闭单项实收界面  1：不选择菜品提交 2:正常提交
	 * 
	 * */
	public void singleRealIncome(int[] index,String[] money,int flag) {
		click(Shop.singleRealIncome);	
		switch (flag) {
		case 0:
			log.info("*******跳转到单项实收并关闭********");
			click(Shop.singleRealIncome_Close);
			break;
		case 1:
			log.info("*******不选择菜品跳转到单项实收并提交********");
			for (int i = 0; i < index.length; i++) {
				changeMoney(index[i],money[i]);
			}
			click(Shop.singleRealIncome_Submit);
			break;
		case 2:
			log.info("*******跳转到单项实收并提交********");
			for (int i = 0; i < index.length; i++) {
				checkDish(index[i]);
				changeMoney(index[i],money[i]);
			}
			click(Shop.singleRealIncome_Submit);
			break;
		default:
			log.error("The operation: "+flag+" is not find!");
			break;
		}
	}
	
	/**
	 *  转单
	 *  @param index 它是转台界面选择桌台的序号从0开始（下拉第几项）
	 *  @param list 选择哪行菜品，单行，从上往下
	 *  @param flag 0: 跳转到转台界面并关闭; 1: 跳转到转台并确定. 
	 * */
	public void changeDish(int index,int list,int flag) {
		String dish="//div[@class='x-window-ct']/div/div"+"["+list+"]"+"/table/tr/td";
		log.info("dish:"+dish);
		click(Shop.desktop_Order_Right_SwitchDish);
		log.info("*********选择菜品********");
		click(dish);
		log.info("*********选择转单桌台********");
		select(Shop.desktop_Order_TableAllReturn,index);
		switch (flag) {
		case 0:
			log.info("*********跳转到转单并关闭*********");
			click(Shop.desktop_Order_ReturnClose);
			break;
			
		case 1:
			log.info("*********跳转到转单*********");
			click(Shop.desktop_Order_ReturnSubmit);
			break;

		default:
			log.error("The operation: "+flag+" is not find!");
			break;
		}
	}
	/**
	 * 订单预结普通打折
	 * @param list 折扣78%（1）80%（2）88%（3）90%（4）
	 * @param flag 确定（1）或取消（0）
	 * 
	 **/
	
	public void pre_settlement_Discount(int list, int flag) {
		log.info("-----订单预结-----");
		click(Shop.desktop_Order_Right_PreCheck);
		log.info("***选择普通折扣****");
		click(Shop.desktop_Order_Right_PreCheck_common);
		log.info("****选择打折方案****");
		waitTime(5000);
		switch (list) {
		case 1:
			log.info("-----78%-----");
			click(Shop.desktop_Order_Right_PreCheck_common_78);
			break;

		case 2:
			log.info("----80%-----");
			click(Shop.desktop_Order_Right_PreCheck_common_80);
			break;
		case 3:
			log.info("-----88%-----");
			click(Shop.desktop_Order_Right_PreCheck_common_88);
			break;
		case 4:
			log.info("----90%-----");
			click(Shop.desktop_Order_Right_PreCheck_common_90);
			break;

		default:
			log.error("The operation: "+flag+" is not find!");
			break;
		}
		
		switch (flag) {
		case 0:
			log.info("-----取消预结-----");
			click(Shop.desktop_Order_ReturnClose);
			break;

		case 1:
			log.info("-----确定预结-----");
			
			click(Shop.desktop_Order_ReturnSubmit);
			click(Shop.desktop_Order_Right_SwitchDish_OK);
			break;

		default:
			log.error("The operation: "+flag+" is not find!");
			break;
		}
	}
	
	/**
	 *  
	 * 
	 * */
	

}
