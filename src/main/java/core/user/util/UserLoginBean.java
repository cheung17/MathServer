/**
 * 
 */
package core.user.util;

import java.io.Serializable;
/**
 * @author libing
 * @descripton
 * @date 2015年8月17日 下午5:31:50
 */
public class UserLoginBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4374189455347297L;
	
	
	/**
	 * id 主键
	 */
	private String id;
	/**
	 * account 登录账号
	 */
	private String account;
	/**
	 * password:登录密码
	 */
	private String loginpassword;
	/**
	 * checkcode  验证码
	 */
	private String checkcode;
	/**
	 * checkimageid 标识该验证码图片的id
	 */
	private String checkimagecode;
	/**
	 * model 终端设备型号
	 */
	private String model;
	/**
	 * brand  终端设备品牌
	 */
	private String brand;
	/**
	 * stationid 基站id
	 */
	private String stationid;
	/**
	 * lnd经度值
	 */
	private String lnd;
	/**
	 * lat 纬度值
	 */
	private String lat;
	/**
	 * machineid 终端设备号
	 */
	private String machineid;
	/**
	 * usertype 用户类型
	 */
	private int usertype;
	/**
	 * 用户状态
	 */
	private int userstate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getLoginpassword() {
		return loginpassword;
	}
	public void setLoginpassword(String loginpassword) {
		this.loginpassword = loginpassword;
	}
	public String getCheckcode() {
		return checkcode;
	}
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	public String getCheckimagecode() {
		return checkimagecode;
	}
	public void setCheckimagecode(String checkimagecode) {
		this.checkimagecode = checkimagecode;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getStationid() {
		return stationid;
	}
	public void setStationid(String stationid) {
		this.stationid = stationid;
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
	public String getMachineid() {
		return machineid;
	}
	public void setMachineid(String machineid) {
		this.machineid = machineid;
	}
	public int getUsertype() {
		return usertype;
	}
	public void setUsertype(int usertype) {
		this.usertype = usertype;
	}
	public int getUserstate() {
		return userstate;
	}
	public void setUserstate(int userstate) {
		this.userstate = userstate;
	}
}
