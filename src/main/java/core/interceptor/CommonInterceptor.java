package core.interceptor;

import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import core.constant.SystemConst;
import core.util.LogUtil;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateModelException;

/**
 * spring拦截器，用于拦截*.do结尾的请求。
 * action执行之前根据请求页面校验权限，完毕之后将request对象返回到页面，便于处理
 *
 * @author chenweju
 *
 * @since Apr 24, 2015
 */
public class CommonInterceptor extends HandlerInterceptorAdapter {
	/**
	 * 
	 * 在业务处理器处理请求之前被调用 如果返回false 从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链。
	 * 如果返回true 执行下一个拦截器,直到所有的拦截器都执行完毕 再执行被拦截的Controller 然后进入拦截器链。
	 * 从最后一个拦截器往回执行所有的postHandle() 接着再从最后一个拦截器往回执行所有的afterCompletion()
	 */
	@SuppressWarnings("rawtypes")
	private static Class[] defaultStaticClasses = { SystemConst.class };

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		boolean result = SQLInjectionInterceptor.getInstance().SQLInterceptor(
				request, response);
		boolean isAjaxRequest = false;
		if (request.getHeader("x-requested-with") != null
				&& request.getHeader("x-requested-with").equalsIgnoreCase(
						"XMLHttpRequest")) {
			isAjaxRequest = true;
			LogUtil.debug(this.getClass(), request.getRequestURI() + "?"
					+ request.getQueryString() + " 是ajax请求！");
		}
		if (!result) {
			if (isAjaxRequest) {
				OutputStream out = response.getOutputStream();
				out.write("{\"code\":-1,\"message\":\"您的操作存在风险，请重试！\"}"
						.getBytes("UTF-8"));
				out.flush();
				out.close();
			} else {
				OutputStream out = response.getOutputStream();
				response.setCharacterEncoding("GBK");
				response.setContentType("text/html;charset=GBK");
				StringBuffer tip = new StringBuffer(
						"<script language='javascript'>alert('您的操作存在风险，请重试！');history.go(-1);</script>");
				out.write(tip.toString().getBytes("GBK"));
				out.flush();
				out.close();
			}
		}
		return result;
	}

	/**
	 * 在业务处理器处理请求执行完成后,生成视图之前执行的动作
	 */

	@SuppressWarnings("rawtypes")
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (modelAndView != null) {
			modelAndView.addObject("request", request);
			// 将常用静态工具类传入
			for (Class clz : defaultStaticClasses) {
				String name = clz.getSimpleName();
				modelAndView.addObject(name, this.getStaticModel(clz));
			}
			String system = request.getHeader("user-agent").toUpperCase().contains("ANDROID")?"ANDROID":"IOS";
			modelAndView.addObject("deviceSystem",system);
		}
	}

	/**
	 * 
	 * 在DispatcherServlet完全处理完请求后被调用
	 * 
	 * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
	 */

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("");
	}

	@SuppressWarnings("rawtypes")
	private Object getStaticModel(Class clz) {
		BeansWrapper wrapper = BeansWrapper.getDefaultInstance();
		try {
			return wrapper.getStaticModels().get(clz.getName());
		} catch (TemplateModelException e) {
			LogUtil.error(this.getClass(), e.getMessage());
		}
		return null;
	}
}
