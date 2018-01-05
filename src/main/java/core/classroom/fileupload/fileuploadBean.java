package core.classroom.fileupload;

import java.io.Serializable;

/**
 * 
 * @author libing
 * @description 消息
 * 
 */
public class fileuploadBean implements Serializable {

	private static final long serialVersionUID = 92388771718L;
	public String id;
	public String filename;
	public String ftpfilename;
	public int blockcount;
	public int flag;
	public String fileext;
	public String filemd5;
	public String md5;
	public String param;
	public int status = 0;
	public String savePath;
	
	public String getSavePath() {
		return savePath;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	public String getFilemd5() {
		return filemd5;
	}
	public void setFilemd5(String filemd5) {
		this.filemd5 = filemd5;
	}
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFtpfilename() {
		return ftpfilename;
	}
	public void setFtpfilename(String ftpfilename) {
		this.ftpfilename = ftpfilename;
	}
	public int getBlockcount() {
		return blockcount;
	}
	public void setBlockcount(int blockcount) {
		this.blockcount = blockcount;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public String getFileext() {
		return fileext;
	}
	public void setFileext(String fileext) {
		this.fileext = fileext;
	}	
}
