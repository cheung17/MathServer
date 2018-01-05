package core.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import core.dao.SuperDao;
import core.listener.JdbcExecSQLListener;
import core.vo.PagerVO;
import core.vo.SuperVO;

/**
 * 所有Dao实现类都要继承该类
 * 
 *
 * @author chenweju
 *
 * @since Apr 24, 2015
 */
@Repository("superDao")
public class SuperDaoImpl<T extends SuperVO> extends JdbcTemplatePageDaoSupport
		implements SuperDao<T> {
	public SuperDaoImpl(JdbcTemplate write_jdbcTemplate,
			JdbcTemplate read_jdbcTemplate) {
		super(write_jdbcTemplate, read_jdbcTemplate);
	}

	@Override
	public void deleteVO(Class<T> clazz, String id) throws Exception {
		super.delete(clazz, id);

	}

	@Override
	public void deleteVO(SuperVO vo, String[] keys) throws Exception {
		super.delete(vo, keys);
	}

	public PagerVO findByPage(T t) throws Exception {
		return this.findByPage(t, null);
	};

	public PagerVO findByPage(T t, String orderStr) throws Exception {
		return super.queryByPage(t.getClass(), t, orderStr);

	};

	public PagerVO findByPageParamMap(String sql,
			HashMap<String, Object> paramMap) throws Exception {
		return super.queryByPageParamMap(sql, paramMap);
	}

	@Override
	public PagerVO findByPageParamMap(Class<? extends SuperVO> clazz,
			String sql, HashMap<String, Object> paramMap) throws Exception {
		return super.queryByPageParamMap(clazz, sql, paramMap);
	}

	public List<T> findByList(T t) throws Exception {
		return this.findByList(t, null);
	};

	@SuppressWarnings("unchecked")
	public List<T> findByList(T t, String orderStr) throws Exception {
		return (List<T>) super.query(t.getClass(), t, orderStr);
	};

	@Override
	public List<T> findByList(String sql, HashMap<String, Object> paramMap,
			Class<T> clazz) throws Exception {
		return (List<T>) super.queryForListByParamMap(sql, paramMap, clazz);
	}

	@Override
	public Map<String, Object> findByMap(String sql, Object[] args)
			throws Exception {
		return super.queryForMap(sql, args);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getVO(Class<T> clazz, String id) throws Exception {
		return (T) super.get(clazz, id);
	}

	@SuppressWarnings("unchecked")
	public T getVO(T t) throws Exception {
		return (T) super.get(t);
	}

	@SuppressWarnings("unchecked")
	public T getVO(T t, String orderby) throws Exception {
		return (T) super.get(t, orderby);
	}

	public String insertVO(T t) throws Exception {
		return super.insert(t);
	};
	
	public Integer updateVO(T oldVO, T newVO) throws Exception {
		return super.update(oldVO, newVO);
	}

	public Integer updateVO(T t, Object[] keys) throws Exception {
		return super.update(t, keys, true);
	};

	public Integer updateVO(T t, Object[] keys, boolean isNullUpdate)
			throws Exception {
		return super.update(t, keys, isNullUpdate);
	};

	public int queryVOCount(T t) throws Exception {
		return super.queryCount(t);
	};

	public List<Map<String, Object>> queryBySQL(String sql, Object... args)
			throws Exception {
		return super.query(sql, args);
	}

	@Override
	public <O> O queryObjectBySQL(String sql, Class<O> requiredType,
			Object... args) throws Exception {
		return super.queryObjectBySQL(sql, requiredType, args);
	}

	@Override
	public void startSQLListener(JdbcExecSQLListener jdbcExecSQLListener) {
		super.setSqlListenerThread(jdbcExecSQLListener);
	}

	@Override
	public void endSQLListener() {
		super.removeSqlListenerThread();
	}

	/* (non-Javadoc)
	 * @see core.dao.SuperDao#deleteBySql(java.lang.String, java.lang.Object[])
	 */
	@Override
	public int jdbcTemplateUpdate(String sql, Object[] args) throws Exception {
		return super.executeUpdate(sql, args);
	}

	/* (non-Javadoc)
	 * @see core.dao.SuperDao#jdbcTemplateUpdate(java.lang.String, java.util.Map)
	 */
	@Override
	public int jdbcTemplateUpdate(String sql, Map<String, Object> paramMap)
			throws Exception {
		return super.executeUpdateByParamMap(sql, paramMap);
	}
}
