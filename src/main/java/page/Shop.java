package page;

public class Shop {
	
	//<---------------------------------登录页面数据----------------------------------->

	
	public static String baseUrl = "https://passport.hualala.com/login?redirectURL=https%3A%2F%2Fvip.shop.hualala.com";
//	public static String baseUrl = "http://vip.shop.hualala.com/";
	public static String passUrl = "https://passport.hualala.com/";
	public static String groupID = "wxdyceshi";
	public static String userName = "maxsu";
	public static String passWord = "123456";
	public static String dynamicCode = "//img[@alt='验证码']";
	public static String loginBtn = "//div[@class='ctn dn wechat']/div/ul/li/label[.='帐号登录']";

	public static String authorisedPwd = "111";//授权密码
	
	
	//<----------------------------------页面Frame------------------------------------>
	
	public static String orderMagFrame = "//frame[@name='menu']";//功能菜单
	public static String deskStatusFrame = "//frame[@name='func']";//功能菜单
	
	
	//<---------------------------------登录页面元素------------------------------------>
	
	public static String EgroupID = "//input[@name='groupId']";
	public static String Eusername = "//input[@name='userId']";
	public static String Epassword = "//input[@name='password']";
	public static String Ecode = "//ul[@class='accountForm form']/li/input[@name='dynamicCode']";
	public static String Esubmit = "//a[@class='AccSubmit submit tc']";
	
	
	//功能菜单---》订单管理
	public static String nv_OrderManager_CurPeo = "//a[@href='/order/apps/order/currentorder.php']";//当前客人管理
	
	
	//<---------------------------------开台界面------------------------------------>
	
	public static String desktops = "//div[@class='x-desktop-cnt-info']/a/div";//桌台所在的列表集合
	public static String desktop_01 = "//div[2]/div[2]/div[2]/a[8]/div/div[1]";
	public static String desktop_03 = "/html/body/div[2]/div[2]/div[2]/a[9]/div";
	
	public static String desktop_People = "//input[@id='undefined']";//人数
	public static String desktop_Submit = "//button[.='提交']";
	public static String desktop_Cancel = "//div[@class='x-window-btns']/button[.='关闭(Esc)']";
	public static String desktop_DishStyle_Pack = "/html/body/div[5]/div[2]/div/div/form/div[5]/div/a[1]/a";//就餐方式--打包
	public static String desktop_DishStyle_Takeout = "/html/body/div[5]/div[2]/div/div/form/div[5]/div/a[3]/a";//就餐方式--外卖
	public static String desktop_DishStyle_quit="//*[@id='exitbtn']";//退出按钮
	
	
	
	//<-----------------------------------点菜界面--------------------------------------->
	
	public static String desktop_ChooseDish_TableLeft1 ="//div[@class='o-dish-base-box-list']";//桌台点菜的菜品集合
	public static String desktop_ChooseDish_TableLeft12 ="//div[@class='o-dish-base-box-list']//div[@class='o-list-dish-row']/table/tr/td/div";//单个菜品集合
	public static String desktop_ChooseDish_TableLeft2 ="//div[@class='o-setmeal-dish']/div";//套餐菜品集合
	
	//----单品菜品分类
	public static String chooseDish_Class_coldDish = "//span[.='凉菜']";
	public static String chooseDish_Class_Wine = "//span[.='工艺品']";
	public static String chooseDish_Class_Hot = "//span[.='热菜']";
	
	
	public static String chooseDish_RepeatDish_Submit = "//button[.='确定']";//重复菜品的提示
	public static String desktop_ChooseDish_CookWay1 = "//div[@class='x-form-checkboxgroup-panel']/a[1]/a";
	public static String desktop_ChooseDish_NoteText = "//div[@class='x-form-checkboxgroup-panel']/a";//备注信息单选
	public static String desktop_ChooseDish_SearchIpt = "//div[@class='o-dish-base-search']/div/input[@id='undefined']";//搜索
	
	
	//****菜品编辑界面
	public static String desktop_ChooseDish_CountIpt = "//div[@class='o-dish-info-tabpanel-cnt-base']/div/input[@id='undefined']";//菜品数量,同时也是编辑界面的（注意以后开发是不是会修改ID）
	public static String desktop_ChooseDish_Edit_Ipt = "//form/div/input[@class='x-form-text-input']";//编辑界面菜品数量输入框
	public static String desktop_ChooseDish_Edit_Submit = "//button[.='确定']";//编辑确定
	public static String desktop_ChooseDish_EditBtn = "//button[.='编辑']";//编辑
	
	
	public static String desktop_ChooseDish_Submit = "//button[.='下单(F12)']";//点菜下单
	public static String desktop_ChooseDish_SelectAll = "//button[.='全选(F2)']";//点全选
	public static String desktop_ChooseDish_ReplaceOrder = "//button[.='补单']";//补单
	
	public static String desktop_ChooseDish_AllNote = "//button[.='全单备注(F5)']";//全单备注(F5)
	public static String desktop_ChooseDish_Note = "//button[.='备注(F4)']";//备注
	public static String desktop_ChooseDish_AllNoteText = "//div[@class='o-order-all-memo']//tr[4]/td[2]";//全单备注文本	
	public static String desktop_ChooseDish_Note_Up1 = "//span[.='微辣']";//备注:微辣
	public static String desktop_ChooseDish_Note_Input = "//form/div[2]/input[@class='x-form-text-input']";//备注：手动输入
	public static String desktop_ChooseDish_Note_Submit = "//button[.='提交']";//提交
	public static String desktop_ChooseDish_Note_Cancel = "//button[.='关闭(Esc)']";
	
	public static String desktop_ChooseDish_Gift = "//button[.='修改赠菜(F9)']";//修改赠菜(F9)
	public static String desktop_ChooseDish_Wait = "//button[.='修改等叫(F8)']";//修改等叫(F8)
	public static String desktop_ChooseDish_Del = "//button[.='删除(Del)']";//删除(Del)

	public static String desktop_ChooseDish_Submit2 = "//button[.='确定']";//确定点菜下单
	public static String desktop_ChooseDish_Submit_Cancel = "//button[.='取消']";//确定点菜下单
	public static String desktop_ChooseDish_FullPrePay = "//button[.='全价预结(11)']";//全价预结
	//public static String desktop_ChooseDish_OrderPrePay ="//button[='订单预结(12)']";//订单预结-黏黏
	public static String desktop_Order_Right_PreCheck= "//button[.='订单预结(12)']";//订单预结
	
	public static String desktop_ChooseDish_AddDish = "//button[.='加菜(22)']";//加菜(22)
	public static String desktop_ChooseDish_AddDishSubmit = "//button[.='提交']";//提交
	
	public static String desktop_ChooseDish_Bill = "//button[.='订单结账(14)']";//订单结账
	public static String desktop_ChooseDish_Save = "//div[@class='o-dish-info-ctrl']/button";//保存(Enter)
	//public static String desktop_ChooseDish_Save = "//div[@class='o-dish-base']//button[.='保存(Enter)']";//保存(Enter)
	public static String desktop_ChooseDish_PackageSave = "(//button[.='保存(Enter)'])[last()]";//只有套餐时的保存
	
	public static String desktop_ChooseDish_Package = "//a[.='套餐']";//套餐选项
	public static String desktop_ChooseDish_Single = "//a[.='普通品项']";//套餐选项
	public static String desktop_ChooseDish_EditTable = "//div[@class='o-list-row o-list-row-odd']/table/tr/td";
	
	//----订单结账界面
	public static String desktop_ChooseDish_Bill_Close = "//button[.='关闭(Esc)']";//订单结账界面关闭
	
	
	
	//<--------------------------------------订单界面--------------------------------------->
	
	public static String desktop_Order_Table = "//div[@class='x-order-more-box']/div/div[2]/div";//订单详情表格
	public static String desktop_Order_TableDetail = "//div[@class='x-orderitem-row x-orderitem-row-back-odd']/table/tr/td";//订单详情表格
	public static String desktop_Order_TableDetailHasGift = "//div[@class='x-orderitem-row x-orderitem-row-back-odd x-orderitem-row-isgif']/table/tr/td";//订单详情表格
	public static String desktop_Order_Close = "//div[@class='x-destop-bar']//a[.='×']";//关闭按钮
	public static String desktop_Order_Right_Return = "//button[.='退菜(23)']";//退菜
	public static String desktop_Order_Right_AllReturn = "//button[.='全单退菜']";//全单退菜
	public static String desktop_Order_Right_Separate = "//button[.='分单(52)']";//分单
	public static String desktop_Order_Right_Merge = "//button[.='并单(53)']";//合并单
	public static String desktop_Order_Right_SwitchDesk = "//button[.='转台(55)']";//转台

	public static String desktop_Order_Right_Gift = "//button[.='赠菜(24)']";//赠菜
	public static String desktop_Order_Right_PrePay = "//button[.='订单预结(12)']"; //预结
	public static String desktop_Order_CombainCheckout = "//button[.='组合结账(15)']";
	public static String order_Shishou = "//span[@class='o-order-memo-font-red']"; //实收
	public static String order_TurntocombainCheckout_Ok = "//button[.='确定']"; //您使用了现金券，系统自动跳转到组合结账
	
			

	public static String desktop_Order_Right_SwitchDish = "//button[.='转单(51)']";//转单
	public static String desktop_Order_Right_PartCheck= "//button[.='组合结账(15)']";//组合结账
	//public static String desktop_

	//<--------------------------------------预结界面--------------------------------------->
	
	//折扣
	public static String desktop_Order_Right_PreCheck_common="//a[@class='x-form-radio-over']";//普通打折
	public static String desktop_Order_Right_PreCheck_common_78="//a[@name='discount1'][1]";//78%
	public static String desktop_Order_Right_PreCheck_common_80="//a[@name='discount1'][2]";//80%
	public static String desktop_Order_Right_PreCheck_common_88="//a[@name='discount1'][3]";//88%
	public static String desktop_Order_Right_PreCheck_common_90="//a[@name='discount1'][4]";//90%
	
	//额外折让
	public static String desktop_Order_Extra = "//*[@id='undefined']";

	
	//服务费无
	public static String desktop_Order_Fee = "/html/body/div[5]/div[2]/div/div/form/div[3]/div/a[1]/a";
	
	//----营销活动
    public static String desktop_Order_Marketing_Pre = "//*[@class='x-form-checkbox-over']";//营销活动
    public static String desktop_Order_Marketing_Pre_Sure ="//*[@class='x-window-btns']/button[1]";//提交
    public static String desktop_Order_Marketing_Pre_is = "//*[@class='x-messagebox-btn']/button[1]";//确定预结
	
	//----会员卡预结
    public static String desktop_Order_Weishenghuo_member = "/html/body/div[5]/div[2]/div/div/form/div[1]/div/a[4]/a";//会员卡预结
    public static String desktop_Order_Weishenghuo_card = "//div/div[1]/input[@id='undefined']";//会员卡
    

	//---转单页面
	public static String  desktop_Order_Right_SwitchDish_OK="//button[@class='x-form-button'][.='确定']";//选择列表
	public static String  desktop_Order_Right_SwitchDish_close="//a[@class='x-window-bar-btn']";//关闭转台
    
	//----退菜界面
	public static String desktop_Order_TableReturn = "//div[@class='x-window-ct']//table/tr/td/div/a/span";//退菜菜品所在的表格
	public static String returnDish_Count = "//div[@class='x-window-ct']//table/tr/td[2]/div/input";//退菜菜品数量所在的表格
	public static String desktop_Order_ReturnSubmit = "//div[@class='x-window-btns']/button[.='提交']";
	public static String desktop_Order_ReturnClose = "//div[@class='x-window-btns']//button[.='关闭(Esc)']";
	public static String desktop_Order_Retrun_ErrorSubmit = "//div[.='确定']";
	public static String desktop_Order_Retrun_OKSubmit = "//div[.='确定']";
	public static String desktop_Order_TableAllReturn = "//div[@class='x-form-combobox']//select";//全单退菜界面的下拉框
	
	//----赠菜界面
	public static String desktop_Order_Gift_Note = "//div[@class='x-form-text']/input[@name='memo']";//备注
	public static String giftDish_close = "//button[.='关闭(Esc)']";
	public static String giftDish_Submit = "//button[.='提交']"; 
	
	//----全价预结的支付界面
	
	public static String desktop_FullPrePay_PayStyle1 = "//div[@class='x-fieldset'][1]/fieldset/div/input";//普通支付方式列表
	public static String desktop_FullPrePay_Pay_Submit = "//button[.='提交']";
	
	//----订单预结界面
	public static String prePayTable = "//a[@name='prechecktype']/span"; // 预结方式表
	public static String prePay_DiscoutTable = "//form/div/div[@class='x-form-radiogroup']"; //折扣率表
	public static String prePay_ServiceMoney = "//a[@name='servicecharges']/span"; //服务费
	public static String prePay_PrintTable = "//a[@name='invoice']/span"; // 是否打印
	public static String prePay_OtherDiscount = "//input[@id='undefined'][@name='undefined']"; // 额外折让
	public static String prePay_DiscountReson = "//div[@class='x-form-combobox']/select"; //折让理由
	public static String prePay_secret = "//input[@name='password']"; // 授权密码
	public static String prePay_Cardcode = "//*[@id='undefined'][@name='cardcode']"; //会员卡
	public static String prePay_Commit = "//button[.='提交']"; //=
	
	//----结账界面
	public static String checkOut_VipTicketTable1 = "//div[@class='x-window-ct']/div/table[1]/tr/td/a/a"; //满减券表
	public static String checkOut_VipTicketTable2 = "//div[@class='x-window-ct']/div/table[2]/tr/td/div/a/a"; //代金券表
	public static String checkOut_VipTicket_Ok = "//button[@class='x-form-button'][.='确定']"; //
	public static String checkOut_VipTicket_Close = "//body/div[5]/div[3]/button[@class='x-form-button'][.='关闭(Esc)']"; //
	public static String checkOut_StyleTable = "//div[@class='x-form-radio-panel']/a/span"; //支付方式表
	public static String checkOut_VerificationCode = "//input[@name='sendm'][@type='password']"; //微生活验证码
	public static String checkOut_GetVerificationCode = "//button[.='获取验证码']"; //微生活验证码
	public static String checkOut_GiveSmallChange = "//input[@id='undefined'][@name='undefined']"; //抹零
	
	
	//----组合结账界面
	
	public static String combainCheckout_Cash = "//*[@id='-1-btnidpay']"; //现金输入框
	public static String combainCheckout_GetVerificationCode = "//button[.='获取验证码']";
	public static String combainCheckout_GetVerificationCode_Ok = "//button[.='确定']"; 
	public static String combainCheckout_VerificationCode = "//input[@name='sendm'][@type='password']"; //微生活验证码
	public static String combainCheckout_VipIntegral = "//*[@id='231-btnidpay']"; //会员积分支付金额
	public static String combainCheckout_ValueCard = "//*[@id='-11-btnidpay']"; //储值卡支付金额：
	public static String combainCheckout_WeiSHIntegral = "//*[@id='-25-btnidpay']"; //微生活积分支付金额
	public static String combainCheckout_ShengYuYinfu = "//div[@class='x-order-yingfu o-order-memo-font-red'][2]"; //剩余应付金额
	
	
	
	//----加菜界面
	public static String addDish_Submit = "//button[.='提交']";
	
	
	//----快速加菜界面
	public static String quickAddish = "//button[.='快速加菜(21)']";
	public static String quickAddish_DishIpt = "//*[@id='undefined']";//版纳野果酸汤鱼数量
	public static String quickAddish_Submit = "//button[.='提交']";
	public static String quickAddish_Close = "//button[.='关闭(Esc)']";
	
	
	//----单项实收界面
	public static String singleRealIncome = "//button[.='单项实收(27)']";
	public static String singleRealIncome_ipt = "//div[@class='x-window-ct']//table/tr/td[2]/div/input";// 菜品价格所在的集合
	public static String singleRealIncome_Submit = "//button[.='提交']";
	public static String singleRealIncome_Close = "//button[.='关闭(Esc)']";
	
	//<--------------------------------------组合结账--------------------------------------->
	public static String cash = "//input[@id='-1-btnidpay']";//现金
	public static String neika = "//input[@id='1-btnidpay']";//内卡
	public static String waika = "//input[@id='8-btnidpay']";//外卡
	public static String zhipiao = "//input[@id='11-btnidpay']";//支票
	public static String zhifubao = "//input[@id='25-btnidpay']";//支付宝（口袋收银）
	public static String jiaohang = "//input[@id='106-btnidpay']";//交行霸王券
	public static String zhifubao_yh = "//input[@id='127-btnidpay']";//支付宝优惠
	public static String jiaohang_dj = "//input[@id='107-btnidpay']";//交行100元代金券
	public static String dianping = "//input[@id='36-btnidpay']";//点评团购
	public static String dazhongdianping = "//input[@id='76-btnidpay']";//大众点评89元团购券
	public static String shanhui = "//input[@id='63-btnidpay']";//闪惠
	public static String shanhui_lq = "//input[@id='64-btnidpay']";//闪惠零钱
	public static String nuomi = "//input[@id='-1-btnidpay']";//糯米89团购券
	//会员
	public static String member_num ="//input[@id='undefined']";//会员卡号
	public static String member_integral ="//input[@id='231-btnidpay']";//会员积分
	public static String member_stored ="//input[@id='-11-btnidpay']";//会员储值
	public static String member_intrgral_wei ="//input[@id='-25-btnidpay']";//微生活积分
	public static String member_testGetCode = "//button[.='获取验证码']";//获取验证码
	public static String member_passwd = "//input[@type='password']";//验证码输入
	
	
	
	
	
	
}
