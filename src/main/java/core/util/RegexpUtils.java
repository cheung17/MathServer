package core.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
     正则表达式工具类
 * @author LB
 *
 */
public final class RegexpUtils  {
	
	public RegexpUtils(){
		
	}
	
	/**用户名匹配：匹配由数字、26个英文字母、下划线、汉字组成的用户名，不能以下划线开头，且长度为4到15位*/
	//public static final String USERNAME_REGEXP = "^[0-9a-zA-Z\u4e00-\u9fa5]{4,15}$";
	
	/**用户名匹配：匹配由数字、26个英文字母、汉字组成的用户名，且长度为2到15位*/
	public static final String USERNAME_REGEXP = "^[0-9a-zA-Z\u4e00-\u9fa5]{2,15}$";
	 
	/**密码匹配：以字母开头，长度在6~18之间，只能包含字符、数字和下划线*/
	public static final String PASSWORD_REGEXP = "^[0-9a-zA-Z]{6,18}$";
	
	/**手机格式验证*/
	public static final String MOBILE_REGEXP = "^1[3|4|5|8][0-9]\\d{4,8}$";
	
	
	/**
	 * @param source 匹配的源字符串
	 * @param regexp 匹配的正则表达式
	 * @return 如果字符串符合要求返回true,否则返回false
	 */
	public static boolean isHardRegexpValidate(String source,String regexp){
		Pattern pattern = Pattern.compile(regexp);
		Matcher matcher = pattern.matcher(source);
		boolean bool = matcher.matches();
		return matcher.matches();
	}
}
