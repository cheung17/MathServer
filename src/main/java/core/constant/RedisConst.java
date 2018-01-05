package core.constant;

import java.io.IOException;

import core.util.PropUtil;
import core.util.Util;

/**
 * Redis配置参数
 *
 * @author chenweju
 *
 */
public class RedisConst {
	/** Redis服务器ip地址 */
	public static String IP;
	/** Redis服务器端口 */
	public static int PORT;
	/** Redis 访问密码 */
	public static String PASSWORD;
	/** 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。*/
	public static int MAX_ACTIVE;
	/** 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例  */
	public static int MAX_IDLE;
	/** 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException */
	public static int MAX_WAIT;
	public static int TIMEOUT;
	/** 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的 */
	public static boolean TEST_ON_BORROW;
	

	
	/**
	 * 重新加载常量
	 * 
	 * @throws IOException
	 */
	public static void reloadConfig() throws Exception {
		IP = PropUtil.getPropValue("redisconst", "ip");
		PORT = Util.getIntValue(PropUtil.getPropValue("redisconst", "port"));
		PASSWORD = PropUtil.getPropValue("redisconst", "password");
		MAX_ACTIVE = Util.getIntValue(PropUtil.getPropValue("redisconst", "max_active"), -1);
		MAX_IDLE = Util.getIntValue(PropUtil.getPropValue("redisconst", "max_idle"), 200);
		MAX_WAIT = Util.getIntValue(PropUtil.getPropValue("redisconst", "max_wait"), 10000);
		TIMEOUT = Util.getIntValue(PropUtil.getPropValue("redisconst", "timeout"), 10000);
		TEST_ON_BORROW = "true".equalsIgnoreCase(PropUtil.getPropValue("redisconst", "test_on_borrow"));
		
	}

	
}