package core.action;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.context.annotation.Scope;
import org.springframework.util.StringUtils;

import core.page.SystemContext;
import core.util.JsonUtil;

/**
 * 分页基础类
 * 
 * @author chenweju
 *
 * @since Apr 24, 2015
 */
@Scope("prototype")
public class PageAct {

	/**
	 * 将bean转换为json对象 要转换的bean如果为对象，
	 * 
	 * 对象中的属性必须有public的get方法
	 * 
	 * @param obj
	 *            要转换的对象
	 * @return
	 * @throws Exception
	 */
	protected String beanToJson(Object obj, String dateFormatStr)
			throws Exception {
		return JsonUtil.beanToJson(obj, dateFormatStr);

	}

	/**
	 * 将bean转换为json对象 要转换的bean如果为对象，
	 * 
	 * 对象中的属性必须有public的get方法
	 * 
	 * @param obj
	 *            要转换的对象
	 * @return
	 * @throws Exception
	 */
	protected String beanToJson(Object obj) throws Exception {
		return JsonUtil.beanToJson(obj, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 初始化分页控件
	 * @param request
	 */
	public String initPageCom(HttpServletRequest request) throws Exception {
		return initPageCom(request, 10);
	}

	/**
	 * 初始化分页控件
	 * @param request
	 * @param pageSize
	 */
	public String initPageCom(HttpServletRequest request, Integer pageSize)
			throws Exception {
		// 预存查询条件
		@SuppressWarnings("unchecked")
		Map<String, String[]> param_map = (Map<String, String[]>) request
				.getParameterMap();
		StringBuffer querySb = new StringBuffer("");
		if (null != param_map) {
			for (String key : param_map.keySet()) {
				String value = request.getParameter(key);
				if (null != value && value.trim().length() > 0) {
					if (!"method".equals(key)) {
						querySb.append(key).append("=").append(value)
								.append("&");
					}
				}
			}
		}
		String queryStr = querySb.toString();
		if (queryStr.endsWith("&")) {
			queryStr = queryStr.substring(0, queryStr.length() - 1);
		}

		String po = request.getParameter("pager_offset");
		Integer pager_offset_value = 1;
		if (null != po) {
			try {
				pager_offset_value = Integer.parseInt(po);
			} catch (Exception e) {
				pager_offset_value = 1;
			}
		}
		SystemContext.setOffset(pager_offset_value);
		if (null != pageSize) {
			SystemContext.setPagesize(pageSize);
		} else {
			SystemContext.setPagesize(10);
		}
		return queryStr;
	}

	/**
	 * 下载excel文件
	 * @param res
	 * @param workbook
	 * @param filename
	 * @throws Exception
	 */
	protected void downExcel(HttpServletRequest request,
			HttpServletResponse res, HSSFWorkbook workbook, String filename)
			throws Exception {
		filename = URLEncoder.encode(filename, "utf-8");
		filename = StringUtils.replace(filename, "+", "%20");
		ServletOutputStream os = null;
		try {
			res.reset();
			res.setContentType("application/msexcel");
			res.setHeader("Content-Disposition", "attachment;Filename="
					+ filename);
			os = res.getOutputStream();
			workbook.write(os);
			os.flush();
		} catch (Exception e) {
			throw e;
		} finally {
			if (null != os) {
				os.close();
			}
		}
	}

	/**
	 * json转换list 
	 * @param json
	 * @param elementClass
	 * @return
	 * @throws Exception
	 */
	public List<?> jsonToList(String json, Class<?> elementClass)
			throws Exception {
		return JsonUtil.jsonToList(json, elementClass);
	}
}