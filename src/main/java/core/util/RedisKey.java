package core.util;

/**
 * Redis缓存key值
 * 
 * @author cwj
 * 
 */
public class RedisKey {
	/** 终端用户登录key */
	public static final String USER_LOGIN = "user_login_";

	public static final String USER_LOGIN_STUDENT = "user_login_student_";

	public static final String USER_LOGIN_TEACHER = "user_login_teacher_";

	public static final String USER_LOGIN_PARENT = "user_login_parent_";

	public static final String USER_LOGIN_MEARCHANT = "user_login_mearchant_";

	public static final String USER_LOGIN_AGENT = "user_login_agent_";

	public static final String USER_LOGIN_USERID = "user_login_userid_";

	/** 教育云RedisKey */
	public static final String EDUCATION_CLOUD_REDISKEY = "education_cloud_";

	/**
	 * 文章分页缓存
	 */
	public static final String BOOK_ARTICLE_LIST = "book_article_list_";

	/**
	 * 评论分页缓存 后面动态拼接对应的classCode来匹配不同表的评论
	 */
	public static final String BOOK_COMMENT_LIST = "book_comment_list_";

	/**
	 * 回复分页缓存 后面动态拼接对应的classCode来匹配不同表的回复
	 */
	public static final String COMMENT_REPLY_LIST = "comment_reply_list_";

	/**
	 * 课程分页缓存 后面动态拼接对应的classCode与筛选条件来匹配课程
	 */
	public static final String BOOK_COURSE_LIST = "book_course_list_";

	/**
	 * 课程分页缓存 后面动态拼接对应的classCode与筛选条件来匹配课程
	 */
	public static final String MY_BOOK_COURSE_LIST = "my_book_course_list_";

	/**
	 * 在线书库分类缓存
	 */
	public static final String BOOK_KIND_TEXT = "book_kind_text_";

	/**
	 * 老师微信端登录标识
	 */
	public static final String PARENT_LOGIN_WECHAT = "parent_login_wechat_";
}
