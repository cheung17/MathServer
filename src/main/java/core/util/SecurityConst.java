package core.util;

/**
 * 安全管控常量类
 * 
 * @author cwj
 * 
 */
public class SecurityConst {
	/** 学生模块--消息提醒 */
	public static String STUDENT_MODEL_REMINDER = "com.qimon.remind.service.RemindModelService";
	/** 家长模块 */
	public static String MODEL_PARENT = "com.qimon.parent.service.KeepOpenService";
	/** 教师模块--消息提醒 */
	public static String TEACHER_MODEL_REMINDER = "com.qimon.schoolmessage.service.RemindModelService";
	/** 在线作业模块 */
	public static String MODEL_HOMEWORK = "com.qimon.homework.service.MessageReceiverService";
	/**教師作业模块 */
	public static String MODEL_TEACHER_HOMEWORK = "com.qimon.teacher.teacherhomework.push.HomeWorkPushService";
	/**學生作业模块 */
	public static String MODEL_STUDENT_HOMEWORK = "com.qimon.stuhomework.push.StuPushService";
	/**家长作业模块*/
	//public static String MODEL_PARENT_HOMEWORK = "com.qimon.parenthomework.push.ParentPushService";
	/** 管控模块 */
	public static String MODEL_SECURITY = "com.qimon.control.service.ControlModelService";
	/** 管控消息类别 */
	public static String TYPE_CONTROLL = "2";
	/** GPS管控消息代码 */
	public static String CODE_GPS = "500";
	/** 数据流量管控消息代码 */
	public static String CODE_DATA_FLOW = "701";
	/** 应用锁管控消息代码 */
	public static String CODE_APP_LOCK = "800";
	/** 锁屏管控消息代码 */
	public static String CODE_LOCK_SCREEN = "502";
	/** 应用卸载管控消息代码 */
	public static String CODE_APP_UN_INSTALL = "702";
	/** 获取设备信息管控消息代码 */
	public static String CODE_DEVICE_INFO = "703";
	/** 低点报警代码 */
	public static String CODE_LOW_POWER = "704";
	/** 定位服务开关代码 */
	public static String CODE_LOCATION = "802";
	/** 管控策略网络锁代码 */
	public static String CODE_INTERNET_LOCK = "803";
	/** 管控策略电话锁代码 */
	public static String CODE_CALL_LOCK = "804";

	// 立即开关管控代码
	/** 立即开关设备锁代码 */
	public static String NOW_DEVICE_LOCK = "1502";
	/** 立即开关应用锁代码 */
	public static String NOW_APP_LOCK = "1702";
	/** 立即开关游戏锁代码 *///（<目前没改动>届时v2版本的游戏锁将1800改成1804，电话锁弃用）
	public static String NOW_GAME_LOCK = "1800";
	/** 立即开关位置开关代码 */
	public static String NOW_LOCATION_LOCK = "1802";
	/** 立即开关网络锁代码 */
	public static String NOW_INTERNET_LOCK = "1803";
	/** 立即开关电话锁代码 */
	public static String NOW_CALL_LOCK = "1804";
	
	/** 班级锁代码 */
	public static String CODE_LOCK_CLASS = "1503";

	/** 应用锁IDS */
	public static String SECTYPE_APP_LOCK = "14";// primary,middle,senior
}
