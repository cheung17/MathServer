package core.constant;

import java.io.IOException;

import core.util.PropUtil;

/**
 * FTP配参数工具类
 * 
 * @author chenweju
 * 
 * @since Apr 24, 2015
 */
public class FTPConst {
	/** FTP服务器ip地址 */
	public static String FTP_IP = "120.76.96.97";
	/** FTP服务器端口 */
	public static String FTP_PORT = "21";
	/** FTP 用户名 */
	public static String FTP_USER = "qimon";
	/** FTP 密码 */
	public static String FTP_PWD = "qimon201610";
	/** FTP验证码存放路径 */
	public static String PATH_CHECKIMAGE = null;
	/** FTP用户附件存放路径 */
	public static String PATH_USER = null;
	/** FTP社交附件存放路径 */
	public static String PATH_SOCIAL = null;
	/** FTP商城广告附件存放路径 */
	public static String PATH_STORE_ADVERTISEMENT = null;
	/** FTP商城商品附件存放路径 */
	public static String PATH_STORE_GOODS = null;
	/** FTP产品网站（qimon）附件存放路径 */
	public static String PATH_STORE_PRODUCT = null;

	/**
	 * FTP聊天上传路径存放地址
	 */
	public static String PATH_SOC_TALK = null;

	/**
	 * FTP在线课堂课本封面路径存放地址
	 */
	public static String PATH_ONLINECOURSE_BOOK = null;

	/**
	 * FTP用户头像存放地址
	 */
	public static String PATH_USER_AVATAR = "/qimon/user/avatar/";
	/**
	 * FTP学校图片存放地址
	 */
	public static String PATH_SCHOOL_IMAGE = "/qimon/school/images/";

	/**
	 * FTP用户二维码存储地址
	 */
	public static String PATH_USER_QRCODE = "/qimon/user/qrcode/";
	/**
	 * 
	 */
	public static String PATH_CLASS_QRCODE = "/qimon/class/qrcode/";

	/**
	 * FTP家校在线上传路径存放地址
	 */
	public static String PATH_JXZX_IMG = "/qimon/jxzx/images/";

	/**
	 * 重新加载常量
	 * 
	 * @throws IOException
	 */
	public static void reloadConfig() throws Exception {
		FTP_IP = PropUtil.getPropValue("ftpconst", "ftp_ip");
		FTP_PORT = PropUtil.getPropValue("ftpconst", "ftp_port");
		FTP_USER = PropUtil.getPropValue("ftpconst", "ftp_user");
		FTP_PWD = PropUtil.getPropValue("ftpconst", "ftp_pwd");
		PATH_CHECKIMAGE = PropUtil.getPropValue("ftpconst", "check_image_path");
		PATH_USER = PropUtil.getPropValue("ftpconst", "user_path");
		PATH_SOCIAL = PropUtil.getPropValue("ftpconst", "social_path");
		PATH_STORE_ADVERTISEMENT = PropUtil.getPropValue("ftpconst",
				"advertisement_path");
		PATH_STORE_GOODS = PropUtil.getPropValue("ftpconst", "goods_path");
		PATH_STORE_PRODUCT = PropUtil.getPropValue("ftpconst", "product_path");
		PATH_SOC_TALK = PropUtil.getPropValue("ftpconst", "soctalk_path");
		PATH_ONLINECOURSE_BOOK = PropUtil.getPropValue("ftpconst",
				"onlinebook_path");

	}

}