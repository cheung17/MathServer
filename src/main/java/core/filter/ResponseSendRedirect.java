package core.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.constant.SystemConst;

/**
 * 登录转向-统一跳转
 *
 * @author chenweju
 *
 * @since Apr 24, 2015
 */
public class ResponseSendRedirect {

	/**
	 * 登录统一转向方法
	 * 
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @throws IOException
	 * @throws ServletException
	 */
	public static void sendRedirect(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws IOException,
			ServletException {

		if (httpServletRequest.getHeader("x-requested-with") != null
				&& httpServletRequest.getHeader("x-requested-with")
						.equalsIgnoreCase("XMLHttpRequest")) {

			httpServletResponse.setCharacterEncoding("UTF-8");
			httpServletResponse
					.setContentType("application/json; charset=utf-8");
			PrintWriter out = httpServletResponse.getWriter();
			out.append("{\"code\":-1,\"message\":\"登录超时，请重新登录\",\"overtime\":true,\"loginpage\":\""
					+ SystemConst.LOGIN_OUT_URL + "\"}");
				out.flush();
				out.close();
			return;
		} else {
			int system_type = SystemConst.SYSTEM_TYPE;
			if (0 == system_type) {//标识终端工程
				httpServletResponse.setCharacterEncoding("UTF-8");
				//httpServletResponse.setContentType("application/json; charset=utf-8");
				PrintWriter out = httpServletResponse.getWriter();
				out.append("{\"code\":-1,\"message\":\"登录超时，请重新登录\",\"overtime\":true,\"loginpage\":\""
						+ SystemConst.LOGIN_OUT_URL + "\"}");
				out.flush();
				out.close();
			}else {
				httpServletResponse.sendRedirect(SystemConst.LOGIN_OUT_URL);
			}
			
			return;
		}
	}
}
