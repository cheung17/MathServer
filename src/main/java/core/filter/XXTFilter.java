package core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.action.SuperAct;
import core.constant.SystemConst;
import core.util.Util;
import core.vo.SessionUser;

/**
 * 校讯通通用登录验证filter
 * 
 */
public class XXTFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		boolean result = true;
		SessionUser user = (SessionUser) httpServletRequest.getSession(true)
				.getAttribute(SuperAct.SESSION_USERNAME);
		// String url = httpServletRequest.getRequestURI();
		// String ismobile = request.getParameter("isMobile");

		// 验证该url是否需要验证
		if (null == user) {
			if (null != SystemConst.NO_CHECK_LOGIN_URL) {
				String page = httpServletRequest.getParameter("page");
				String method = httpServletRequest.getParameter("method");
				String url = httpServletRequest.getRequestURI();
				String no_checkurl = url;
				if (!Util.isBlank(page)) {
					no_checkurl += "?page=" + page;
				}
				if (!Util.isBlank(method)) {
					no_checkurl += "?method=" + method;
				}
				if (!SystemConst.NO_CHECK_LOGIN_URL.contains(no_checkurl)) {
					result = false;
				}
			} else {
				result = false;
			}

		}

		if (result) {
			chain.doFilter(request, response);
			return;
		} else {
			httpServletResponse.sendRedirect(SystemConst.PROPATH
					+ "/qimonAct.xxt?page=index");
			// ResponseSendRedirect.sendRedirect(httpServletRequest,
			// (HttpServletResponse) response);
		}

	}

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
}