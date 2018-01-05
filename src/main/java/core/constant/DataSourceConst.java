package core.constant;

import core.util.PropUtil;
import core.util.Util;

/**
 * 数据源常量类 
 *
 * @author chenweju
 *
 * @since Apr 24, 2015
 */
public class DataSourceConst {
	/**写数据源*/
	public static final String JDBCTEMPLATEWRITE = "jdbcTemplateWrite";
	/**读数据源*/
	public static final String JDBCTEMPLATEREAD = "jdbcTemplateRead";
	/**历史库数据源*/
	public static final String JDBCTEMPLATEHISTORY = "jdbcTemplateHistory";
	
	/** 数据库类型-oracle */
	public static final int DATABASE_ORACLE = 0;
	/** 数据库类型-mysql */
	public static final int DATABASE_MYSQL = 1;
	/** 数据库类型-其他 */
	public static final int DATABASE_OTHER = -1;
	/** 数据库类型 */
	public static int DATABASE_TYPE = DATABASE_MYSQL;

	/**
	 * 重新加载配置
	 * 
	 * @throws Exception
	 */
	public static void reloadConfig() throws Exception {
		String driverClasses = PropUtil.getPropValueFromClasspath("jdbc",
				"DriverClasses");
//		System.out.println("------------------driverClasses"+driverClasses);
//		if (Util.isBlank(driverClasses)) {
//			DATABASE_TYPE = DATABASE_MYSQL;
//			System.out.println(1);
//		}
//		if (-1 != driverClasses.indexOf("oracle")) {
//			DATABASE_TYPE = DATABASE_ORACLE;
//		} else if (-1 != driverClasses.indexOf("mysql")) {
//			DATABASE_TYPE = DATABASE_MYSQL;
//		} else {
//			DATABASE_TYPE = DATABASE_OTHER;
//		}
	}
}
