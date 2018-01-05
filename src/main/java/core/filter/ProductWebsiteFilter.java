package core.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;

import core.constant.SystemConst;
import core.util.Util;

/**
 * 产品王总过滤器
 *
 * @author chenweju
 *
 * @since Apr 24, 2015
 */
public class ProductWebsiteFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		//如果是终端来的请求，需要拦截下来看是否登录成功，否则就拦截下来。
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse)response;
		String userAgent = httpServletRequest.getHeader("user-agent");
		userAgent = userAgent.toUpperCase();
		System.out.println("===="+httpServletRequest.getRequestURI());
		//System.out.println("=========userAgent:" + userAgent);
		
		/*
		ServletInputStream in = httpServletRequest.getInputStream();
		byte[] b = new byte[1024];
		in.read(b);
		String str = new String(b);
		*/
		
		if(userAgent.contains("ANDROID")){
			String cid = "";
			Cookie[] cooks =  httpServletRequest.getCookies();
			if(cooks != null){
				for(Cookie cookie : cooks){
					if(cookie.getName().equals("redisKey")){
						cid = cookie.getValue();
					}
				}
			}
			if(StringUtils.isEmpty(cid)){
				// 验证该url是否需要验证
				boolean result = true;
				if (null != SystemConst.NO_CHECK_LOGIN_URL) {
					String method = httpServletRequest.getParameter("method");
					String url = httpServletRequest.getRequestURI();
					String no_checkurl = url;
					if (!Util.isBlank(method)) {
						no_checkurl += "?method=" + method;
					}
					if (!SystemConst.NO_CHECK_LOGIN_URL.contains(no_checkurl)) { 
						result = false;
					}
				} else {
					result = false;
				}
				
				if (result) {
					chain.doFilter(request, response);
					return;
				} else {
					ResponseSendRedirect.sendRedirect(httpServletRequest,
							(HttpServletResponse) response);
				}
				httpServletResponse.setCharacterEncoding("UTF-8");
				PrintWriter out = httpServletResponse.getWriter();
				out.append("{\"code\":-1,\"message\":\"登录超时，请重新登录\",\"overtime\":true,\"loginpage\":\""
						+ SystemConst.LOGIN_OUT_URL + "\"}");
				out.flush();
				out.close();
			}else{
				chain.doFilter(request, response);
			}
		}else{
			//httpServletResponse.sendRedirect(SystemConst.LOGIN_OUT_URL);
			chain.doFilter(request, response);
		}
		
		/*		
		boolean result = true;
		SessionUser user = null;
		//根据访问来源（终端或PC做不同处理）
		if (0 == SystemConst.SYSTEM_TYPE) {//标识终端工程
			String userid = httpServletRequest.getParameter("userid");
			String machineid = httpServletRequest.getParameter("machineid");
			user = (SessionUser)RedisUtil.getObj(RedisKey.USER_LOGIN + machineid + userid, SessionUser.class);
		}else {
			user = (SessionUser) httpServletRequest.getSession(true)
					.getAttribute(SuperAct.SESSION_USERNAME);
		}
		
		String url = httpServletRequest.getRequestURI();
		
		// 验证该url是否需要验证
		if (null == user) {
			if (null != SystemConst.NO_CHECK_LOGIN_URL) {
				String method = httpServletRequest.getParameter("method");
				request.getAttribute("method");
				String no_checkurl = url + "?method=" + method;
				if (SystemConst.NO_CHECK_LOGIN_URL.contains(no_checkurl)) {
					result = true;
				}else {
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
			ResponseSendRedirect.sendRedirect(httpServletRequest,
					(HttpServletResponse) response);
		}*/

		
	}

	@Override
	public void destroy() {
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
}