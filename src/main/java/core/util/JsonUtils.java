/**
 * 
 */
package core.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author libing
 * @descripton json转换工具类
 * @date 2015年8月19日 下午12:32:10
 */
public class JsonUtils {
	 public static String mapToJson(Map<String, Object> map) {
	        Set<String> keys = map.keySet();
	        String key = "";
	        Object value = "";
	        StringBuffer jsonBuffer = new StringBuffer();
	        jsonBuffer.append("{");
	        for (Iterator<String> it = keys.iterator(); it.hasNext();) {
	            key = (String) it.next();
	            value = map.get(key);
	            jsonBuffer.append("\""+key+"\"" + ":" +"\""+ value+"\"");
	            if (it.hasNext()) {
	                jsonBuffer.append(",");
	            }
	        }
	        jsonBuffer.append("}");
	        return jsonBuffer.toString();
	    }
	 
	 public static String mapToJsonString(Map<String, String> map) {
	        Set<String> keys = map.keySet();
	        String key = "";
	        String value = "";
	        StringBuffer jsonBuffer = new StringBuffer();
	        jsonBuffer.append("{");
	        for (Iterator<String> it = keys.iterator(); it.hasNext();) {
	            key = (String) it.next();
	            value = map.get(key);
	            jsonBuffer.append("\""+key+"\"" + ":" +"\""+ value+"\"");
	            if (it.hasNext()) {
	                jsonBuffer.append(",");
	            }
	        }
	        jsonBuffer.append("}");
	        return jsonBuffer.toString();
	    }

}
