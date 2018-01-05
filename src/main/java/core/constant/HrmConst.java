package core.constant;

/**
 * 用户信息常量字段类
 * 
 * @author lb
 */
public class HrmConst {
	/** 工作环境：true 测试调试环境 false 生产环境 */
	public static final boolean IS_DEBUG = true;
	public static final String RONG_YUN_APPSECRET = "SGWwL4AldowFrj";
	public static final String RONG_YUN_APPKEY = "p5tvi9dsp5ud4";
	public static final String FTP_IP = "http://cdn.qimonjy.cn:80/";
	//public static final String IP = "http://120.76.96.97:7777";
	
	/**
	 * 如果type是家长并Mobile不为null，则以短信带超链接的形式发送通知到家长手机，
	 * 家长点击超链接能看到学生的课前任务（增加时间：2017年9月14日11:48:52）
	 */
	public static final String WEIXIN_PARENT_WEB = "http://dbs.qimonjy.cn:9012/platform_intf/qimon/v2/parent/task.action?task_id=";
	/**
	 * aapt的目录
	 */
	public static final String AAPT = "E:/book/store/goods/";
	/**
	 * 批量导入学生用户数据时的激活vip之后赠送积分所需的module_coding值为launcher_center_vip_activation（尽量不改名字），
	 * 此字段值在数据库person_integral_dic表里，
	 * 日后改动此字段值，影响xxt网站批量导入时无法给所导入的学生赠送相应的激活积分。
	 */
	public static final String MODULE_CODING = "launcher_center_vip_activation";
	/**
	 * 默认的群头像
	 */
	public static final String DEFAULT_CLASS_GROUP_AVATAR = "";
	// public static final String DEFAULT_CLASS_AVATAR = "";
	/** 请求成功状态码 */
	public static final int REQUEST_SUCCESS = 1;

	/** 请求成功状态码 */
	public static final int REQUEST_FAILED = -1;

	/***/
	public static final String RESULT = "result";
	/** 合并文件 */
	public static final String CLASSROOM_mit = "e:/book/classroom/uploadfile";
	/** 下载文件url拼接路径（新定义） */
	public static final String CLASSROOM_PATH = "/classroom/uploadfile/";

	/** 学习日志图片上传路径 */
	public static final String STUDENT_LOG = "e:/classroom/uploadfile";
	/***/
	// private static final String PWD = "Qimon20150715";

	/********************* 通用 *****************************/
	/** 本地临时文件存放路径 */
	public static final String LOCAL_TEMP_FILE_PATH = "e:/test/";

	/** 本地临时文件存放路径 */
	public static final String LOCAL_TEMP_FILE_PATH_ONE = "e:/test/";

	/** app存放路径 */
	public static final String LOCAL_TEMP_FILE_PATH_ONE_APP = "e:/book/intf/APP";

	/** MUI请示参数 */
	public static final String PARAMS = "params";

	/** 平台使用资源 */
	public static final String RESOURCE_STATICS = "resourceStatistics";

	/********************* HrmUserinfo *****************************/
	/** 用户类型:学生 */
	public static final String REGISTER_NAME = "registername";

	/** 用户类型:学生 */
	public static final int USERTYPE_STUDENT = 0;

	/** 用户类型:家长 */
	public static final int USERTYPE_PARENT = 1;

	/** 用户类型:教师 */
	public static final int USERTYPE_TEACHER = 2;

	/** 用户类型空提示 */
	public static final String USERTYPE_NULL_TIP = "用户类型为空";

	/** 用户信息(前端参数) */
	public static final String REDISKEY = "redisKey";

	/***/
	public static final String REDISDATA = "redisData";

	/** 逻辑主键 */
	public static final String ID = "id";

	/** 逻辑主键 */
	public static final String USER_ID = "userId";

	/** 用户信息(前端参数) */
	public static final String USERINFO = "userInfo";

	/** 用户名 */
	public static final String USERNAME = "userName";

	/** 昵称 */
	public static final String NICKNAME = "nickName";

	/** 账号 */
	public static final String ACCOUNT = "account";

	/** 用户类型 学生 0 家长 1 教师 2 */
	public static final String USERTYPE = "userType";

	/** 用户密码 */
	public static final String PASSWORD = "loginPassword";

	/** 用户密码 */
	public static final String LOGIN_PASSWORD = "loginpassword";

	/** 新密码 */
	public static final String NEW_PASSWORD = "newPassword";

	/** 密码最大长度 */
	public static final int PASSWORD_MAX_LENGTH = 12;

	/** 密码最小长度 */
	public static final int PASSWORD_MIN_LENGTH = 4;

	/** 密码长度提示 */
	public static final String PASSWORD_TIP_ONE = "提示，请输入长度为6至12位的密码";

	/** 修改密码时:新密码为空时提示信息 */
	public static final String PASSWORD_TIP_TWO = "新密码不能为空！";

	/** 修改密码时:旧密码为空时提示信息 */
	public static final String PASSWORD_TIP_THREE = "旧密码不能为空！";

	/** 修改密码时:确认密码为空时提示信息 */
	public static final String PASSWORD_TIP_FOUR = "确认密码不能为空！";

	/** 修改密码时:确认密码为空时提示信息 */
	public static final String PASSWORD_TIP_FIVE = "新密码与确认密码不一致!";

	/** 修改密码时:确认密码为空时提示信息 */
	public static final String PASSWORD_TIP_SIX = "新密码不能与原始密码相同!";

	/** 修改密码时:确认密码为空时提示信息 */
	public static final String PASSWORD_TIP_SEVEN = "旧密码不正确!";

	/** 修改密码时:确认密码为空时提示信息 */
	public static final String PASSWORD_TIP_EIGHT = "密码修改成功!";

	/** 旧密码 */
	public static final String OLD_PASSWORD = "oldPassword";

	/** 确认密码 */
	public static final String CON_PASSWORD = "conPassword";

	/** 确认密码 */
	public static final String CONFIRM_PASSWORD = "confirmPassword";

	/***/
	public static final String CHANNEL_ID = "channelId";

	/** 手机号码 */
	public static final String MOBILE = "mobile";

	/** 邮箱 */
	public static final String EMAIL = "email";

	/** 城市Code */
	public static final String CITY_CODE = "cityCode";

	/** 前缀长度 */
	public static final int PREFIX_LENGTH = 36;

	/** 分隔 */
	public static final String BACK_SLASH = "/";

	/** 头像前缀 */
	public static final String AVATAR_PREFIX = "/book/";

	/** 教师头像前缀 */
	public static final String AVATAR_TEACHER = "E:/book/";
	
	/** 初始化头像路径 */
	public static final String INIT_AVATAR_PATH = "/user/avatar/default/";

	/** 初始化头像路径 */
	public static final String INIT_AVATAR_PATH_ONE = "/user/avatar/";

	/********************* HrmParent *****************************/
	/** 家长用户Id */
	public static final String PARENT_ID = "parentId";
	/** 家长姓名 */
	public static final String PARENT_NAME = "parName";
	/** 家长姓名 */
	public static final String PARENT_NAME_ONE = "parentName";
	/** 家长性别 */
	public static final String PARENT_SEX = "parSex";
	/** 家长性别编码 */
	public static final String PARENT_SEX_CODE = "parSexCode";
	/** 家长地址 */
	public static final String PARENT_ADDRESS = "parAddress";
	/** 解除孩子绑定成功提示 */
	public static final String UNBIND_SUCCESS = "解绑成功";

	/********************* HrmStudent *****************************/
	/** 学生用户Id */
	public static final String STUDENT_ID = "studentId";

	/** 学生Id为空时的提示 */
	public static final String STUDENT_ID_NULL_TIP = "studentId为空!";

	/** 学生用户Id */
	public static final String CHILD_ID = "childId";

	/** 学生绑定了一个或多个班级标识符 */
	public static final String ATTENDSCHOOL_YES = "1";

	/** 学生尚未绑定班级标识符 */
	public static final String ATTENDSCHOOL_NO = "0";

	/********************* HrmTeacher *****************************/
	/** 教师用户Id */
	public static final String TEACHER_ID = "teacherId";

	/** 教师Id为空时的提示 */
	public static final String TEACHER_ID_NULL_TIP = "teacherId为空!";

	/** 教师姓名 */
	public static final String NAME = "name";

	/** 关系绑定Id */
	public static final String BIND_ID = "bindid";

	/** 关系绑定成功提示 */
	public static final String BIND_SUCCESS_TIP = "加入成功!";

	/** 关系绑定失败提示 */
	public static final String BIND_FAILD_TIP = "绑定失败！";
	/** 此班级已绑定提示 */
	public static final String CLASS_BINDED_TIP = "对不起，你已经绑定了此班级！";

	/********************* HrmSchool *****************************/
	/** 学校Id */
	public static final String SCHOOL_ID = "schoolid";

	/** 学校Id为空时的提示 */
	public static final String SCHOOL_ID_NULL_TIP = "schoolId为空!";

	/** 学校Id */
	public static final String SCHOOL_ID_ONE = "schoolId";

	/** 学校级别 */
	public static final String SCHOOL_LEVEL = "schoolLevel";

	/** 幼儿园级别 */
	public static final String SCHOOL_LEVEL_YOUERYUAN = "youeryuan";

	/** 小学级别 */
	public static final String SCHOOL_LEVEL_XIAOXUE = "xiaoxue";

	/** 初中级别 */
	public static final String SCHOOL_LEVEL_CHUZHONG = "chuzhong";

	/** 高中级别 */
	public static final String SCHOOL_LEVEL_GAOZHONG = "gaozhong";

	/** 全国使用此平台的学校数 */
	public static final String COUNTRY_SCHOOL_COUNT = "countrySchoolCount";

	/***/
	public static final String COUNTRY_SCHOOL_CLASS_COUNT = "countrySchoolClassCount";

	/********************* HrmGrade *****************************/
	/** 年级Id */
	public static final String GRADE_ID = "gradeid";

	/********************* HrmClass *****************************/
	/** 班级识别码 */
	public static final String CLASS_NUMBER = "classnumber";

	/** 班级Id */
	public static final String CLASS_ID = "classId";

	/** 班级Id */
	public static final String CLASS_ID_ONE = "classid";

	/** 班级Id空提示 */
	public static final String CLASS_ID_NULL_TIP = "classId为空！";

	/** 班级解绑成功提示 */
	public static final String CLASS_UNBIND_SUCCESS = "班级解绑成功";

	/** 班级识别码空提示 */
	public static final String CLASSNUMBER_NULL_TIP = "classNumber为空!";

	/** 无班级识别码对应的班级时提示 */
	public static final String NO_CLASSNUMBER_CLASS_TIP = "你好，不存在与此班级编码对应的班级信息,请检查!";

	/** 全国使用此平台的学校数 */
	public static final String COUNTRY_CLASS_COUNT = "countryClassCount";

	/********************* SysSubject *****************************/
	/** 学科Id */
	public static final String SUBJECT_ID = "subjectid";

	/** 学科编码 */
	public static final String SUBJECT_CODE = "subjectcode";

	/** 学科名称 */
	public static final String SUBJECT_NAME = "subjectname";

	/** 学科级别 */
	public static final String SUBJECT_LEVEL = "subjectlevel";

	/** 学科编码码空提示 */
	public static final String SUBJECT_ID_NULL_TIP = "subjectId为空!";

	/********************* HrmGrade *****************************/
	/** 教师班级id 逻辑主键 */
	public static final String TEACHER_CLASS_ID = "teaclsid";

	/********************* 二维码 *****************************/
	/** 二维码size */
	public static final int QRCODE_SIZE = 600;

	/** 字节数组size */
	public static final int BYTE_SIZE = 1024;

	/** 二维码班级图片 */
	public static final String QRCODE_CLASS_FLAG_IMG = "";

	/********************* 登录 *****************************/
	/** 登录成功提示信息 */
	public static final String LOGIN_SUCCESS_TIP = "登录成功!";

	/** 用户名或密码错误提示 */
	public static final String USERNAME_PASSWORD_ERROR = "用户名或密码错误！";

	/** 验证码错误提示 */
	public static final String CHECKCODE_ERROR = "验证码错误！";

	/********************* 分布查询消息列表 *****************************/
	/** 默认每页显示的记录数 */
	public static final int DEFAULT_PAGE_SIZE = 10;

	/********************* 家校在线：用户角色 *****************************/
	/** 校长 */
	public static final String ROLE_PRINCIPAL = "4";
	/** 班主任 */
	public static final String ROLE_HEADTEACHER = "3";
	/** 老师 */
	public static final String ROLE_TEACHER = "2";
	/** 家长 */
	public static final String ROLE_PARENT = "1";
	/** 学生 */
	public static final String ROLE_STUDENT = "0";
	/** 家校在线，导出学生名单excel模板存放路径 */
	public static final String EXCEL_TEMP_PATH_STU_LT = "resource/student_list_temp.xls";
}
