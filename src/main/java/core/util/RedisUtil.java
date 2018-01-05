package core.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSON;

import core.constant.RedisConst;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;

public final class RedisUtil {
	private static Jedis jedis;
	private static JedisPool jedisPool;

	/**
	 * 初始化Redis连接池
	 */
	static {
		try {
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxActive(RedisConst.MAX_ACTIVE);
			config.setMaxIdle(RedisConst.MAX_IDLE);
			config.setMaxWait(RedisConst.MAX_WAIT);
			config.setTestOnBorrow(RedisConst.TEST_ON_BORROW);
			// jedisPool = new JedisPool(config, RedisConst.IP, RedisConst.PORT,
			// RedisConst.TIMEOUT, RedisConst.PASSWORD);
			// jedisPool = new JedisPool(config, RedisConst.IP, RedisConst.PORT,
			// RedisConst.TIMEOUT);
			jedisPool = new JedisPool(config, RedisConst.IP, RedisConst.PORT,
					RedisConst.TIMEOUT, RedisConst.PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取Jedis实例
	 * 
	 * @return
	 */
	public static Jedis getJedis() {
		try {
			jedis.get("1");
			return jedis;
		} catch (JedisConnectionException e) {
			e.printStackTrace();
			returnBrokenResource(jedis);
			return null;
		} catch (Exception e) {
			if (!(e instanceof NullPointerException))
				e.printStackTrace();
			returnBrokenResource(jedis);
			return jedis = jedisPool.getResource();
		}
	}

	/**
	 * 释放jedis资源
	 * 
	 * @param jedis
	 */
	public static void returnResource(Jedis jedis) {
		if (jedis != null) {
			jedisPool.returnResource(jedis);
		}
	}

	/**
	 * 
	 * @title returnBrokenResource
	 * @description 释放出现异常的jedis
	 * @param jedis
	 *            void
	 */
	public static void returnBrokenResource(Jedis jedis) {
		if (jedis != null) {
			jedisPool.returnBrokenResource(jedis);
		}
	}

	/**
	 * 存
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static String setKey(String key, String value) {
		try {
			value = getJedis().set(key, value);
			returnResource(jedis);
		} catch (Exception e) {
			e.printStackTrace();
			returnBrokenResource(jedis);
		}
		return value;

	}

	/**
	 * 存
	 * 
	 * @param key
	 * @param value
	 * @param seconds
	 *            有效期
	 * @return
	 */
	public static String setKey(String key, String value, int seconds) {
		try {
			value = getJedis().set(key, value);
			getJedis().expire(key, seconds);
			returnResource(jedis);
		} catch (Exception e) {
			e.printStackTrace();
			returnBrokenResource(jedis);
		}
		return value;

	}

	/**
	 * 取
	 * 
	 * @param key
	 * @return
	 */
	public static String getKey(String key) {
		String value = null;
		try {
			value = getJedis().get(key);
			returnResource(jedis);
		} catch (Exception e) {
			e.printStackTrace();
			returnBrokenResource(jedis);
		}
		return value;
	}

	/**
	 * 存
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static String setObj(String key, Object value) {
		try {
			String json = JSON.toJSONString(value);
			String retvalue = getJedis().set(key, json);
			returnResource(jedis);
			return retvalue;
		} catch (Exception e) {
			returnBrokenResource(jedis);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 存
	 * 
	 * @param key
	 * @param value
	 * @param seconds
	 *            有效时间
	 * @return
	 */
	public static String setObj(String key, Object value, int seconds) {
		try {
			String json = JSON.toJSONString(value);
			String retvalue = getJedis().set(key, json);
			getJedis().expire(key, seconds);
			returnResource(jedis);
			return retvalue;
		} catch (Exception e) {
			returnBrokenResource(jedis);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @title setObj2
	 * @description 版本v2
	 * @param key
	 * @param value
	 * @param seconds
	 * @return
	 */
	public static String setObj2(String key, Object value, int seconds) {
		try {
			String json = JsonUtil.beanToJson(key);
			String retvalue = getJedis().setex(key, seconds, json);
			returnResource(jedis);
			return retvalue;
		} catch (Exception e) {
			returnBrokenResource(jedis);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 取
	 * 
	 * @param key
	 * @param cls
	 * @return
	 * @throws Exception
	 */
	public static <T> Object getObj(String key, Class<T> cls) {
		try {
			String json = getJedis().get(key);
			returnResource(jedis);
			return JSON.parseObject(json, cls);
		} catch (Exception e) {
			returnBrokenResource(jedis);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 移除
	 * 
	 * @param key
	 * @return
	 */
	public static Long removeKey(String key) {
		if (!getJedis().exists(key)) {
			return null;
		}
		try {
			Long value = getJedis().del(key);
			returnResource(jedis);
			return value;
		} catch (Exception e) {
			returnBrokenResource(jedis);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @title synKeys
	 * @description 同步keys的清除与添加 删除分页缓存key的集合，用于存放分页动态keys，适用于删除keys功能
	 *              （不使用redis的keys匹配功能，我的理解是redis，keys匹配功能扫描全部key，有性能问题）
	 * @param keys
	 * @param redis_key
	 */
	public synchronized static void synKeys(List<String> keys,
			String... key_value) {
		if (key_value.length == 0) {
			getJedis();
			try {
				for (String key : keys) {
					jedis.del(key);
				}
				returnResource(jedis);
				keys.clear();
			} catch (Exception e) {
				returnBrokenResource(jedis);
				e.printStackTrace();
				throw new RuntimeException("清除缓存失败");
			}
		} else {
			keys.add(key_value[0]);
			setKey(key_value[0], key_value[1], 3600 * 24);
		}
	}

	/**
	 * 删除所有老师账号缓存
	 * 
	 * @param teacherid
	 */
	public static void deleteTeacher(String teacherid) {
		RedisUtil.removeKey(RedisKey.USER_LOGIN + teacherid);
		RedisUtil.removeKey(RedisKey.USER_LOGIN_TEACHER + teacherid);
		RedisUtil.removeKey(RedisKey.USER_LOGIN_USERID + teacherid);

	}

	/**
	 * 删除所有学生账号缓存
	 * 
	 * @param studentid
	 */
	public static void deleteStudent(String studentid) {
		RedisUtil.removeKey(RedisKey.USER_LOGIN + studentid);
		RedisUtil.removeKey(RedisKey.USER_LOGIN_STUDENT + studentid);
		RedisUtil.removeKey(RedisKey.USER_LOGIN_USERID + studentid);
	}

	/**
	 * 删除所有家长账号缓存
	 * 
	 * @param parentid
	 */
	public static void deleteParent(String parentid) {
		RedisUtil.removeKey(RedisKey.USER_LOGIN + parentid);
		RedisUtil.removeKey(RedisKey.USER_LOGIN_PARENT + parentid);
		RedisUtil.removeKey(RedisKey.USER_LOGIN_USERID + parentid);
	}

	/**
	 * 
	 * @title removePageCache
	 * @description 移除全部分页缓存，tomcat启动时调用
	 */
	public static void removePageCache() {
		getJedis();
		try {
			Set<String> keys = jedis.keys(RedisKey.BOOK_ARTICLE_LIST + "*");
			keys.addAll(jedis.keys(RedisKey.BOOK_COMMENT_LIST + "*"));
			keys.addAll(jedis.keys(RedisKey.COMMENT_REPLY_LIST + "*"));
			keys.addAll(jedis.keys(RedisKey.BOOK_COURSE_LIST + "*"));
			keys.addAll(jedis.keys(RedisKey.MY_BOOK_COURSE_LIST + "*"));
			for (String key : keys) {
				jedis.del(key);
			}
			returnResource(jedis);
		} catch (Exception e) {
			returnBrokenResource(jedis);
			e.printStackTrace();
		}
	}

	/**
	 * 以hashMap形式存储对象(已有key值没有字段则添加，已有会覆盖字段)，null值不存储
	 * 
	 * @param key
	 * @param object
	 */
	public static void setObject(String key, Object object) {
		getJedis();
		try {
			Map<String, String> objMap = new HashMap<String, String>();
			Field[] fs = object.getClass().getDeclaredFields();
			Object obj = null;
			for (Field f : fs) {
				f.setAccessible(true);
				if ((obj = f.get(object)) != null) {
					if (f.getType().getName().equals(Date.class.getName())) {
						obj = ((Date) obj).getTime();
					}
					objMap.put(f.getName(), obj + "");
				}
			}
			jedis.hmset(key, objMap);
			returnResource(jedis);
		} catch (Exception e) {
			returnBrokenResource(jedis);
			e.printStackTrace();
		}
	}
	/**
	 * 以存储hashmap(已有key值没有字段则添加，已有会覆盖字段)，null值不存储
	 * 
	 * @param key
	 * @param object
	 */
	public static void setMap(String key, Map<String, Object> map) {
		getJedis();
		try {
			Map<String, String> objMap = new HashMap<String, String>();
			for (Object k : map.keySet()) {
				if (map.get(k) != null)
					objMap.put(k.toString(), map.get(k) + "");
			}
			jedis.hmset(key, objMap);
			returnResource(jedis);
		} catch (Exception e) {
			returnBrokenResource(jedis);
			e.printStackTrace();
		}
	}

	/**
	 * 获取以hashMap形式存储对象，null值不存储
	 * 
	 * @param key
	 * @param clazz
	 * @return
	 */
	public static <T> T getObject(String key, Class<T> clazz) {
		getJedis();
		T t = null;
		try {
			t = clazz.newInstance();
			Field[] fs = clazz.getDeclaredFields();
			List<String> fieldName = new ArrayList<String>(), fieldValue;
			for (Field f : fs) {
				fieldName.add(f.getName());
			}
			fieldValue = jedis.hmget(key, fieldName.toArray(new String[]{}));
			int i = 0;
			String value = "";
			for (Field f : fs) {
				f.setAccessible(true);
				value = fieldValue.get(i++);
				if (!value.equals("null")) {
					f.set(f.getName(), value);
				}
			}
			returnResource(jedis);
		} catch (Exception e) {
			returnBrokenResource(jedis);
			e.printStackTrace();
		}
		return t;
	}

	/**
	 * 获取对象字段值
	 * 
	 * @param key
	 * @param fields
	 * @return
	 */
	public static List<String> objectFieldsValue(String key, String... fields) {
		getJedis();
		List<String> fieldValues = null;
		try {
			fieldValues = jedis.hmget(key, fields);
			returnResource(jedis);
		} catch (Exception e) {
			returnBrokenResource(jedis);
			e.printStackTrace();
		}
		return fieldValues;
	}

	/**
	 * 获取对象单个属性
	 * 
	 * @param key
	 * @param field
	 * @return
	 */
	public static String getObjectFieldValue(String key, String field) {
		String value = null;
		getJedis();
		try {
			value = jedis.hget(key, field);
			returnResource(jedis);
		} catch (Exception e) {
			returnBrokenResource(jedis);
			e.printStackTrace();
		}
		return value;
	}

	/**
	 * 设置对象单个属性
	 * 
	 * @param key
	 * @param field
	 * @param value
	 */
	public static void setObjectFieldValue(String key, String field,
			String value) {
		getJedis();
		try {
			jedis.hset(key, field, value);
			returnResource(jedis);
		} catch (Exception e) {
			returnBrokenResource(jedis);
			e.printStackTrace();
		}
	}
}