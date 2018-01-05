/**
 * 
 */
package core.user.util;


import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.constant.DataBaseTableConstant;
import core.user.util.LoginBean;
import core.user.util.LoginResultBean;
import core.util.RedisKey;

/**
 * @author libing
 * @descripton 向response中加入指定的Cookie
 * @date 2015年9月6日 下午12:33:48
 */
public class UserCookieUtil{
	//处理用户登录的Cookie
	public static void writeCookie(HttpServletRequest request,HttpServletResponse response,
			RequestLogin requestInfo,Map<String, Object> resultMap){
		//RedisId
		String cookieId=RedisKey.USER_LOGIN + requestInfo.getMachineid() + requestInfo.getRandomLoginId();
		
		//建立Cookie
		Cookie cidCookie = new Cookie("cid", cookieId);//redisID
		
		//设置Cookie路径
		cidCookie.setPath("/");//redidId
		
		//指定Cookie的最长存活周期
		cidCookie.setMaxAge(DataBaseTableConstant.COOKIE_MAX_AGE);//redidId
		
		//在response中添加指定的Cookie
		response.addCookie(cidCookie);
	
	}

	/**
	 * 用户登录Cookie
	 */
	public static void writeCookie(HttpServletRequest request,HttpServletResponse response,LoginBean loginBean,
			LoginResultBean loginResultBean){
		String cookieId=RedisKey.USER_LOGIN+loginBean.getAccount()+loginBean.getRandomUserLoginId();
		
		//建立Cookie
		Cookie cidCookie = new Cookie("cid", cookieId);//redisID
		 
		
		// 设置Cookie路径
		cidCookie.setPath("/");// redidId
		 

		// 指定Cookie的最长存活周期
		cidCookie.setMaxAge(DataBaseTableConstant.COOKIE_MAX_AGE);// redidId
		 

		// 在response中添加指定的Cookie
		response.addCookie(cidCookie);
	}
	
	public static void writeCookie(HttpServletResponse response,String redisKey)throws Exception{
		        //建立Cookie
				Cookie cidCookie = new Cookie("redisKey", redisKey);//redisID
				// 设置Cookie路径
				cidCookie.setPath("/");// redidId
				// 指定Cookie的最长存活周期
				cidCookie.setMaxAge(DataBaseTableConstant.COOKIE_MAX_AGE);// redidId
				// 在response中添加指定的Cookie
				response.addCookie(cidCookie);
	}
}
