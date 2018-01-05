package core.classroom.fileupload;

import java.io.Serializable;

/**
 * 
 * @author libing
 * @description 消息
 * 
 */
public class uploadResBean implements Serializable{
	private static final long serialVersionUID = 92388743558L;
	//文件名
	public String 	filename;
	//總的BLOCK
	public int 		blockcount;
	//FLAG：0为开始，1为其他的传输，2为传输结束
	public int 		flag;
	//保存的位置
	public String 	savepath;
	//上传块的序号
	public int		blockidx;
	//块的文件长度
	public int		filesize;
	//保存的类型
	public int		type;
	
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
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
	public String getSavepath() {
		return savepath;
	}
	public void setSavepath(String savepath) {
		this.savepath = savepath;
	}
	public int getBlockidx() {
		return blockidx;
	}
	public void setBlockidx(int blockidx) {
		this.blockidx = blockidx;
	}
	public int getFilesize() {
		return filesize;
	}
	public void setFilesize(int filesize) {
		this.filesize = filesize;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}	
}
