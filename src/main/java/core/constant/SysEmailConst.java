package core.constant;

import java.io.IOException;

import core.util.PropUtil;

/**
 * 邮箱信息配置
 *
 * @author chenweju
 *
 * @since Apr 24, 2015
 */
public class SysEmailConst {
	/** 邮箱服务器 */
	public static String EMAILHOST = null;
	/** 邮箱发送用户名 */
	public static String EMAILSENDUSER = null;
	/** 邮箱发送用户密码 */
	public static String EMAILSENDPWD = null;
	/** 邮箱后缀 */
	public static String EMAILSUFFIX = null;
	/** 邮箱附件最大值 */
	public static String EMAILATTACHMENT = null;
	/** 系统出错监控邮箱 */
	public static String MONITOEMAIL = null;
	/****邮箱昵称****/
	public static String NICK = null;

	/**
	 * 重新加载常量
	 * 
	 * @throws IOException
	 */
	public static void reloadConfig() throws Exception {
		EMAILHOST = PropUtil.getPropValueFromClasspath("sysemailconst",
				"emailHost");
		EMAILSENDUSER = PropUtil.getPropValueFromClasspath("sysemailconst",
				"emailSendUser");
		EMAILSENDPWD = PropUtil.getPropValueFromClasspath("sysemailconst",
				"emailSendPwd");
		EMAILSUFFIX = PropUtil.getPropValueFromClasspath("sysemailconst",
				"emailSuffix");
		EMAILATTACHMENT = PropUtil.getPropValueFromClasspath("sysemailconst",
				"emailAttachment");
		MONITOEMAIL = PropUtil.getPropValueFromClasspath("sysemailconst",
				"monitoEmail");
		NICK = PropUtil.getPropValueFromClasspath("sysemailconst",
				"nick");
	}

}