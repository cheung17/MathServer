/**
 * 
 */
package core.constant;

/**
 * 所有数据库表自定义类型 都放在这里
 * 
 * @author Administrator
 * 
 */
public class DataBaseTableConstant {

  /** 好友请求 －－默认 **/
  public static final String SOC_ADD_FRIENDS_DEFAULT = "0";
  /** 好友请求 －－同意 **/
  public static final String SOC_ADD_FRIENDS_AGREE = "1";
  /** 好友请求 －－拒绝 **/
  public static final String SOC_ADD_FRIENDS_REFAUSE = "2";

  /**
   * 注册类型
   */
  public static final int REGISTRATION_TYPE_EMAIL = 0;// 邮箱注册
  public static final int REGISTRATION_TYPE_MOBILE = 1;// 手机注册
  public static final int REGISTRATION_TYPE_USERNAME = 2;// 用户名注册

  /**
   * 批量导入时的默认密码
   */
  public static final String REGISTE_DEFAULT_PASSWORD = "123456";

  /**
   * 正则表达式验证
   */
  // 用户名
  //public static final String REG_USERNAME = "^[0-9a-zA-Z\u4e00-\u9fa5]+$";
  public static final String REG_USERNAME = "/^[0-9a-zA-Z\u4e00-\u9fa5]{2,15}$/";
  // 邮箱
  public static final String REG_EMAIL = "";
  // 手机
  public static final String REG_MOBILE = "";
  // 身份证号码
  public static final String REG_IDNUMBER = "";

  /** 用户名空提示 */
  public static final String USERNAME_NULL_HINT = "用户名不能为空!";
  /** 用户名正则验证不成功时提示信息 */
 // public static final String USERNAME_NO_REGXP_HINT = "用户名须是数字、英文字母、下划线、汉字组合，且长度为4至15个字符!";
  public static final String USERNAME_NO_REGXP_HINT = "用户名须是数字、英文字母、汉字组合或者二至四个汉字组合，且长度为2至15个字符!";
  /** 注册密码为空提示信息 */
  public static final String PASSWORD_NULL_HINT = "密码不能为空！";
  /** 密码正则验证不成功时提示信息 */
  public static final String PASSWORD_NO_REGXP_HINT = "密码须以字母开头，长度在6~18之间，只能包含字母、数字和下划线!";
  /**
   * 用户信息修改
   */
  public static final String DEFAULT_INFO = "";// 用户名注册
  public static final String DEFAULT_SEX = "0";// 默认性别
  public static final String DEFAULT_FOLK = "";// 默认民族
  public static final String DEFAULT_POLICY = "";// 默认政治面貌
  public static final String DEFAULT_AGE = "0";// 默认年龄
  public static final String DEFAULT_TITLE = "";// 默认职称(教师)
  public static final String DEFAULT_BIRTHDAY = "";// 默认出生日期
  public static final String DEFAULT_STUDENT_AVATAR = "user/avatar/default/xuesheng.png";// 默认出生日期
  public static final String DEFAULT_PARENT_AVATAR = "user/avatar/default/jiazhang.png";// 默认出生日期
  public static final String DEFAULT_TEACHER_AVATAR = "user/avatar/default/jiaoshi.png";// 默认出生日期
  public static final String DEFAULT_IMG_SUFFIX = ".png";// 默认出生日期
  /**
   * 教师职务
   */
  public static final String HEADMASTER = "校长";
  public static final String HEADTEACHER = "班主任";
  /**
   * 用户类型
   */
  public static final int USERTYPE_STUDENT = 0;// 学生
  public static final int USERTYPE_PARENT = 1;// 家长
  public static final int USERTYPE_TEACHER = 2;// 教师
  public static final int USERTYPE_SCHOOL = 3;
  public static final int USERTYPE_TRAIN_UNIT = 4;
  public static final int USERTYPE_MERCHANT = 5;
  public static final int USERTYPE_AGENT = 6;
  public static final int USERTYPE_PARTNER = 6; // 合伙人
  public static final int USERTYPE_CUSTOM_SERVICE = 7; // 合伙人
  /**
   * 用户头像
   */
  public static final int DEAULT_WIDTH = 96;// 学生
  public static final int DEAULT_HEIGHT = 96;// 学生
  public static final String TEMP_OUTPUT_DIR = "e:/avatar/output/";
  /**
   * 学校类型、级别
   */
  // 公办学校
  public static final String PUBLIC_SCHOOL = "1f028f32b9ed4d2b9483977aa8464ffd";
  // 幼儿园
  public static final String KINDERGARTEN = "afa6bd4ed23642ca8eec90ed41fa3670";
  // 当前学年
  public static final int CURRENT_SCHOOLYEAR = 201707;
  /**
	 * 
	 */
  public static final int ADVERTISEMENT_STUDENT = 0;// 学生广告
  public static final int ADVERTISEMENT_PARENT = 1;// 学生广告
  public static final int ADVERTISEMENT_TEACHER = 2;// 学生广告
  /**
   * Cookie有关
   */
  public static final int COOKIE_MAX_AGE = 60 * 60 * 24 * 365;
  public static final String COOKIE_DOMAIN = "www.qimon.com";
  public static final String REDISKEY = "redisKey";
  /**
   * Session有关
   */
  public static final int SESSION_MAX_AGE = 60 * 60 * 24 * 365;

  /**
   * 用户状态
   */
  public static final String USERSTATE_ENABLE = "1";
  /**
   * Redis有关
   */
  public static final int REDIS_MAX_AGE = 60 * 60 * 24 * 365;

  /**
   * 分组相关
   */
  public static final String GROUPTYPE_SYS = "0"; // 系统生成的
  public static final String GROUPTYPE_DEFINE = "1"; // 自定义的
  public static final String GROUPTYPE_GROUP = "2"; // 群主
  public static final String GROUPTYPE_CUSTOM_SERVICE = "3"; // 客服
  public static final String GROUPTYPE_CLASS = "4"; // 班级
  public static final String GROUPTYPE_PARENT = "5"; // 家长 用于页面输出显示
  public static final String GROUPTYPE_MYCLASS_STUDENT = "6"; // 学生 我的同学 老师
  // 其它老师
  public static final String GROUPTYPE_MYCLASS_TEACHER = "6"; // 老师 其它老师
  public static final String GROUPTYPE_CHIDREN_STUDENT = "7"; // 家长 孩子 同学
  public static final String GROUPTYPE_CHIDREN_PRAENT = "8"; // 家长 孩子 同学家长

  /** 创建默认分组 －－－－家长－－－我的孩子 */
  public static final String SOC_GROUP_NAME_CHILDREN = "我的孩子";

  /** 创建默认分组 －－－－孩子－－－我的父母 */
  public static final String SOC_GROUP_NAME_PARENT = "父母";

  /** 创建默认分组 －－－－孩子－－－我的老师 */
  public static final String SOC_GROUP_NAME_TEACHER = "我的老师";

  /** 创建默认分组 －－－－公用－－－我的好友 */
  public static final String SOC_GROUP_NAME_FRIENDS = "我的好友";

  /** 创建默认分组 －－－－公用－－－客户 */
  public static final String SOC_GROUP_NAME_KEFU = "客户";
  /**
   * 会话分类
   */
  public static final String SOC_TALK_TYPE_ONE = "1"; // 单人会话
  public static final String SOC_TALK_TYPE_MORE = "2"; // 多人会话

  /** 分组成员表 */
  public static final String SOC_GROUP_MEMBER_STATUS_DEFAULT = "0"; // 不是好友
  public static final String SOC_GROUP_MEMBER_STATUS_FRIEND = "1"; // 好友

  /**
   * 商城
   */
  /*
   * public static final String GOODS_TYPE_EQUIPMENT = "2b9da7861b04431b9e41ae0e4027eee1"; //学生装备 public static final String GOODS_TYPE_APPLY = "1f8a07a19a2a42e49e8e522226dd7aed"; //应用分类 public static
   * final String GOODS_TYPE_COURSE = "0282c029ee614a2f867f27b15d5d308d"; //在线课程 public static final String GOODS_TYPE_HOMEWORK = "4cc3bcf023094f929e322a3df82201f3"; //在线作业
   */
  public static final String GOODS_TYPE_OLDAPPLY = "1f8a07a19a2a42e49e8e522226dd7aed"; // 应用分类

  public static final String TUTORING_SUBJECT = "teacher_tutoring_000_subject"; // 关联在线辅导科目
  public static final String GOODS_TYPE_EQUIPMENT = "1"; // 学生装备()
  public static final String GOODS_TYPE_APPLY = "2"; // 应用
  public static final String GOODS_TYPE_COURSE = "3"; // 在线课程
  public static final String GOODS_TYPE_HOMEWORK = "4cc3bcf023094f929e322a3df82201f3"; // 在线作业
  public static final String KING_UPLOAD_ERROR = "error";

  public static final int GOODS_TYPE_RESOURCE = 1; // 云资源
  public static final String RESOURCE_TYPR_YUN = "online_yunziyuan_000";
  public static final String RESOURCE_TYPR_YOUER = "online_yunziyuan_001"; // 云资源-幼儿
  public static final String RESOURCE_TYPR_XIAOXUE = "online_yunziyuan_002"; // 云资源-小学
  public static final String RESOURCE_TYPR_CHUZHONG = "online_yunziyuan_003"; // 云资源-中学
  public static final String RESOURCE_TYPR_GOAZHONG = "online_yunziyuan_004"; // 云资源-
  // 高中
  public static final String RESOURCE_TYPR_SUZHI = "online_yunziyuan_005"; // 云资源
  // -素质
  // 图片文档压缩前缀
  public static final String UPLOAD_PIC_PREFIX = "press_";
  public static final String RESREQ_CHARACTER_TYPE = "utf-8"; // 请求及响应的字符编码转换

  /**
   * 词典工具
   */
  public static final String KEY_CDGJ = "词典工具";
  public static final String CDGJ = "@study_app_007_001,@study_app_007_002,@study_app_007_013";
  /**
   * 听力口语
   */
  public static final String KEY_TLKY = "听力口语";
  public static final String TLKY = "@study_app_007_003,@study_app_007_004";
  /**
   * 视频学习
   */
  public static final String KEY_SPXX = "视频学习";
  public static final String SPXX = "@study_app_007_014";
  /**
   * 考试培训
   */
  public static final String KEY_KSPX = "考试培训";
  public static final String KSPX = "@study_app_007_006,@study_app_007_007";
  /**
   * 同步课程
   */
  public static final String KEY_TBKT = "同步课程";
  public static final String TBKT = "@study_app_001,@study_app_002,@study_app_003,@study_app_004";
  /**
   * 素质教育
   */
  public static final String KEY_SZJY = "素质教育";
  public static final String SZJY = "@study_app_006";
  /**
   * 综合
   */
  public static final String KEY_ZH = "综合";
  public static final String ZH = "@study_app_007";
  
  public static final String QINMON_APP_CLIENT = "com.qimon.teacher,com.qimon.parents,org.qimon.launcher6" ; 

//=====================返回全部应用，（返回数据：分类和包名,V2版本桌面需求而改的分类）==================

  /**
   * 语文
   */
  public static final String KEY_YW = "语文";
  public static final String YW = "@study_app_004_001,@study_app_003_001,@study_app_002_001";
  /**
   * 数学
   */
  public static final String KEY_SX = "数学";
  public static final String SX = "@study_app_004_002,@study_app_003_002,@study_app_002_002";
  /**
   * 英语
   */
  public static final String KEY_YY = "英语";
  public static final String YY = "@study_app_007_014,@study_app_001_006,online_yunziyuan_001_002,study_app_002_003,study_app_003_003,study_app_004_003";
  /**
   * 其他
   */
  public static final String KEY_QTYY = "其他";
  public static final String QTYY = "@study_app_006";

  /**
   * 综合
   */
  public static final String KEY_ZH1 = "综合";
  public static final String ZH1 = "@study_app_007_003,@study_app_007_004,@study_app_007_005,@study_app_007_006,@study_app_007_007,@study_app_007_008,"
		  						+ "@study_app_007_009,@study_app_007_010,@study_app_007_011,@study_app_007_012,@study_app_007_014,@study_app_007_000,"
	  							+ "@study_app_004_004,@study_app_004_005,@study_app_004_006,@study_app_004_007,@study_app_004_008,@study_app_004_009,@study_app_004_010"
	  							+ "@study_app_003_004,@study_app_003_005,@study_app_003_006,@study_app_003_007,@study_app_003_008,@study_app_003_009,@study_app_003_010,@study_app_002_004,@study_app_001";

  public static final String QINMON_APP_CLIENTV2 = "com.qimon.teacher,com.qimon.parents,org.qimon.launcher6" ; 
  
  //============================================================================分割线============================================================================================================================

  
  // input类型
  public static final String GOODS_INPUT_TEXT = "1";
  public static final String GOODS_INPUT_SELECT = "2";
  public static final String GOODS_INPUT_CHECKBOX = "3";
  // 公共
  public static final String GOODS_COMMON_ZERO = "0";
  public static final String GOODS_COMMON_ONE = "1";
  public static final String GOODS_COMMON_TWO = "2";
  public static final String COMMON_FLAG_OK = "ok";
  public static final int COMMON_NUMBER_ONE = 1;
  public static final int COMMON_NUMBER_TWO = 2;
  public static final int COMMON_NUMBER_THREE = 3;
  public static final String GOODS_COMMON_THREE = "3";
  public static final String FILE_SUFFIX_APP = "APP";
  public static final String FILE_SUFFIX_PIC = "PIC";

  public static final String COMPRESS_PIC_TEMP = "E:\\CompressPic\\";

  /**
   * 收藏点赞表类开型
   */
  /** 1 为点赞 2为收藏 */
  public static final String SOC_POINT_PRAISE_TYPE_POINT = "1";
  public static final String SOC_POINT_PRAISE_TYPE_PRAISE = "2";

  /**
   * 评论表类型
   */
  /** 评论类型 1 文字 2 图片 3 音频 */
  public static final String SOC_USER_COMMENT_TYPE_WORD = "1";
  public static final String SOC_USER_COMMENT_TYPE_PHOTO = "2";
  public static final String SOC_USER_COMMENT_TYPE_AUTO = "3";

  /** 消息提醒 */
  /** 提醒消息对方是否已收到 0未收到 1已收到 */
  public static final String MESSAGE_REMINDER_NO_RECEIVE = "0";
  public static final String MESSAGE_REMINDER_IS_RECEIVE = "1";

  /** 消息提醒是否查看 0已查看 1未查看 */
  public static final String MESSAGE_REMINDER_NO_READ = "1";
  public static final String MESSAGE_REMINDER_IS_READ = "0";

  /**
   * 表online_course_catalog表type类型
   */
  /** 1 目录 */
  public static final String CATALOG_TYPE_MULU = "1";
  /** 2 备课 */
  public static final String CATALOG_TYPE_BEIKE = "2";
  /** 3 讲解 */
  public static final String CATALOG_TYPE_JIANGJIE = "3";
  /** 4 练习 */
  public static final String CATALOG_TYPE_LIANXI = "4";

  /**
   * 书本类型
   */
  /** 0官方 1 非官方 */
  public static final String BOOK_TYPE_GUANFANG = "0";
  public static final String BOOK_TYPE__NOT_GUANFANG = "1";

  /**
   * 朋友圈名字及唯一id
   */
  public static final String FRIENDS_CIRCLE_PARENTS_NAME = "朋友圈";
  public static final String FRIENDS_CIRCLE_TEACHER_NAME = "朋友圈";
  public static final String FRIENDS_CIRCLE_CLASSMATE_NAME = "朋友圈";
  public static final String FRIENDS_CIRCLE_COMMON_ID = "f2f780df610f4297baa918edc4d920c5";
  public static final String FRIENDS_CIRCLE_PARENTS_ID = "f2f780df610f4297baa918edc4d922c";
  public static final String FRIENDS_CIRCLE_TEACHER_ID = "cdcbacf24a9e4d7db81259057909e097";
  public static final String FRIENDS_CIRCLE_CLASSMATE_ID = "4518e3b2f5144e56a7ca4d10e8b9be3d";
  /** 群聊天状态 0 已查看 1未查看 */
  public static final String SOC_GROUP_STATUS_READ = "0";
  public static final String SOC_GROUP_STATUS_UNREAD = "1";
  /** 群聊天状态 0 已收到 1未未收到 */
  public static final String BAIDU_PUSH_STATUS_READ = "0";
  public static final String BAIDU_PUSH_STATUS_UNREAD = "1";
  /** 消息接收模块名 */
  public static final String MSG_MODEL_RECEIVER_STUDENT = "com.qimon.message.service.MessageModelService";
  public static final String MSG_MODEL_RECEIVER_PARENT = "com.qimon.parent.service.KeepOpenService";
  public static final String MSG_MODEL_RECEIVER_TEACHER = "com.qimon.message.service.MessageModelService";
  public static final String SEND_TYPE_TALK = "1";// 聊天
  public static final String SEND_TYPE_CONTROL = "2";// 管控
  public static final String SEND_TYPE_REMINDER = "3";// 提醒
  public static final String MESSAGE_MODEL_TYPE_TALK = "0"; // 聊天消息
  public static final String MESSAGE_MODEL_TYPE_DELE_FRIEND = "1"; // 删除好友
  public static final String MESSAGE_TALK_TYPE_ONE = "1"; // 单人聊天
  public static final String MESSAGE_TALK_TYPE_MORE = "2"; // 朋友圈聊天
  public static final String MESSAGE_REMINDER_IS_DEL_YES = "1"; // 消息提醒已删除
  public static final String MESSAGE_REMINDER_IS_DEL_NO = "0"; // 消息提醒未删除
}
