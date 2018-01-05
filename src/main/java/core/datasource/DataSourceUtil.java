package core.datasource;

import org.springframework.jdbc.core.JdbcTemplate;

import core.util.BeanFactory;
import core.util.PropUtil;

/**
 * 数据源datasource.properties文件操作类
 *
 * @author chenweju
 *
 * @since Apr 24, 2015
 */
public class DataSourceUtil {
	/**
	 * 获取配置文件中的jdbctemplate
	 * 
	 * @param datasourceCode
	 * @param propName
	 * @return
	 * @throws Exception
	 */
	public static JdbcTemplate getJdbcTemplate(String datasourceCode,
			String propName) throws Exception {
		String jdbcTemplateName = getJdbcTemplateName(datasourceCode, propName);
		JdbcTemplate jdbcTemplate = null;
		if (null != jdbcTemplateName) {
			jdbcTemplate = BeanFactory.getJdbcTemplate(jdbcTemplateName);
		}
		return jdbcTemplate;
	}

	/**
	 * 获取jdbctemplate
	 * 
	 * @param datasourceCode
	 * @param propName
	 * @return
	 * @throws Exception
	 */
	public static String getJdbcTemplateName(String datasourceCode,
			String propName) throws Exception {
		String jdbcTemplateName = null;
		String datasource_name = getValueByName(propName);
		if (null != datasource_name) {
			String[] ecology_all_names = datasource_name.split("\\|");
			String[] names;
			for (String all_names : ecology_all_names) {
				names = all_names.split("_");
				if (null != names && names.length == 2) {
					if (datasourceCode.equals(names[0])) {
						jdbcTemplateName = names[1];
					}
				}
			}
		}
		return jdbcTemplateName;
	}

	/**
	 * 根据名称获取属性值
	 * 
	 * @param propName
	 * @return
	 * @throws Exception
	 */
	public static String getValueByName(String propName) throws Exception {
		return PropUtil.getPropValueFromClasspath("jdbc", propName);
	}
}
