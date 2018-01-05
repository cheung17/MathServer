package core.action;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

import core.user.util.UserLoginBean;
import core.util.DateUtil;
import core.util.RedisUtil;
import core.util.Util;
import core.vo.SessionUser;
import core.vo.SuperVO;

/**
 * 所有controller的父类
 *
 * @author chenweju
 *
 * @since Apr 24, 2015
 */
public class SuperAct extends PageAct{
	/** session中的用户对象名 ，存当前登录用户*/
	public static final String SESSION_USERNAME = "platform_sessionuser";
	/**session中的用户在平台中的操作权限*/
	public static final String SESSION_ROLE_POWER = "role_power";
	/** session中的登录次数 */
	public static final String SESSION_LOGINTIMES = "platform_logintimes";
	/** 登录-验证码 */
	public static final String SESSION_LOGIN_VALIDATECODE = "validateCode";
	/** cookie中的记录多选项卡用户上次操作习惯 */
	public static final String MULTITABCOM_COOKIE_NAME = "mtmc";
	/** cookie中的记录上次登录成功的用户id */
	public static final String LASTLOGINID_COOKIE_NAME = "lastlgid";
	/** 终端查询数据时传入参数使用的JSON KEY */
	public static final String DEVICE_QUERY_PARAMS_KEY = "textcontent";
	/** 终端往后退推送数据（如日志等）时传入参数使用的JSON KEY */
	public static final String DEVICE_SEND_DATA_KEY = "SEND_DATA";

	/**
	 * 解析终端传来的查询参数
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	protected Map<String, String> getQueryParamsFromDevice(HttpServletRequest request)
			throws Exception {
		String query_json = request.getParameter(DEVICE_QUERY_PARAMS_KEY);
		if (Util.isBlank(query_json)) {
			return null;
		}
		Map<String, String> map = ((List<Map<String, String>>) jsonToList(query_json, Map.class)).get(0);
		return map;
	}
	/**
	 * 解析终端传来的数据集
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	protected List<Map<String, String>> getDataListFromDevice(HttpServletRequest request)
			throws Exception {
		String query_json = request.getParameter(DEVICE_SEND_DATA_KEY);
		if (Util.isBlank(query_json)) {
			return null;
		}
		List<Map<String, String>> list = (List<Map<String, String>>) jsonToList(query_json, Map.class);
		return list;
	}
	
	/**
	 * 权限验证
	 * @param request
	 * @param rightCode 权限项编码
	 * @return
	 * @throws Exception
	 */
	protected boolean rightAuth(HttpServletRequest request, String rightCode)
			throws Exception {
		//TODO 待平台权限控制结构确定后完成功能
		return true;
	}

	/**
	 * 获取权限验证提示信息
	 * @param request
	 * @return
	 * @throws Exception
	 */
	protected String getRightAuthMsg(HttpServletRequest request)
			throws Exception {
		String msg = (String) request.getAttribute("rightauth_msg");
		if (null == msg) {
			return "没有权限";
		} else {
			return msg;
		}
	}

	/**
	 * 跳转到错误页面
	 * @param msg
	 * @return
	 */
	protected String toNoright(HttpServletRequest request, String msg) {
		request.setAttribute("rightauth_msg", msg);
		return "/msg/noright";
	}

	/**
	 * 获取当前登录用户
	 * @param request request对象
	 * @return
	 */
	protected SessionUser getUserFromSession(HttpServletRequest request) {
		SessionUser user = (SessionUser) request.getSession(true).getAttribute(
				SESSION_USERNAME);
		return user;
	}

	/**
	 * 从session中获取属性值
	 * @param request
	 * @param attrName
	 * @return
	 */
	protected Object getAttrFromSession(HttpServletRequest request,
			String attrName) {
		return request.getSession(true).getAttribute(attrName);
	}
	
	/**
	 * 获取cookie的值
	 * @param request
	 * @return
	 */
	protected UserLoginBean getLoginUserInfo(HttpServletRequest request){
		String cid = "";
		for(Cookie cookie : request.getCookies()){// 遍历Cookie
			if(cookie.getName().equalsIgnoreCase("rediskey")){ // 如果为SSID  ssid = cookie.getValue();// 保存SSID内容
				cid = cookie.getValue();
			}
		}
		
		if(!StringUtils.isEmpty(cid)){
			Object obj = RedisUtil.getObj(cid, UserLoginBean.class);
			return (UserLoginBean) obj;
		}
		
		return null;
	}

	protected <T> Object getLoginUserInfo(HttpServletRequest request,Class<T> cls){
		String cid = "";
		Cookie[] cookies = request.getCookies();
		if (null == cookies) {
			System.out.println("cookies lost!");
		}
		for(Cookie cookie : request.getCookies()){// 遍历Cookie
			if(cookie.getName().equals("cid")){ // 如果为SSID  ssid = cookie.getValue();// 保存SSID内容
				cid = cookie.getValue();
			}
		}
		
		if(!StringUtils.isEmpty(cid)){
			return   RedisUtil.getObj(cid, cls);
		}
		
		return null;
	}
	
	/**
	 * 设置值到session
	 * @param request
	 * @param objName
	 * @param objVal
	 */
	protected void setObjectToSession(HttpServletRequest request,
			String objName, Object objVal) {
		request.getSession(true).setAttribute(objName, objVal);
	}

	/**
	 * 从session中删除属性值
	 * @param request
	 * @param attrName
	 */
	protected void removeAttrFromSession(HttpServletRequest request,
			String attrName) {
		Object obj = this.getAttrFromSession(request, attrName);
		if (null != obj) {
			request.getSession(true).removeAttribute(attrName);
		}
	}

	/**
	 * 获取域名
	 * @param request
	 * @return
	 */
	protected String getBasePath(HttpServletRequest request) {
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path + "/";
		return basePath;
	}
	/**
	 * 根据request中textcontent参数封装实体类
	 * @param request
	 * @param class1
	 * @return
	 */
	protected SuperVO initBean(HttpServletRequest request, SuperVO vo) throws Exception {
		Map<String, String> map = this.getQueryParamsFromDevice(request);
		List<Field> list = vo.getAttributeFieldEntry();
		Set<String> keySet = map.keySet();
		for (Field field : list) {
			String fname = field.getName();
			if (keySet.contains(fname)) {
				Object fvalue = map.get(fname);
				String fieldType = field.getType().getName();
				if ("java.lang.String".equals(fieldType)) {
				}else if ("java.lang.Integer".equals(fieldType)) {
					fvalue = Util.getIntegerValue(map.get(fname));
				}else if ("java.lang.Float".equals(fieldType)) {
					fvalue = Util.getFloatValue(map.get(fname), null);
				}else if ("java.util.Date".equals(fieldType)) {
					if (null == map.get(fname)) {
						fvalue = null;
					}else if (map.get(fname).length() == 10) {
						fvalue = DateUtil.parseStringToDate(map.get(fname), DateUtil.C_YYYY_MM_DD);
					}else if (map.get(fname).length() == 19) {
						fvalue = DateUtil.parseStringToDate(map.get(fname), DateUtil.C_YYYY_MM_DD_HH_MM_SS);
					}else {
						fvalue = null;
					}
				}
				vo.setAttributeValue(fname, fvalue);
			}
		}
		
		return vo;
	}
	/**
	 * @param request
	 * @return
	 */
	public String getUseridFromCookie(HttpServletRequest request) {
		Cookie[] cooks =  request.getCookies();
		String userId  = "";
		if(cooks != null){
			for(Cookie cookie : cooks){
				if(cookie.getName().equals("redisKey")){
					userId = cookie.getValue();
					userId = userId.substring(userId.length()-32, userId.length());
				}
			}
		}
		return userId;
	}
}
