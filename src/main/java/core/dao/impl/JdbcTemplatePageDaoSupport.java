package core.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import core.constant.DataSourceConst;
import core.page.SystemContext;
import core.vo.PagerVO;
import core.vo.SuperVO;

/**
 * 
 *
 * @author chenweju
 *
 * @since Apr 24, 2015
 */
public class JdbcTemplatePageDaoSupport extends JdbcTemplateDaoSupport {

	/**
	 * 构造方法
	 * 
	 * @param jdbcTemplate
	 */
	public JdbcTemplatePageDaoSupport(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate);
	}

	/**
	 * 构造方法
	 * 
	 * @param write_jdbcTemplate
	 * @param read_jdbcTemplate
	 */
	public JdbcTemplatePageDaoSupport(JdbcTemplate write_jdbcTemplate,
			JdbcTemplate read_jdbcTemplate) {
		super(write_jdbcTemplate, read_jdbcTemplate);
	}

	/**
	 * 内部函数-直接执行sql
	 * 
	 * @param sql
	 * @param args
	 * @param offset
	 * @param pagesize
	 * @return
	 * @throws Exception
	 */
	protected PagerVO queryByPage(Class<? extends SuperVO> clazz, String sql,
			Object[] args, final int offset, final int pagesize)
			throws Exception {
		PagerVO pv = new PagerVO();
		StringBuffer query_count_sql = new StringBuffer(
				"select count(1) from (").append(sql).append(") tcount");
		
		StringBuffer query_datas_sql = new StringBuffer();

		switch (DataSourceConst.DATABASE_TYPE) {
		case DataSourceConst.DATABASE_ORACLE:
			query_datas_sql
					.append("select * from (select t.*,rownum rwn from (")
					.append(sql).append(")t where rownum <=")
					.append(offset * pagesize).append(") where rwn>")
					.append((offset - 1) * pagesize);
			break;
		case DataSourceConst.DATABASE_MYSQL:
			query_datas_sql.append(sql).append(" limit ").append((offset - 1) * pagesize).append(",").append(pagesize);
			break;
		}
		
		pv.setTotal(this.queryForInt(query_count_sql.toString(), args));
		if (null != clazz) {
			pv.setDatas(this.query(clazz, query_datas_sql.toString(), args));
		} else {
			pv.setDatas(this.query(query_datas_sql.toString(), args));
		}
		return pv;
	}

	/**
	 * 内部函数-直接执行sql
	 * 
	 * @param clazz
	 * @param sql
	 * @param paramMap
	 *            参数名：参数值
	 * @param offset
	 * @param pagesize
	 * @return
	 * @throws Exception
	 */
	private PagerVO queryByPageByParamMap(Class<? extends SuperVO> clazz,
			String sql, HashMap<String, Object> paramMap, final int offset,
			final int pagesize) throws Exception {
		PagerVO pv = new PagerVO();
		StringBuffer query_count_sql = new StringBuffer(
				"select count(1) from (").append(sql).append(") total");
		StringBuffer query_datas_sql = new StringBuffer();

		switch (DataSourceConst.DATABASE_TYPE) {
		case DataSourceConst.DATABASE_ORACLE:
			query_datas_sql
					.append("select * from (select t.*,rownum rwn from (")
					.append(sql).append(")t where rownum <=")
					.append(offset * pagesize).append(") where rwn>")
					.append((offset - 1) * pagesize);
			break;
		case DataSourceConst.DATABASE_MYSQL:
			query_datas_sql.append(sql).append(" limit ").append((offset - 1) * pagesize).append(",").append(pagesize);
			break;
		}
		
		pv.setTotal(this.queryForIntByParamMap(query_count_sql.toString(),
				paramMap));
		if (null != clazz) {
			pv.setDatas(this.queryByParamMap(clazz, query_datas_sql.toString(),
					paramMap));
		} else {
			pv.setDatas(this.queryByParamMap(query_datas_sql.toString(),
					paramMap));
		}
		return pv;
	}

	/**
	 * 分页查询
	 * 
	 * @param clazz
	 * @param where_sql
	 * @param order_sql
	 * @param args
	 * @param offset
	 * @param pagesize
	 * @return
	 * @throws Exception
	 */
	private PagerVO queryByPage(SuperVO superVO,
			String join_sql, String where_sql, String order_sql, Object[] args,
			final int offset, final int pagesize) throws Exception {

		// 记录查询sql
		StringBuffer query_sql = this.generateSelectSql(superVO);

		if (null != join_sql && join_sql.trim().length() > 0) {
			query_sql.append(" ").append(join_sql);
		}

		if (null != where_sql && where_sql.trim().length() > 0) {
			query_sql.append(" where ").append(where_sql);
		}
		query_sql.append(" order by ");
		if (null != order_sql && order_sql.trim().length() > 0) {
			query_sql.append(order_sql);
		} else {
			query_sql.append(superVO.getPKFieldName() + " desc");
		}
		return queryByPage(superVO.getClass(), query_sql.toString(), args, offset, pagesize);
	}

	/**
	 * 分页查询
	 * 
	 * @param clazz
	 * @param where_sql
	 * @param order_sql
	 * @param args
	 * @param offset
	 * @param pagesize
	 * @return
	 * @throws Exception
	 */
	private PagerVO queryByPage(Class<? extends SuperVO> clazz,
			SuperVO where_vo, String order_sql, final int offset,
			final int pagesize) throws Exception {

		List<Object> args = new ArrayList<Object>();
		String join_sql = this.generateJoinSql(where_vo);
		String where_sql = this.generateQuerySql(where_vo, args);
		if(null==where_vo){
			where_vo=clazz.newInstance();
		}
		return queryByPage(where_vo,join_sql,where_sql, order_sql, args.toArray(), offset,
				pagesize);
	}

	/**
	 * 分页查询
	 * 
	 * @param clazz
	 * @param where_sql
	 * @param order_sql
	 * @param args
	 * @return
	 * @throws Exception
	 */
	public PagerVO queryByPage(Class<? extends SuperVO> clazz,String join_sql,
			String where_sql, String order_sql, Object[] args) throws Exception {
		return this.queryByPage(clazz.newInstance(),join_sql, where_sql, order_sql, args,
				SystemContext.getOffset(), SystemContext.getPagesize());
	}

	/**
	 * 分页查询
	 * 
	 * @param clazz
	 * @param where_vo
	 * @param order_sql
	 * @return
	 * @throws Exception
	 */
	public PagerVO queryByPage(Class<? extends SuperVO> clazz,
			SuperVO where_vo, String order_sql) throws Exception {
		return this.queryByPage(clazz, where_vo, order_sql,
				SystemContext.getOffset(), SystemContext.getPagesize());
	}

	/**
	 * 分页查询
	 * 
	 * @param sql
	 * @param args
	 * @return
	 * @throws Exception
	 */
	public PagerVO queryByPage(String sql, Object[] args) throws Exception {
		return this.queryByPage(null, sql, args, SystemContext.getOffset(),
				SystemContext.getPagesize());
	}

	/**
	 * 分页查询
	 * 
	 * @param sql
	 * @param args
	 * @return
	 * @throws Exception
	 */
	public PagerVO queryByPageParamMap(String sql,
			HashMap<String, Object> paramMap) throws Exception {
		return this.queryByPageByParamMap(null, sql, paramMap,
				SystemContext.getOffset(), SystemContext.getPagesize());
	}

	/**
	 * 分页查询
	 * 
	 * @param clazz
	 * @param sql
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public PagerVO queryByPageParamMap(Class<? extends SuperVO> clazz,
			String sql, HashMap<String, Object> paramMap) throws Exception {
		return this.queryByPageByParamMap(clazz, sql, paramMap,
				SystemContext.getOffset(), SystemContext.getPagesize());
	}

}
