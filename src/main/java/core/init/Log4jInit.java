package core.init;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * 系统运行日志初始化类
 *
 * @author chenweju
 *
 * @since Apr 24, 2015
 */
public class Log4jInit extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2112790610507424820L;
	static Logger logger = Logger.getLogger(Log4jInit.class);

	public Log4jInit() {
	}

	public void init(ServletConfig config) throws ServletException {
		String prefix = config.getServletContext().getRealPath("/");
		String file = config.getInitParameter("log4j");
		String filePath = prefix + file;
		Properties props = new Properties();
		try {
			FileInputStream istream = new FileInputStream(filePath);
			props.load(istream);
			istream.close();
			
			String A2_logFile = prefix
					+ props.getProperty("log4j.appender.A2.File");// 设置路径
			props.setProperty("log4j.appender.A2.File", A2_logFile);
			
			String A3_logFile = prefix
					+ props.getProperty("log4j.appender.A3.File");// 设置路径
			props.setProperty("log4j.appender.A3.File", A3_logFile);
			
			PropertyConfigurator.configure(props);// 装入log4j配置信息
		} catch (IOException e) {
			toPrint("Could not read configuration file [" + filePath + "].");
			toPrint("Ignoring configuration file [" + filePath + "].");
			return;
		}
	}

	public static void toPrint(String content) {
		System.out.println(content);
	}

}
