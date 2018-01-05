package core.application;

import java.io.File;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import core.constant.SystemConst;
import core.task.TaskManager;
import core.util.BeanFactory;
import core.util.LogUtil;
import core.util.PropUtil;
import core.util.RedisUtil;

/**
 * 项目启动时候调用，配置在web.xml中
 * 
 * @author chenweju
 * 
 * @since Apr 24, 2015
 */
public class ApplicationContextLoader extends HttpServlet {
    private static final long serialVersionUID = 8937450357131799601L;

    public void init(ServletConfig servletConfig) throws ServletException {
	try {
	    ServletContext localServletContext = servletConfig
		    .getServletContext();

	    BeanFactory.init(localServletContext);

	    String rootPath = localServletContext.getRealPath("/");
	    if (!rootPath.endsWith(String.valueOf(File.separatorChar)))
		rootPath += File.separatorChar;
	    PropUtil.setROOT_PATH(rootPath);
	    // 工程自己的启动类
	    projectInit(localServletContext);
	    // 启动定时任务
	    TaskManager.doTask(localServletContext
		    .getRealPath("/WEB-INF/conf/schedule.xml"));

	    // 清除分页缓存
	    RedisUtil.removePageCache();
	} catch (Exception e) {
	    LogUtil.error(this.getClass(), e);
	}

    }

    protected void projectInit(ServletContext localServletContext)
	    throws ServletException {
	try {
	    SystemConst.reloadConfigAll();
	} catch (Exception e) {
	    LogUtil.error(this.getClass(), e);
	}
    }

    public void destroy() {
	super.destroy();
    }
}
