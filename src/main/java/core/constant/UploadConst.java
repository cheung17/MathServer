package core.constant;

import java.io.IOException;

import core.util.PropUtil;

/**
 * 上传参数设置
 * 
 * @author Administrator
 * 
 */
public class UploadConst {
    public static String UPLOAD_IP = "http://upload.qimonjy.cn:9081/platform_intf/";
    public static String UPLOAD_ROOTPATH = "E:/book";
    public static String UPLOAD_RESROOTPATH = "/classroom/uploadfile/";
    public static String UPLOAD_COMMENTPATH = "/netstudy/comments/";
    public static String UPLOAD_CLASSPATH = "/netstudy/class/";
    public static String UPLOAD_ARTICLEPATH = "/netstudy/article/";
    public static String UPLOAD_MAINPATH = "/netstudy/main/";
    public static String UPLOAD_PICPATH = "/intf/onlinebook/";
    
    /** Upload资源路径 */
	public static String UPLOADRESOURCE;
    /** 用户二维码存储地址 */
    public static String PATH_USER_QRCODE = "/user/qrcode/";
    /** 验证码存放路径 */
	public static String PATH_CHECKIMAGE = null;
	/** 用户附件存放路径 */
	public static String PATH_USER = null;
	/** 社交附件存放路径 */
	public static String PATH_SOCIAL = null;
	/** 商城广告附件存放路径 */
	public static String PATH_STORE_ADVERTISEMENT = null;
	/** 商城商品附件存放路径 */
	//public static String PATH_STORE_GOODS = null;
	public static String PATH_STORE_GOODS = "/store/goods/";
	/** 产品网站（qimon）附件存放路径 */
	public static String PATH_STORE_PRODUCT = null;
	/** 聊天上传路径存放地址 */
	public static String PATH_SOC_TALK = null;
	/** 用户头像存放地址 */
	public static String PATH_USER_AVATAR = "/user/avatar/";
	/** 学校图片存放地址 */
	public static String PATH_SCHOOL_IMAGE = "/school/images/";
	/** 创建班级时生成的二维码 */
	public static String PATH_CLASS_QRCODE = "/class/qrcode/";
	/** 家校在线上传路径存放地址 */
	public static String PATH_JXZX_IMG = "/jxzx/images/";
	
	
	/**
     * 重新加载常量
     * 
     * @throws IOException
     */
    public static void reloadConfig() throws Exception {
	UPLOAD_IP = PropUtil.getPropValue("uploadconst", "upload_ip");
	UPLOAD_ROOTPATH = PropUtil.getPropValue("uploadconst",
		"upload_rootpath");
	UPLOAD_RESROOTPATH = PropUtil.getPropValue("uploadconst",
		"upload_resrootpath");
	UPLOAD_COMMENTPATH = PropUtil.getPropValue("uploadconst",
		"upload_commentpath");
	UPLOAD_CLASSPATH = PropUtil.getPropValue("uploadconst",
		"upload_classpath");
	UPLOAD_ARTICLEPATH = PropUtil.getPropValue("uploadconst",
		"upload_articlepath");
	UPLOAD_PICPATH = PropUtil.getPropValue("uploadconst", "upload_picpath");
    
	PATH_CHECKIMAGE = PropUtil.getPropValue("uploadconst", "check_image_path");
	PATH_USER = PropUtil.getPropValue("uploadconst", "user_path");
	PATH_SOCIAL = PropUtil.getPropValue("uploadconst", "social_path");
	PATH_STORE_ADVERTISEMENT = PropUtil.getPropValue("uploadconst",
			"advertisement_path");
	PATH_STORE_GOODS = PropUtil.getPropValue("uploadconst", "goods_path");
	PATH_STORE_PRODUCT = PropUtil.getPropValue("uploadconst", "product_path");
	PATH_SOC_TALK = PropUtil.getPropValue("uploadconst", "soctalk_path");
	UPLOADRESOURCE = PropUtil.getPropValue("uploadconst", "uploadresource");
	PATH_USER_AVATAR = PropUtil.getPropValue("uploadconst", "user_avatar_path");
	PATH_SCHOOL_IMAGE = PropUtil.getPropValue("uploadconst", "school_image_path");
	PATH_CLASS_QRCODE = PropUtil.getPropValue("uploadconst", "class_qrcode_path");

    }

    /**
     * 获取资源的路径
     * 
     * @return
     */
    public static String getResFilePath() {
	return UPLOAD_ROOTPATH + UPLOAD_RESROOTPATH;
    }

    /**
     * 获取评论文件的路径
     * 
     * @return
     */
    public static String getCommentFilePath() {
	return UPLOAD_ROOTPATH + UPLOAD_COMMENTPATH;
    }

    /**
     * 获取课程文件的路径
     * 
     * @return
     */
    public static String getClassFilePath() {
	return UPLOAD_ROOTPATH + UPLOAD_CLASSPATH;
    }

    /**
     * 获取文章文件的路径
     * 
     * @return
     */
    public static String getArticleFilePath() {
	return UPLOAD_ROOTPATH + UPLOAD_ARTICLEPATH;
    }

    /**
     * 获取MAIN的路径
     * 
     * @return
     */
    public static String getMainFilePath() {
	return UPLOAD_ROOTPATH + UPLOAD_MAINPATH;
    }

    public static String getPicFilePath() {
	return UPLOAD_ROOTPATH + UPLOAD_PICPATH;
    }

    public static String getNetPicPath() {
	return UPLOAD_PICPATH;
    }
    //商城apk地址
    public static String getApkpath(){
		return UPLOAD_ROOTPATH + PATH_STORE_GOODS;
    }
    //用户头像地址
    public static String getUserAvatarpath(){
    	return UPLOAD_ROOTPATH + PATH_USER_AVATAR;
    }
    //创建班级时生成的二维码
    public static String getClassQrcodepath(){
    	return UPLOAD_ROOTPATH + PATH_CLASS_QRCODE;
    }
    //班级图片存放地址
    public static String schoolImagePath(){
    	return UPLOAD_ROOTPATH + PATH_SCHOOL_IMAGE;
    }
}