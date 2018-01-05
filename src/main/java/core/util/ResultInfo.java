package core.util;

import java.io.Serializable;
import java.net.URLEncoder;

import org.springframework.stereotype.Repository;

/**
 * @Description action请求返回信息
 * 
 * @author chenwenju
 * 
 */
@Repository
public class ResultInfo implements Serializable {
    /**
	 * 
	 */
    private static final long serialVersionUID = 512681557748919471L;
    /**
     * 结果状态值 默认0，-1：失败；1：成功；
     */
    private int code = 0;
    /**
     * 对状态值的描述
     */
    private String msg;
    /**
     * 数据
     */
    private Object data;
    
    public ResultInfo(int code) {
	this.code = code;
    }

    public int getCode() {
	return code;
    }

    public void setCode(int code) {
	this.code = code;
    }

    public Object getData() {
	return data;
    }

    public void setData(Object data) {
	this.data = data;
    }

    public ResultInfo(int code, String msg) {
	this.code = code;
	this.msg = msg;
    }

    /**
     * 设定字符集
     * 
     * @param code
     * @param message
     * @param charset
     */
    public ResultInfo(int code, String msg, String charset) {
	this.code = code;
	try {
	    this.msg = URLEncoder.encode(msg, charset);
	} catch (Exception e) {
	    this.msg = msg;
	}
    }

    /**
     * 设定data
     * 
     * @param code
     * @param message
     * @param charset
     * @param data
     */
    public ResultInfo(int code, String msg, String charset, Object data) {
	this.code = code;
	this.data = data;
	try {
	    this.msg = URLEncoder.encode(msg, charset);
	} catch (Exception e) {
	    this.msg = msg;
	}
    }

    public ResultInfo() {
	this.code = 0;
	this.msg = "";
    }

    public ResultInfo(Integer resultCode) {
	this.code = resultCode;
    }

    public ResultInfo(Integer resultCode, Object data) {
	this.code = resultCode;
	this.data = data;
    }

    public void setSuccess(String msg, Object data) {
	this.code = 1;
	this.msg = msg;
	this.data = data;
    }

    public void setFailure(String msg) {
	this.code = -1;
	this.msg = msg;
	this.data = null;
    }

    public String getMsg() {
	return msg;
    }

    public void setMsg(String msg) {
	this.msg = msg;
    }
}
