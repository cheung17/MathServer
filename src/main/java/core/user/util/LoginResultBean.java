package core.user.util;

import java.io.Serializable;
import java.sql.Date;

public class LoginResultBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5710611826123563995L;
	
	public LoginResultBean(){
		
	}
	//用户公共字段
	/**
	 * 登录用户id 32 位UUID
	 */
	private String userId;
	/**
	 * 昵称
	 */
	private String nickName;
	/**
	 * 电话号码
	 */
	private String mobile;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 用户级别
	 */
	private String userLevel;
	/**
	 * 用户类型
	 */
	private int userType;
	/**
	 *最后一次登录日期
	 */
	private Date lastLoginTime;
	/**
	 * 记录创建日期
	 */
	private Date createTime;
	/**
	 * 登录密码
	 */
	private String loginPassword;
	/**
	 * 支付密码
	 */
	private String payPwd;
	/**
	 * 登录经度
	 */
	private String lnd;
	/**
	 * 登录纬度
	 */
	private String lat;
	/**
	 * 最大登录天数
	 */
	private int maxContinueNumber;
	/**
	 * 当前连续最大登录天数
	 */
	private int curContinueNumber;
	/**
	 * 注册类型
	 */
	private int registrationType;
	/**
	 * 会员等级
	 */
	private int vipLevel;
	/**
	 * 登录城市
	 */
	private String cityCode;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 *注册账号
	 */
	private String registerName;
	/**
	 * 用户状态
	 */
	private Integer userState;
	/**
	 * 
	 */
	private String channelId;
	
	//商家专属信息字段
	/**
	 * 商家名称
	 */
	private String companyName;
	/**
	 * 店铺名称
	 */
	private String shopName;
	/**
	 * 注册城市
	 */
	private String registerCityCode;
	/**
	 * 店铺描述
	 */
	private String shopDesc;
	/**
	 * 注册人
	 */
	private String register;
	/**
	 * 身份证号码
	 */
	private String idCard;
	/**
	 * 办公地点
	 */
	private String workPlace;
	/**
	 * 注册城市经度
	 */
	private String registerLnd;
	/**
	 * 注册城市纬度
	 */
	private String registerLat;
	/**
	 * 店铺网址
	 */
	private String shopUrl;
	/**
	 * 代理编号
	 */
	private String agentid;
	
	
	
	
	
	//父母专属字段
	
	/**
	 * 父母真实姓名
	 */
	private String parName;
	/**
	 * 性别
	 */
	private int sex;
	/**
	 * 民族
	 */
	private int folk;
	/**
	 * 生日
	 */
	private Date birtday;
	/**
	 * jobname
	 */
	private String jobName;
	/**
	 * 家庭住址
	 */
	private String address;
	/**
	 * 工作单位（公司）
	 */
	private String company;
	/**
	 * 最后一次更新日期
	 */
	private String lastUpdateTime;
	/**
	 * 政治面貌
	 */
	private int ploicy;
	/**
	 * 身份证号
	 */
	private String cardid;
	/**
	 * 大头像URL
	 */
	private String bigHeadUrl;
	/**
	 * 小头像URL
	 */
	private String smallHeadUrl;
	/**
	 * 工作单位 
	 */
	private String workUnit;
	/**
	 *  
	 */
	private String type4child;
	/**
	 * 
	 */
	private String personalId;
	
	

	public String getParName() {
		return parName;
	}
	public void setParName(String parName) {
		this.parName = parName;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getFolk() {
		return folk;
	}
	public void setFolk(int folk) {
		this.folk = folk;
	}
	public Date getBirtday() {
		return birtday;
	}
	public void setBirtday(Date birtday) {
		this.birtday = birtday;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public int getPloicy() {
		return ploicy;
	}
	public void setPloicy(int ploicy) {
		this.ploicy = ploicy;
	}
	public String getCardid() {
		return cardid;
	}
	public void setCardid(String cardid) {
		this.cardid = cardid;
	}
	public String getBigHeadUrl() {
		return bigHeadUrl;
	}
	public void setBigHeadUrl(String bigHeadUrl) {
		this.bigHeadUrl = bigHeadUrl;
	}
	public String getSmallHeadUrl() {
		return smallHeadUrl;
	}
	public void setSmallHeadUrl(String smallHeadUrl) {
		this.smallHeadUrl = smallHeadUrl;
	}
	public String getWorkUnit() {
		return workUnit;
	}
	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}
	public String getType4child() {
		return type4child;
	}
	public void setType4child(String type4child) {
		this.type4child = type4child;
	}
	public String getPersonalId() {
		return personalId;
	}
	public void setPersonalId(String personalId) {
		this.personalId = personalId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserLevel() {
		return userLevel;
	}
	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getLoginPassword() {
		return loginPassword;
	}
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
	public String getPayPwd() {
		return payPwd;
	}
	public void setPayPwd(String payPwd) {
		this.payPwd = payPwd;
	}
	public String getLnd() {
		return lnd;
	}
	public void setLnd(String lnd) {
		this.lnd = lnd;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public int getMaxContinueNumber() {
		return maxContinueNumber;
	}
	public void setMaxContinueNumber(int maxContinueNumber) {
		this.maxContinueNumber = maxContinueNumber;
	}
	public int getCurContinueNumber() {
		return curContinueNumber;
	}
	public void setCurContinueNumber(int curContinueNumber) {
		this.curContinueNumber = curContinueNumber;
	}
	public int getRegistrationType() {
		return registrationType;
	}
	public void setRegistrationType(int registrationType) {
		this.registrationType = registrationType;
	}
	public int getVipLevel() {
		return vipLevel;
	}
	public void setVipLevel(int vipLevel) {
		this.vipLevel = vipLevel;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRegisterName() {
		return registerName;
	}
	public void setRegisterName(String registerName) {
		this.registerName = registerName;
	}
	public Integer getUserState() {
		return userState;
	}
	public void setUserState(Integer userState) {
		this.userState = userState;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getRegisterCityCode() {
		return registerCityCode;
	}
	public void setRegisterCityCode(String registerCityCode) {
		this.registerCityCode = registerCityCode;
	}
	public String getShopDesc() {
		return shopDesc;
	}
	public void setShopDesc(String shopDesc) {
		this.shopDesc = shopDesc;
	}
	public String getRegister() {
		return register;
	}
	public void setRegister(String register) {
		this.register = register;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getWorkPlace() {
		return workPlace;
	}
	public void setWorkPlace(String workPlace) {
		this.workPlace = workPlace;
	}
	public String getRegisterLnd() {
		return registerLnd;
	}
	public void setRegisterLnd(String registerLnd) {
		this.registerLnd = registerLnd;
	}
	public String getRegisterLat() {
		return registerLat;
	}
	public void setRegisterLat(String registerLat) {
		this.registerLat = registerLat;
	}
	public String getShopUrl() {
		return shopUrl;
	}
	public void setShopUrl(String shopUrl) {
		this.shopUrl = shopUrl;
	}
	public String getAgentid() {
		return agentid;
	}
	public void setAgentid(String agentid) {
		this.agentid = agentid;
	}
	
}
