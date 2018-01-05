package core.constant;

import java.io.IOException;

import core.util.PropUtil;

/**
 * 短信接口相关 常量
 *
 * @author chenweju
 *
 * @since Apr 25, 2015
 */
public class SMSConst {

	/** 短信配置:用户名 */
	public static String SMS_USER;
	/** 短信配置:密码 */
	public static String SMS_PWD;
	/** 短信配置:URL */
	public static String SMS_URL;
	/** 短信配置:是否开启 */
	public static Boolean SMS_OPEN;
	/** 短信配置:用户注册模板id */
	public static String templateId5 = "1ouph3GGQFa9Tx0dAjB8Lo";
	/** 短信配置:修改密码模板id */
	public static String templateId4 = "dOg2nXmu4RF8yHwGgXItTX";
	/** 短信配置:身份验证模板id */
	public static String templateId3 = "etLhHxDLkq7aq8lpbVEfdN";
	/** 短信配置:通用验证模板id */
	public static String templateId2 = "cJyw2IA0477bM_OP3NY9Ym";
	/** 短信配置:找回密码模板id */
	public static String templateId = "3ZPLAl694A98hEwHnbiwxP";
	/** 短信配置:常用验证码模板id（弃用） */
	public static String templateId6 = "2dNPdJbj494bKrWk2IfNkg";
	/** 短信配置:租机通知短信模板 */
	public static String templateId7 = "2uxQhrPoQPg9A-P5lprMdM";
	/** 短信设置:教师端发送校讯通到学生端通知模板*/
	public static String templateId8 = "5UYIRlSO4dtbAt6JOlzmjJ";
	/** 短信设置:发送学生课前作业通知到家长手机模板*/
	public static String templateId9 = "3QDzjTOQAJn8fZ69qXb99k";
	/** 短信配置:手机号码所属国家区号 */
	public static String region = "86";
	/**
	 * 重新加载常量
	 * 
	 * @throws IOException
	 */
	public static void reloadConfig() throws Exception {
		// 短信接口
		SMS_URL = PropUtil.getPropValue("smsconst", "sms_url");
		SMS_USER = PropUtil.getPropValue("smsconst", "sms_user");
		SMS_PWD = PropUtil.getPropValue("smsconst", "sms_pwd");
		SMS_OPEN = new Boolean(PropUtil.getPropValue("smsconst", "sms_open"));
	}

}