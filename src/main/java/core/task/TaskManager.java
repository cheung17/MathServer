package core.task;

import it.sauronsoftware.cron4j.Scheduler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import core.util.LogUtil;
import core.util.PropUtil;

/**
 * web项目定时任务管理类
 *
 * @author chenweju
 *
 * @since Apr 24, 2015
 */
public class TaskManager {
	private static Logger logger = LogUtil.getLogger(TaskManager.class,
			PropUtil.ROOT_PATH + "WEB-INF" + File.separator + "logs");

	/**
	 * 启动定时任务
	 * 
	 * @param CfgPath
	 *            定时任务配置文件路径
	 * @throws JDOMException
	 * @throws IOException
	 */
	public static void doTask(String CfgPath) throws JDOMException, IOException {

		File file = new File(CfgPath);
		if (file.exists()) {
			InputStream _in = new FileInputStream(file);

			Reader in = new InputStreamReader(_in);

			SAXBuilder builder = new SAXBuilder();
			Document doc = null;
			doc = builder.build(in);
			Element root = doc.getRootElement();
			List<Element> ls = root.getChildren();

			for (Iterator<Element> iter = ls.iterator(); iter.hasNext();) {
				Element el = iter.next();
				if ("daily".equalsIgnoreCase(el.getName())) {
					List<Element> timeList = el.getChildren();
					for (Iterator<Element> iter1 = timeList.iterator(); iter1
							.hasNext();) {
						Element time = (Element) iter1.next();
						if ("time".equalsIgnoreCase(time.getName())) {
							String timeValue = time.getAttributeValue("vlaue");
							Scheduler scheduler = new Scheduler();
							int classTaskSize = time.getChildren().size();
							if (classTaskSize < 1) {

							} else {
								List<Element> classTaskList = time
										.getChildren();
								for (Iterator<Element> iter2 = classTaskList
										.iterator(); iter2.hasNext();) {
									Element classTask = (Element) iter2.next();
									String classname = classTask.getText()
											.trim();
									try {
										// 实例化任务包装类
										TaskWrap wrap = createTaskWrap(classTask);
										// 安排定时任务执行（安排的时包装类对象，通过包装类对象调用具体定时任务）
										scheduler.schedule(timeValue, wrap);
									} catch (Exception e) {
										logger.error(classname + "执行失败", e);
									}
								}
								scheduler.start();
							}
						}
					}
				}
			}
		}

	}

	/**
	 * 创建任务包装类
	 * 
	 * @param ele
	 * @return
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	private static TaskWrap createTaskWrap(Element ele)
			throws ClassNotFoundException, InstantiationException,
			IllegalAccessException {
		String classname = ele.getText().trim();
		Class<?> c = Class.forName(classname);
		// 实例化定时任务对象
		Runnable td = (Runnable) c.newInstance();

		// 设置配置参数
		// <taskclass>节点中的属性
		List<Attribute> attrs = ele.getAttributes();

		Iterator<Attribute> _iter = attrs.iterator();
		while (_iter.hasNext()) {
			Attribute attr = _iter.next();
			String attrName = attr.getName();
			String attrValue = attr.getValue();
			try {
				Field field = c.getDeclaredField(attrName);
				Class<?> attrType = field.getType();
				Object attrObj = attrValue;

				if (Integer.class.equals(attrType)
						|| int.class.equals(attrType)) {
					attrObj = Integer.parseInt(attrValue);
				} else if (Boolean.class.equals(attrType)
						|| boolean.class.equals(attrType)) {
					attrObj = Boolean.parseBoolean(attrValue);
				}

				Field f = c.getDeclaredField(attrName);
				f.setAccessible(true);
				f.set(td, attrObj);
			} catch (Exception e) {
				logger.error(e);
			}
		}
		return new TaskWrap(td);
	}

}
