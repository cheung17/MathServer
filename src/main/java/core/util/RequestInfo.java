/**
 * 
 */
package core.util;

import java.io.Serializable;

/**
 * @author libing
 * @descripton 前端请求参数封装
 * @date 2015年8月26日 下午2:14:32
 */
public class RequestInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -333517344389660374L;
	/**
	 * 默认的构造函数
	 */
	public RequestInfo(){
		
	}
	/**
	 * machineid  终端设备编号
	 */
	private String machineid;
	/**
	 * userid :用户编号
	 */
	private String userid;
	/**
	 * textcontent 前端发来的请求内容
	 */
	private String textcontent;
	public String getMachineid() {
		return machineid;
	}
	public void setMachineid(String machineid) {
		this.machineid = machineid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getTextcontent() {
		return textcontent;
	}
	public void setTextcontent(String textcontent) {
		this.textcontent = textcontent;
	}
}
