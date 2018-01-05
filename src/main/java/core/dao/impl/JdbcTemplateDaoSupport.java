package core.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import core.listener.JdbcExecSQLListener;
import core.util.LogUtil;
import core.util.Util;
import core.vo.SuperVO;

/**
 * dao基类
 * 
 * @author chenweju
 * 
 * @since Apr 24, 2015
 */
public abstract class JdbcTemplateDaoSupport {
	/** 写操作jdbc */
	private JdbcTemplate write_jdbcTemplate;
	/** 读操作jdbc */
	private JdbcTemplate read_jdbcTemplate;
	/** 写操作name赋值方式jdbc */
	private NamedParameterJdbcTemplate write_namedParameterJdbcTemplate;
	/** 读操作name赋值方式jdbc */
	private NamedParameterJdbcTemplate read_namedParameterJdbcTemplate;
	/** sql监听线程变量 */
	protected static ThreadLocal<JdbcExecSQLListener> sqlListenerThread = new ThreadLocal<JdbcExecSQLListener>();

	/** 获取sql语句监听 */
	protected JdbcExecSQLListener getSqlListenerThread() {
		return sqlListenerThread.get();
	}

	/** 设置sql语句监听 */
	protected void setSqlListenerThread(
			JdbcExecSQLListener jdbcExecSQLListener) {
		sqlListenerThread.set(jdbcExecSQLListener);
	}

	/** 清除sql语句监听 */
	protected void removeSqlListenerThread() {
		sqlListenerThread.remove();
	}

	/**
	 * 追加执行的sql
	 * 
	 * @param sql
	 */
	protected void appendExecSql(String sql, Object[] args) {
		try {
			JdbcExecSQLListener jdbcExecSQLListener = getSqlListenerThread();
			if (null != jdbcExecSQLListener) {
				if (null != args && args.length > 0) {
					Pattern p = Pattern.compile("\\?");
					Matcher m = p.matcher(sql);
					StringBuffer sb = new StringBuffer();
					int i = 0;
					while (m.find()) {
						m.appendReplacement(
								sb, "'"
										+ Util.null2String(args[i++])
												.replaceAll("\\$", "\\\\\\$")
										+ "'");
					}
					m.appendTail(sb);
					sql = sb.toString();
				}
				jdbcExecSQLListener.appendExecSql(sql);
			}
		} catch (Exception e) {
			LogUtil.error(this.getClass(), e);
		}
	}

	/**
	 * 追加执行的sql
	 * 
	 * @param sql
	 */
	protected void appendExecSql(String sql, Map<String, Object> paramMap) {
		JdbcExecSQLListener jdbcExecSQLListener = getSqlListenerThread();
		if (null != jdbcExecSQLListener) {
			if (null != paramMap && paramMap.size() > 0) {
				for (String key : paramMap.keySet()) {
					sql = sql.replaceAll(key, "'" + paramMap.get(key) + "'");
				}
			}
			jdbcExecSQLListener.appendExecSql(sql);
		}
	}

	protected JdbcTemplateDaoSupport(JdbcTemplate jdbcTemplate) {
		this(jdbcTemplate, jdbcTemplate);
	}

	protected JdbcTemplateDaoSupport(JdbcTemplate write_jdbcTemplate,
			JdbcTemplate read_jdbcTemplate) {
		this.write_jdbcTemplate = write_jdbcTemplate;
		this.read_jdbcTemplate = read_jdbcTemplate;
		write_namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
				write_jdbcTemplate.getDataSource());
		read_namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
				read_jdbcTemplate.getDataSource());
	}

	/**
	 * 内部函数
	 * 
	 * @param sql
	 * @param args
	 * @return
	 */
	protected Integer executeUpdate(String sql, Object[] args)
			throws Exception {
		appendExecSql(sql, args);
		return write_jdbcTemplate.update(sql, args);
	}

	/**
	 * 执行插入返回主键
	 * 
	 * @param sql
	 * @param sv
	 * @return
	 * @throws Exception
	 */
	protected String executeInsert(String sql, SuperVO sv) throws Exception {
		if (null != sv.getPKFieldName()) {
			appendExecSql(sql, new Object[]{});
			KeyHolder holder = new GeneratedKeyHolder();
			write_namedParameterJdbcTemplate.update(sql,
					new BeanPropertySqlParameterSource(sv), holder,
					new String[]{sv.getPKFieldName()});
			return (String) sv.getAttributeValue(sv.getPKFieldName());
		} else {
			throw new Exception("Id is null !");
		}
	}

	/**
	 * 内部函数
	 * 
	 * @param sql
	 * @param args
	 * @return
	 */
	protected Integer executeUpdateByParamMap(String sql,
			Map<String, Object> paramMap) throws Exception {
		appendExecSql(sql, paramMap);
		return this.write_namedParameterJdbcTemplate.update(sql, paramMap);
	}

	/**
	 * 查询一条map数据，数据不存在时返回null
	 * 
	 * @param sql
	 * @param args
	 * @return
	 * @throws Exception
	 */
	protected Map<String, Object> queryForMap(String sql, Object[] args)
			throws Exception {
		try {
			return read_jdbcTemplate.queryForMap(sql, args);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	/**
	 * 查询一条map数据，数据不存在时返回null
	 * 
	 * @param sql
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	protected Map<String, Object> queryForMapByParamMap(String sql,
			Map<String, Object> paramMap) throws Exception {
		try {
			return this.read_namedParameterJdbcTemplate.queryForMap(sql,
					paramMap);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	/**
	 * 内部函数
	 * 
	 * @param sql
	 * @param clazz
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({"unchecked", "rawtypes"})
	protected <E extends SuperVO> SuperVO queryForObject(String sql,
			Class<E> clazz, Object... args) throws Exception {
		try {
			return (SuperVO) read_jdbcTemplate.queryForObject(sql,
					new BeanPropertyRowMapper(clazz), args);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	/**
	 * 执行sql查询返回list，返回实体类列表
	 * 
	 * @param clazz
	 * @param sql
	 * @param args
	 * @return
	 */
	@SuppressWarnings({"unchecked", "rawtypes"})
	protected <E extends SuperVO> List<E> queryForList(Class<E> clazz,
			String sql, Object[] args) throws Exception {
		if (null == args || args.length == 0) {
			return (List<E>) read_jdbcTemplate.query(sql,
					new BeanPropertyRowMapper(clazz));
		}
		return (List<E>) read_jdbcTemplate.query(sql, args,
				new BeanPropertyRowMapper(clazz));
	}

	/**
	 * 执行sql查询返回list，参数为map类型
	 * 
	 * @param sql
	 * @param paramMap
	 *            参数名，参数值
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	protected <E extends SuperVO> List<E> queryForListByParamMap(String sql,
			HashMap<String, Object> paramMap, Class<E> clazz) throws Exception {
		return read_namedParameterJdbcTemplate.query(sql, paramMap,
				BeanPropertyRowMapper.newInstance(clazz));
	}

	/**
	 * 内部函数
	 * 
	 * @param sql
	 * @param args
	 * @return
	 */
	protected List<Map<String, Object>> queryForListByParamMap(String sql,
			HashMap<String, Object> paramMap) throws Exception {
		return read_namedParameterJdbcTemplate.queryForList(sql, paramMap);
	}

	/**
	 * 内部函数
	 * 
	 * @param sql
	 * @param args
	 * @return
	 */
	private List<Map<String, Object>> queryForList(String sql, Object[] args)
			throws Exception {
		if (null == args || args.length == 0) {
			return read_jdbcTemplate.queryForList(sql);
		}
		return read_jdbcTemplate.queryForList(sql, args);
	}

	/**
	 * 执行sql返回int类型
	 * 
	 * @param sql
	 * @param paramMap
	 *            参数名：参数值
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	protected int queryForIntByParamMap(String sql, HashMap<String, ?> paramMap)
			throws Exception {
		return this.read_namedParameterJdbcTemplate.queryForInt(sql, paramMap);
	}

	/**
	 * 执行sql返回int类型
	 * 
	 * @param sql
	 * @param args
	 * @return
	 */
	@SuppressWarnings("deprecation")
	protected int queryForInt(String sql, Object[] args) throws Exception {
		try {
			if (null == args || args.length == 0) {
				return read_jdbcTemplate.queryForInt(sql);
			}
			return read_jdbcTemplate.queryForInt(sql, args);
		} catch (EmptyResultDataAccessException e) {
			return 0;
		}
	}

	/**
	 * 执行sql返回list,sql语句中只能返回一个值
	 * 
	 * @param elementType
	 *            返回list的泛型
	 * @param sql
	 *            sql语句
	 * @param paramMap
	 *            参数map
	 * @return
	 * @throws Exception
	 */
	protected <E> List<E> queryForListByParamMap(Class<E> elementType,
			String sql, HashMap<String, ?> paramMap) throws Exception {
		return read_namedParameterJdbcTemplate.queryForList(sql, paramMap,
				elementType);
	}

	/**
	 * 执行sql返回list,sql语句中只能返回一个值
	 * 
	 * @param sql
	 *            sql语句
	 * @param args
	 *            参数数组
	 * @param elementType
	 *            返回list的泛型
	 * @return
	 * @throws Exception
	 */
	protected <E> List<E> queryForList(String sql, Object[] args,
			Class<E> elementType) throws Exception {
		if (null == args || args.length == 0) {
			return read_jdbcTemplate.queryForList(sql, elementType);
		}
		return read_jdbcTemplate.queryForList(sql, args, elementType);
	}

	/**
	 * 查询记录数
	 */
	protected int queryCount(SuperVO vo) throws Exception {
		if (null != vo) {
			List<Object> args = new ArrayList<Object>();
			StringBuffer query_sql = new StringBuffer("select count(1) from ")
					.append(vo.getTableName());
			String where_sql = this.generateQuerySql(vo, args, "=");
			if (null != where_sql && where_sql.trim().length() > 0) {
				query_sql.append(" where ").append(where_sql);
			}

			return this.queryForInt(query_sql.toString(), args.toArray());

		} else {
			return 0;
		}

	}

	/**
	 * 执行sql返回对象
	 * 
	 * @param sql
	 * @param requiredType
	 * @param args
	 * @return
	 * @throws Exception
	 */
	protected <O> O queryObjectBySQL(String sql, Class<O> requiredType,
			Object... args) throws Exception {
		try {
			return this.read_jdbcTemplate.queryForObject(sql, requiredType,
					args);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	/**
	 * 根据vo产生where查询条件与参数数组
	 * 
	 * @param where_vo
	 * @param args
	 * @return
	 * @throws Exception
	 */
	protected String generateQuerySql(SuperVO where_vo, List<Object> args)
			throws Exception {
		return this.generateQuerySql(where_vo, args, "like");
	}

	/**
	 * 根据vo产生where查询条件与参数数组
	 * 
	 * @param where_vo
	 * @param args
	 * @param operator
	 * @return
	 * @throws Exception
	 */
	protected String generateQuerySql(SuperVO where_vo, List<Object> args,
			String operator) throws Exception {
		StringBuffer where_sql = new StringBuffer();
		if (null != where_vo) {
			List<String> field_list = where_vo.getAttributeNames();
			for (String field : field_list) {
				Object field_value = where_vo.getAttributeValue(field);
				if (null != field_value
						&& field_value.toString().trim().length() > 0) {
					if (where_sql.length() > 0) {
						where_sql.append(" and ");
					}
					if ("like".equalsIgnoreCase(operator)) {
						if (field_value instanceof String) {
							where_sql.append(field).append(" ").append(operator)
									.append(" ? ");
							args.add("%" + field_value + "%");
						} else {
							where_sql.append(field).append("=").append(" ? ");
							args.add(field_value);
						}
					} else {
						where_sql.append(field).append(" ").append(operator)
								.append(" ? ");
						args.add(field_value);
					}
				}
			}
			if (null != where_vo.getAdditionalWhereSQL()
					&& where_vo.getAdditionalWhereSQL().trim().length() > 0) {
				if (where_sql.length() > 1) {
					where_sql.append(" and ")
							.append(where_vo.getAdditionalWhereSQL());
				} else {
					where_sql.append(" ")
							.append(where_vo.getAdditionalWhereSQL());
				}
				if (null != where_vo.getAdditionalWhereParams()
						&& where_vo.getAdditionalWhereParams().size() > 0) {
					args.addAll(where_vo.getAdditionalWhereParams());
				}
			}

		}
		return where_sql.toString();
	}

	/**
	 * 返回对象的join语句
	 * 
	 * @param where_vo
	 * @return
	 */
	protected String generateJoinSql(SuperVO where_vo) {
		if (null != where_vo) {
			return where_vo.getJoinSQL();
		}
		return null;
	}

	/**
	 * 根据class获取查询select部分sql语句
	 * 
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	protected StringBuffer generateSelectSql(SuperVO superVO) throws Exception {
		StringBuffer sql = new StringBuffer(" select ")
				.append(superVO.getAllAttributeNamesByStr()).append(" from ")
				.append(superVO.getTableName()).append(" ")
				.append(superVO.getBYNAME()).append(" ");
		return sql;
	}

	/**
	 * 
	 * @title generateSelectSql
	 * @description 生成查询sql
	 * @param superVO
	 * @param unSelectColumn
	 * @return StringBuffer
	 * @throws Exception
	 */
	protected StringBuffer generateSelectSql(SuperVO superVO,
			String... unSelectColumn) throws Exception {
		StringBuffer sql = new StringBuffer(" select ")
				.append(superVO.getAllAttributeNamesByStr(unSelectColumn))
				.append(" from ").append(superVO.getTableName()).append(" ")
				.append(superVO.getBYNAME()).append(" ");
		return sql;
	}

	/**
	 * 根据class，查询vo，排序sql查询vo列表
	 */
	protected <E extends SuperVO> List<E> query(Class<E> clazz,
			SuperVO where_vo, String order_sql) throws Exception {
		List<Object> args = new ArrayList<Object>();
		StringBuffer query_sql = this.generateSelectSql(where_vo);
		String join_sql = generateJoinSql(where_vo);
		String where_sql = this.generateQuerySql(where_vo, args);
		if (null != join_sql && join_sql.trim().length() > 0) {
			query_sql.append(" ").append(join_sql);
		}
		if (null != where_sql && where_sql.trim().length() > 0) {
			query_sql.append(" where ").append(where_sql);
		}
		if (null != order_sql && order_sql.trim().length() > 0) {
			query_sql.append(" order by ").append(order_sql);
		}
		return this.queryForList(clazz, query_sql.toString(), args.toArray());
	}

	/**
	 * 查询返回list
	 * 
	 * @param clazz
	 * @param where_sql
	 * @param order_sql
	 * @param args
	 * @return
	 * @throws Exception
	 */
	protected <E extends SuperVO> List<E> query(Class<E> clazz,
			String where_sql, String order_sql, Object[] args)
			throws Exception {
		SuperVO superVO = clazz.newInstance();
		StringBuffer query_sql = new StringBuffer(generateSelectSql(superVO));
		if (null != where_sql && where_sql.trim().length() > 0) {
			query_sql.append(" where ").append(where_sql);
		}
		if (null != order_sql && order_sql.trim().length() > 0) {
			query_sql.append(" order by ").append(order_sql);
		}
		return queryForList(clazz, query_sql.toString(), args);

	}

	/**
	 * 执行sql查询返回list
	 * 
	 * @param clazz
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	protected <E extends SuperVO> List<E> query(Class<E> clazz, String sql)
			throws Exception {
		return queryForList(clazz, sql, null);
	}

	/**
	 * 执行sql查询，待参数数组，返回list
	 * 
	 * @param clazz
	 * @param sql
	 * @param args
	 * @return
	 * @throws Exception
	 */
	protected <E extends SuperVO> List<E> query(Class<E> clazz, String sql,
			Object[] args) throws Exception {
		return queryForList(clazz, sql, args);
	}

	/**
	 * 执行sql查询，返回list
	 * 
	 * @param clazz
	 * @param sql
	 * @param paramMap
	 *            参数名：参数值
	 * @return
	 * @throws Exception
	 */
	protected <E extends SuperVO> List<E> queryByParamMap(Class<E> clazz,
			String sql, HashMap<String, Object> paramMap) throws Exception {
		return queryForListByParamMap(sql, paramMap, clazz);
	}

	/**
	 * 执行sql查询，返回list+map
	 * 
	 * @param sql
	 * @param args
	 * @return
	 * @throws Exception
	 */
	protected List<Map<String, Object>> query(String sql, Object[] args)
			throws Exception {
		return this.queryForList(sql, args);
	}

	/**
	 * 
	 * @param sql
	 * @param paramMap
	 *            参数名：参数值
	 * @return
	 * @throws Exception
	 */
	protected List<Map<String, Object>> queryByParamMap(String sql,
			HashMap<String, Object> paramMap) throws Exception {
		return this.queryForListByParamMap(sql, paramMap);
	}

	/**
	 * 根据id查询vo对象
	 * 
	 * @param clazz
	 * @param id
	 * @return
	 * @throws Exception
	 */
	protected <E extends SuperVO> SuperVO get(Class<E> clazz, Object id)
			throws Exception {
		SuperVO superVO = clazz.newInstance();
		StringBuffer get_sql = new StringBuffer(generateSelectSql(superVO))
				.append(" where ").append(superVO.getPKFieldName())
				.append("=?");
		return queryForObject(get_sql.toString(), clazz, id);
	}

	/**
	 * 
	 * @title get
	 * @description 根据id查询vo
	 * @param clazz
	 * @param id
	 * @param unSelectColumn
	 *            不查询的列
	 * @return
	 * @throws Exception
	 *             SuperVO
	 */
	protected <E extends SuperVO> SuperVO get(Class<E> clazz, Object id,
			String... unSelectColumn) throws Exception {
		SuperVO superVO = clazz.newInstance();
		StringBuffer get_sql = new StringBuffer(
				generateSelectSql(superVO, unSelectColumn)).append(" where ")
						.append(superVO.getPKFieldName()).append("=?");

		return queryForObject(get_sql.toString(), clazz, id);
	}

	/**
	 * 
	 * @title get
	 * @description 根据id查询vo自定义where
	 * @param where_clause
	 * @param id
	 * @param clazz
	 * @param unSelectColumn
	 * @return
	 * @throws Exception
	 */
	protected <E extends SuperVO> SuperVO get(String where_clause, Object id,
			Class<E> clazz, String... unSelectColumn) throws Exception {
		SuperVO superVO = clazz.newInstance();
		StringBuffer get_sql = new StringBuffer(
				generateSelectSql(superVO, unSelectColumn)).append(" where ")
						.append(superVO.getPKFieldName()).append("=? ")
						.append(where_clause);
		return queryForObject(get_sql.toString(), clazz, id);
	}

	/**
	 * 根据vo构造条件查询vo对象
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	protected SuperVO get(SuperVO vo) throws Exception {
		return this.get(vo, null);
	}

	/**
	 * 根据vo构造条件，排序条件查询vo对象
	 * 
	 * @param vo
	 * @param orderby
	 * @return
	 * @throws Exception
	 */
	protected SuperVO get(SuperVO vo, String orderby) throws Exception {
		List<Object> args = new ArrayList<Object>();
		StringBuffer query_sql = this.generateSelectSql(vo);
		String where_sql = this.generateQuerySql(vo, args, "=");
		if (null != where_sql && where_sql.trim().length() > 0) {
			query_sql.append(" where ").append(where_sql);
		}
		if (null != orderby && orderby.trim().length() > 0) {
			query_sql.append(" order by  ").append(orderby);
		}

		List<?> list = this.queryForList(vo.getClass(), query_sql.toString(),
				args.toArray());
		if (null != list && list.size() > 0) {
			return (SuperVO) list.get(0);
		}
		return null;
	}

	/**
	 * 新增对象
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	protected String insert(SuperVO vo) throws Exception {
		StringBuffer insert_sql = new StringBuffer(" insert into ");
		StringBuffer insert_value_sql = new StringBuffer(" values (");

		insert_sql.append("`").append(vo.getTableName()).append("`")
				.append("(");
		String id = UUID.randomUUID().toString().replace("-", "");
		if (null == vo.getAttributeValue(vo.getPKFieldName())) {
			vo.setAttributeValue(vo.getPKFieldName(), id);
		} else {
			id = (String) vo.getAttributeValue(vo.getPKFieldName());
		}
		List<String> field_list = vo.getAttributeNames();

		String split = "";
		for (String fieldName : field_list) {
			if (null != vo.getAttributeValue(fieldName)) {
				insert_sql.append(split).append("`").append(fieldName)
						.append("`");
				insert_value_sql.append(split).append(":").append(fieldName);
				split = ",";
			}
		}
		insert_sql.append(")");
		insert_value_sql.append(")");
		String sql = insert_sql.append(insert_value_sql).toString();
		this.executeInsert(sql, vo);

		return id;
	}

	/**
	 * 比对旧对象与新对象,更新对象数据
	 * 
	 * @param oldVO
	 *            旧对象
	 * @param newVO
	 *            新对象
	 * @return
	 * @throws Exception
	 */
	protected Integer update(SuperVO oldVO, SuperVO newVO) throws Exception {

		StringBuffer update_sql = new StringBuffer(" update ");
		StringBuffer update_where_sql = new StringBuffer(" where ");

		update_sql.append(oldVO.getTableName()).append(" set ");

		List<String> field_list = oldVO.getAttributeNames();
		List<Object> value_list = new ArrayList<Object>();
		// 是否更新
		boolean isUpdate = false;
		for (int i = 0; i < field_list.size(); i++) {
			if (oldVO.getPKFieldName().equalsIgnoreCase(field_list.get(i))) {
				update_where_sql.append(field_list.get(i)).append("=?");
			} else {
				String oldVal = Util.null2String(
						oldVO.getAttributeValue(field_list.get(i)));
				String newVal = Util.null2String(
						newVO.getAttributeValue(field_list.get(i)));
				if (!oldVal.equals(newVal)) {
					update_sql.append(field_list.get(i)).append("=?");
					value_list.add(newVO.getAttributeValue(field_list.get(i)));
					update_sql.append(",");
					isUpdate = true;
				}
			}
		}
		// 如果不更新直接返回
		if (!isUpdate) {
			return 0;
		}
		if (update_sql.lastIndexOf(",") == update_sql.length() - 1) {
			update_sql.deleteCharAt(update_sql.length() - 1);
		}
		value_list.add(oldVO.getAttributeValue(oldVO.getPKFieldName()));
		return executeUpdate(update_sql.append(update_where_sql).toString(),
				value_list.toArray());
	}

	/**
	 * 更新对象，null值是否更新
	 * 
	 * @param vo
	 * @param isNullUpdate
	 * @return
	 * @throws Exception
	 */
	protected Integer update(SuperVO vo, boolean isNullUpdate)
			throws Exception {
		StringBuffer update_sql = new StringBuffer(" update ");
		StringBuffer update_where_sql = new StringBuffer(" where ");

		update_sql.append("`").append(vo.getTableName()).append("`")
				.append(" set ");

		List<String> field_list = vo.getAttributeNames();
		List<Object> value_list = new ArrayList<Object>();

		for (int i = 0; i < field_list.size(); i++) {
			if (vo.getPKFieldName().equalsIgnoreCase(field_list.get(i))) {
				update_where_sql.append("`").append(field_list.get(i))
						.append("`").append("=?");
			} else {
				if (!isNullUpdate
						&& null == vo.getAttributeValue(field_list.get(i))) {
					continue;
				}
				update_sql.append("`").append(field_list.get(i)).append("`")
						.append("=?");
				value_list.add(vo.getAttributeValue(field_list.get(i)));
				update_sql.append(",");
			}
		}
		if (update_sql.lastIndexOf(",") == update_sql.length() - 1) {
			update_sql.deleteCharAt(update_sql.length() - 1);
		}
		value_list.add(vo.getAttributeValue(vo.getPKFieldName()));
		return executeUpdate(update_sql.append(update_where_sql).toString(),
				value_list.toArray());
	}

	/**
	 * 按照指定的字段更新对象
	 * 
	 * @param vo
	 *            对象
	 * @param keys
	 *            指定字段
	 * @param isNullUpdate
	 *            null值是否更新
	 * @return
	 * @throws Exception
	 */
	protected Integer update(SuperVO vo, Object[] keys, boolean isNullUpdate)
			throws Exception {
		StringBuffer update_sql = new StringBuffer(" update ");
		StringBuffer update_where_sql = new StringBuffer(" where ");

		update_sql.append(vo.getTableName()).append(" set ");

		List<String> field_list = vo.getAttributeNames();
		List<Object> value_list = new ArrayList<Object>();

		for (int i = 0; i < field_list.size(); i++) {
			if (null == keys && vo.getPKFieldName()
					.equalsIgnoreCase(field_list.get(i))) {
				update_where_sql.append(field_list.get(i)).append("=?");
			} else {
				if (!isNullUpdate
						&& null == vo.getAttributeValue(field_list.get(i))) {
					continue;
				}
				update_sql.append(field_list.get(i)).append("=?");
				value_list.add(vo.getAttributeValue(field_list.get(i)));
				update_sql.append(",");
			}
		}
		if (update_sql.lastIndexOf(",") == update_sql.length() - 1) {
			update_sql.deleteCharAt(update_sql.length() - 1);
		}
		if (null != keys) {
			for (int i = 0; i < keys.length; i++) {
				update_where_sql.append(keys[i] + "=?");
				value_list.add(vo.getAttributeValue(keys[i].toString()));
				if (i != keys.length - 1) {
					update_where_sql.append(" and ");
				}
			}

		} else {
			value_list.add(vo.getAttributeValue(vo.getPKFieldName()));
		}
		return executeUpdate(update_sql.append(update_where_sql).toString(),
				value_list.toArray());
	}

	/**
	 * 根据id删除对象
	 * 
	 * @param clazz
	 * @param id
	 * @return
	 * @throws Exception
	 */
	protected <E extends SuperVO> Integer delete(Class<E> clazz, Object id)
			throws Exception {
		SuperVO superVO = clazz.newInstance();
		StringBuffer delete_sql = new StringBuffer(" delete from ");
		delete_sql.append(superVO.getTableName()).append(" where ")
				.append(superVO.getPKFieldName()).append("=?");
		return executeUpdate(delete_sql.toString(), new Object[]{id});
	}

	/**
	 * 根据vo与指定字段删除对象
	 * 
	 * @param vo
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	protected Integer delete(SuperVO vo, String[] keys) throws Exception {
		StringBuffer delete_sql = new StringBuffer(" delete from ")
				.append(vo.getTableName());
		StringBuffer where_sql = new StringBuffer("");
		List<Object> value_list = new ArrayList<Object>();
		if (null != keys) {
			for (int i = 0; i < keys.length; i++) {
				where_sql.append(keys[i] + "=?");
				value_list.add(vo.getAttributeValue(keys[i].toString()));
				if (i != keys.length - 1) {
					where_sql.append(" and ");
				}
			}
		}
		if (null != vo.getAdditionalWhereSQL()) {
			if (where_sql.length() > 1) {
				where_sql.append(" and ").append(vo.getAdditionalWhereSQL());
			} else {
				where_sql.append(" ").append(vo.getAdditionalWhereSQL());
			}
			if (null != vo.getAdditionalWhereParams()
					&& vo.getAdditionalWhereParams().size() > 0) {
				value_list.addAll(vo.getAdditionalWhereParams());
			}
		}
		if (where_sql.length() > 0) {
			delete_sql.append(" where ").append(where_sql);
		}
		return executeUpdate(delete_sql.toString(), value_list.toArray());
	}

	/**
	 * 
	 * @title idAvailable
	 * @description 获得可用的主键id
	 * @param tableName
	 * @param pkName
	 * @param size
	 * @return String[]
	 */
	protected String[] idAvailable(int size) {
		String[] ids = new String[size];
		for (int i = 0; i < size; i++) {
			ids[i] = UUID.randomUUID().toString().replace("-", "");
		}
		return ids;
	}

	/**
	 * 
	 * @title genStr
	 * @description 返回带双引号的字符串,防止sql注入
	 * @param str
	 * @return String
	 */
	protected String genStr(String str) {
		if (str == null) {
			return null;
		} else {
			return new StringBuilder("\"").append(str.replace("\"", "\\\""))
					.append("\"").toString();
		}
	}

	/**
	 * 
	 * @title genStr
	 * @description 返回带双引号的字符串,防止sql注入
	 * @param str
	 * @return String
	 */
	protected String genStr(Integer str) {
		if (str == null) {
			return null;
		} else {
			return new StringBuilder("\"").append(str).append("\"").toString();
		}
	}

	/**
	 * 
	 * @title genStr
	 * @description 返回带双引号的字符串,防止sql注入
	 * @param str
	 * @return String
	 */
	protected String genStr(Double str) {
		if (str == null) {
			return null;
		} else {
			return new StringBuilder("\"").append(str).append("\"").toString();
		}
	}

	/**
	 * 
	 * @title genStr
	 * @description 返回带双引号的字符串,防止sql注入
	 * @param str
	 * @return String
	 */
	protected String genStr(Float str) {
		if (str == null) {
			return null;
		} else {
			return new StringBuilder("\"").append(str).append("\"").toString();
		}
	}

	/**
	 * 
	 * @title genStr
	 * @description 返回带双引号的字符串,防止sql注入
	 * @param str
	 * @return String
	 */
	protected String genStr(Long str) {
		if (str == null) {
			return null;
		} else {
			return new StringBuilder("\"").append(str).append("\"").toString();
		}
	}

	/**
	 * 
	 * @title assertNotZero
	 * @description 数据库更新断言非零
	 * @param ret
	 * @return
	 */
	protected int assertNotZero(int ret) {
		if (ret == 0) {
			throw new RuntimeException("操作失败，数据库没有更新");
		} else
			return ret;
	}

	/**
	 * 
	 * @title assertNotZero
	 * @description 数据库更新断言非指定数
	 * @param ret
	 * @return
	 */
	protected int assertNotZero(int ret, int num) {
		if (ret == 0 || ret != num) {
			throw new RuntimeException("操作失败，数据库没有更新");
		} else
			return ret;
	}

}
