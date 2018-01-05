package core.util;

import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.type.JavaType;

import com.alibaba.fastjson.JSONObject;

/**
 * json工具类
 * 
 * @author chenweju
 * 
 * @since Apr 24, 2015
 */
public class JsonUtil {

    private static ObjectMapper mapper = new ObjectMapper();

    /**
     * 将bean转换为json对象 要转换的bean如果为对象，对象中的属性必须有public的get方法
     * 
     * @param obj
     *            要转换的对象
     * @return
     * @throws Exception
     */
    public static String beanToJson(Object obj) throws Exception {
	return beanToJson(obj, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 将bean转换为json对象 要转换的bean如果为对象，对象中的属性必须有public的get方法
     * 
     * @param obj
     *            要转换的对象
     * @return
     * @throws Exception
     */
    @SuppressWarnings("deprecation")
    public static String beanToJson(Object obj, String dateFormatStr)
	    throws Exception {
	if (null == obj) {
	    return "";
	}
	JsonGenerator gen = null;
	StringWriter writer = null;
	String json = "";
	try {
	    ObjectMapper mapper = new ObjectMapper();
	    // 个性化设置
	    mapper.configure(SerializationConfig.Feature.WRITE_NULL_PROPERTIES,
		    false);
	    mapper.getSerializationConfig().setSerializationInclusion(
		    Inclusion.NON_NULL);
	    // 格式化日期
	    mapper.setDateFormat(new SimpleDateFormat(dateFormatStr));
	    writer = new StringWriter();
	    gen = new JsonFactory().createJsonGenerator(writer);
	    mapper.writeValue(gen, obj);
	    json = writer.toString();
	} catch (Exception e) {
	} finally {
	    if (null != gen) {
		gen.close();
	    }
	    if (null != writer) {
		writer.close();
	    }
	}
	json = json.replaceAll("'", "&#39;").replaceAll(":null", ":\"\"");
	return json;

    }

    /**
     * json转换list 例：this.json2List(josn, Map.class); this.json2List(josn,
     * EWfNodeBase.class);
     * 
     * @param json
     *            json字符串
     * @param elementClass
     *            转化的类型
     * @return
     */
    public static List<?> jsonToList(String json, Class<?> elementClass)
	    throws Exception {
	if (!Util.isBlank(json)) {
	    if (!"[".equals(json.substring(0, 1))) {
		json = "[" + json + "]";
	    }
	    ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES,
		    true);
	    JavaType javaType = mapper.getTypeFactory()
		    .constructParametricType(ArrayList.class, elementClass);
	    return mapper.readValue(json, javaType);
	}
	return null;
    }

    public static <T> T jsonToBean(String json, Class<T> clazz) {
	T t = null;
	try {
	    t = mapper.readValue(json, clazz);
	} catch (JsonParseException e) {
	    e.printStackTrace();
	} catch (JsonMappingException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return t;
    }

    /**
     * 数组转json
     * 
     * @param arrays
     * @return
     * @throws Exception
     */
    public static String arrayToJson(Object[] arrays) throws Exception {
	Map<String, Object> map;
	List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
	if (null != arrays && arrays.length > 0) {
	    for (Object o : arrays) {
		map = new HashMap<String, Object>();
		map.put("name", o);
		map.put("id", o);
		mapList.add(map);
	    }
	}
	return beanToJson(mapList);
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> parseJSON2Map(String jsonStr)
	    throws Exception {
	List<Map<String, Object>> list = (List<Map<String, Object>>) jsonToList(
		jsonStr, Map.class);
	if (null != list && list.size() > 0) {
	    return list.get(0);
	}
	return null;
    }
    
    public static String getJsonValue(String rescontent, String key) {
		JSONObject jsonObject;
		String v = null;
		try {
			jsonObject = JSONObject.parseObject(rescontent);
			v = jsonObject.getString(key);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return v;
	}
}
