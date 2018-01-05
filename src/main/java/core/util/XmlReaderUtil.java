package core.util;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.xml.sax.InputSource;

import core.action.PageAct;
/**
 * XML解析工具
 *
 * @author chenweju
 *
 * @since Apr 24, 2015
 */
public class XmlReaderUtil {
    /**
     * XML解析节点
     * @param xmlDoc xlm格式的字符串
     * @param elements 转化类型对应的节点 例：elements="res,personalAssets"-res：根节点，personalAssets：根节点下一级节点
     * @param elementClass 转化的类型
     * @return    
     *
     */
    public static List<?> xmlElements(String xmlDoc, String elements, Class<?> elementClass ) {
        List<Object> list = new ArrayList<Object>(); 
        String[] elementArr = elements.split(",");
        StringReader read = new StringReader(xmlDoc);
        InputSource source = new InputSource(read);
        SAXBuilder sb = new SAXBuilder();
        try {
            Document doc = sb.build(source);
            Element element = getElementByXmlElements(doc.getRootElement(), elementArr, 1);
            if(element == null){
                return null;
            }
            List<Element> childs = element.getChildren();
            for(int i=0;i<childs.size();i++){
                List<Element> subchilds = childs.get(i).getChildren();//循环依次得到子元素
                Element et = null;
                Map<String, Object> map = new HashMap<String, Object>();
                for(int j=0;j<subchilds.size();j++){
                    et = (Element) subchilds.get(j);//循环依次得到子元素
                    map.put(et.getName(), et.getText());
                }
                list.add(map);
            }
            PageAct pageAct = new PageAct();
            return pageAct.jsonToList(JsonUtil.beanToJson(list), elementClass);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * XML解析节点（单一节点）
     * @param xmlDoc xlm格式的字符串
     * @param elements 转化类型对应的节点 例：elements="res,personalAssets"-res：根节点，personalAssets：根节点下一级节点
     * @param elementClass 转化的类型
     * @return    
     *
     */
    public static Map<String, Object> xmlElementsForMap(String xmlDoc, String elements) {
        Map<String, Object> map = new HashMap<String, Object>();
        String[] elementArr = elements.split(",");
        StringReader read = new StringReader(xmlDoc);
        InputSource source = new InputSource(read);
        SAXBuilder sb = new SAXBuilder();
        try {
            Document doc = sb.build(source);
            Element element = getElementByXmlElements(doc.getRootElement(), elementArr, 1);
            if(element == null){
                return null;
            }
            List<Element> childs = element.getChildren();
            for(int i=0;i<childs.size();i++){
                Element et = (Element) childs.get(i);//循环依次得到子元素
                map.put(et.getName(), et.getText());
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * XML解析节点属性（单一节点）
     * @param xmlDoc
     * @param elements
     * @param param
     * @return    
     *
     */
    public static Map<String, Object> xmlAttributes(String xmlDoc, String elements) {
        String[] elementArr = elements.split(",");
        Map<String, Object> map = new HashMap<String, Object>();
        StringReader read = new StringReader(xmlDoc);
        InputSource source = new InputSource(read);
        SAXBuilder sb = new SAXBuilder();
        try {
            Document doc = sb.build(source);
            Element element = getElementByXmlElements(doc.getRootElement(), elementArr, 1);
            if(element == null){
                return null;
            }
            List<?> childs = element.getAttributes();
            Attribute attribute = null;
            for(int i=0;i<childs.size();i++){
                attribute = (Attribute) childs.get(i);//循环依次得到子元素
                map.put(attribute.getName(), attribute.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return map;
        }
        
        return map;
    }
    
    private static Element getElementByXmlElements(Element element, String[] elementArr, int index){
        if((elementArr.length) > index){
            return getElementByXmlElements(element.getChild(elementArr[index]), elementArr, index+1);
        }
        return element;
    }
    /**
     * 解析xml，返回List<Map<String, String>>
     * @param xmlDoc
     * @return
     * @throws Exception
     */
    public static List<Map<String, String>> parseXMLToList(String xmlDoc) throws Exception {
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
        StringReader read = new StringReader(xmlDoc);
        InputSource source = new InputSource(read);
        SAXBuilder sb = new SAXBuilder();
        try {
            Document doc = sb.build(source);
            
            List<Element> hzList = doc.getRootElement().getChildren();
            for (Element element : hzList) {
            	Map<String, String> map = new HashMap<String, String>();
            	List<Attribute> attList = element.getAttributes();
            	for (Attribute attribute : attList) {
            		String key = element.getName() + attribute.getName();
					map.put(key.toLowerCase(), attribute.getValue());
				}
                List<Element> childs = element.getChildren();
                Element et = null;
                for(int i=0;i<childs.size();i++){
                    et = childs.get(i);//循环依次得到子元素
                    map.put(et.getName(), et.getText());
                    attList = et.getAttributes();
                    for (Attribute attribute : attList) {
                		String key = et.getName() + attribute.getName();
    					map.put(key.toLowerCase(), attribute.getValue());
    				}
                }
                list.add(map);
			}
        } catch (Exception e) {
        	throw e;
        }
        return list;
    }
}
