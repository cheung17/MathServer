package core.listener;

import core.util.LogUtil;

/**
 * jdbc执行的sql语句监听
 *
 * @author chenweju
 *
 * @since Apr 24, 2015
 */
public class JdbcExecSQLListener {
	
	/** 执行的sql语句列表 */
	private StringBuffer execSql = new StringBuffer();

	/**
	 * 获取执行的sql
	 * 
	 * @return
	 */
	public String getExecSql() {
		LogUtil.debug(this.getClass(),"------catch sql:"+execSql.toString());
		return execSql.toString();
	}

	/**
	 * 追加执行的sql
	 * 
	 * @param sql
	 */
	public void appendExecSql(String sql) {
		execSql.append(sql).append(";");
	}

}
