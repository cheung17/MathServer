/**
 * 
 */
package core.constant;

/**
 * 针对广西客户数据库表自定义类型 都放在这里
 * 
 * @author Administrator
 * 
 */
public class GXDataBaseTableConstant {

  
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
  
  //=====================返回全部应用，（返回数据：分类和包名<按某一客户需求而改的分类,局部使用>）==================
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
   * 词典工具
   */
  public static final String KEY_CDGJ = "词典工具";
  public static final String CDGJ = "@study_app_007_001,@study_app_007_002,@study_app_007_013";

  /**
   * 综合
   */
  public static final String KEY_ZH = "综合";
  public static final String ZH = "@study_app_007_003,@study_app_007_004,@study_app_007_005,@study_app_007_006,@study_app_007_007,@study_app_007_008,"
		  						+ "@study_app_007_009,@study_app_007_010,@study_app_007_011,@study_app_007_012,@study_app_007_014,@study_app_007_000,"
	  							+ "@study_app_006,@study_app_004_004,@study_app_004_005,@study_app_004_006,@study_app_004_007,@study_app_004_008,@study_app_004_009,@study_app_004_010"
	  							+ "@study_app_003_004,@study_app_003_005,@study_app_003_006,@study_app_003_007,@study_app_003_008,@study_app_003_009,@study_app_003_010,@study_app_002_004,@study_app_001";
  
  //============================================================================分割线============================================================================================================================
  
  public static final String QINMON_APP_CLIENT = "com.qimon.teacher,com.qimon.parents,org.qimon.launcher6" ; 

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

}
