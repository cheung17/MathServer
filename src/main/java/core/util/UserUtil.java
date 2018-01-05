/**
 * 
 */
package core.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author libing
 * @descripton
 * @date 2015年8月12日 上午8:53:10
 */
public class UserUtil
{	
	/**
	 * @functionName:isMobileNo
	 * @description:判断字符串是否为手机号码
	 * @param:mobiles:待验证的手机号字符串
	 * @return:true:是手机号 false:不是手机号
	 * @author libing
	 * @date:2015年8月12日 上午8:55:33
	 */
	public static boolean isMobileNo(String mobiles){
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles); 
		return m.matches();
	}
	
	/**
	 * @functionName:isEmail
	 * @description:判断邮箱格式是否正确
	 * @param:email 邮箱字符串
	 * @return:true:邮箱格式正确   false:邮箱格式错误
	 * @author libing
	 * @date:2015年8月12日 上午9:02:54
	 */
	public static boolean isEmail(String email){
		if(null==email || "".equals(email)){
			return false;
		}
		Pattern p = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
		Matcher matcher=p.matcher(email);
		return matcher.matches();
	}
	
	/**
	 * @functionName:isDigital
	 * @description:判断字符串是否以纯数字形式
	 * @param:str：待校验字符串
	 * @return:true:纯数字格式的字符串    false:非数字格式的字符串
	 * @author libing
	 * @date:2015年8月12日 上午9:10:01
	 */
	public static boolean isDigital(String str){
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if(!isNum.matches()){
			return false;
		}
		else {
			return true;
		}
	}

}
