package core.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author chenweju
 *
 * @since Apr 24, 2015
 */
public class PagerVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3550008277757626894L;
	
	/**记录总数*/ 
	private int total;
	
	/**记录列表*/
	private List<?> datas;
	
	/**json参数map*/
	private Map<String,Object> jsonParamMap;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<?> getDatas() {
		return datas;
	}

	public void setDatas(List<?> datas) {
		this.datas = datas;
	}
	
	public void addJsonParam(String param,Object value){
		if(null==jsonParamMap){
			jsonParamMap=new HashMap<String,Object>();
		}
		jsonParamMap.put(param, value);
	}

	public Map<String, Object> getJsonParamMap() {
		return jsonParamMap;
	}
}