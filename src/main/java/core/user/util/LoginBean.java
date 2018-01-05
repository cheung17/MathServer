package core.user.util;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 
 * @author libing
 * @description 商家登录bean
 *
 */
public class LoginBean implements Serializable {
	 
	private static final long serialVersionUID = 9020511228954911718L;
	/**
	 * 默认构造器
	 */
	public LoginBean(){
		
	}
	/**
	 * userId
	 */
	private String userId;
	/**
	 * 登录账号
	 */
	@JsonProperty("account")
	private String account;
	
	/**
	 * 登录密码
	 */
	@JsonProperty("loginPassword")
	private String loginPassword;
	
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 手机号码
	 */
	private String mobile;
	
	/**
	 * 用户类型 
	 */
	@JsonProperty("userType")
	private int userType = -1;
	
	/**
	 * 用户状态
	 */
	@JsonProperty("userState")
	private String userState ;
	
	private String channelId;
	/**
	 * 
	 */
	private String randomUserLoginId;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public String getUserState() {
		return userState;
	}

	public void setUserState(String userState) {
		this.userState = userState;
	}

	public String getRandomUserLoginId() {
		return randomUserLoginId;
	}

	public void setRandomUserLoginId(String randomUserLoginId) {
		this.randomUserLoginId = randomUserLoginId;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	
	 
}
