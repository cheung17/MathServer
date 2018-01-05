package core.classroom;

import java.io.Serializable;

/**
 * 
 * @author libing
 * @description 消息
 * 
 */
public class RoomMessage implements Serializable {

	private static final long serialVersionUID = 9020345676788771718L;
	// 0,为成功
	public int code = 0;
	// 信息
	public String msg;
	// 参数1
	public String arg1;
	public int arg2;
		
	public RoomMessage(int code, String msg, String arg1, int arg2) {
		super();
		this.code = code;
		this.msg = msg;
		this.arg1 = arg1;
		this.arg2 = arg2;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getArg1() {
		return arg1;
	}
	public void setArg1(String arg1) {
		this.arg1 = arg1;
	}
	public int getArg2() {
		return arg2;
	}
	public void setArg2(int arg2) {
		this.arg2 = arg2;
	}
}
