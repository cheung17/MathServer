package core.classroom.fileupload;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author libing
 * @description 消息
 * 
 */
public class FileInfosBean implements Serializable {

	private static final long serialVersionUID = 9238544718L;
	public List<fileuploadBean> lt;
	public List<fileuploadBean> getLt() {
		return lt;
	}
	public void setLt(List<fileuploadBean> lt) {
		this.lt = lt;
	}
}
