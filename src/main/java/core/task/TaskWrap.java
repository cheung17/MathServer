package core.task;

import java.io.File;

import org.apache.log4j.Logger;

import core.util.LogUtil;
import core.util.PropUtil;


/**
 * 定时任务包装类
 * 用于代理执行具体任务,记录定时任务的【开始】与【结束】
 *
 * @author chenweju
 *
 * @since Apr 24, 2015
 */
public class TaskWrap implements Runnable {
	
	private static Logger logger;
	
	/**
	 * 定时任务对象
	 */
	private Runnable task;
	
	/**
	 * 构造方法，
	 * @param task 定时任务对象
	 */
	public TaskWrap(Runnable task){
		
		if(logger==null)
			logger = LogUtil.getLogger(getClass(), PropUtil.ROOT_PATH
					+ "WEB-INF" + File.separator + "logs");
		
		this.task = task;
	}

	@Override
	public void run() {
		long tid = Thread.currentThread().getId();
		String taskName = task.getClass().getName();
		
		logger.info("["+tid+"]任务开始:" + taskName);
		
		//执行定时任务
		try{
			task.run();
		}catch (Exception e) {
			logger.error("["+tid+"]任务执行发生异常:" + taskName, e);
		}

		logger.info("["+tid+"]结束:" + taskName);
	}

}
