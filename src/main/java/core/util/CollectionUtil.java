/**
 * 
 */
package core.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author libing
 * @descripton 集合类处理工具类
 * @date 2015年8月19日 下午2:25:51
 */
public class CollectionUtil {
	/**
	 * @functionName:combineMap
	 * @description:将数据类型为Map<String, Object>的map集合childMap合并到parMap中
	 * @param:parMap
	 * @return:
	 * @date:2015年8月19日 下午3:16:43
	 */
	public static void combineMap(Map<String, Object> parMap,Map<String, Object> childMap){
		 Set<String> keySet = childMap.keySet();
		 String key="";
		 Object value="";
		 /**
		  * 遍历childMap集合中的key-value对，并将其添加到parMap集合中
		  */
		 for(Iterator<String> it=keySet.iterator();it.hasNext();){
			 key = (String)it.next();
			 value = childMap.get(key);
			 parMap.put(key, value);
		 }
	}
}
