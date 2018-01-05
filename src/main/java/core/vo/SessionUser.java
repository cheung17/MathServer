package core.vo;

import java.io.Serializable;
import java.util.Map;

/**
 * session中的用户对象
 *
 * @author chenweju
 *
 * @since Apr 24, 2015
 */
public class SessionUser implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3679298153759703651L;
	/**
	 * 当前用户id
	 */
	private String id;
	/** 登录id */
	private String loginid;
	/** 登录密码 */
	private String loginpwd;
	/** 登录类型 */
	private Integer logintype;
	/** 姓名 */
	private String lastname;
	/** 安全级别 */
	private Integer seclevel;
	/** 最后登录时间 */
	private String lastlogindate;
	/** 最后登录IP */
	private String lastloginip;
	/** 最后登录的服务器ip */
	private String lastloginServerip;
	/** 是否短信提醒 0：否；1：是 */
	private Integer isMessage;
	/** 办公电话 */
	private String officephone;
	/** 个人手机 */
	private String mobile;
	/** 邮箱 */
	private String email;
	/** sessionid */
	private String sessionid;
	/** 是否手机登录 */
	private boolean mobilelogin;
	
	
	/** coolie中的记录多选项卡用户上次操作习惯 */
	private Map<String, String> multitabcom_cookie_map;

	public SessionUser() {
		super();
	}

	public SessionUser(String userId, String loginIp, String loginTime,
			String sessionId, boolean mobileLogin, String serverIp,
			String lastName,String officephone, Integer secLevel, String email) {
		super();
		this.id = userId;
		this.lastloginip = loginIp;
		this.lastlogindate = loginTime;
		this.sessionid = sessionId;
		this.mobilelogin = mobileLogin;
		this.lastloginServerip = serverIp;
		this.lastname = lastName;
		this.officephone = officephone;
		this.seclevel = secLevel;
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLoginid() {
		return loginid;
	}

	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}

	public String getLoginpwd() {
		return loginpwd;
	}

	public void setLoginpwd(String loginpwd) {
		this.loginpwd = loginpwd;
	}

	public Integer getLogintype() {
		return logintype;
	}

	public void setLogintype(Integer logintype) {
		this.logintype = logintype;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public Integer getSeclevel() {
		return seclevel;
	}

	public void setSeclevel(Integer seclevel) {
		this.seclevel = seclevel;
	}

	public String getLastlogindate() {
		return lastlogindate;
	}

	public void setLastlogindate(String lastlogindate) {
		this.lastlogindate = lastlogindate;
	}

	public String getLastloginip() {
		return lastloginip;
	}

	public void setLastloginip(String lastloginip) {
		this.lastloginip = lastloginip;
	}

	public String getLastloginServerip() {
		return lastloginServerip;
	}

	public void setLastloginServerip(String lastloginServerip) {
		this.lastloginServerip = lastloginServerip;
	}

	public Integer getIsMessage() {
		return isMessage;
	}

	public void setIsMessage(Integer isMessage) {
		this.isMessage = isMessage;
	}
	
	public String getOfficephone() {
		return officephone;
	}

	public void setOfficephone(String officephone) {
		this.officephone = officephone;
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

	public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}

	public void setMobilelogin(boolean mobilelogin) {
		this.mobilelogin = mobilelogin;
	}

	public Boolean getMobilelogin() {
		return mobilelogin;
	}

	public void setMobilelogin(Boolean mobilelogin) {
		this.mobilelogin = mobilelogin;
	}

	public Map<String, String> getMultitabcom_cookie_map() {
		return multitabcom_cookie_map;
	}

	public void setMultitabcom_cookie_map(
			Map<String, String> multitabcom_cookie_map) {
		this.multitabcom_cookie_map = multitabcom_cookie_map;
	}

}