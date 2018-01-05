package core.util;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.action.SuperAct;
import core.vo.SessionUser;


public class CookieUtil {
	/**
	 * 设置登录cookie
	 * 
	 * @param sessionUser
	 * @param request
	 * @param response
	 */
	public static void setLoginCookie(SessionUser sessionUser,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			// cookie中的记录上次登录成功的用户id
			addCookie(request, response, SuperAct.LASTLOGINID_COOKIE_NAME,
					Util.null2String(sessionUser.getLoginid()),
					1000 * 60 * 60 * 24);
			// 获取cookie中的记录多选项卡用户上次操作习惯并写入到session中
			readMultitabCookieAndSetSession(request, sessionUser);

		} catch (Exception e) {
			LogUtil.error(CookieUtil.class, e);
		}
	}

	/**
	 * 设置多选项卡用户选择习惯cookie
	 * 
	 * @param sessionUser
	 * @param request
	 * @param response
	 * @param power
	 *            是否分权（1 不分权,0分权）
	 * @param type
	 *            （0:单选1：多选）区分单选、多选
	 * @param isval
	 *            区分控件默认标签
	 */
	@SuppressWarnings("deprecation")
	public static void setMultitabCookie(SessionUser sessionUser,
			HttpServletRequest request, HttpServletResponse response,
			String cookiekey, String isval) throws Exception {
		Map<String, String> cookieMap = sessionUser.getMultitabcom_cookie_map();
		if (null == cookieMap) {
			cookieMap = new HashMap<String, String>();
		}
		if (!Util.isBlank(cookiekey)) {
			cookieMap.put(cookiekey, isval);
			// 写cookie
			String valcookie = JsonUtil.beanToJson(cookieMap).toString();
			valcookie = URLEncoder.encode(valcookie);

			CookieUtil.addCookie(request, response,
					SuperAct.MULTITABCOM_COOKIE_NAME + sessionUser.getId(),
					valcookie, 30 * 60 * 60 * 24);

			sessionUser.setMultitabcom_cookie_map(cookieMap);
		}
	}

	/**
	 * 获取cookie中的记录多选项卡用户上次操作习惯并写入到session中
	 * 
	 * @param request
	 * @param sessionUser
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	public static void readMultitabCookieAndSetSession(
			HttpServletRequest request, SessionUser sessionUser) {
		try {
			String cookieval = getCookie(request,
					SuperAct.MULTITABCOM_COOKIE_NAME + sessionUser.getId());

			if (!Util.isBlank(cookieval)) {
				cookieval = URLDecoder.decode(cookieval);
				List<Map<String, String>> cookieList;
				cookieList = (List<Map<String, String>>) JsonUtil.jsonToList(
						cookieval, Map.class);
				sessionUser.setMultitabcom_cookie_map(cookieList.get(0));
			}
		} catch (Exception e) {
			LogUtil.error(CookieUtil.class, e);
		}
	}

	/**
	 * 设置cookie
	 * 
	 * @param request
	 * @param response
	 * @param cookieName
	 * @param cookieVal
	 * @param expiry
	 * @throws Exception
	 */
	public static void addCookie(HttpServletRequest request,
			HttpServletResponse response, String cookieName, String cookieVal,
			Integer expiry) throws Exception {
		String secondDomain = Util.getSecondDomain(request);// 二级域名

		Cookie cookie = new Cookie(cookieName, cookieVal);
		cookie.setDomain(secondDomain);
		if (null != expiry) {
			cookie.setMaxAge(expiry);
		}
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	/**
	 * 获取cookie值
	 * 
	 * @param request
	 * @param cookieName
	 * @return
	 * @throws Exception
	 */
	public static String getCookie(HttpServletRequest request, String cookieName)
			throws Exception {
		Cookie[] cks = request.getCookies();
		String cookieval = "";
		if (null != cks && cks.length > 0) {
			for (Cookie cookie : cks) {
				if (cookie.getName().equals(cookieName)) {
					cookieval = cookie.getValue();
					break;
				}
			}
		}
		return cookieval;
	}
}
