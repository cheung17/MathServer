package core.constant;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.RequestMapping;

import core.annotation.NoLoginMethod;
import core.util.ClassUtil;
import core.util.DateUtil;
import core.util.PropUtil;

/**
 * 系统常量
 * 
 * @author chenweju
 * 
 * @since Apr 24, 2015
 */
public class SystemConst {

	/** js服务器地址 */
	public static String JSPATH;
	/** FTP资源路径 */
	public static String FTPRESOURCE;
	/** 工程名 */
	public static String PROJECTNAME;
	/** 项目路径 */
	public static String PROPATH;
	/** js参数值 */
	public static String JSCOUNT = "";
	/** 登录超时的跳转地址 */
	public static String LOGIN_OUT_URL = "";
	/** 不需要登陆验证的url */
	public static List<String> NO_CHECK_LOGIN_URL;
	/** controller包名 */
	public static String ACTION_PACKAGE;
	/** 是否删除session */
	public static String deleteSession;

	/** 登录状态-true：允许登录，false:禁止登录 */
	public static String LOGINSTATUS;

	/** 系统类型（0：终端；1：pc；），用于用户登录处理 */
	public static int SYSTEM_TYPE;
	/** 校讯通的路径 */
	public static String XXTPATH;
	/**新闻的路径*/
	public static String NEWSPATH;
	
	/**
	 * 重置全部常量
	 * 
	 * @throws Exception
	 */
	public static void reloadConfigAll() throws Exception {
		reloadConfig();
		DataSourceConst.reloadConfig();
		FTPConst.reloadConfig();
		SysEmailConst.reloadConfig();
		RedisConst.reloadConfig();
		UploadConst.reloadConfig();
	}

	/**
	 * 重新加载常量
	 * 
	 * @throws IOException
	 */
	public static void reloadConfig() throws Exception {
		XXTPATH = PropUtil.getPropValue("systemconst", "xxtpath");
		PROJECTNAME = PropUtil.getPropValue("systemconst", "projectname");
		PROPATH = "/" + PROJECTNAME;
		// js常量相关
		JSPATH = PropUtil.getPropValue("systemconst", "jspath");
		FTPRESOURCE = PropUtil.getPropValue("systemconst", "ftpresource");
		LOGIN_OUT_URL = PropUtil.getPropValue("systemconst", "login_out_url");
		ACTION_PACKAGE = PropUtil.getPropValue("systemconst", "action_package");
		// 登录拦截验证相关 不需要登陆验证的接口
		if (null == NO_CHECK_LOGIN_URL) {
			NO_CHECK_LOGIN_URL = new ArrayList<String>();
		}
		NO_CHECK_LOGIN_URL.add(SystemConst.PROPATH + "/");
		if (null != ACTION_PACKAGE) {
			for (String pkgname : ACTION_PACKAGE.split(";")) {
				Package pkg = Package.getPackage(pkgname);
				if (pkg != null) {
					Set<Class<?>> s = ClassUtil.getClasses(pkg);
					for (Class<?> classess : s) {
						String className = classess.getName();
						if (className.indexOf("$Proxy") == -1) {
							Method[] methodList = classess.getDeclaredMethods();
							for (Method method : methodList) {
								boolean noLoginAccessBool = method
										.isAnnotationPresent(NoLoginMethod.class);
								boolean requessMappingBool = method
										.isAnnotationPresent(RequestMapping.class);
								if (noLoginAccessBool && requessMappingBool) {
									RequestMapping requestMapping = method
											.getAnnotation(RequestMapping.class);
									String methodName = requestMapping.params()[0];
									RequestMapping actMapping = classess
											.getAnnotation(RequestMapping.class);
									String doUrl = actMapping.value()[0];
									if (doUrl.startsWith("/")) {
										NO_CHECK_LOGIN_URL.add("/" + PROJECTNAME
												+ doUrl + "?" + methodName);
									} else {
										NO_CHECK_LOGIN_URL.add("/" + PROJECTNAME
												+ "/" + doUrl + "?"
												+ methodName);
									}
								}
							}
						}
					}
				}
			}
		}
	}

	/**
	 * 重置jscount值
	 * 
	 * @throws Exception
	 */
	public static void reloadJSCOUNT() throws Exception {
		JSCOUNT = DateUtil.getCurrentDateStr(DateUtil.C_YYYY_MM_DD_HH_MM_SS);
	}

}